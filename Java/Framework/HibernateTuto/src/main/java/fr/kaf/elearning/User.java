package fr.kaf.elearning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.CascadeType;

@Entity
@NamedQuery(name="User.byId", query="from User where id = ?") // permits to have some default named query
@NamedNativeQuery(name="User.byName", query="select * from User_Details where user_name = ?", resultClass= User.class) // run SQL query to retrieve the object
@Table(name="User_Details")
@SelectBeforeUpdate // hibernate specifique pour dire de vérifier l'état avant de possiblement modifier
public class User {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) //Deuxieme annotation permet de générer la valeur automatiquement
	@Column(name="user_id")
	//@EmbeddedId pour marquer un objet comme id -> cas de clef composites
	private int id;
	
	@Column(name="user_name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	//@Embedded -> indique un obbjet à décomposer directement dans la table
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="USER_ADDRESS",
	joinColumns=@JoinColumn(name="USER_ID"))
	private Collection<Address> address = new ArrayList<>();
	
	@Basic(optional=true)
	@Lob //Large object 
	private String description;
	
	@OneToOne(fetch=FetchType.EAGER,optional=true)
	private User partner;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@OneToMany(cascade={CascadeType.PERSIST},mappedBy="owner")
//	@JoinTable(name="Vehicle_User",  --> le mapped by enleve cette table intermediaire qui devient non nécessaire ici
//			joinColumns=@JoinColumn(name="User_id"),
//	inverseJoinColumns=@JoinColumn(name="Vehicle_id"))
	private Collection<Vehicle> vehicle = new ArrayList<>();
	
	@ManyToMany(mappedBy="smartPeople") //-> dans le cas du manyToMany on met la config dans l'autre entité mappé
	private Collection<Competence> competences = new ArrayList<>();
	
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public User(){
		
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Address> getAddress() {
		return address;
	}

	public void setAddress(Collection<Address> address) {
		this.address = address;
	}

	public User getPartner() {
		return partner;
	}

	public void setPartner(User partner) {
		this.partner = partner;
	}

	public Collection<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Collection<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	public Collection<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Collection<Competence> competences) {
		this.competences = competences;
	}
	
}
