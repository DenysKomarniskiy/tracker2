package models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class User {

	@Id
	@Column(name = "id", nullable = false, unique = true, length = 10)
	private String id;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "full_name", length = 50, nullable = true)
	private String full_name;

	@Column(name = "sd_enc_pass", length = 50, nullable = true)
	private String sdEncPass;

	@Column(name = "department", length = 50, nullable = true)
	private String department;

	@Column(name = "active", length = 50, nullable = true)
	private int active;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getSdEncPass() {
		return sdEncPass;
	}

	public void setSdEncPass(String sdEncPass) {
		this.sdEncPass = sdEncPass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFullname(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getFullname() {
		return full_name;
	}

	@Override
	public String toString() {
		return full_name;
	}

}
