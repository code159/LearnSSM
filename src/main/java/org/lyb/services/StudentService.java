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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

public class StudentService {
    private Logger logger =
            LoggerFactory.getLogger(getClass());
    private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;  
    private static SqlSessionFactory MyBatisSqlSessionFactory;  
    private SqlSession sqlSession;
    private static ApplicationContext ctx; 
    private StudentMapper studentMapper; 
    
    public StudentService() throws IOException{
    	mybatisOpenSession();
//    	springOpenSession();
    }
    
  //使用mybatis来管理数据库连接
    private void mybatisOpenSession() throws IOException{	
    	PropertyConfigurator.configure(ClassLoader.getSystemResource("config/log4j.properties"));
    	String resource = "config/mybatis-config.xml";  
        Reader reader = Resources.getResourceAsReader(resource); 
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();  
        MyBatisSqlSessionFactory = sqlSessionFactoryBuilder.build(reader); 
    	sqlSession = MyBatisSqlSessionFactory.openSession();
    	studentMapper =
                sqlSession.getMapper(StudentMapper.class);
    }
    
   //使用spring来管理数据库连接
    private void springOpenSession(){   	
    	ctx=new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
    	studentMapper = (StudentMapper)ctx.getBean("userMapper");
    }

    public List<Student> findAllStudents(){ 	
        
        try {
        	 return studentMapper.findAllStudents();
        } finally {
            //If sqlSession is not closed then database Connection associated this sqlSession will not be
            //returned to pool and application may run out of connections.
            if(sqlSession!=null){
            	sqlSession.close();
            }
        }
    }

    public Student findStudentById(Integer studId) {
        logger.debug("Select Student By ID :{}", studId);
       
        try {
            
            return studentMapper.findStudentById(studId);
        } finally {
        	 if(sqlSession!=null){
             	sqlSession.close();
             }
        }
    }
    
    //只能在mybatis管理数据库连接的时候使用
    public Student findStudentById2(Integer studId) {
        logger.debug("Select Student By ID :{}", studId);
        
        try {
            Student student = (Student) sqlSession.
                    selectOne("org.lyb.mappers.StudentMapper.findStudentById",
                            studId);
            return student;
        } finally {
        	 if(sqlSession!=null){
             	sqlSession.close();
             }
        }
    }

    public void createStudent(Student student) {
       
        try {
            
            studentMapper.insertStudent(student);
            if(sqlSession!=null){
       		 sqlSession.commit();
            }
        } finally {
        	 if(sqlSession!=null){
             	sqlSession.close();
             }
        }
    }
    
    public void deleteStudentById(Integer studId) {
        logger.debug("Delete Student By ID :{}", studId);
        
        try {
        	
             studentMapper.deleteStudentById(studId);
             if(sqlSession!=null){
        		 sqlSession.commit();
             }
        } finally {
        	 if(sqlSession!=null){
             	sqlSession.close();
             }
        }
    }
    
    public void updateStudent(Student stud) {
        logger.debug("Update Student By ID :{}", stud.getStudId());
        
        try {
        	 
             Student student=studentMapper.findStudentById(stud.getStudId().intValue());
             if(stud.getName()!=null) student.setName(stud.getName());
             if(stud.getEmail()!=null) student.setEmail(stud.getEmail());
             if(stud.getDob()!=null) student.setDob(stud.getDob());
        	 studentMapper.updateStudent(student);
        	 if(sqlSession!=null){
        		 sqlSession.commit();
             }
             
        } finally {
        	 if(sqlSession!=null){
             	sqlSession.close();
             }
        }
    }
    
    public void getStudentArticles(int studId){
    	logger.debug("Student association Article :{}", studId);
        
        try {
        	    
            List<Article> articles = studentMapper.getStudentArticles(studId);
            for(Article article:articles){
                System.out.println(article);
            }
        } finally {
        	 if(sqlSession!=null){
             	sqlSession.close();
             }
        }
    }
}


