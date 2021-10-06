package it.bank;

import java.io.Serializable;

import lombok.Data;

@Data
public class Creditor implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1480362346053176102L;

	private String name;
	
	private Account account; 
	
	private Address address;

}
