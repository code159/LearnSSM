package org.lyb.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.lyb.mappers.StudentMapper;
import org.lyb.model.Article;
import org.lyb.model.Student;


public class MyBatisSpringTest {
    
    private static ApplicationContext ctx;  
    
    static 
    {  
        ctx = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");  
    }        
      
    public static void main(String[] args)  
    {  
    	StudentMapper mapper = (StudentMapper)ctx.getBean("userMapper"); 
        //测试id=1的用户查询，根据数据库中的情况，可以改成你自己的.
        System.out.println("得到用户id=1的用户信息");
        Student user = mapper.findStudentById(1);
        System.out.println(user.getEmail()); 
        
        //得到文章列表测试
        System.out.println("得到用户id为1的所有文章列表");
        List<Article> articles = mapper.getStudentArticles(1);
        
        for(Article article:articles){
            System.out.println(article.getContent()+"--"+article.getTitle());
        }
        
    }  

    
}
