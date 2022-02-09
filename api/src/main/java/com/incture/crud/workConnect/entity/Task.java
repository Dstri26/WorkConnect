package com.incture.crud.workConnect.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TASK")
public class Task {
	
	@Id
	@GeneratedValue
	private int id;
	private String taskName;
	private String platform;
	private String email;
	private int status;
	private Date time;
	

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String tname) {
        this.taskName = tname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String em) {
        this.email = em;
    }
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String pt) {
        this.platform = pt;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int s) {
        this.status = s;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date d) {
    	this.time = d;
    }
}
