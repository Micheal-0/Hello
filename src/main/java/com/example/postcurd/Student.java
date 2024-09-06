package com.example.postcurd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name="std")
public class Student {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	
	//@Column(name="stu_id")
	private int id;
	
	//@Column(name="stu_name")
	private String name;
	
	//@Column(name="stu_course")
	private String course;
	
	//@Column(name="stu_age")
	private int age;

	public Student()
	{}
	
	public Student(int id, String name, String course, int age) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
