package com.example.sms.controller;

import com.example.sms.service.impl.userService;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}


	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}


	@PostMapping("/login")
	public String loginAfterRegister(HttpServletRequest request, Model model) {
		if (!(request.getParameter("password").equals(request.getParameter("confirmPassword")))) {
			model.addAttribute("message", "Re-enter the same password!!!");
			return "register";
		} else {
			model.addAttribute("successMessage", "Registered Successfully!!!");
			String email = request.getParameter("email");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String phoneNumber = request.getParameter("phoneNumber");
			User user = new User(email, userName, password, phoneNumber);
			userService.saveUser(user);
			return "login";
		}
	}

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}

//	List all students
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

//	add student
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {

		// created student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

//	save student
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/";
	}



//	update student actual 
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, 
								@ModelAttribute("student") Student student, 
								Model model) {
		// Get Student details from database
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setDate(student.getDate());
		existingStudent.setNationality(student.getNationality());
		
		// save updated student object
		studentService.updateStudent(existingStudent);
		
		return "redirect:/students";
	}

	
	

}
















