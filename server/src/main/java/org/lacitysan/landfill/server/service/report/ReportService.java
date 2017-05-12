package org.lacitysan.landfill.server.service.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IntegratedDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeRepairData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseRepairData;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceType;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;
import org.lacitysan.landfill.server.service.report.model.ExceedanceReport;
import org.lacitysan.landfill.server.service.report.model.InstantaneousReport;
import org.lacitysan.landfill.server.service.report.model.IntegratedReport;
import org.lacitysan.landfill.server.service.report.model.Report;
import org.lacitysan.landfill.server.service.report.model.data.InstantaneousReportData;
import org.lacitysan.landfill.server.service.report.model.data.IntegratedReportData;
import org.lacitysan.landfill.server.service.report.model.data.ProbeExceedanceReportData;
import org.lacitysan.landfill.server.service.report.model.data.SurfaceEmissionExceedanceReportData;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.ImeNumberService;
import org.lacitysan.landfill.server.service.surfaceemission.integrated.IseNumberService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.lacitysan.landfill.server.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.quodlibet.boxable.BaseTable;

/**
 * @author Alvin Quach
 * @author Allen Huang
 */
@Service
public class ReportService {

	@Autowired
	InstantaneousDataDao instantaneousDataDao;

	@Autowired
	IntegratedDataDao integratedDataDao;

	@Autowired
	ImeNumberDao imeNumberDao;

	@Autowired
	IseNumberDao iseNumberDao;

	@Autowired
	ImeNumberService imeNumberService;

	@Autowired
	IseNumberService iseNumberService;

	public Report generateReport(ReportQuery reportQuery) {
		if (reportQuery.getReportType() == ReportType.EXCEEDANCE) {
			return generateExceedanceReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			return generateInstantaneousReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.INTEGRATED) {
			return generateIntegratedReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.PROBE) {
			// TODO Implement this.
		}
		return null;
	}
	
