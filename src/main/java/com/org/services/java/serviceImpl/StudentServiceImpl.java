package com.org.services.java.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.org.services.java.entity.Student;
import com.org.services.java.repository.StudentRepository;
import com.org.services.java.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repository;

	/*
	 * @PostConstruct public void initDB() { List<Student> students =
	 * IntStream.rangeClosed(1, 200) .mapToObj(i -> new Student(i, "student" + i,
	 * null, new Random().nextInt(100), null, new Random().nextInt(50000)))
	 * .collect(Collectors.toList()); repository.saveAll(students); }
	 */

	@Override
	public List<Student> findAllStudents() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Student> findStudentsWithSorting(String field) {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	@Override
	public Page<Student> findStudentsWithPagination(int offset, int pageSize) {
		Page<Student> products = repository.findAll(PageRequest.of(offset, pageSize));
		return products;
	}

	@Override
	public Page<Student> findStudentsWithPaginationAndSorting(int offset, int pageSize, String field) {
		Page<Student> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return products;
	}

}
