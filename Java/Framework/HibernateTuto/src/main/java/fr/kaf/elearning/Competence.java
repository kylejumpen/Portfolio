package fr.kaf.elearning;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Skills")
public class Competence {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@Lob
	private String description;
	
	@ManyToMany
	@JoinTable(name="User_Skill",  
	joinColumns=@JoinColumn(name="Skill_id"),
	inverseJoinColumns=@JoinColumn(name="User_id"))
	private Collection<User> smartPeople = new ArrayList<>();
	
	public Competence(){
		
	}
	
	public Competence(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<User> getSmartPeople() {
		return smartPeople;
	}

	public void setSmartPeople(Collection<User> smartPeople) {
		this.smartPeople = smartPeople;
	}
}
