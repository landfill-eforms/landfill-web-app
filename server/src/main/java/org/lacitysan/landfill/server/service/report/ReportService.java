package org.lacitysan.landfill.server.service.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.WarmspotDataDao;
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
import org.lacitysan.landfill.server.service.report.model.WarmspotReport;
import org.lacitysan.landfill.server.service.report.model.data.InstantaneousReportData;
import org.lacitysan.landfill.server.service.report.model.data.IntegratedReportData;
import org.lacitysan.landfill.server.service.report.model.data.ProbeExceedanceReportData;
import org.lacitysan.landfill.server.service.report.model.data.SurfaceEmissionExceedanceReportData;
import org.lacitysan.landfill.server.service.report.model.data.WarmspotReportData;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.ImeNumberService;
import org.lacitysan.landfill.server.service.surfaceemission.integrated.IseNumberService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.lacitysan.landfill.server.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.quodlibet.boxable.BaseTable;

/**
 * Handles the logical operations for reports.
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
	WarmspotDataDao warmspotDataDao;
	
	@Autowired
	ImeNumberService imeNumberService;

	@Autowired
	IseNumberService iseNumberService;
	
	@Autowired
	ReportQueryService reportQueryService;

	public Report generateReport(ReportQuery reportQuery) {
		
		// Calculate updated date range, if necessary.
		reportQueryService.updateReportQueryDateRange(reportQuery);
		
		if (reportQuery.getReportType() == ReportType.EXCEEDANCE) {
			return generateExceedanceReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			return generateInstantaneousReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.INTEGRATED) {
			return generateIntegratedReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.WARMSPOT){
			return generateWarmspotReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.PROBE) {
			// TODO Implement this.
		}
		return null;
	}
	
	public void generateReportPdf(OutputStream out, ReportQuery reportQuery) throws IOException {
		if (reportQuery.getReportType() == ReportType.EXCEEDANCE) {
			generateExceedanceReportPdf(out, (ExceedanceReport)generateReport(reportQuery));
		}
		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			generateInstantaneousReportPdf(out, (InstantaneousReport)generateReport(reportQuery));
		}
		if (reportQuery.getReportType() == ReportType.INTEGRATED) {
			generateIntegratedReportPdf(out, (IntegratedReport)generateReport(reportQuery));
		}
		if (reportQuery.getReportType() == ReportType.WARMSPOT){
			generateWarmspotReportPdf(out, (WarmspotReport)generateReport(reportQuery));
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
						ImeData initialReading = imeDataList.get(0);
						ImeData finalReading = imeDataList.get(imeDataList.size() - 1);
						ImeRepairData finalRepair = imeNumberService.findLastRepair(c);

						SurfaceEmissionExceedanceReportData d = new SurfaceEmissionExceedanceReportData();
						d.setDiscoveredDate(DateTimeUtils.formatSimpleDate(initialReading.getDateTime().getTime()));
						d.setExceedanceNumber(c.getImeNumber());
						d.setMonitoringPoints(StringUtils.collectionToCommaDelimited(c.getMonitoringPoints(), true));
						d.setRepairDescription(finalRepair == null ? "" : finalRepair.getDescription());
						d.setInitial(String.format("%.2f", initialReading.getMethaneLevel() / 100.0));
						d.setRecheck(imeDataList.size() == 1 ? "" : String.format("%.2f", imeDataList.get(imeDataList.size() - 1).getMethaneLevel() / 100.0));
						d.setClearedDate(c.getStatus() == ExceedanceStatus.CLEARED ? DateTimeUtils.formatSimpleDate(finalReading.getDateTime().getTime()) : "");
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
		Long endDate = reportQuery.getEndDate() == null ? null : DateTimeUtils.addDay(reportQuery.getEndDate().getTime());

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
		Long endDate = reportQuery.getEndDate() == null ? null : DateTimeUtils.addDay(reportQuery.getEndDate().getTime());

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
	
	private WarmspotReport generateWarmspotReport(ReportQuery reportQuery){
		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : DateTimeUtils.addDay(reportQuery.getEndDate().getTime());

		// Query the database and convert the queried data into report data.
		List<WarmspotReportData> warmspotReportData = warmspotDataDao.getBySiteAndDate(reportQuery.getSite(), startDate, endDate)
				.parallelStream()
				.sorted((a, b) -> {
					// Sort data here instead of having IntegratedData implement Comparable, so that we can add sort options in the future.
					int sort = a.getDate().compareTo(b.getDate());
					if (sort != 0) {
						return sort;
					}
					return a.getMonitoringPoint().ordinal() - b.getMonitoringPoint().ordinal();
				})
				.map(c -> {
					WarmspotReportData d = new WarmspotReportData();
					d.setDate(DateTimeUtils.formatSimpleDate(c.getDate().getTime()));
					d.setInspector(c.getInspector().printName());
					d.setMonitoringPoint(c.getMonitoringPoint().getName());
					d.setInstrument(c.getInstrument().getSerialNumber());
					d.setMethane(String.format("%.2f", c.getMethaneLevel() / 100.0));
					d.setDescription(c.getDescription());
					d.setSize(c.getSize());
					return d;
				})
				.collect(Collectors.toList());

		// Return the generated report data.
		return new WarmspotReport(reportQuery, warmspotReportData);
	}
	
	/**
	 * Generates a PDF document for the Warmspot report.
	 * @param out 	 Where the PDF will be saved to when created
	 * @param report The object containing the data to generate the report
	 */
	private void generateWarmspotReportPdf(OutputStream out, WarmspotReport report) throws IOException{
		PDPage blankPage;
		
		// The list that contain will each row of data for the warmspot report 
		List<List<String>> reportData = new ArrayList<>();
		
		// Gets the list of data in the Warmspot report object
		List<WarmspotReportData> dataEntries = report.getWarmspotReportData();
		
		// Creates a pdf document object
		PDDocument document = new PDDocument();		
		
		blankPage = new PDPage();
		
		// Sets the dimensions of a rectangle within the page, the contents will be written within the rectangle space
		blankPage.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));
		
		// Margin determines the amount of white space between the contents and boundary of the page
		float margin = 10;
		
		// Determines the width of the table that will be created
		float tableWidth = blankPage.getMediaBox().getWidth() - (2 * margin);
		
		// Determines where the table will be placed on the y-axis
		float yStartNewPage = blankPage.getMediaBox().getHeight() - (2 * margin);
		float yStart = yStartNewPage;
		
		float bottomMargin = 20;
		
		document.addPage( blankPage );
		
		ArrayList<String> columnNames = new ArrayList<>();

		columnNames.add("Date");
		columnNames.add("Grid");
		columnNames.add("Description");
		columnNames.add("Estimate Size");
		columnNames.add("ECI (full name)");
		columnNames.add("Max Value");
		columnNames.add("Instrument");
		
		// Getting the information from the objects, adds them to a list in the order of the column name
		for(int i = 0; i < dataEntries.size(); i++){
			ArrayList<String> info = new ArrayList<>();
			info.add( (dataEntries.get(i)).getDate() );                	
			info.addAll( (dataEntries.get(i)).getMonitoringPoint() );
			info.add( (dataEntries.get(i)).getDescription() );     	
			info.add( (dataEntries.get(i)).getSize() );
			info.add( (dataEntries.get(i)).getInspector() );
			info.add( (dataEntries.get(i)).getMethane() );
			info.add( (dataEntries.get(i)).getInstrument() );
			
			reportData.add(info);
		}
		
		// Creates a table object on the specified page of the pdf document
		BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage, true, true);

		// Create Header row for the table
		be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
		be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, "Warmspot Report for " + report.getReportQuery().getSite().getName() + " " + getFormattedDateRange(report.getReportQuery()));			
		cell.setFont(PDType1Font.HELVETICA_BOLD);

		// Create the row for the column name for the table
		be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
		for (int i = 0; i < columnNames.size(); i++) {
			
			// Sets the width of each cell in the row
			float width = 100 / 9f;
			if (i == 2) {
				width = (100 / 3.0f);
			}
			cell = hea.createCell(width, "" + columnNames.get(i));
			cell.setFont(PDType1Font.HELVETICA_BOLD);
		}	
		dataTable.addHeaderRow(hea); 
		
		// Create a row for each warmspot data for the table
		for (List<String> info : reportData) {
			be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

			for (int i = 0; i < info.size(); i++) {
				
				// Create cells in the row with certain width
				float width = 100 / 9f;
				if (i == 2) {
					width = (100 / 3.0f);
				}
				cell = row.createCell(width, "" + info.get(i));
			}
		}
		
		// Draws the table onto the page specified in the document, if table exceeds 1 page, it will insert more pages till table is done drawing
		dataTable.draw(); 

		document.save(out);
		document.close();	
	}
	
	/**
	 * Generates a PDF document for the Instantaneous report.
	 * @param out 	 Where the PDF will be saved to when created
	 * @param report The object containing the data to generate the report
	 */
	private void generateInstantaneousReportPdf(OutputStream out, InstantaneousReport report) throws IOException{
		PDPage blankPage;
		
		// The list that contain will each row of data for the instantaneous report 
		List<List<String>> reportData = new ArrayList<>();
		
		// Gets the list of data in the Instantaneous report object
		List<InstantaneousReportData> listType = report.getInstantaneousReportData();
		
		// Creates a pdf document object
		PDDocument document = new PDDocument();		
		
		blankPage = new PDPage();
		
		// Sets the dimensions of a rectangle within the page, the contents will be written within the rectangle space
		blankPage.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));
		
		// Margin determines the amount of white space between the contents and boundary of the page
		float margin = 10;
		
		// Determines the width of the table that will be created
		float tableWidth = blankPage.getMediaBox().getWidth() - (2 * margin);
		
		// Determines where the table will be placed on the y-axis
		float yStartNewPage = blankPage.getMediaBox().getHeight() - (2 * margin);
		float yStart = yStartNewPage;
		
		float bottomMargin = 20;
				
		document.addPage( blankPage );

		ArrayList<String> columnNames = new ArrayList<>();

		columnNames.add("Date");
		
		columnNames.add("Barometric Pressure");
		columnNames.add("Inspector");
		
		columnNames.add("Grid");
		columnNames.add("Start");
		columnNames.add("End");
		columnNames.add("Instrument");
		columnNames.add("Reading");
		columnNames.add("IME #(s)");

		// Getting the information from the objects, adds them to a list in the order of the column name
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
		
		// Creates a table object on the specified page of the pdf document
		BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage, true, true);

		//Create Header row
		be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
		be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, "Instantaneous Report for " + report.getReportQuery().getSite().getName() + " " + getFormattedDateRange(report.getReportQuery()));			
		cell.setFont(PDType1Font.HELVETICA_BOLD);

		// Create the row for the column name for the table
		be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
		for (int i = 0; i < columnNames.size(); i++) {
			float width = 100 / 9f;
			cell = hea.createCell(width, "" + columnNames.get(i));
			cell.setFont(PDType1Font.HELVETICA_BOLD);
		}	
		dataTable.addHeaderRow(hea); 

		// Create a row for each instantaneous data for the table
		for (List<String> info : reportData) {
			be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

			for (int i = 0; i < info.size(); i++) {
				
				// Create cells in the row with certain width
				float width = 100 / 9f;				
				cell = row.createCell(width, "" + info.get(i));
			}
		}

		// Draws the table onto the page specified in the document, if table exceeds 1 page, it will insert more pages till table is done drawing
		dataTable.draw(); 

		document.save(out);
		document.close();	
	}
	
	/**
	 * Generates a PDF document for the Integrated report.
	 * @param out 	 Where the PDF will be saved to when created
	 * @param report The object containing the data to generate the report
	 */
	private void generateIntegratedReportPdf(OutputStream out, IntegratedReport report) throws IOException{
		PDPage blankPage;

		// The list that contain will each row of data for the instantaneous report 
		List<List<String>> reportData = new ArrayList<>();
		
		// Gets the list of data in the Integrated report object
		List<IntegratedReportData> listType = report.getIntegratedReportData();
		
		// Creates a pdf document object
		PDDocument document = new PDDocument();		
		
		blankPage = new PDPage();
		
		// Sets the dimensions of a rectangle within the page, the contents will be written within the rectangle space
		blankPage.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));

		// Margin determines the amount of white space between the contents and boundary of the page
		float margin = 10;
		
		// Determines the width of the table that will be created
		float tableWidth = blankPage.getMediaBox().getWidth() - (2 * margin);
		
		// Determines where the table will be placed on the y-axis
		float yStartNewPage = blankPage.getMediaBox().getHeight() - (2 * margin);
		float yStart = yStartNewPage;
		
		float bottomMargin = 20;
		
		document.addPage( blankPage );

		ArrayList<String> columnNames = new ArrayList<>();
		
		columnNames.add("Grid ID#");
		columnNames.add("Inspector");
		columnNames.add("Sample ID");
		columnNames.add("Bag #");
		columnNames.add("Date");
		columnNames.add("Start Time");
		columnNames.add("End Time");
		columnNames.add("Volume (Liters)");
		columnNames.add("Barometric Pressure");		
		columnNames.add("Instrument");
		columnNames.add("Reading ppm");
		
		// Getting the information from the objects, adds them to a list in the order of the column name
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
		
		// Creates a table object on the specified page of the pdf document
		BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage, true, true);

		//Create Header row
		be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
		be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, "Integrated Report for " + report.getReportQuery().getSite().getName() + " " + getFormattedDateRange(report.getReportQuery()));
		cell.setFont(PDType1Font.HELVETICA_BOLD);

		// Create the row for the column name for the table
		be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
		for (int i = 0; i < columnNames.size(); i++) {
			float width = 100 / 11f;
			cell = hea.createCell(width, "" + columnNames.get(i));
			cell.setFont(PDType1Font.HELVETICA_BOLD);
		}	
		dataTable.addHeaderRow(hea);

		// Create a row for each integrated data for the table
		for (List<String> info : reportData) {
			be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

			for (int i = 0; i < info.size(); i++) {
				
				// Create cells in the row with certain width
				float width = 100 / 11f;
				cell = row.createCell(width, "" + info.get(i));
			}
		}

		// Draws the table onto the page specified in the document, if table exceeds 1 page, it will insert more pages till table is done drawing
		dataTable.draw(); 

		document.save(out);
		document.close();	
	}
	
	/**
	 * Generates a PDF document for the Exceedance report.
	 * It will generate a PDF for each Exceedance type and merge them into one document.
	 * @param out 	 Where the PDF will be saved to when created
	 * @param report The object containing the data to generate the report
	 */
	@SuppressWarnings("deprecation")
	private void generateExceedanceReportPdf(OutputStream out, ExceedanceReport report) throws IOException {
		
		PDPage blankPage;

		// Gets the list of exceedance types in the exceedance report object
		Set<ExceedanceType> types = report.getReportQuery().getExceedanceTypes();
		
		// List is undefined right now since it could be a list of any exceedance type
		List<List<?>> reportData;		
		List<?> listType;
		
		// The created PDFs will be stored within this list
		ArrayList<ByteArrayOutputStream> allpdf = new ArrayList<>();

		String exceedType = "";
		
		// Creates a PDF for each exceedance type in the set
		for (ExceedanceType exType : types) {
			reportData = new ArrayList<>();
			
			// Creates a pdf document object
			PDDocument document = new PDDocument();		
			
			blankPage = new PDPage();
			
			// Sets the dimensions of a rectangle within the page, the contents will be written within the rectangle space
			blankPage.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(),PDRectangle.A4.getWidth()));

			// Margin determines the amount of white space between the contents and boundary of the page
			float margin = 10;
			
			// Determines the width of the table that will be created
			float tableWidth = blankPage.getMediaBox().getWidth() - (2 * margin);
			
			// Determines where the table will be placed on the y-axis
			float yStartNewPage = blankPage.getMediaBox().getHeight() - (2 * margin);
			float yStart = yStartNewPage;
			
			float bottomMargin = 20;
			
			// Determining the type of exceedance object the list will contain 
			if (exType == ExceedanceType.INSTANTANEOUS){
				exceedType = "Instantaneous Exceedances";
				listType = report.getImeReportData();
			}
			else if(exType == ExceedanceType.INTEGRATED){
				exceedType = "Integrated";
				listType = report.getIseReportData();
			}
			else{
				exceedType = "Probe Exceedances";
				listType = report.getProbeExceedanceReportData();
			}
			
			// Will change according which exceedance pdf is being created
			ArrayList<String> columnNames = new ArrayList<>();
			if (exType == ExceedanceType.INSTANTANEOUS || exType == ExceedanceType.INTEGRATED){

				columnNames.add("Date Discovered");
				if(exType == ExceedanceType.INSTANTANEOUS){
					columnNames.add("IME#");
					columnNames.add("Grid(s)");
				}
				else{
					columnNames.add("ISE#");
					columnNames.add("Grid");
				}      	
				columnNames.add("Repair Description");
				columnNames.add("Initial Reading (ppm)");
				columnNames.add("Recheck value (ppm)");
				columnNames.add("Date Cleared");

				// Getting the information from the objects, adds them to a list in the order of the column name
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
				columnNames.add("Date Discovered");
				columnNames.add("Probe ID");
				columnNames.add("Depth");           	            	     	
				columnNames.add("Repair Description");
				columnNames.add("Initial Reading (ppm)");
				columnNames.add("Recheck value (ppm)");
				columnNames.add("Date Cleared");

				// Getting the information from the objects, adds them to a list in the order of the column name
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

			// Creates a table object on the specified page of the pdf document
			BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, blankPage, true, true);
			document.addPage( blankPage );
			
			//Create Header row
			exceedType = exceedType + " for " + report.getReportQuery().getSite().getName()	+ " " + getFormattedDateRange(report.getReportQuery());
			be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(15f);
			be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100, exceedType);			
			cell.setFont(PDType1Font.HELVETICA_BOLD);

			// Create the row for the column names for the table
			be.quodlibet.boxable.Row<PDPage> hea = dataTable.createRow(15f);
			for (int i = 0; i < columnNames.size(); i++) {
				
				// Create cells in the row with certain width
				float width = 100 / 9f;
				if (i == 3) {
					width = (100 / 6.0f) * 2;
				}
				cell = hea.createCell(width, "" + columnNames.get(i));
				cell.setFont(PDType1Font.HELVETICA_BOLD);
			}	
			dataTable.addHeaderRow(hea); //derRow

			// Create a row for each exceedance data for the table
			for (List<?> info : reportData) {
				be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(10f);

				for (int i = 0; i < info.size(); i++) {
					
					// Create cells in the row with certain width
					float width = 100 / 9f;
					if (i == 3) {
						width = (100 / 6.0f) * 2;
					}
					cell = row.createCell(width, "" + info.get(i));
				}
			}
			
			// Draws the table onto the page specified in the document, if table exceeds 1 page, it will insert more pages till table is done drawing
			dataTable.draw(); 

			ByteArrayOutputStream exceeedanceTypePDF = new ByteArrayOutputStream();
			document.save(exceeedanceTypePDF);
			allpdf.add(exceeedanceTypePDF);
			document.close();
			
			
		}
		
		// Combines all the PDFs in the list into one PDF document
		PDFMergerUtility mergedPDF = new PDFMergerUtility();
		for(int i = 0; i < allpdf.size(); i++){
			mergedPDF.addSource(new ByteArrayInputStream(allpdf.get(i).toByteArray()));
		}
		mergedPDF.setDestinationStream(out);
		mergedPDF.mergeDocuments();		
	}

	private String getFormattedDateRange(ReportQuery reportQuery) {
		Date start = reportQuery.getStartDate();
		Date end = reportQuery.getEndDate();
		if (start == null && end == null) {
			return "";
		}
		if (start == null) {
			return "until " + end;
		}
		if (end == null) {
			return "from " + start;
		}
		if (start.getTime() == end.getTime()) {
			return "for " + start;
		}
		return "from " + start + " to " + end;
	}

}
