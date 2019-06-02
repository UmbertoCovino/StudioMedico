package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDirector {
	public static Report buildPart(ReportBuilder builder, ResultSet rs) throws SQLException {
		if(builder instanceof ReportMediciBuilder) {
			builder.createRows("Elenco dei medici ordinati per numero di visite", rs);
		}
		if(builder instanceof ReportTipoVisiteBuilder) {
			builder.createRows("Elenco delle tipologie di visite ordinate per numerosita", rs);
		}
		if(builder instanceof ReportVisiteBuilder) {
			builder.createRows("Elenco di visite effettuare ordinate per medico", rs);
		}
		if(builder instanceof ReportVisitePerMedicoBuilder) {
			builder.createRows("Elenco di visite effettuare ordinate per giorno con indicazione del medico", rs);
		}
		
		return builder.buildPart();
	}
}
