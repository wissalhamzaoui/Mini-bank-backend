package service;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import model.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

	UserDto mapToDto(User entity);

	User mapToDomain(UserDto dto);
	
	void mapToDomain(UserDto dto, @MappingTarget User entity);
}
