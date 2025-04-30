package com.bankapp.service;

import java.util.List;

import com.bankapp.entity.Account;

public interface AccountService {
	public Account createAccount (Account account);
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	public List<Account> getAccountDetails();
	public Account depositAmount(Long accountNumber,Double amount);
	public Account withdrawAmount(Long accountNumber, Double amount);
	public void closeAccount(Long accountNymber);
	

}
