package models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Test_set")
public class TestSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "sd_set", length = 200, nullable = false)
	private String sd_set;

	@Column(name = "local_set", length = 200, nullable = false)
	private String local_set;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSdSet() {
		return sd_set;
	}

	public void setSdSet(String sd_set) {
		this.sd_set = sd_set;
	}

	public String getLocalSet() {
		return local_set;
	}

	public void setLocalSet(String local_set) {
		this.local_set = local_set;
	}
}
