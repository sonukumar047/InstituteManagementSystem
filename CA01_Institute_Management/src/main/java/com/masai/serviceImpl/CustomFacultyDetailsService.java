package com.masai.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.model.Faculty;
import com.masai.repository.FacultyRepository;

@Service
public class CustomFacultyDetailsService implements UserDetailsService{
	
	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Faculty> opt = facultyRepository.findByEmail(username);
		System.out.println("HIi");
		System.out.println(opt);
		if (opt.isEmpty())
			throw new UsernameNotFoundException("User with" + username + "Not found");
		else {
			Faculty faculty = opt.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(null));

			return new User(faculty.getEmail(), faculty.getPassword(), authorities);
		}
	}

}
