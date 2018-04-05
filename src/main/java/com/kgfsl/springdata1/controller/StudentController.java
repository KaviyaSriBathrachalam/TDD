package com.kgfsl.springdata1.controller;

//
import java.util.List;

import com.kgfsl.springdata1.model.Student;
import com.kgfsl.springdata1.repository.*;

//import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;





/**
 * StudentController

 */
@RestController
 
@RequestMapping("/students")
public class StudentController{
    @Autowired
    StudentRepository studentRepository;

	@RequestMapping(method = RequestMethod.GET)
  public List<Student> findStudents() {
    // System.out.println("??????????????????"+studentRepository.findAll());
    return studentRepository.findAll();
  }
  
  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public Student findone(@PathVariable Long id) {
    return  studentRepository.findOne(id);
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Student addStudent(@RequestBody Student Student) {
	  Student.setId(null);
    return studentRepository.saveAndFlush(Student);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Student updateStudent(@RequestBody Student updatedStudent, @PathVariable Long id) {
    updatedStudent.setId(id);
    return studentRepository.saveAndFlush(updatedStudent);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteStudent(@PathVariable Long id) {
    studentRepository.delete(id);
}

// public Student saveAndFlush(Student student1, Object object) {
// 	return null;
// }

}