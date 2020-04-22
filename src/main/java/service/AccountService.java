package service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;
import model.Account;
import model.AccountRepository;

@Service

public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private GridFsTemplate gridFsTemplate;

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
	
	public String uploadPicture(MultipartFile file) throws IOException {
		return gridFsTemplate.store(file.getResource().getInputStream(), file.getResource().getFilename()).toString();
	}
	
	public GridFSFile getPicture(String id) {
		return gridFsTemplate.findOne(Query
				.query((Criteria.where("_id").is(id))));
	}

}
