package com.ats.request;

public class BackupRequest {
	private int tenderid;
	private int contractorid;
	private String Contractorname;
	private String username;
	private String userphoneno;
	private String contractorphoneno;
	private String budget;
	private String userfile;
	private String contractorfile;
	private String contractorgst;
	public int getTenderid() {
		return tenderid;
	}
	public void setTenderid(int tenderid) {
		this.tenderid = tenderid;
	}
	public int getContractorid() {
		return contractorid;
	}
	public void setContractorid(int contractorid) {
		this.contractorid = contractorid;
	}
	public String getContractorname() {
		return Contractorname;
	}
	public void setContractorname(String contractorname) {
		Contractorname = contractorname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphoneno() {
		return userphoneno;
	}
	public void setUserphoneno(String userphoneno) {
		this.userphoneno = userphoneno;
	}
	public String getContractorphoneno() {
		return contractorphoneno;
	}
	public void setContractorphoneno(String contractorphoneno) {
		this.contractorphoneno = contractorphoneno;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getUserfile() {
		return userfile;
	}
	public void setUserfile(String userfile) {
		this.userfile = userfile;
	}
	public String getContractorfile() {
		return contractorfile;
	}
	public void setContractorfile(String contractorfile) {
		this.contractorfile = contractorfile;
	}
	public String getContractorgst() {
		return contractorgst;
	}
	public void setContractorgst(String contractorgst) {
		this.contractorgst = contractorgst;
	}
	
}
