package tn.esprit.boostra.entity;

import org.springframework.security.core.GrantedAuthority;
public enum RoleName implements GrantedAuthority {
	employe,moderator,administrator,organizer;
	@Override
	public String getAuthority() {
	return "ROLE_" + name();
	}
}