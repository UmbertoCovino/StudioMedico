package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMediciBuilder extends ReportBuilder {
	ReportMedici report;

	public Report buildPart() {
		return report;
	}

	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		report = new ReportMedici();
		report.setTipologia(tipologiaReport);
		while(rs.next()) {
			this.report.addRiga(new RigaMedici(rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getInt("codice"), rs.getString("nome_specializzazione"), rs.getInt("num_visite")));
		}
		return buildPart();
	}
}
