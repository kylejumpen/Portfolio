package fr.kaf.elearning;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity // Par défaut hibernate adopte la singletable strategy
@DiscriminatorValue("MotorCycle") // override la valeur par défaut donné qui est le nom de la classe
public class Motto extends Vehicle {

	public String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
