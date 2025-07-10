package com.project.cityFlowV2Back.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cityFlowV2Back.entities.User;
import com.project.cityFlowV2Back.repos.UserRepository;


@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User saveOneUser(User newUser) {
		User savedUser = userRepository.save(newUser); // burada ID oluşur
		return savedUser;
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	public User getOneUserByFireBaseId(String userId){
		return userRepository.findByFirebaseUid(userId).orElse(null);
	}
	

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
		
	}
		
}