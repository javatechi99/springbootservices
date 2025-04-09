package com.org.services.java.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
	
	private int id;
	private String name;
	private String branch;
	private int age;
	private String course;
	private int uniqId;

}
