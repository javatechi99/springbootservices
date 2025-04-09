package com.org.services.java.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.org.services.java.entity.Student;

@Service
public interface StudentService {
	
	 List<Student> findAllStudents();
	 
	 List<Student> findStudentsWithSorting(String field);
	 
	 Page<Student> findStudentsWithPagination(int offset,int pageSize);
	 
	 Page<Student> findStudentsWithPaginationAndSorting(int offset,int pageSize,String field);
	
	
	
	

}
