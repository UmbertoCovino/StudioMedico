package Amministrazione;

public class ReportDirector {
	private ReportBuilder reportBuilder;

	public ReportBuilder getReportBuilder() {
		return reportBuilder;
	}

	public void setReportBuilder(ReportBuilder reportBuilder) {
		this.reportBuilder = reportBuilder;
	}

	public static Report buildPart() {
		return null;
	}
}
