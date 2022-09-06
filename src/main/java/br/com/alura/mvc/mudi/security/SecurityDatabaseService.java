package br.com.alura.mvc.mudi.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Service
public class SecurityDatabaseService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new RuntimeException("Usuário não encontrado: " + username);
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		userEntity.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		UserDetails userDetails = 
				new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
						userEntity.getPassword(),
						authorities);
		return userDetails;
	}
	
	

}
