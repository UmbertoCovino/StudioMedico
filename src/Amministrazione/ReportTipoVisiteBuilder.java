package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportTipoVisiteBuilder extends ReportBuilder {
	ReportTipoVisite report;

	public Report buildPart() {
		return report;
	}

	public Report createRows(String tipologiaReport, ResultSet rs) throws SQLException {
		report.setTipologia(tipologiaReport);
		while(rs.next()) {
			this.report.addRiga(new RigaTipoVisite(rs.getString("nome"), rs.getFloat("prezzo_fisso"), rs.getFloat("costo_manodopera"), rs.getFloat("costo_esercizio"), rs.getInt("num_visite")));
		}
		return buildPart();
	}
}
