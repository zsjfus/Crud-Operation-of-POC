package com.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.entity.Account;
import com.bankapp.repo.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountRepository repo;
	

	@Override
	public Account createAccount(Account account) {
		Account account_saved = repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not Present");
		}
		Account account_found=account.get();
		return account_found;
	}

	@Override
	public List<Account> getAccountDetails() {
		List<Account> ListofAccount = repo.findAll();
		return ListofAccount;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
			
		}
		Account accountPresent=account.get();
		Double TotalAccount=accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(TotalAccount);
		repo.save(accountPresent);
		return null;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
			
		}
		Account accountPresent=account.get();
		Double accountBalance=accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNymber) {
		getAccountDetailsByAccountNumber(accountNymber);
		repo.deleteById(accountNymber);
		
	}
	

}
