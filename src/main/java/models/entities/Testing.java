package models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Testing", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Testing  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true, length=10)
	private String id;	
	
	@Column(name="name", length=100, nullable=false)
	private String name;
	
	@Column(name="date_start", length=50, nullable=true)
	private String date_start;
	
	@Column(name="date_end", length=50, nullable=true)
	private String date_end;	
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getDateStart() {
		return date_start;
	}
	
	public String getDateEnd() {
		return date_end;
	}

	@Override
	public String toString() {
		return "id=" + this.id + ", name=" + this.name + ", date_start=" + this.date_start + ", date_end=" + this.date_end;
	}

}
