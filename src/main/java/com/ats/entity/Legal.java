package com.ats.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "legal")
public class Legal {
	@Id
	private int contractorid;
	private int tenderid;
	private String name;
	private String emailid;
	private String gstno;
	private String phoneno;
	private int experience;
	private String filepath;
	public int getContractorid() {
		return contractorid;
	}
	public void setContractorid(int contractorid) {
		this.contractorid = contractorid;
	}
	public int getTenderid() {
		return tenderid;
	}
	public void setTenderid(int tenderid) {
		this.tenderid = tenderid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getGstno() {
		return gstno;
	}
	public void setGstno(String gstno) {
		this.gstno = gstno;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
