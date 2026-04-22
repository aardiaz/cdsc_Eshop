package com.cdsc.eshopdemo.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cdsc.eshopdemo.dto.PageableResponse;
import com.cdsc.eshopdemo.dto.UserDto;
import com.cdsc.eshopdemo.entity.User;
import com.cdsc.eshopdemo.repository.UserRepository;
import com.cdsc.eshopdemo.service.UserService;
import com.cdsc.eshopdemo.utils.Helper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper  modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		 //convert DTO to Entity
		 User userEntity = modelMapper.map(userDto, User.class);
		 userEntity = userRepository.save(userEntity);
		 //convert and return DTO
		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public void deleteUser(String userId) {

		userRepository.deleteById(userId);
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		 
		User  user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		
		//User userEntity  = modelMapper.map(userDto, User.class);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhone(userDto.getPhone());
		user.setUserId(userId); // Ensure the user ID is set for the update
		user.setGender(userDto.getGender());
		
		User userEntity= userRepository.save(user);
		
		//userEntity.setUserId(userId); // Ensure the user ID is set for the update
		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
//        pageNumber default starts from 0
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        
        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

        return response;
	}

	@Override
	public UserDto getUserById(String userId) {
		 
		User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		return modelMapper.map(u, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		
		 User u = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email: " + email));
		
		return modelMapper.map(u, UserDto.class);
	}

	@Override
	public List<UserDto> searchUsersByName(String name) {
		
		List<User> ulist = userRepository.findByNameContainingIgnoreCase(name);
//		List<UserDto> userDtoList = new ArrayList<>();
//		for (User u : ulist) {
//		          UserDto userDto = modelMapper.map(u, UserDto.class);  	
//		          userDtoList.add(userDto);
//		        }
		
		  List<UserDto> userDtoList= ulist.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
		
		return userDtoList;
	}
	
	

}
