package entity;

import javax.persistence.*;

@Entity
@Table(schema="betatrade", name="UserAccount")
public class Transaction {

	private int id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId(){
		return id;
	}
}
