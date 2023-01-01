package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private String bhitcount;
	private String battachoname;
	private String battachsname;
	private String battachtype;
	private MultipartFile battach;
	public int getBno() {
		return bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public Date getBdate() {
		return bdate;
	}
	public String getMid() {
		return mid;
	}
	public String getBhitcount() {
		return bhitcount;
	}
	public String getBattachoname() {
		return battachoname;
	}
	public String getBattachsname() {
		return battachsname;
	}
	public String getBattachtype() {
		return battachtype;
	}
	public MultipartFile getBattach() {
		return battach;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setBhitcount(String bhitcount) {
		this.bhitcount = bhitcount;
	}
	public void setBattachoname(String battachoname) {
		this.battachoname = battachoname;
	}
	public void setBattachsname(String battachsname) {
		this.battachsname = battachsname;
	}
	public void setBattachtype(String battachtype) {
		this.battachtype = battachtype;
	}
	public void setBattach(MultipartFile battach) {
		this.battach = battach;
	}
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bdate=" + bdate + ", mid="
				+ mid + ", bhitcount=" + bhitcount + ", battachoname=" + battachoname + ", battachsname=" + battachsname
				+ ", battachtype=" + battachtype + "]";
	}
	
	
	
	
	
}
