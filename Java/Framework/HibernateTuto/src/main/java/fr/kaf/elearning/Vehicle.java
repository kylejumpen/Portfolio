package fr.kaf.elearning;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="Vehicle_details")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // permet de définir la stratégie d'héritage
@DiscriminatorColumn( // Permet de spécifier le nom de la colonne qui permet d'identifier la classe spécifique et de spécifier son contenu
		name="VEHICLE_TYPE",
		discriminatorType=DiscriminatorType.STRING		)
public class Vehicle {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String brand;
	
	@ManyToOne
	@JoinColumn(name="Owner_id")
	@NotFound(action=NotFoundAction.IGNORE) // Hibernate pour gerer l'absence de relation, il faut préciser l'attribut action pour avoir la finalité
	private User owner;

	public Vehicle(){
		
	}
	
	public Vehicle(int id, String brand) {
		super();
		this.id = id;
		this.brand = brand;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
//	public User getOwner() {
//		return owner;
//	}
//
//	public void setOwner(User owner) {
//		this.owner = owner;
//	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
