package models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Storage")
public class StorageTC  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
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
	
	@Column(name = "test_set_id")
	private int testSetId;
	
	public int getIsLab() {
		return isLab;
	}

	public void setIsLab(int isLab) {
		this.isLab = isLab;
	}

	public int getIsGene() {
		return isGene;
	}

	public void setIsGene(int isGene) {
		this.isGene = isGene;
	}

	@Column(name = "is_lab")
	private int isLab;
	
	@Column(name = "is_gene")
	private int isGene;
	
	public int getTestSetId() {
		return testSetId;
	}

	public void setTestSetId(int testSetId) {
		this.testSetId = testSetId;
	}

	@Column(name="run_parameters", length=50, nullable=true)
	private String run_param;
	
	@Column(name="run_path", length=500, nullable=true)
	private String run_path;

	@OneToOne
	@JoinColumn(name = "test_set_id", nullable = false , insertable = false, updatable = false)
	private TestSet testSet;

	public TestSet getTestSet() {
		return testSet;
	}

	public void setTestSet(TestSet testSet) {
		this.testSet = testSet;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getRun_param() {
		return run_param;
	}

	public void setRun_param(String run_param) {
		this.run_param = run_param;
	}

	public String getRun_path() {
		return run_path;
	}

	public void setRun_path(String run_path) {
		this.run_path = run_path;
	}

}
