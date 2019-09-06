package com.lendico.challenge.util;

import java.text.DecimalFormat;
import java.util.List;

import com.lendico.challenge.domain.Payment;

public class ChallengeUtil {

	public static void generatePlan(List<Payment> plan, Payment payment, double nominalRate, double annuity) {

		if(formatCurrencyTwoDigits(payment.getRemainingOutstandingPrincipal()) > 0.0) {
			Payment newPayment = new Payment();
			newPayment.setDate(payment.getDate().plusMonths(1));
			newPayment.setInitialOutstandingPrincipal(payment.getRemainingOutstandingPrincipal());
			
			double interest = calculateInterest(payment.getRemainingOutstandingPrincipal(), nominalRate * 100);
			newPayment.setInterest(interest);
			
			double principal = (annuity - interest);
			if(interest > newPayment.getInitialOutstandingPrincipal()) {
				principal = newPayment.getInitialOutstandingPrincipal();
			}
			double diffInitialAndPrincipal = newPayment.getInitialOutstandingPrincipal() - principal;
			
			newPayment.setBorrowerPaymentAmount(interest + principal);
			newPayment.setRemainingOutstandingPrincipal(diffInitialAndPrincipal);
			
			if (diffInitialAndPrincipal <= 0) {
				principal = principal + newPayment.getRemainingOutstandingPrincipal();
				newPayment.setBorrowerPaymentAmount(newPayment.getBorrowerPaymentAmount() + newPayment.getRemainingOutstandingPrincipal());
				newPayment.setRemainingOutstandingPrincipal(0);
			}
			
			double diffRemainingAndPrincipal = newPayment.getRemainingOutstandingPrincipal() - principal;
			
			if (diffRemainingAndPrincipal < 0) {
				principal = principal + newPayment.getRemainingOutstandingPrincipal();
				newPayment.setBorrowerPaymentAmount(interest + principal);
				newPayment.setRemainingOutstandingPrincipal(0);
			}

			newPayment.setPrincipal(principal);
			
			plan.add(newPayment);

			generatePlan(plan, newPayment, nominalRate, annuity);
		}

	}

	public static double calculateAnnuity(double pv, double r, int t)
	{
		double result;

		int months = 12;

		if (t <= 0) { 
			result = pv;
		}
		else if (r == 0) { 
			result = pv / t; 
		}
		else {
				double z = 1 / (1 + r / months);
				double div = 1 - Math.pow(z, t);
				div *=  z;
				result = round(pv * (1 - z) / div, 2);	
		}
		
		return result;
	}	
	
	public static double calculateInterest(double pv, double r) {
		return ((r * 30 * pv) / 360) / 100;
	}


	private static double round(double n, int dig)
	{
		double x = n * Math.pow(10, dig);
		x = Math.round(x);
		return x / Math.pow(10, dig);
	}
	
    public static Payment formatCurrency(Payment payment) {
    	
    	payment.setBorrowerPaymentAmount(formatCurrencyTwoDigits(payment.getBorrowerPaymentAmount()));
    	payment.setInitialOutstandingPrincipal(formatCurrencyTwoDigits(payment.getInitialOutstandingPrincipal()));
    	payment.setInterest(formatCurrencyTwoDigits(payment.getInterest()));
    	payment.setPrincipal(formatCurrencyTwoDigits(payment.getPrincipal()));
    	payment.setRemainingOutstandingPrincipal(formatCurrencyTwoDigits(payment.getRemainingOutstandingPrincipal()));
    	
		return payment;
	}
    
    public static Double formatCurrencyTwoDigits(double value) {
    	
    	DecimalFormat df = new DecimalFormat("#.##");     

		return Double.valueOf(df.format(value));
	}
	
}
