package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails{
	
	//versão da classe que estamos trabalhando
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	//verifica as autorizações que usuário tem (abas, funções que ele tem acesso)
	private List<GrantedAuthority> authorities;
	

	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() {}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// autorizações de acesso do usuário
		return authorities;
	}

	@Override
	public String getPassword() {
		// contém a informação de senha do usuário
		return password;
	}

	@Override
	public String getUsername() {
		// contém a informação de nome do usuário
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// se a conta do usuario não estiver expirada, autorizar acesso
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// se a conta do usuario não estiver bloqueada, autorizar acesso
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// se a credencial do usuario não estiver expirada, autorizar acesso
		return true;
	}

	@Override
	public boolean isEnabled() {
		// se a o usuario estiver habilitado (loggado), autorizar acesso
		return true;
	}
	
	
}
