package it.bank;

import java.io.Serializable;

import lombok.Data;

@Data
public class LegalPersonBeneficiary implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3700400445109999188L;
	private String fiscalCode;
	private String legalRepresentativeFiscalCode;

}
