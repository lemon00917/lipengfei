package com.cdutcm.tcms.ajy.DTO;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author mxq
 * 经络报告数据传输对象
 */
public class ReportDTO  {
	private Long id;//和report  id相同
	private String tzbs;//体质报告
	private String tztz;//体质特征
    private String jl; //经络
	private String jlzz; //经络证型
	private String healScore; //得分
    private String xl;//心率
	
	private String tw;//体温
	
	private String tzbfb;//体脂百分比
	
	private String sleepTime; //睡眠时间
	private Date  gmtCreate;
	
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
	public String getTztz() {
		return tztz;
	}
	public void setTztz(String tztz) {
		this.tztz = tztz;
	}
	public String getJl() {
		return jl;
	}
	public void setJl(String jl) {
		this.jl = jl;
	}
	public String getJlzz() {
		return jlzz;
	}
	public void setJlzz(String jlzz) {
		this.jlzz = jlzz;
	}
	public String getHealScore() {
		return healScore;
	}
	public void setHealScore(String healScore) {
		this.healScore = healScore;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}
