package it.bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bank.model.Transaction;
@Service
public class PersistanceBankService {
	
	@Autowired
	PersistanceBank persistanceBank;

	public void saveAll(List<Transaction> tra) {
		persistanceBank.saveAll(tra);
		
	}

}
