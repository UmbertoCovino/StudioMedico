package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

import GUI.FormCreazioneReport;

public class ReportDirector {
	public static Report buildPart(ReportBuilder builder, ResultSet rs) throws SQLException {
		if(builder instanceof ReportVisiteBuilder) {
			builder.createRows(FormCreazioneReport.TIPOLOGIE_REPORT[0], rs);
		}
		if(builder instanceof ReportVisitePerMedicoBuilder) {
			builder.createRows(FormCreazioneReport.TIPOLOGIE_REPORT[1], rs);
		}
		if(builder instanceof ReportMediciBuilder) {
			builder.createRows(FormCreazioneReport.TIPOLOGIE_REPORT[2], rs);
		}
		if(builder instanceof ReportTipoVisiteBuilder) {
			builder.createRows(FormCreazioneReport.TIPOLOGIE_REPORT[3], rs);
		}
		
		return builder.buildPart();
	}
}
