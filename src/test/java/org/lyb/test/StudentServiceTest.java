package org.lyb.test;

import org.lyb.model.Student;
import org.lyb.services.StudentService;
import org.junit.AfterClass;  
import org.junit.Assert;  
import org.junit.BeforeClass;  
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentServiceTest {
    private static StudentService studentService;

    @BeforeClass
    public static void setup() throws IOException {
        studentService = new StudentService();
        Locale.setDefault(Locale.CHINA);
    }

    @AfterClass
    public static void tearDown() {
        studentService = null;
    }

    @Test
    public void testFindAllStudents() throws IOException {
        List<Student> students = studentService.findAllStudents();
        Assert.assertNotNull(students);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testFindStudentById() {
        Student student = studentService.findStudentById(1);
        Assert.assertNotNull(student);
        System.out.println(student);
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        Long id = 7L;
        student.setStudId(id);
        student.setName("student_" + id);
        student.setEmail("student_" + id + "@gmail.com");
        student.setDob(new Date());
        studentService.createStudent(student);
        Student newStudent = studentService.findStudentById(id.intValue());
        Assert.assertNotNull(newStudent);
    }
    
    @Test
    public void testDeleteStudentById() {
    	Long id = 7L;
        studentService.deleteStudentById(id.intValue());
    }
    
    @Test
    public void testUpdateStudentById() {
    	Long id = 1L;
    	Student student=new Student();
    	student.setStudId(id);
    	student.setName("liyubin");
//        student.setEmail("lyb@corp.com");
//        student.setDob(new Date());
        studentService.updateStudent(student);
    }
    
    @Test
    public void testGetStudentArticlesById() {
        studentService.getStudentArticles(1); 
    }


}
