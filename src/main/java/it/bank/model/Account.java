package it.bank.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Account implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountCode;
	
	private String bicCode;

}
