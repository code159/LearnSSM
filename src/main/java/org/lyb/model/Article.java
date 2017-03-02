package org.lyb.model;

public class Article {
    
    private int id;
    private Student student;
    private String title;
    private String content;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student user) {
        this.student = user;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString(){
    	return id+"\t"+title+"\t"+content;
    }

}
