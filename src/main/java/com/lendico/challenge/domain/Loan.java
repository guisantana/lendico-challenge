package com.lendico.challenge.domain;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Loan {

	private int loanAmount;
	private double nominalRate;
	private int duration;
	private LocalDateTime startDate;

}
