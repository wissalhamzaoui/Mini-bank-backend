package com.example.bank.user.service;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.user.domain.User;
import com.example.bank.user.domain.UserRepository;@Service

public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	public UserDto createUser(UserDto UserDto) {
		User savedUser = userRepository.save(userMapper.mapToDomain(UserDto));
		return userMapper.mapToDto(savedUser);
	}

	public List<UserDto> getAll() {
		return userRepository.findAll().stream().map(c -> userMapper.mapToDto(c)).collect(Collectors.toList());
	}
	
	public UserDto getUser ( String id) {
		return userRepository.findById(id).map(c -> userMapper.mapToDto(c))
				.orElseThrow(() -> new IllegalArgumentException(String.format("User not found with Id: %s", id)));
	}
	
	public UserDto getLogin ( String email, String password) {
		User c= new User();
		c = userRepository.findByEmailAndPassword(email, password);
		if(c == null) {
            throw new InvalidParameterException("invalid email or password");
        }		
		return userMapper.mapToDto(c);
	}
	
	public UserDto updateUser( UserDto UserDto,  String  UserId) {

	return userRepository.findById(UserId).map(c -> {
		userMapper.mapToDomain(UserDto, c);// MAP TO DOMAIN
		userRepository.save(c);// UPDATE ENTITY
		return userMapper.mapToDto(c);// RETURN DTO
	}).orElseThrow(() -> new IllegalArgumentException(String.format("User not found with Id: %s", UserId)));
	}
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
	
	public Integer NumberOfUsers(){
		return userRepository.findAll().size();
	}

	public List<UserDto> getAllClients() {
		return userRepository.findAll().stream().filter(c -> c.getRole().equals("Client")).map(c -> userMapper.mapToDto(c)).collect(Collectors.toList());

	}


}
