package com.comit.notjpa.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comit.notjpa.config.CustomUserDetails;
import com.comit.notjpa.dao.RoleDao;
import com.comit.notjpa.dao.UserDao;
import com.comit.notjpa.dto.RegisterDto;
import com.comit.notjpa.dto.UserDto;
import com.comit.notjpa.entities.Role;
import com.comit.notjpa.entities.User;



@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	public void saveUser(User user) {
		User user1 = new User();
		user1.setUsername(user.getUsername());

		// encrypt the password using spring security
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleDao.findByName("ROLE_ADMIN");
		if (role == null) {
			role = checkRoleExist();
		}

		user1.setRoles(Arrays.asList(role));
		userDao.save(user1);
	}

	
	public List<UserDto> findAllUsers() {
		List<User> users = userDao.findAll();
		return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
	}

	
	public String getUsername() {
		Object userCurrent = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (userCurrent instanceof CustomUserDetails) {
			username = ((CustomUserDetails) userCurrent).getUsername();
		} else {
			username = userCurrent.toString();
		}

		return username;
	}

	
	public void updateUserProfile(UserDto userDto) {

		User user = userDao.findById(userDto.getId());
		if (user != null) {
			user.setUsername(userDto.getUsername());
			user.setFullName(userDto.getFullName());
			user.setPhoneNumber(userDto.getPhoneNumber());
			user.setEmail(userDto.getEmail());
			this.userDao.save(user);
		}

//      Update username for UserDetail in Security
		/*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof CustomUserDetails customUserDetails) {
			customUserDetails.setUsername(userDto.getUsername());
		}*/

	}

	
	public UserDto getUserProfileByUsername() {
		User user = findUserByUsername(getUsername());

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setEmail(user.getEmail());

		return userDto;
	}

	
	public UserDto getUserProfileByUsername(String name) {
		User user = findUserByUsername(name);

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setEmail(user.getEmail());

		return userDto;
	}

	private UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setFullName(user.getFullName());
		userDto.setEmail(user.getEmail());
		userDto.setRoles(user.getRoles());
		return userDto;
	}

	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleDao.save(role);
	}

	
	public User findUserByUsername(String username) {
		User user = this.userDao.findByUsername(username);
		if (user == null) {
			return null;
		}
		return user;
	}

	public List<Role> listRoles() {
		return roleDao.findAll();
	}

	public void save(UserDto userDto) {
		User user = new User();
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setFullName(userDto.getFullName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setRoles(userDto.getRoles());
		user.setUsername(userDto.getUsername());
		userDao.save(user);
	}

	
	public long deleteUserById(long id) {
		userDao.deleteById(id);
		return id;
	}

	
	public User get(Long id) {
		return userDao.findById(id);
	}

	public void update(UserDto userDto, long id) {
		User user = userDao.findById(id);
		if (userDto.getPassword() != "") {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setFullName(userDto.getFullName());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setPhoneNumber(userDto.getPhoneNumber());
		userDao.save(user);
	}

	
	public long restoreUserById(long id) {
		User user = userDao.findById(id);
		user.setDeleted(false);
		userDao.save(user);
		return id;
	}

	
	public List<User> findAll() {
		return userDao.findAll();
	}

	
	public long trashUserById(long id) {
		User user = userDao.findById(id);
		user.setDeleted(true);
		userDao.save(user);
		return id;
	}

	
	public boolean fieldValueExists(String username, long id) throws UnsupportedOperationException {
		User existingUser = this.findUserByUsername(username);
		if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
			if (id != existingUser.getId()) {
				return true;
			}
		}
		return false;

	}


	@Transactional
	public void register(RegisterDto registerDto) {
		Role userRole = roleDao.findByName("ROLE_USER");
		List<Role> roles = Arrays.asList(userRole);
		User user = new User();
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setEmail(registerDto.getEmail());
		user.setRoles(roles);
		user.setUsername(registerDto.getUserName());
		userDao.save(user);
		
	}

}
