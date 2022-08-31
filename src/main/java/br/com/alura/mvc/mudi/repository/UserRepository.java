package br.com.alura.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.mvc.mudi.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = (:username)")
	public User findByUsername(@Param ("username") String username);
}
