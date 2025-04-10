package com.org.services.java.serviceImpl;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.org.services.java.entity.Student;

@Component
public class PdfServiceImpl {

	public static ByteArrayInputStream employeePDFReport(List<Student> students) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
 
        try {
 
            PdfWriter.getInstance(document, out);
            document.open();
 
            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("STUDENT DATA IN DB", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
 
            PdfPTable table = new PdfPTable(6);
            // Add PDF Table Header ->
            Stream.of("ID", "NAME", "BRANCH", "AGE", "COURSE", "UNIQID").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.CYAN);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });
 
            for (Student student : students) {
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(student.getId())));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
 
                PdfPCell firstNameCell = new PdfPCell(new Phrase(student.getName()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);
 
                PdfPCell branchCell = new PdfPCell(new Phrase(String.valueOf(student.getBranch())));
                branchCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                branchCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                branchCell.setPaddingRight(4);
                table.addCell(branchCell);
 
                PdfPCell ageCell = new PdfPCell(new Phrase(String.valueOf(student.getAge())));
                ageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                ageCell.setPaddingRight(4);
                table.addCell(ageCell);
 
                PdfPCell courseCell = new PdfPCell(new Phrase(String.valueOf(student.getCourse())));
                courseCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                courseCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                courseCell.setPaddingRight(4);
                table.addCell(courseCell);
                
                PdfPCell uniqIdCell = new PdfPCell(new Phrase(String.valueOf(student.getUniqId())));
                uniqIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                uniqIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                uniqIdCell.setPaddingRight(4);
                table.addCell(uniqIdCell);
            }
            document.add(table);
 
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
 
        return new ByteArrayInputStream(out.toByteArray());
    }
}
