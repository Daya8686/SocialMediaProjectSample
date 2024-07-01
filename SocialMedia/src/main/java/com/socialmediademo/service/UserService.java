package com.socialmediademo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.socialmediademo.entity.User;

@Component
public class UserService {
	
	private static List<User> userDetails=new ArrayList<>();
	
	private static Integer idCounter=0;
	
	static {
		userDetails.add(new User(++idCounter,"Ravi",LocalDate.now().minusYears(23)));
		userDetails.add(new User(++idCounter,"Mahesh",LocalDate.now().minusYears(33)));
		userDetails.add(new User(++idCounter,"Suresh",LocalDate.now().minusYears(13)));
	}

	public List<User> findAll() {
		return userDetails;
		
	}

	public User addUser(User user) {
		user.setUserId(++idCounter);
		 userDetails.add(user);
		 return user;
		
	}

	public User findUser(Integer id) {
		 User user2 = userDetails.stream().filter(user->user.getUserId().equals(id)).findFirst().orElse(null);
		 System.out.println(user2);
		 return user2;
		
	}

	public boolean removeUser(int id) {
		
		boolean userRemove = userDetails.removeIf(user->user.getUserId()==id);
		
		return userRemove;
	}
	
	

}
