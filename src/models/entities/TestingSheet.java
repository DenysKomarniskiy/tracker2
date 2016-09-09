package models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Testing_sheet")
public class TestingSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "testing_id", insertable = false, updatable = false)
	private int testingId;

	@Column(name = "runner")
	private String runner;

	@Column(name = "tc_status", insertable = false)
	private String tcStatus;

	@Column(name = "duration")
	private int tduration;

	@Column(name = "comment", nullable = true, insertable = false)
	private String comment;

	@Column(name = "softdev", nullable = true, insertable = false)
	private Integer softdev;

	@Column(name = "tqc_ver", nullable = true, insertable = false)
	private String tqcVer;

	@Column(name = "lab_ver", nullable = true, insertable = false)
	private String labVer;

	@Column(name = "gene_ver", nullable = true, insertable = false)
	private String geneVer;

	@ManyToOne
	@JoinColumn(name = "testing_id")
	private Testing testing;

	@OneToOne
	@JoinColumn(name = "strg_id", nullable = false)
	private StorageTC storageTC;

	@Column(name = "fail_info", nullable = true, insertable = false)
	private String failInfo;

	@OneToOne
	@JoinColumn(name = "env_id", nullable = true, insertable = false)
	private Env env;

	public void setEnv(Env env) {
		this.env = env;
	}

	public Env getEnv() {
		return env;
	}

	public String getComment() {
		return comment;
	}

	public int getDuration() {
		return tduration;
	}

	public String getFailInfo() {
		return failInfo;
	}

	public String getGeneVer() {
		return geneVer;
	}

	public int getId() {
		return id;
	}

	public String getLabVer() {
		return labVer;
	}

	public String getRunner() {
		return runner;
	}

	public int getSoftdev() {
		return softdev;
	}

	public StorageTC getStorageTC() {
		return storageTC;
	}

	public String getTcStatus() {
		return tcStatus;
	}

	public Testing getTesting() {
		return testing;
	}

	public int getTestingId() {
		return testingId;
	}

	public String getTqcVer() {
		return tqcVer;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDuration(int duration) {
		this.tduration = duration;
	}

	public void setFailInfo(String failInfo) {
		this.failInfo = failInfo;
	}

	public void setGeneVer(String geneVer) {
		this.geneVer = geneVer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLabVer(String labVer) {
		this.labVer = labVer;
	}

	public void setRunner(String runner) {
		this.runner = runner;
	}

	public void setSoftdev(int softdev) {
		this.softdev = softdev;
	}

	public void setStorageTC(StorageTC storageTC) {
		this.storageTC = storageTC;
	}

	public void setTcStatus(String tcStatus) {
		this.tcStatus = tcStatus;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

	public void setTestingId(int testingId) {
		this.testingId = testingId;
	}

	public void setTqcVer(String tqcVer) {
		this.tqcVer = tqcVer;
	}
}