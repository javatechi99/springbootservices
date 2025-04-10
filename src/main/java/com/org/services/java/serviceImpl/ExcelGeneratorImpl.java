package com.org.services.java.serviceImpl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.services.java.entity.Student;
import com.org.services.java.repository.StudentRepository;
import com.org.services.java.service.ExcelGeneratorService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGeneratorImpl implements ExcelGeneratorService{
	
	@Autowired
	private StudentRepository repository;

	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Student> students = repository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("STUDENT INFORMATION");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("BRANCH");
		row.createCell(3).setCellValue("AGE");
		row.createCell(4).setCellValue("COURSE");
		row.createCell(5).setCellValue("UNIQID");

		int dataRowIndex = 1;

		for (Student student : students) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(student.getId());
			dataRow.createCell(1).setCellValue(student.getName());
			dataRow.createCell(2).setCellValue(student.getBranch());
			dataRow.createCell(3).setCellValue(student.getAge());
			dataRow.createCell(4).setCellValue(student.getCourse());
			dataRow.createCell(5).setCellValue(student.getUniqId());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

}