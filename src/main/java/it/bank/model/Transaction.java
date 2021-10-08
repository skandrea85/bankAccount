package it.bank.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Transaction implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6466774465417499175L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="transaction_id",unique=true)
	private String transactionId;
	
	
	@Column(name="operation_id")
	private String operationId;
	
	@Column(name="accounting_date")
	private Date accountingDate;
	
	@Column(name="value_date")
	private Date valueDate;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="description")
	private String description;


}
