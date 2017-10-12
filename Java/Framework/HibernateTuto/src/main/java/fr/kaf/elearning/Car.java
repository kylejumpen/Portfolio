package fr.kaf.elearning;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle {

	public String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
}
