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
	
	public Company() {}
	public Company(String location, String description) {
		super();
		Location = location;
		Description = description;
	}
	public Company(Integer compId, String location, String description) {
		super();
		CompId = compId;
		Location = location;
		Description = description;
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
