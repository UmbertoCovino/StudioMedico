package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ReportVisitePerMedicoBuilder extends ReportBuilder {
	ReportVisitePerMedico report;

	public Report buildPart() {
		return report;
	}

	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		report = new ReportVisitePerMedico();
		report.setTipologia(tipologiaReport);
		do {
			this.report.addRiga(new RigaVisitePerMedico(rs.getDate("giorno"), rs.getTime("ora", Calendar.getInstance()), rs.getString("nome_tipologia_visita"), rs.getString("nome_paziente")));
		} while(rs.next());
		return buildPart();
	}
}
