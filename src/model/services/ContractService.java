package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	public void processContract (Contract contract, int months, PaymentService payment) {
		contract.setInstallments(
				payment.generateInstallments(contract.getDate(), contract.getTotalvalue(), months));
	}
	
	public String printInstallments(Contract contract) {
		StringBuilder sb = new StringBuilder();
		sb.append("Installments:\n");
		for(Installment i : contract.getInstallments()) {
			sb.append(i + "\n");
		}
		return sb.toString();
	}
}
