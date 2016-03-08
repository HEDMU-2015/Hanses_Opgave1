package opgave1;

import java.time.LocalDate;

public class Container {
	
	private int id;
	private LocalDate dateOfArrival;
	private LocalDate pickupDate;
	
	public Container(int id, LocalDate dateOfArrival, LocalDate pickupDate) {
		this.id = id;
		this.dateOfArrival = dateOfArrival;
		this.pickupDate = pickupDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateOfArrival() {
		return dateOfArrival;
	}

	public void setDateOfArrival(LocalDate dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}

	public LocalDate getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(LocalDate pickupDate) {
		this.pickupDate = pickupDate;
	}

	@Override
	public String toString() {
		return "Container [id=" + id + ", dateOfArrival=" + dateOfArrival + ", pickupDate=" + pickupDate + "]";
	}
	
	
	
}
