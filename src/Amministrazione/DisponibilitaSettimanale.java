package Amministrazione;

public class DisponibilitaSettimanale extends Disponibilita {
	private DayOfTheWeek giorno;
	private DayOfTheWeek dayOfTheWeek;
	
	public DayOfTheWeek getGiorno() {
		return giorno;
	}
	
	public void setGiorno(DayOfTheWeek giorno) {
		this.giorno = giorno;
	}
	
	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}
	
	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}
}
