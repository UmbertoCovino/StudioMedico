package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ReportBuilder {
	public abstract Report buildPart();
	
	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		return buildPart();
	}
}
