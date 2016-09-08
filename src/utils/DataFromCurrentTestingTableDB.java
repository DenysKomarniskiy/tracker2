package utils;

public class DataFromCurrentTestingTableDB {
	private Long countPassedTC;
	private Long countFailedTC;
	private Long countWaitingTC;
	private Long countCorrectionTC;
	private Long countEmptyTC;
	private Long countNoNeedTC;
	private Long countTotalTC;
	private Long durationTotalTC;
	private Long countInvestigatingTC;
	public Long getCountPassedTC() {
		return countPassedTC;
	}
	public void setCountPassedTC(Long tuple) {
		this.countPassedTC = tuple;
	}
	
	public Long getCountInvestigatingTC() {
		return countInvestigatingTC;
	}
	public void setCountInvestigatingTC(Long countInvestigatingTC) {
		this.countInvestigatingTC = countInvestigatingTC;
	}
	public Long getCountFailedTC() {
		return countFailedTC;
	}
	public void setCountFailedTC(Long tuple) {
		this.countFailedTC = tuple;
	}
	public Long getCountWaitingTC() {
		return countWaitingTC;
	}
	public void setCountWaitingTC(Long countWaitingTC) {
		this.countWaitingTC = countWaitingTC;
	}
	public Long getCountCorrectionTC() {
		return countCorrectionTC;
	}
	public void setCountCorrectionTC(Long countCorrectionTC) {
		this.countCorrectionTC = countCorrectionTC;
	}
	public Long getCountEmptyTC() {
		return countEmptyTC;
	}
	public void setCountEmptyTC(Long countEmptyTC) {
		this.countEmptyTC = countEmptyTC;
	}
	public Long getCountNoNeedTC() {
		return countNoNeedTC;
	}
	public void setCountNoNeedTC(Long countNoNeedTC) {
		this.countNoNeedTC = countNoNeedTC;
	}
	public Long getCountTotalTC() {
		return countTotalTC;
	}
	public void setCountTotalTC(Long countTotalTC) {
		this.countTotalTC = countTotalTC;
	}
	public Long getDurationTotalTC() {
		return durationTotalTC;
	}
	public void setDurationTotalTC(Long durationTotalTC) {
		this.durationTotalTC = durationTotalTC;
	}
	
	
	
}
