package com.org.services.java.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.services.java.entity.Student;
import com.org.services.java.service.StudentService;
import com.org.services.java.serviceImpl.PdfServiceImpl;

@RestController
public class PdfGenerateController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/openpdf/students", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
		List<Student> listStudents = studentService.findAllStudentDetails();
		
		ByteArrayInputStream bis = PdfServiceImpl.employeePDFReport(listStudents);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=student.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
}