package com.cdutcm.tcms.ajy.model;

public class Report {

	private Long id;
	
	private String tzbs;//体质报告
	
	private Long jltzId; //经络体质id
	
	private Long sjysId;//四季饮食ID
	
	private String healthScore; //健康得分
	
	private Long recordId; //检测报告ID
	

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTzbs() {
		return tzbs;
	}

	public void setTzbs(String tzbs) {
		this.tzbs = tzbs;
	}

	public Long getJltzId() {
		return jltzId;
	}

	public void setJltzId(Long jltzId) {
		this.jltzId = jltzId;
	}

	public Long getSjysId() {
		return sjysId;
	}

	public void setSjysId(Long sjysId) {
		this.sjysId = sjysId;
	}

	public String getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(String healthScore) {
		this.healthScore = healthScore;
	}
}
