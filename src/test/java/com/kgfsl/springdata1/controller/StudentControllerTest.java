//TDD  PROGRAM EXAMPLE. (this program is written by developer so only developer can understand when business people 
                    // people write a featur file and when we put functions based on it then they could understand, that is callled BDD)


package com.kgfsl.springdata1.controller;

import java.util.Arrays;
import java.util.List;

import com.kgfsl.springdata1.model.Student;
import com.kgfsl.springdata1.repository.StudentRepository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
  @InjectMocks       //in which injection is going to take place is marked by injectmocks.Injection enables shorthand mock and spy injections
  private StudentController studentController;     
  @Mock        //going to create a test double object ie., a mock object. mock is implemented in tdd and bdd.
  private StudentRepository studentRepository;
            //studentRepository is the mock object.

  public static List<Student> expectedStudents;


  //AAA PATTERN of TDD. (A-assign,A-action,A-assert)
  
  @Test
  public void findStudentsTest() {                    
    // Assign
    Student student1 = new Student(1L, "KGiSL");
    Student student2 = new Student(2L, "KGfSL");      //going to hardcode with these two values to expectedStudents

    expectedStudents = Arrays.asList(student1, student2);     //asList is a fixed size which will contain studen1 and student2 so fixedsize is 2.   
                              
    when(studentRepository.findAll()).thenReturn(expectedStudents); //when finall method is called (ie., when u press f12 on findall method) then 
                                  //FINDALL method goes to jpa repository and also this is prooved when you see finall method in studentController.java
                                  //it goes to studentRepository and from studrntrepository it goes to jparepository....
                                  //instead of this all, we shall just return a value that is enough so we are returning expectedStudents.
    // Action
    List<Student> actualStudents = studentController.findStudents();
                                  // findStudents method should be entered as values 1L,KGiSl and 2L KGfsL by the user.
    // Assert-
    assertNotNull(actualStudents);    //actualStudents null ah ilaya
    assertEquals(2, actualStudents.size());   //hardcodes is 2 user going to enter values is actalstudents.size()
    assertEquals(expectedStudents, actualStudents);     //assertequals methods is present in junit.assert whichis different from assertj-core.
  }

  @Test
  public void findoneTest() {     //this function checks whether a id is present or not.
    // Assign
    Long studentId=1L;           //going to check 1L is there or not.
    Student student1 = new Student(1L, "KGiSL");    //hardcoding 1L value
   
    when(studentRepository.findOne(studentId)).thenReturn(student1);   //sameway stopping in going to repository class by returning student1

    // Action
    Student student = studentController.findone(studentId);     //will return actual values to student

    // Assert-
    assertNotNull(student);     //student checks null or what
    assertEquals(studentId, student.getId());   //user id and hardcoded id is checked whether same or not
    assertEquals("KGiSL", student.getName());     //chcks name is whether KGiSL or not.
  }

  @Test
  public void addStudentTest() {
    
    Student student1 = new Student();       
    student1.setName("KGiSL");    //adding is done by setting so setName(name)  since id is autoincrement 1L wil be added automatically.           
    Student student2 = new Student(1L, "KGiSL");      //student2 is going to be added
    when(studentRepository.saveAndFlush(student1)).thenReturn(student2);

    Student AR = studentController.addStudent(student1); //stopping saveand flush in getting into repository by returning student1
    
    
    assertEquals("KGiSL", AR.getName());      //setted is checked whether properly setted by getting the same.
  assertEquals(student2,AR);  
  }
  @Test  
  public void updateStudentTest()
  {
    Long studentId=1L;
    Student student1 = new Student(1L, "KGiSL");
    Student student2 = new Student(1L , "KGfSL");         //going to update 1L with KGfSL with KGiSL. 

    when(studentRepository.saveAndFlush(student2)).thenReturn(student2);      //stopping saveand flush in getting into repository by returning student2
    Student student = studentController.updateStudent(student2, studentId);   //studentId is set to 1L when we update that studentID with student2,it will change and now 1L  will have KGfSL and not KGisl

    assertNotNull(student);
    assertEquals(student, student2);   //checking student object with hardcoded student2 variable to check whether it is been updated or not.
  }
  
  
  @Test
  public void deleteStudentTest() {
    Long studentId=2L;
   
    studentController.deleteStudent(studentId);
    verify(studentRepository).delete(studentId);        //deleting 2L value we will use verify method for deleting.
 
  }
 

}