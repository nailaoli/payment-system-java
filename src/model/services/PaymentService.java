package model.services;

import java.util.Date;

import model.entities.Installment;

public interface PaymentService {

	Double paymentFee(Double basicPaymentValue);
	
	Double interest(Double basicPaymentValue, int months);
	
	Installment[] generateInstallments(Date date, Double totalValue, int installmentNumber);
	
}
