package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Installment;

public class PaypalService implements PaymentService {

	static double SIMPLEINTEREST = 0.01;
	static double PAYMENTFEE = 0.02;
	
	@Override
	public Double interest(Double value, int months) {
		return value*(SIMPLEINTEREST*months);
	}
	
	@Override
	public Double paymentFee(Double value) {
		return value*PAYMENTFEE;
	}
	
	private Double installmentTotalValue(Double installmentBasicValue, int months) {
		double interest = interest(installmentBasicValue, months);
		return installmentBasicValue 
				+ paymentFee(installmentBasicValue + interest)
				+ interest;
	}
	
	@Override
	public Installment[] generateInstallments(Date date, Double totalValue, int installmentNumber) {
		Installment[] installments = new Installment[installmentNumber];
		double installmentBasicValue = totalValue/installmentNumber;
		Calendar cal = Calendar.getInstance();
		for (int i=0; i<installments.length; i++) {
			cal.setTime(date);
			cal.add(Calendar.MONTH, i+1);
			installments[i] = new Installment(cal.getTime(), installmentTotalValue(installmentBasicValue, i+1));
		}
		return installments;
	}
}
