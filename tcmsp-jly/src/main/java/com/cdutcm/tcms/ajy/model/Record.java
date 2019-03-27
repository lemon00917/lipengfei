package com.cdutcm.tcms.ajy.model;

import java.util.Date;

import com.cdutcm.core.page.Page;

public class Record {

	private Long id;
	
	private Long familyMemberId;
	
	private String detectionResult;
	
	private String xl;//心率
	
	private String tw;//体温
	
	private String tzbfb;//体脂百分比
	
	private String sleepTime; //睡眠时间
	
	private Date  gmtCreate;
	
	private Date  gmtModified;
	
	private Page page;
	
	private Report report;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(Long familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public String getDetectionResult() {
		return detectionResult;
	}

	public void setDetectionResult(String detectionResult) {
		this.detectionResult = detectionResult;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getTw() {
		return tw;
	}

	public void setTw(String tw) {
		this.tw = tw;
	}

	public String getTzbfb() {
		return tzbfb;
	}

	public void setTzbfb(String tzbfb) {
		this.tzbfb = tzbfb;
	}

	public String getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}
