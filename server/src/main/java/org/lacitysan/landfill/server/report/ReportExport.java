package org.lacitysan.landfill.server.report;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportExport {
	public void exportReport() throws IOException{
		PDDocument document = new PDDocument();		
		PDDocumentInformation pdd = document.getDocumentInformation();
		pdd.setAuthor("Allen Ma");
		pdd.setTitle("Landfill report");
		pdd.setCreator("Allen Ma");
		
		PDPage blankPage = new PDPage();
		document.addPage( blankPage );
	         
		int pageNum = 0;
		PDPage writePage = document.getPage(0);
		
		PDPageContentStream contentStream = new PDPageContentStream(document, writePage);
		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);	//font type and size
		
		contentStream.setLeading(14.5f);
		contentStream.newLineAtOffset(25, 750);				//position of new line
		
		int t = 320;
		
		while(t >= 0){
			String text = "This is a guide for the proper way to chop dicks.";
		    //System.out.println(text);
			contentStream.showText(text);
	    	contentStream.newLine();
	    	t--;
	    	
	    	if(t%50 == 0 && t > 0){
				contentStream.endText();		
				contentStream.close();
				
				blankPage = new PDPage();
		        document.addPage( blankPage );				
				pageNum = pageNum + 1;				
				writePage = document.getPage(pageNum);
				contentStream = new PDPageContentStream(document, writePage);
				
				contentStream.beginText();
				contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);	//font type and sizePDPage 
				
				contentStream.setLeading(14.5f);
				contentStream.newLineAtOffset(25, 750);				//position of new line
	    	} else if(t%50 == 0 && t == -1){
	    		contentStream.endText();		
				contentStream.close();	
	    	}
	    	
		}
		
	    contentStream.endText();		
		contentStream.close();
		document.save("C:/Users/Allen/Desktop/pdf_generate/Test.pdf");
		document.close();
	}

}
