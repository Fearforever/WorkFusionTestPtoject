package rate;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rate")
public class RateEntity {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id; 
	
	@Column(name = "rate")
	private float rate;
	
	@Column(name = "date")
	private Date date;
	
	public RateEntity(){
		date = null;
	}
	
	public RateEntity(float rate, Date date){
		this.rate = rate; 
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
