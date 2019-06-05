package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class DisponibilitaDirector {
	public static Object buildPart(DisponibilitaBuilder builder, ResultSet rs) throws SQLException {
		if(builder instanceof DisponibilitaGiornalieraBuilder) {
			builder.setDisponibilita(rs.getTime("ora_inizio", Calendar.getInstance()), rs.getTimestamp("ora_fine", Calendar.getInstance()), rs.getInt("max_num_visite"));
			builder.setGiorno(rs.getDate("giorno"), false);
		}
		return builder.buildPart();
	}
}
