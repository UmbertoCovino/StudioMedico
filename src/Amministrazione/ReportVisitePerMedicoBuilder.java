package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportVisitePerMedicoBuilder extends ReportBuilder {
	ReportVisitePerMedico report;

	public Report buildPart() {
		return report;
	}

	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		report.setTipologia(tipologiaReport);
		while(rs.next()) {
			this.report.addRiga(new RigaVisitePerMedico(rs.getDate("giorno"), rs.getDate("ora"), rs.getString("nome_tipologia_visita"), rs.getString("nome_paziente")));
		}
		return buildPart();
	}
}