	public void generateReportPdf(HttpServletResponse response, ReportQuery reportQuery) throws IOException {
		if (reportQuery.getReportType() == ReportType.EXCEEDANCE) {
			generateExceedanceReportPdf(response, (ExceedanceReport)generateReport(reportQuery));
		}
		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			generateInstantaneousReportPdf(response, (InstantaneousReport)generateReport(reportQuery));
		}
		if (reportQuery.getReportType() == ReportType.INTEGRATED) {
			generateIntegratedReportPdf(response, (IntegratedReport)generateReport(reportQuery));
		}
		if (reportQuery.getReportType() == ReportType.PROBE) {
			// TODO Implement this.
		}
	}

	private ExceedanceReport generateExceedanceReport(ReportQuery reportQuery) {

		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		ExceedanceReport exceedanceReport = new ExceedanceReport(reportQuery);

		// Query the database for IMEs and add them to the report data.
		if (reportQuery.getExceedanceTypes().contains(ExceedanceType.INSTANTANEOUS)) {
			
			// Generate the date code ranges.
			Short startDateCode = startDate == null ? null : imeNumberService.generateDateCodeFromLong(startDate);
			Short endDateCode = startDate == null ? null : imeNumberService.generateDateCodeFromLong(endDate);

			exceedanceReport.getImeReportData().addAll(
					imeNumberDao.getVerifiedBySiteAndDateCodeRange(reportQuery.getSite(), startDateCode, endDateCode)
					.parallelStream()
					.sorted((a, b) -> a.compareTo(b))
					.map(c -> {

						// Check if the IME number contains at least one data entry.
						if (c.getImeData() == null || c.getImeData().isEmpty()) {
							return null;
						}

						// Get initial IME entry and final repair entry, if exists.
						List<ImeData> imeDataList = c.getImeData().stream().sorted().collect(Collectors.toList());
						ImeData initial = imeDataList.get(0);
						ImeRepairData finalRepair = imeNumberService.findLastRepair(c);

						SurfaceEmissionExceedanceReportData d = new SurfaceEmissionExceedanceReportData();
						d.setDiscoveredDate(DateTimeUtils.formatSimpleDate(initial.getDateTime().getTime()));
						d.setExceedanceNumber(c.getImeNumber());
						d.setMonitoringPoints(StringUtils.collectionToCommaDelimited(c.getMonitoringPoints(), true));
						d.setRepairDescription(finalRepair == null ? "" : finalRepair.getDescription());
						d.setInitial(String.format("%.2f", initial.getMethaneLevel() / 100.0));
						d.setRecheck(imeDataList.size() == 1 ? "" : String.format("%.2f", imeDataList.get(imeDataList.size() - 1).getMethaneLevel() / 100.0));
						d.setClearedDate(c.getStatus() == ExceedanceStatus.CLEARED ? DateTimeUtils.formatSimpleDate(finalRepair.getDateTime().getTime()) : "");
						return d;
					})
					.filter(e -> e != null)
					.collect(Collectors.toList()));
		}

		// Query the database for ISEs and add them to the report data.
		if (reportQuery.getExceedanceTypes().contains(ExceedanceType.INTEGRATED)) {
			
			// Generate the date code ranges.
			Short startDateCode = startDate == null ? null : imeNumberService.generateDateCodeFromLong(startDate);
			Short endDateCode = startDate == null ? null : imeNumberService.generateDateCodeFromLong(endDate);
			
			exceedanceReport.getIseReportData().addAll(
					iseNumberDao.getVerifiedBySiteAndDateCodeRange(reportQuery.getSite(), startDateCode, endDateCode)
					.parallelStream()
					.sorted((a, b) -> a.compareTo(b))
					.map(c -> {

						// Check if the ISE number contains at least one data entry.
						if (c.getIseData() == null || c.getIseData().isEmpty()) {
							return null;
						}

						// Get initial IME entry and final repair entry, if exists.
						List<IseData> iseDataList = c.getIseData().stream().sorted().collect(Collectors.toList());
						IseData initial = iseDataList.get(0);
						IseRepairData finalRepair = iseNumberService.findLastRepair(c);

						SurfaceEmissionExceedanceReportData d = new SurfaceEmissionExceedanceReportData();
						d.setDiscoveredDate(DateTimeUtils.formatSimpleDate(initial.getDateTime().getTime()));
						d.setExceedanceNumber(c.getIseNumber());
						d.setMonitoringPoints(c.getMonitoringPoint().getName());
						d.setRepairDescription(finalRepair == null ? "" : finalRepair.getDescription());
						d.setInitial(String.format("%.2f", initial.getMethaneLevel() / 100.0));
						d.setRecheck(iseDataList.size() == 1 ? "" : String.format("%.2f", iseDataList.get(iseDataList.size() - 1).getMethaneLevel() / 100.0));
						d.setClearedDate(c.getStatus() == ExceedanceStatus.CLEARED ? DateTimeUtils.formatSimpleDate(finalRepair.getDateTime().getTime()) : "");
						return d;
					})
					.filter(e -> e != null)
					.collect(Collectors.toList()));
		}

		// Query the database for Probe exceedances and add them to the report data.
		if (reportQuery.getExceedanceTypes().contains(ExceedanceType.PROBE)) {
			// TODO Implement this.
		}

		return exceedanceReport;

	}

	private InstantaneousReport generateInstantaneousReport(ReportQuery reportQuery) {

		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		// Query the database and convert the queried data into report data.
		List<InstantaneousReportData> instantaneousReportData = instantaneousDataDao.getBySiteAndDate(reportQuery.getSite(), startDate, endDate)
				.parallelStream()
				.sorted((a, b) -> {
					// Sort data here instead of having InstantaneousData implement Comparable, so that we can add sort options in the future.
					int sort = a.getStartTime().compareTo(b.getStartTime());
					if (sort != 0) {
						return sort;
					}
					return a.getMonitoringPoint().ordinal() - b.getMonitoringPoint().ordinal();
				})
				.map(c -> {
					InstantaneousReportData d = new InstantaneousReportData();
					d.setDate(DateTimeUtils.formatSimpleDate(c.getStartTime().getTime()));
					d.setBarometricPressure(String.format("%.2f", c.getBarometricPressure() / 100.0));
					d.setInspector(c.getInspector().printName());
					d.setMonitoringPoint(c.getMonitoringPoint().getName());
					d.setStartTime(DateTimeUtils.formatSimpleTime(c.getStartTime().getTime()));
					d.setEndTime(DateTimeUtils.formatSimpleTime(c.getEndTime().getTime()));
					d.setInstrument(c.getInstrument().getSerialNumber());
					d.setMethaneLevel(String.format("%.2f", c.getMethaneLevel() / 100.0));
					d.setImeNumber(imeNumberService.stringifyCommonCollection(c.getImeNumbers()));
					return d;
				})
				.collect(Collectors.toList());

		// Return the generated report data.
		return new InstantaneousReport(reportQuery, instantaneousReportData);

	}

	private IntegratedReport generateIntegratedReport(ReportQuery reportQuery) {

		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		// Query the database and convert the queried data into report data.
		List<IntegratedReportData> integratedReportData = integratedDataDao.getBySiteAndDate(reportQuery.getSite(), startDate, endDate)
				.parallelStream()
				.sorted((a, b) -> {
					// Sort data here instead of having IntegratedData implement Comparable, so that we can add sort options in the future.
					int sort = a.getStartTime().compareTo(b.getStartTime());
					if (sort != 0) {
						return sort;
					}
					return a.getMonitoringPoint().ordinal() - b.getMonitoringPoint().ordinal();
				})
				.map(c -> {
					IntegratedReportData d = new IntegratedReportData();
					d.setDate(DateTimeUtils.formatSimpleDate(c.getStartTime().getTime()));
					d.setBarometricPressure(String.format("%.2f", c.getBarometricPressure() / 100.0));
					d.setInspector(c.getInspector().printName());
					d.setMonitoringPoint(c.getMonitoringPoint().getName());
					d.setStartTime(DateTimeUtils.formatSimpleTime(c.getStartTime().getTime()));
					d.setEndTime(DateTimeUtils.formatSimpleTime(c.getEndTime().getTime()));
					d.setInstrument(c.getInstrument().getSerialNumber());
					d.setMethaneLevel(String.format("%.2f", c.getMethaneLevel() / 100.0));
					d.setBagNumber(String.valueOf(c.getBagNumber()));
					d.setVolume(String.valueOf(c.getVolume()));
					d.setSampleId(c.getSampleId());
					return d;
				})
				.collect(Collectors.toList());

		// Return the generated report data.
		return new IntegratedReport(reportQuery, integratedReportData);

	}
	
	private void generateInstantaneousReportPdf(HttpServletResponse response, InstantaneousReport report) throws IOException{
		PDPage blankPage1;

		List<List<String>> reportData = new ArrayList<>();
		List<InstantaneousReportData> listType = report.getInstantaneousReportData();
		
		PDDocument document = new PDDocument();		
		PDDocumentInformation pdd = document.getDocumentInformation();
		pdd.setAuthor("Allen Ma");
		pdd.setTitle("Landfill report");
		pdd.setCreator("Allen Ma");
		blankPage1 = new PDPage();
		blankPage1.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));

		float margin = 10;
		float tableWidth = blankPage1.getMediaBox().getWidth() - (2 * margin);
		float yStartNewPage = blankPage1.getMediaBox().getHeight() - (2 * margin);
		float yStart = yStartNewPage;
		float bottomMargin = 20;

		document.addPage( blankPage1 );

		ArrayList<String> header = new ArrayList<>();

		header.add("Date");
		
		header.add("Barometric Pressure");
		header.add("Inspector");
		
		header.add("Grid");
		header.add("Start");
		header.add("End");
		header.add("Instrument");
		header.add("Reading");
		header.add("IME #(s)");

		for(int i = 0; i < listType.size(); i++){
			ArrayList<String> info = new ArrayList<>();
			info.add( (listType.get(i)).getDate() );                	
			info.add( (listType.get(i)).getBarometricPressure() );
			info.add( (listType.get(i)).getInspector() );     	
			info.add( (listType.get(i)).getMonitoringPoint() );
			info.add( (listType.get(i)).getStartTime() );
			info.add( (listType.get(i)).getEndTime() );
			info.add( (listType.get(i)).getInstrument() );
			info.add( (listType.get(i)).getMethaneLevel() );
			info.add( (listType.get(i)).getImeNumber() );

			reportData.add(info);
		}
		
		BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage1, true, true);

		//Create Header row
		be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
		be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, "Instantaneous Report " + report.getReportQuery().getSite().getName());			
		cell.setFont(PDType1Font.HELVETICA_BOLD);



		be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
		for (int i = 0; i < header.size(); i++) {
			float width = 100 / 9f;
			cell = hea.createCell(width, "" + header.get(i));
			cell.setFont(PDType1Font.HELVETICA_BOLD);
		}	
		dataTable.addHeaderRow(hea); 

		for (List<String> info : reportData) {
			be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

			for (int i = 0; i < info.size(); i++) {
				float width = 100 / 9f;
				cell = row.createCell(width, "" + info.get(i));
			}
		}

		dataTable.draw(); 
