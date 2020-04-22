package service;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;


import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;
import model.User;
import model.UserRepository;@Service

public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GridFsTemplate gridFsTemplate;

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
	
	public String uploadPicture(MultipartFile file) throws IOException {
		return gridFsTemplate.store(file.getResource().getInputStream(), file.getResource().getFilename()).toString();
	}
	
	public GridFSFile getPicture(String id) {
		return gridFsTemplate.findOne(Query
				.query((Criteria.where("_id").is(id))));
	}

	public Integer NumberOfUsers(){
		return userRepository.findAll().size();
	}

	public List<UserDto> getAllClients() {
		return userRepository.findAll().stream().filter(c -> c.getRole().equals("Client")).map(c -> userMapper.mapToDto(c)).collect(Collectors.toList());

	}


}
