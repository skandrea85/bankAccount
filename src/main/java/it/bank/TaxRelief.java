package it.bank;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaxRelief implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String taxReliefId;
	private Boolean isCondoUpgrade;   
	private String creditorFiscalCode;
	private String beneficiaryType;
	private NaturalPersonBeneficiary naturalPersonBeneficiary;
	    
	private LegalPersonBeneficiary legalPersonBeneficiary;

	

}