//			pdfName = "C:/Users/Allen/Desktop/generatePDF/" + exType.getName() +"ExceedTest.pdf";
//			document.save(pdfName);
		document.save(response.getOutputStream());
		document.close();	
	}
	
	private void generateIntegratedReportPdf(HttpServletResponse response, IntegratedReport report) throws IOException{
		PDPage blankPage1;

		List<List<String>> reportData = new ArrayList<>();
		List<IntegratedReportData> listType = report.getIntegratedReportData();
		
		PDDocument document = new PDDocument();		
		PDDocumentInformation pdd = document.getDocumentInformation();
		pdd.setAuthor("Allen Ma");
		pdd.setTitle("Landfill report");
		pdd.setCreator("Allen Ma");
		blankPage1 = new PDPage();
		blankPage1.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));

		float margin = 10;
		float tableWidth = blankPage1.getMediaBox().getWidth() - (2 * margin);
		float yStartNewPage = blankPage1.getMediaBox().getHeight() - (2 * margin);
		float yStart = yStartNewPage;
		float bottomMargin = 20;

		document.addPage( blankPage1 );

		ArrayList<String> header = new ArrayList<>();
		
		header.add("Grid ID#");
		header.add("Inspector");
		header.add("Sample ID");
		header.add("Bag #");
		header.add("Date");
		header.add("Start Time");
		header.add("End Time");
		header.add("Volume (Liters)");
		header.add("Barometric Pressure");		
		header.add("Instrument");
		header.add("Reading ppm");
		
		for(int i = 0; i < listType.size(); i++){
			ArrayList<String> info = new ArrayList<>();
			info.add( (listType.get(i)).getMonitoringPoint() );                	
			info.add( (listType.get(i)).getInspector() );
			info.add( (listType.get(i)).getSampleId() );     	
			info.add( (listType.get(i)).getBagNumber() );
			info.add( (listType.get(i)).getDate() );
			info.add( (listType.get(i)).getStartTime() );
			info.add( (listType.get(i)).getEndTime() );
			info.add( (listType.get(i)).getVolume() );
			info.add( (listType.get(i)).getBarometricPressure() );
			info.add( (listType.get(i)).getInstrument() );
			info.add( (listType.get(i)).getMethaneLevel() );

			reportData.add(info);
		}
		
		BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage1, true, true);

		//Create Header row
		be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
		be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, "Instantaneous Report for " + report.getReportQuery().getSite().getName() + 
				" from " + report.getReportQuery().getStartDate() + " to " + report.getReportQuery().getEndDate());			
		cell.setFont(PDType1Font.HELVETICA_BOLD);



		be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
		for (int i = 0; i < header.size(); i++) {
			float width = 100 / 11f;
			cell = hea.createCell(width, "" + header.get(i));
			cell.setFont(PDType1Font.HELVETICA_BOLD);
		}	
		dataTable.addHeaderRow(hea);

		for (List<String> info : reportData) {
			be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

			for (int i = 0; i < info.size(); i++) {
				float width = 100 / 11f;
				cell = row.createCell(width, "" + info.get(i));
			}
		}

		dataTable.draw(); 
