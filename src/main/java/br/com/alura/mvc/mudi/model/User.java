package br.com.alura.mvc.mudi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 20, nullable = false)
	private String username;
	@Column(length = 100, nullable = false)
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"))
	@Column(name = "role_id")
	private List<String> roles = new ArrayList<>();
	
	public User() {	}

	public User(String username) {
		super();
		this.username = username;
	}
	
}
