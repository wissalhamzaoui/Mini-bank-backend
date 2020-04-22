package com.example.bank.user.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmailAndPassword(String email, String password);

}
