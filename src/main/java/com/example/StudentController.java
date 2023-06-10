package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	private StudentRepository repo;
	@Autowired
	private StudentService service;
	
	@GetMapping("/")
	public String home(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "index";
	}
	@PostMapping("/add")
	public String addStudent(Model model,@ModelAttribute("student") Student student ,@RequestParam(name="add" , required=false) String add, @RequestParam(name="reset", required=false) String reset) {
		Student student2 = service.get(student.getId());
		int dem=0;
		if(add!=null) {
			if(student.getId()=="") {
				model.addAttribute("mess", "Id khong duoc de trong");
				dem++;
			}
			if(student2!=null) {
				model.addAttribute("mess1", "Id da ton tai");
				dem++;
			}
			if(student.getName()=="") {
				model.addAttribute("mess2", "Ten khong duoc de trong");
				dem++;
			}
			if(student.getDob()==null) {
				model.addAttribute("mess3", "Ngay sinh khong duoc de trong");
				dem++;
			}
			if(student.getDepartment()=="") {
				model.addAttribute("mess4", "Department khong duoc de trong");
				dem++;
			}
			if(dem!=0) {
				return "index";
			}
			return "confirm";
		}
		return "redirect:/";
	}
	@PostMapping("/save")
	public String saveStudent(HttpSession session,Student student, @RequestParam(name="confirm", required=false) String confirm, @RequestParam(name="back", required=false) String back) {
		if(confirm!=null) {
			session.setAttribute("nameid", student.getId());
			session.setAttribute("name", student.getName());
			session.setAttribute("namedob", student.getDob());
			session.setAttribute("namedepartment", student.getDepartment());
			repo.save(student);
		}
		return "redirect:/";
	}
}
