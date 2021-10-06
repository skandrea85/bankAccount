package it.bank;

import java.io.Serializable;

import lombok.Data;

@Data
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String address;
	
	private String city;
	
	private String countryCode;
	
	

}
