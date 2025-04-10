package com.org.services.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.services.java.service.ExcelGeneratorService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/excel")
public class ExcelGeneratorController {
	
	@Autowired
	private ExcelGeneratorService excelGeneratorService;
	
	@GetMapping("/exceldata")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=student.xls";

		response.setHeader(headerKey, headerValue);
		
		excelGeneratorService.generateExcel(response);
		
		response.flushBuffer();
	}

}


