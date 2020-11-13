package entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;




@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer JobId;
	private String Title;
	private String Description;
	private String Category;
	private Integer EducationLV;
	private Integer ExpYear;
	private BigInteger Luong;
	private Integer OwnerId;

	
	
	
	public Integer getOwnerId() {
		return OwnerId;
	}
	public void setOwnerId(Integer ownerId) {
		OwnerId = ownerId;
	}
	
	public Job(Integer jobId, String title, String description, String category, Integer educationLV, Integer expYear,
			BigInteger luong, Integer ownerId) {
		super();
		JobId = jobId;
		Title = title;
		Description = description;
		Category = category;
		EducationLV = educationLV;
		ExpYear = expYear;
		Luong = luong;
		OwnerId = ownerId;
	}
	public Job() {}

	
	public Job(String title, String description, String category, Integer educationLV, Integer expYear,
			BigInteger luong, Integer ownerId) {
		super();
		Title = title;
		Description = description;
		Category = category;
		EducationLV = educationLV;
		ExpYear = expYear;
		Luong = luong;
		OwnerId = ownerId;
	}
	public BigInteger getLuong() {
		return Luong;
	}
	public void setLuong(BigInteger luong) {
		Luong = luong;
	}
	public Integer getJobId() {
		return JobId;
	}
	public void setJobId(Integer jobId) {
		JobId = jobId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public Integer getEducationLV() {
		return EducationLV;
	}
	public void setEducationLV(Integer educationLV) {
		EducationLV = educationLV;
	}
	public Integer getExpYear() {
		return ExpYear;
	}
	public void setExpYear(Integer expYear) {
		ExpYear = expYear;
	}
	
	
	
	
}
