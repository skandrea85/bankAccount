package it.bank.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PayLoad implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Transaction> list;

}
