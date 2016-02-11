package mail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class MailEntity {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id; 
	
	@Column(name = "EMAIL")
	private String email;
	
	public MailEntity(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
