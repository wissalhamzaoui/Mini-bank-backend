package service;

import org.mapstruct.Mapper;
import model.Account;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface AccountMapper {
	AccountDto mapToDto(Account entity);

	Account mapToDomain(AccountDto dto);
	
	void mapToDomain(AccountDto dto, @MappingTarget Account entity);
}
