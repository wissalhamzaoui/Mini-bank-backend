package com.example.bank.account.service;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.bank.account.domain.Account;
@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	AccountDto mapToDto(Account entity);

	Account mapToDomain(AccountDto dto);
	
	void mapToDomain(AccountDto dto, @MappingTarget Account entity);
}
