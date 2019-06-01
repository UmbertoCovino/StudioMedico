package Amministrazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisponibilitaDirector {
	public static Object buildPart(DisponibilitaBuilder builder, ResultSet rs) throws SQLException {
		if(builder instanceof DisponibilitaGiornalieraBuilder) {
			builder.setDisponibilita(rs.getDate("ora_inizio"), rs.getDate("ora_fine"), rs.getInt("max_num_visite"));
			builder.setGiorno(rs.getDate("giorno"), false);
		}
		return builder.buildPart();
	}
}
