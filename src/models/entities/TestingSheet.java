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

	public TestingSheet() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "testing_id", insertable=false, updatable=false)
	private int testingId;

	@Column(name = "runner")
	private String runner;

	@Column(name = "tc_status", nullable=true)
	private String tcStatus;

	@Column(name = "duration")
	private int tduration;

	@Column(name = "comment", nullable=true)
	private String comment;

	@Column(name = "softdev", nullable=true)
	private int softdev;

	@Column(name = "tqc_ver", nullable=true)
	private String tqcVer;

	@Column(name = "lab_ver", nullable=true)
	private String labVer;

	@Column(name = "gene_ver", nullable=true)
	private String geneVer;
	
	@ManyToOne
	@JoinColumn(name="testing_id", nullable=false)
	private Testing testing;
	
	@OneToOne
	@JoinColumn(name = "strg_tc_id", nullable = false, updatable=false)
	private StorageTC storageTC;

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestingId() {
		return testingId;
	}

	public void setTestingId(int testingId) {
		this.testingId = testingId;
	}

	public String getRunner() {
		return runner;
	}

	public void setRunner(String runner) {
		this.runner = runner;
	}

	public String getTcStatus() {
		return tcStatus;
	}

	public void setTcStatus(String tcStatus) {
		this.tcStatus = tcStatus;
	}

	public int getDuration() {
		return tduration;
	}

	public void setDuration(int duration) {
		this.tduration = duration;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getSoftdev() {
		return softdev;
	}

	public void setSoftdev(int softdev) {
		this.softdev = softdev;
	}

	public String getTqcVer() {
		return tqcVer;
	}

	public void setTqcVer(String tqcVer) {
		this.tqcVer = tqcVer;
	}

	public String getLabVer() {
		return labVer;
	}

	public void setLabVer(String labVer) {
		this.labVer = labVer;
	}

	public String getGeneVer() {
		return geneVer;
	}

	public void setGeneVer(String geneVer) {
		this.geneVer = geneVer;
	}

	public StorageTC getStorageTC() {
		return storageTC;
	}

	public void setStorageTC(StorageTC storageTC) {
		this.storageTC = storageTC;
	}
}