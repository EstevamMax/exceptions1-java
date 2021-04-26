package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn, checkOut;
	
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); //Pega os valores em milissegundos e subtrai
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //Converte esses milissegundos em dias e retorna o resultado
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) { //verifica se as datas fornecidas são de antes da data do sistema
			return "Reservation dates for update must be future";
		}
		if(!checkOut.after(checkIn)) {
			return "Check-Out date must be after check-In date";
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null; //Caso o resultado seja nulo não ocorreu nenhum erro
	}
	
	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut + ", " + duration() + " nights");
	}
	
}
