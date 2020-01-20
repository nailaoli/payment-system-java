package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.services.ContractService;
import model.services.FullCashPaymentService;
import model.services.PaypalService;

//Programa para gerar um contrato, escolher se o pagamento é a vista ou parcelado, e gerar o valor a ser pago

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Enter constract data");
		System.out.print("Number: ");
		int contractNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.nextLine());
		System.out.print("Contract Value: ");
		double contractValue = sc.nextDouble();
		
		Contract ct = new Contract(contractNumber, date, contractValue);
		
		System.out.print("Enter number of installments (0 for full cash payment): ");
		int installmentNumber = sc.nextInt();
		
		ContractService cs = new ContractService();
		
		cs.processContract(ct, installmentNumber, 
				(installmentNumber==0) ? new FullCashPaymentService() : new PaypalService());
		
		System.out.println(cs.printInstallments(ct));
		
		sc.close();
	}

}
