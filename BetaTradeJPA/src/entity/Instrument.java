package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Instrument
 *
 */
@Entity
@Table(schema="betatrade", name="Instrument")
public class Instrument implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Instrument() {
		super();
	}
	
	
	
	/*
	 * 
	 */
	//@Column
	@Id
	protected String symbol;
	
	/*
	 * 
	 */
	//@Column
	protected String instruName;
	
	/*
	 * 
	 */
	//@Column
	protected String instruType;
	
	/*
	 * 
	 */
	//@Column
	protected String Company;
	
	/*
	 * 
	 */
	//@Column
	protected String description;
	
	/*
	 * 
	 */
	//@Column
	protected String createdDate;
	
	/*
	 * 
	 */
	//@Column
	protected String closedDate;

	
	protected List<InstrumentHistory> histories;
	
	@OneToMany(mappedBy="instrument")
	public List<InstrumentHistory> getHistories(){
		return histories;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getInstruName() {
		return instruName;
	}

	public void setInstruName(String instruName) {
		this.instruName = instruName;
	}

	public String getInstruType() {
		return instruType;
	}

	public void setInstruType(String instruType) {
		this.instruType = instruType;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	
	
   
}
