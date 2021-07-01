package com.ust.user.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer role_id;

	String created_date;

	String update_date;
	
	@ManyToOne
	@JoinColumn(name="authority_id")
	private Authority  authorityId;
	
	public User() {
		super();
	}

	public User(int id, int role_id, String created_date, String update_date, Authority  authorityId) {
		super();
		this.id = id;
		this.role_id = role_id;
		this.created_date = created_date;
		this.update_date = update_date;
		this.authorityId= authorityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public Authority getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Authority authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role_id=" + role_id + ", created_date=" + created_date + ", update_date="
				+ update_date + ", authorityId=" + authorityId + "]";
	}
		
}
