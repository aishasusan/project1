package com.ust.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="authority")
public class Authority {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String authority;
	
	@ManyToMany( mappedBy = "authority", cascade= CascadeType.ALL)
	public List<User> user;

	public Authority() {
		super();
	}
	
	

	public Authority(Integer id, String authority, List<User> user) {
		super();
		this.id = id;
		this.authority = authority;
		this.user = user;
	}



	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		
		if (user != null) {
			user.forEach(add->add.setAuthorityId(this));
        }
		this.user = user;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + ", user=" + user + "]";
	}
	
}
