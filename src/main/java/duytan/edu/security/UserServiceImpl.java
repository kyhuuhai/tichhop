package duytan.edu.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import duytan.edu.entity.UserEntity;
import duytan.edu.service.UserEntityManager;

public class UserServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserEntityManager userManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userManager.getUserByUserName(username);
		if (userEntity == null)
			throw new UsernameNotFoundException("No such user: " + username);
		else {
			Account acc = new Account(userEntity);
			logger.info("========== User Info: {}", acc.toString());
			return acc;
		}
	}

}
