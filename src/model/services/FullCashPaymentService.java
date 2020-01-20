package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Installment;

public class FullCashPaymentService implements PaymentService {

	//No pagamento a vista não há juros e nem taxa
	static double SIMPLEINTEREST = 0;
	static double PAYMENTFEE = 0;
	
	@Override
	public Double paymentFee(Double basicPaymentValue) {
		return basicPaymentValue*PAYMENTFEE;
	}

	@Override
	public Double interest(Double basicPaymentValue, int months) {
		return basicPaymentValue*SIMPLEINTEREST;
	}
	
	private Double installmentTotalValue(Double installmentBasicValue, int months) {
		double interest = interest(installmentBasicValue, months);
		return installmentBasicValue 
				+ paymentFee(installmentBasicValue + interest)
				+ interest;
	}
	
	@Override
	public Installment[] generateInstallments(Date date, Double totalValue, int installmentNumber) {
		Installment[] paymentValue = new Installment[1];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//O prazo de pagamento é até o último dia do mês do contrato
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		paymentValue[0] = new Installment(cal.getTime(), installmentTotalValue(totalValue, 0));
		return paymentValue;
	}

}
