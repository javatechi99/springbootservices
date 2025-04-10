package com.org.services.java.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface ExcelGeneratorService {

	void generateExcel(HttpServletResponse response) throws Exception;

}
