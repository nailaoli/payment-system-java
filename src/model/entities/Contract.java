package model.entities;

import java.util.Date;

public class Contract {
	private Integer number;
	private Date date;
	private Double totalvalue;
	
	private Installment[] installments;
	
	public Contract(Integer number, Date date, Double totalvalue) {
		this.number = number;
		this.date = date;
		this.totalvalue = totalvalue;
	}

	public Date getDate() {
		return date;
	}
	
	public Integer getNumber() {
		return number;
	}

	public Double getTotalvalue() {
		return totalvalue;
	}

	public Installment[] getInstallments() {
		return installments;
	}

	public void setInstallments(Installment[] installments) {
		this.installments = installments;
	}
	
}
