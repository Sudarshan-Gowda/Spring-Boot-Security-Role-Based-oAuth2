/**
 * 
 */
package com.star.sud.user.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.star.sud.exception.UserExistsException;
import com.star.sud.exception.UserNotFoundException;
import com.star.sud.user.dao.RoleDao;
import com.star.sud.user.dao.UserDao;
import com.star.sud.user.dto.UserDto;
import com.star.sud.user.model.RoleType;
import com.star.sud.user.model.TUser;
import com.star.sud.user.service.UserService;

/**
 * @author Sudarshan
 *
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TUser user = userDao.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Invalid username or password.");

		Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase()))
				.collect(Collectors.toSet());

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

	@Override
	public List<UserDto> getUsers() {
		List<UserDto> userDto = new ArrayList<UserDto>();
		userDao.findAll().iterator().forEachRemaining(user -> userDto.add(user.toUserDto()));
		return userDto;
	}

	@Override
	public UserDto findOne(long id) throws UserNotFoundException {

		TUser tUser = userDao.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User record not found for the id: " + id));

		return tUser.toUserDto();
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public UserDto createUser(UserDto userDto) throws UserExistsException {
		TUser tUser = userDao.findByUsername(userDto.getUsername());
		if (tUser != null)
			throw new UserExistsException("User name already exists!!");

		tUser = userDao.findByEmail(userDto.getEmail());
		if (tUser != null)
			throw new UserExistsException("Email Already Exists!!");

		tUser = new TUser();
		BeanUtils.copyProperties(userDto, tUser);
		tUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

		tUser.setCreatedDate(Calendar.getInstance().getTime());
		tUser.setModifiedDate(Calendar.getInstance().getTime());
		tUser.setStatus('A');

		List<RoleType> roleTypes = new ArrayList<>();
		userDto.getRole().stream().map(role -> roleTypes.add(RoleType.valueOf(role)));
		tUser.setRoles(roleDao.find(userDto.getRole()));
		TUser save = userDao.save(tUser);
		userDto.setUserId(save.getUserId());
		return userDto;
	}

}
