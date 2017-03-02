package org.lyb.services;
import java.io.Reader;
import java.text.DateFormat;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.lyb.model.Student;

public class Test extends ClassLoader{
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 

    static{
        try{
            reader    = Resources.getResourceAsReader("config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
    public static void main(String[] args) {
//        SqlSession session = sqlSessionFactory.openSession();
//        try {
//        Student user = (Student) session.selectOne("org.lyb.mappers.StudentMapper.findStudentById", 1);
//        System.out.println(user.getStudId());
//        System.out.println(user.getName());
//        } finally {
//        session.close();
//        }
    	System.out.println(ClassLoader.getSystemResource("config/log4j.properties"));
    	System.out.println(DateFormat.getInstance().format(new Date()));
    }
}