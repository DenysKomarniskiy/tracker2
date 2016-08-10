package models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Storage", uniqueConstraints = { @UniqueConstraint(columnNames = { "tc_id" }) })
public class StorageTC  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tc_id", nullable=false, unique=true, length=50)
	private String tc_id;	
	
	@Column(name="author", length=100, nullable=false)
	private String author;
	
	@Column(name="step_num", length=10, nullable=false)
	private int step_num;
		
	@Column(name="duration", length=50, nullable=false)
	private int duration;	
	
	@Column(name="auto_ide", length=50, nullable=true)
	private String auto_ide;
	
	@Column(name="apps", length=50, nullable=true)
	private String apps;
	
	@Column(name="features", length=500, nullable=true)
	private String features;
	
	@Column(name="tags", length=500, nullable=true)
	private String tags;
	
	@OneToOne
	@JoinColumn(name = "test_set_id", nullable = false)
	private TestSet testSet;

	public TestSet getTestSet() {
		return testSet;
	}

	public void setTestSet(TestSet testSet) {
		this.testSet = testSet;
	}

	public String getTc_id() {
		return tc_id;
	}

	public void setTc_id(String tc_id) {
		this.tc_id = tc_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStep_num() {
		return step_num;
	}

	public void setStep_num(int step_num) {
		this.step_num = step_num;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAuto_ide() {
		return auto_ide;
	}

	public void setAuto_ide(String auto_ide) {
		this.auto_ide = auto_ide;
	}

	public String getApps() {
		return apps;
	}

	public void setApps(String apps) {
		this.apps = apps;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


}
