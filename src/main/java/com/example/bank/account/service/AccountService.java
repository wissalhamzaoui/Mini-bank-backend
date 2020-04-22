package com.example.bank.account.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.account.domain.Account;
import com.example.bank.account.domain.AccountRepository;

@Service

public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;
	
	public AccountDto createAccount(AccountDto AccountDto) {
		Account savedAccount = accountRepository.save(accountMapper.mapToDomain(AccountDto));
		return accountMapper.mapToDto(savedAccount);
	}

	public List<AccountDto> getAll() {
		return accountRepository.findAll().stream().map(c -> accountMapper.mapToDto(c)).collect(Collectors.toList());
	}
	
	public AccountDto getAccount( String id) {
		Optional<Account> c = accountRepository.findById(id);
		return accountMapper.mapToDto(c.get());
	}

	public AccountDto updateAccount( AccountDto AccountDto, String  AccountId) {

		return accountRepository.findById(AccountId).map(c -> {
			accountMapper.mapToDomain(AccountDto, c);// MAP TO DOMAIN
			accountRepository.save(c);// UPDATE ENTITY
			return accountMapper.mapToDto(c);// RETURN DTO
		}).orElseThrow(() -> new IllegalArgumentException(String.format("Account not found with Id: %s", AccountId)));
		}
	
	public void deleteAll() {
		accountRepository.deleteAll();
	}
	
	public void deleteAccount(String id) {
		accountRepository.deleteById(id);
	}

}
