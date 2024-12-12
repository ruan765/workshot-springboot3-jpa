package com.educandoWeb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoWeb.course.entities.User;
import com.educandoWeb.course.repositories.UserRepository;
import com.educandoWeb.course.services.exceptions.DataBaseException;
import com.educandoWeb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	public void deleteById(Long Id) {
		try {
			if(userRepository.existsById(Id)) {
				userRepository.deleteById(Id);
			}else {
				throw new ResourceNotFoundException(Id);
			}
	}catch(ResourceNotFoundException e) {
		throw new ResourceNotFoundException(Id);
	}catch(DataIntegrityViolationException e) {
		throw new DataBaseException(e.getMessage());
	}}
	
	public User update(Long id, User obj) {
		User entity = userRepository.getReferenceById(id);
		updateData(entity, obj);
		return userRepository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setNameString(obj.getNameString());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	
	
	

}
