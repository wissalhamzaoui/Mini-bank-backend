package com.example.bank.account.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.account.service.AccountDto;
import com.example.bank.account.service.AccountService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/Account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/all")
	public List<AccountDto> getAll() {
		return accountService.getAll();
	}

	@GetMapping("/{id}")
	public AccountDto get(@PathVariable String id) {
		return accountService.getAccount(id);
	}

	@PostMapping("/create")
	public AccountDto createAccount(@RequestBody AccountDto AccountDto) {
		return accountService.createAccount(AccountDto);
	}

	@PutMapping("/update/{AccountId}")
	public AccountDto updateAccount(@RequestBody AccountDto AccountDto, @PathVariable String AccountId) {

		return accountService.updateAccount(AccountDto, AccountId);

	}

	@DeleteMapping("/all")
	public void deleteAll() {
		accountService.deleteAll();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAccount(@PathVariable String id) {
		accountService.deleteAccount(id);
	}

}
