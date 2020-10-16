package entity;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class FilterSP {

	@Id
	private String name;
	private Integer count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
