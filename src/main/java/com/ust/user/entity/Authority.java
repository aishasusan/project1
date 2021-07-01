package com.ust.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String authority;
	
//	@ManyToMany( mappedBy = "authority", cascade= CascadeType.ALL)
//	public List<User> user;

	public Authority() {
		super();
	}
	

	public Authority(Integer id, String authority) {
	super();
	this.id = id;
	this.authority = authority;
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


	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}
}
