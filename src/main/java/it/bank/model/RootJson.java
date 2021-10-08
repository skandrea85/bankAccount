package it.bank.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RootJson implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PayLoad payload;

}
