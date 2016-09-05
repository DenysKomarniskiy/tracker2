package models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Testing", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Testing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true, length = 10)
	private int id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "date_start", length = 50, nullable = true)
	private String date_start;

	@Column(name = "date_end", length = 50, nullable = true)
	private String date_end;
	
	@OneToMany(mappedBy = "testing")
	private List<TestingSheet> testingSheet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public List<TestingSheet> getTestingSheet() {
		return testingSheet;
	}

	public void setTestingSheet(List<TestingSheet> testingSheet) {
		this.testingSheet = testingSheet;
	}

	@Override
	public String toString() {
		return "id=" + this.id + ", name=" + this.name + ", date_start=" + this.date_start + ", date_end=" + this.date_end;
	}

}
