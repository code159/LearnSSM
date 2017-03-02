package org.lyb.services;

import org.lyb.model.Article;
import org.lyb.model.Student;
import org.lyb.mappers.StudentMapper;
import org.apache.ibatis.io.Resources;
//import org.lyb.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

public class StudentService {
    private Logger logger =
            LoggerFactory.getLogger(getClass());
    private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;  
    private static SqlSessionFactory MyBatisSqlSessionFactory;  
    
    public StudentService() throws IOException{
    	//PropertyConfigurator.configure("src/main/java/config/log4j.properties");
    	PropertyConfigurator.configure(ClassLoader.getSystemResource("config/log4j.properties"));
    	String resource = "config/mybatis-config.xml";  
        Reader reader = Resources.getResourceAsReader(resource); 
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();  
        MyBatisSqlSessionFactory = sqlSessionFactoryBuilder.build(reader);  
    }

    public List<Student> findAllStudents(){ 	
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper =
                    sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findAllStudents();
        } finally {
            //If sqlSession is not closed then database Connection associated this sqlSession will not be
            //returned to pool and application may run out of connections.
            sqlSession.close();
        }
    }

    public Student findStudentById(Integer studId) {
        logger.debug("Select Student By ID :{}", studId);
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper =
                    sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findStudentById(studId);
        } finally {
            sqlSession.close();
        }
    }

    public Student findStudentById2(Integer studId) {
        logger.debug("Select Student By ID :{}", studId);
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
            Student student = (Student) sqlSession.
                    selectOne("org.lyb.mappers.StudentMapper.findStudentById",
                            studId);
            return student;
        } finally {
            sqlSession.close();
        }
    }

    public void createStudent(Student student) {
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
            StudentMapper studentMapper =
                    sqlSession.getMapper(StudentMapper.class);
            studentMapper.insertStudent(student);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    
    public void deleteStudentById(Integer studId) {
        logger.debug("Delete Student By ID :{}", studId);
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
        	 StudentMapper studentMapper =
                     sqlSession.getMapper(StudentMapper.class);
             studentMapper.deleteStudentById(studId);
             sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    
    public void updateStudent(Student stud) {
        logger.debug("Update Student By ID :{}", stud.getStudId());
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
        	 StudentMapper studentMapper =
                     sqlSession.getMapper(StudentMapper.class);
             Student student=studentMapper.findStudentById(stud.getStudId().intValue());
             if(stud.getName()!=null) student.setName(stud.getName());
             if(stud.getEmail()!=null) student.setEmail(stud.getEmail());
             if(stud.getDob()!=null) student.setDob(stud.getDob());
        	 studentMapper.updateStudent(student);
             sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    
    public void getStudentArticles(int studId){
    	logger.debug("Student association Article :{}", studId);
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try {
        	 StudentMapper studentMapper =
                     sqlSession.getMapper(StudentMapper.class);       
            List<Article> articles = studentMapper.getStudentArticles(studId);
            for(Article article:articles){
                System.out.println(article);
            }
        } finally {
        	sqlSession.close();
        }
    }
}


