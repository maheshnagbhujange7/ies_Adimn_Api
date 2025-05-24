package com.spider.ADMIN_API.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ELTG_DTLS")
public class EligEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private String planStatus;
	private Double benifitAmt;
	private Integer edgTraced;

	public Double getBenifitAmt() {
		return benifitAmt;
	}

	public void setBenifitAmt(Double benifitAmt) {
		this.benifitAmt = benifitAmt;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public Integer getEdgTraced() {
		return edgTraced;
	}

	public void setEdgTraced(Integer edgTraced) {
		this.edgTraced = edgTraced;
	}
	
	
	
	
	
}
