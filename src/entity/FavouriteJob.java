package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FavouriteJob implements Serializable {
	@Id
	private Integer JobId;
	@Id
	private Integer UserId;
	
	public FavouriteJob() {}
	
	public FavouriteJob(Integer jobId, Integer userId) {
		super();
		JobId = jobId;
		UserId = userId;
	}
	public Integer getJobId() {
		return JobId;
	}
	public void setJobId(Integer jobId) {
		JobId = jobId;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	
	
}
