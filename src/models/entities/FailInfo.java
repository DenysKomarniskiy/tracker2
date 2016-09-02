package models.entities;

import java.util.Map;

public class FailInfo {

	private Map<Long, String> steps;
	private String[] issues;

	public Map<Long, String> getSteps() {
		return steps;
	}

	public void setSteps(Map<Long, String> steps) {
		this.steps = steps;
	}

	public String[] getIssues() {
		return issues;
	}

	public void setIssues(String[] issues) {
		this.issues = issues;
	}

}
