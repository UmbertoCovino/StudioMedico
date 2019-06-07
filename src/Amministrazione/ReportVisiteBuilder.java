package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ReportVisiteBuilder extends ReportBuilder {
	ReportVisite report;

	public Report buildPart() {
		return report;
	}

	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		report = new ReportVisite();
		report.setTipologia(tipologiaReport);
		while(rs.next()) {
			this.report.addRiga(new RigaVisite(rs.getDate("giorno"), rs.getTime("ora", Calendar.getInstance()), rs.getString("nome_tipologia_visita"), rs.getString("nome_medico"), rs.getString("nome_paziente")));
		}
		return buildPart();
	}
}
