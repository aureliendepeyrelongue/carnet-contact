package com.lip6.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Contacts")
public class Contact implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3586181024269191105L;
	
	private String firstName;
	private String lastName;
	private String email;
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade=CascadeType.PERSIST,orphanRemoval=true)
	@JoinColumn(name="id_adress")
	private Address address;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="contact", fetch = FetchType.EAGER, orphanRemoval = true)
	Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="CTC_GRP",
	joinColumns=@JoinColumn(name="CTC_ID"),
	inverseJoinColumns=@JoinColumn(name="GRP_ID"))
	private Set<ContactGroup> contactGroups=new HashSet<>();
	
	public Contact(){
	}

	public Contact(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	 
	public Contact(String firstName, String lastName, String email, Address adress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = adress;
	}
	
	public Contact(String firstName, String lastName, String email, Address adress, Set<PhoneNumber> phones) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = adress;
		this.phones = phones;
	}


	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	public void addPhoneNumber(PhoneNumber phone) {
		this.phones.add(phone);
	}
	
	public void removePhoneNumber(PhoneNumber phone) {
		this.phones.remove(phone);
	}

	public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}
	
	
	
}
