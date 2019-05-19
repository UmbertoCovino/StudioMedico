package Amministrazione;

public class DisponibilitaDirector {
	private DisponibilitaBuilder disponibilitaBuilder;

	public DisponibilitaBuilder getDisponibilitaBuilder() {
		return disponibilitaBuilder;
	}

	public void setDisponibilitaBuilder(DisponibilitaBuilder disponibilitaBuilder) {
		this.disponibilitaBuilder = disponibilitaBuilder;
	}

	public static Disponibilita buildPart() {
		return null;
	}
}
