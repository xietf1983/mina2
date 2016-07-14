package com.lytx.webservice.datacopylast.model;

import java.util.Date;

public class DatacopyLast {
	private String domainId;
	private String action;
	private Date datalasttime;
	private Date acttime;
	private String detail;
	private Long lastseq;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDatalasttime() {
		return datalasttime;
	}

	public void setDatalasttime(Date datalasttime) {
		this.datalasttime = datalasttime;
	}

	public Date getActtime() {
		return acttime;
	}

	public void setActtime(Date acttime) {
		this.acttime = acttime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getLastseq() {
		return lastseq;
	}

	public void setLastseq(Long lastseq) {
		this.lastseq = lastseq;
	}

}
