package org.lyb.mappers;
import org.lyb.model.Article;
import org.lyb.model.Student;  
  
import java.util.List;  
  
public interface StudentMapper {  
    List<Student> findAllStudents();  
  
    Student findStudentById(Integer id);  
  
    void insertStudent(Student student);  
    
    void deleteStudentById(Integer id);
    
    void updateStudent(Student stud);

	List<Article> getStudentArticles(int userid);
}  