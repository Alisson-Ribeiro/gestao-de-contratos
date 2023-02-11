package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner leitor = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato: ");
		System.out.print("Numero: ");
		int number = leitor.nextInt();
		System.out.println("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(leitor.next(), fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = leitor.nextDouble();
		
		Contract obj = new Contract(number, date, totalValue);
		
		System.out.println("Entre com o numero de parcelas: ");
		int n = leitor.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(obj, n);
		
		System.out.println("Parcelas: ");
		
		for (Installment installment : obj.getInstallments()) {
			System.out.println(installment);
		}
		
		
		
		
		leitor.close();

	}

}
