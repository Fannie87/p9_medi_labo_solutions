package com.openclassrooms.medi_labo.front.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Value("${security.user}")
	private String user;

	@Value("${security.password}")
	private String password;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		UserDetails userDetails = (UserDetails) new User(user, new BCryptPasswordEncoder().encode(password),
				Arrays.asList(authority));
		return userDetails;
	}
}