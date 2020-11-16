package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CompId;
	private String Location;
	private String Description;
	private String Name;
	private String Phone;
	
	
	public Company() {}
	public Company(String location, String description,String name, String phone) {
		super();
		Location = location;
		Description = description;
		Name = name;
		Phone = phone;
	}
	public Company(Integer compId, String location, String description, String name, String phone) {
		super();
		CompId = compId;
		Location = location;
		Description = description;
		Name = name;
		Phone = phone;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public Integer getCompId() {
		return CompId;
	}
	public void setCompId(Integer compId) {
		CompId = compId;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
