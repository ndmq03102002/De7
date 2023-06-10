package com.example;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
	@Autowired
	private StudentRepository repo;
	
	public Student get(String id) {
		Optional<Student> student = repo.findById(id);
		if(student.isPresent()) {
			return student.get();
		}
		return null;
	}
}
