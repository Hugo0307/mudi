package br.com.alura.mvc.mudi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Component
public class StartApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User userAdmin = userRepository.findByUsername("admin");
		if (userAdmin == null) {
			userAdmin = new User();
			userAdmin.setNome("ADMINISTRADOR");
			userAdmin.setUsername("admin");
			userAdmin.setPassword("master123");
			userAdmin.getRoles().add("ADMINS");
			userRepository.save(userAdmin);
		}
		
		User user = userRepository.findByUsername("user");
		if (user == null) {
			user = new User();
			user.setNome("USUARIO");
			user.setUsername("user");
			user.setPassword("user123");
			user.getRoles().add("USERS");
			userRepository.save(user);
		}
	}
	
	

}
