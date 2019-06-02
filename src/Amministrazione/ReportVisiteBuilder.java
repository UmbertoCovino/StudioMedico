package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportVisiteBuilder extends ReportBuilder {
	ReportVisite report;

	public Report buildPart() {
		return report;
	}

	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		report.setTipologia(tipologiaReport);
		while(rs.next()) {
			this.report.addRiga(new RigaVisite(rs.getDate("giorno"), rs.getDate("ora"), rs.getString("nome_tipologia_visita"), rs.getString("nome_medico"), rs.getString("nome_paziente")));
		}
		return buildPart();
	}
}
