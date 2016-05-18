package com.zm.execl.model;

import java.util.Date;


/**
 * excel 实体
 * @author zm
 *
 */
public class XlsDto {

	/**
	 * 时间
	 */
	private Date time;
	
	private Double ltq;
	
	private Double hyq;
	
	private Double gyq;
	
	private Double wjq;



	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getLtq() {
		return ltq;
	}

	public void setLtq(Double ltq) {
		this.ltq = ltq;
	}

	public Double getHyq() {
		return hyq;
	}

	public void setHyq(Double hyq) {
		this.hyq = hyq;
	}

	public Double getGyq() {
		return gyq;
	}

	public void setGyq(Double gyq) {
		this.gyq = gyq;
	}

	public Double getWjq() {
		return wjq;
	}

	public void setWjq(Double wjq) {
		this.wjq = wjq;
	}

	public XlsDto() {
		super();
	}

	public XlsDto(Date time, Double ltq, Double hyq, Double gyq, Double wjq) {
		super();
		this.time = time;
		this.ltq = ltq;
		this.hyq = hyq;
		this.gyq = gyq;
		this.wjq = wjq;
	}


}
