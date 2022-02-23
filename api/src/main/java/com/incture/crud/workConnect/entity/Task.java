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
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue
	private int id;
	private String taskName;
	private String platform;
	private String receiver;
	private int status;
	private int isDeleted=0;
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
    
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String em) {
        this.receiver = em;
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
    
    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int dl) {
        this.isDeleted = dl;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date d) {
    	this.time = d;
    }
}
