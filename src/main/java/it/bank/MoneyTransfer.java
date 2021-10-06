package it.bank;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class MoneyTransfer implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Creditor creditor;

	private String executionDate;
	private String uri;
	private String description;
	private Number amount;
	private String currency ;
	private Boolean isUrgent; 
	private Boolean isIstant;
	private String Feetype; 
	private String feeAccountId;
	private TaxRelief taxRelief;
	  
	
	

}