//			pdfName = "C:/Users/Allen/Desktop/generatePDF/" + exType.getName() +"ExceedTest.pdf";
//			document.save(pdfName);
		document.save(response.getOutputStream());
		document.close();	
	}
	
	private void generateExceedanceReportPdf(HttpServletResponse response, ExceedanceReport report) throws IOException {
		
		
		PDPage blankPage1;


		Set<ExceedanceType> types = report.getReportQuery().getExceedanceTypes();
		List<List<?>> reportData = new ArrayList<>();
		List<?> listType;
		
		ArrayList<ByteArrayOutputStream> allpdf = new ArrayList<>();

		String exceedType = "";
		for (ExceedanceType exType : types) {
			PDDocument document = new PDDocument();		
			PDDocumentInformation pdd = document.getDocumentInformation();
			pdd.setAuthor("Allen Ma");
			pdd.setTitle("Landfill report");
			pdd.setCreator("Allen Ma");
			blankPage1 = new PDPage();
			blankPage1.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));

			float margin = 10;
			float tableWidth = blankPage1.getMediaBox().getWidth() - (2 * margin);
			float yStartNewPage = blankPage1.getMediaBox().getHeight() - (2 * margin);
			float yStart = yStartNewPage;
			float bottomMargin = 20;
			
			if (exType == ExceedanceType.INSTANTANEOUS){
				exceedType = "Instantaneous Exceedances";
				listType = report.getImeReportData();
			}
			else if(exType == ExceedanceType.INTEGRATED){
				exceedType = "Integrated";
				listType = report.getIseReportData();
			}
			else{
				exceedType = "Perimeter Probe Exceedances";
				listType = report.getProbeExceedanceReportData();
			}
			System.out.println(exceedType);
			ArrayList<String> header = new ArrayList<>();
			if (exType == ExceedanceType.INSTANTANEOUS || exType == ExceedanceType.INTEGRATED){

				header.add("Date Discovered");
				if(exType == ExceedanceType.INSTANTANEOUS){
					header.add("IME#");
					header.add("Grid(s)");
				}
				else{
					header.add("ISE#");
					header.add("Grid");
				}      	
				header.add("Repair Description");
				header.add("Initial Reading (ppm)");
				header.add("Recheck value (ppm)");
				header.add("Date Cleared");

				for(int i = 0; i < listType.size(); i++){
					ArrayList<String> info = new ArrayList<>();
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getDiscoveredDate() );                	
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getExceedanceNumber() );
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getMonitoringPoints() );     	
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getRepairDescription() );
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getInitial() );
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getRecheck() );
					info.add( ((SurfaceEmissionExceedanceReportData) listType.get(i)).getClearedDate() );

					reportData.add(info);
				}
			}
			else if (exType == ExceedanceType.PROBE){
				header.add("Date Discovered");
				header.add("Probe ID");
				header.add("Depth");           	            	     	
				header.add("Repair Description");
				header.add("Initial Reading (ppm)");
				header.add("Recheck value (ppm)");
				header.add("Date Cleared");

				for(int i = 0; i < listType.size(); i++){
					ArrayList<String> info = new ArrayList<>();
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getDiscoveredDate() );                	
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getProbeId() );
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getDepth() );      	      	
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getRepairDescription() );
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getInitial() );
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getRecheck() );
					info.add( ((ProbeExceedanceReportData) listType.get(i)).getClearedDate() );

					reportData.add(info);
				}
			}

			BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage1, true, true);
			document.addPage( blankPage1 );
			
			//Create Header row
			exceedType = exceedType + " for " + report.getReportQuery().getSite().getName()	+ " from " + report.getReportQuery().getStartDate() + " to " + report.getReportQuery().getEndDate();
			be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
			be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, exceedType);			
			cell.setFont(PDType1Font.HELVETICA_BOLD);

			be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
			for (int i = 0; i < header.size(); i++) {
				float width = 100 / 9f;
				if (i == 3) {
					width = (100 / 6.0f) * 2;
				}
				cell = hea.createCell(width, "" + header.get(i));
				cell.setFont(PDType1Font.HELVETICA_BOLD);
			}	
			dataTable.addHeaderRow(hea); //derRow

			for (List<?> info : reportData) {
				be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

				for (int i = 0; i < info.size(); i++) {
					float width = 100 / 9f;
					if (i == 3) {
						width = (100 / 6.0f) * 2;
					}
					cell = row.createCell(width, "" + info.get(i));
				}
			}
			
			
			dataTable.draw(); 
//			pdfName = "C:/Users/Allen/Desktop/generatePDF/" + exType.getName() +"ExceedTest.pdf";
//			document.save(pdfName);
//			document.save(response.getOutputStream());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			document.save(baos);
			allpdf.add(baos);
			document.close();
			
			
		}
		
		PDFMergerUtility mergedPDF = new PDFMergerUtility();
		for(int i = 0; i < allpdf.size(); i++){
			mergedPDF.addSource(new ByteArrayInputStream(allpdf.get(i).toByteArray()));
		}
		mergedPDF.setDestinationStream(response.getOutputStream());
		mergedPDF.mergeDocuments();		
	}



}
