package com.org.services.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.services.java.dto.APIResponse;
import com.org.services.java.entity.Student;
import com.org.services.java.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	  @GetMapping("/findAll")
	    private APIResponse<List<Student>> getStudents() {
	        List<Student> allStudents = service.findAllStudents();
	        return new APIResponse<>(allStudents.size(), allStudents);
	    }

	    @GetMapping("/{field}")
	    private APIResponse<List<Student>> getStudentsWithSort(@PathVariable String field) {
	        List<Student> allStudntes = service.findStudentsWithSorting(field);
	        return new APIResponse<>(allStudntes.size(), allStudntes);
	    }

	    @GetMapping("/pagination/{offset}/{pageSize}")
	    private APIResponse<Page<Student>> getStudentsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
	        Page<Student> studentsWithPagination = service.findStudentsWithPagination(offset, pageSize);
	        return new APIResponse<>(studentsWithPagination.getSize(), studentsWithPagination);
	    }

	    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	    private APIResponse<Page<Student>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
	        Page<Student> studentsWithPagination = service.findStudentsWithPaginationAndSorting(offset, pageSize, field);
	        return new APIResponse<>(studentsWithPagination.getSize(), studentsWithPagination);
	    }

}
