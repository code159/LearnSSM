package org.lyb.model;

import java.text.DateFormat;
import java.util.Date;  
  
public class Student {  
    private Long studId;  
    private String name;  
    private String email;  
    private Date dob;  
  
    public Long getStudId() {  
        return studId;  
    }  
  
    public void setStudId(Long studId) {  
        this.studId = studId;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getEmail() {  
        return email;  
    }  
  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    public Date getDob() {  
        return dob;  
    }  
  
    public void setDob(Date dob) {  
        this.dob = dob;  
    }
    
    @Override
    public String toString(){
    	return studId+"\t"+name+"\t"+email+"\t"+DateFormat.getInstance().format(dob);
    }
}  