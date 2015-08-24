package entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(schema="betatrade", name="UserAccount")
public class UserAccount {
	
	private Long userId;
	private String username;
	private String password;
	private String email;
	private String phone;
	private Double cashBalance;
	private String createdDate; //contain the time stamp inside
	private String closedDate;
	
	private List<Strategy> strategies;
	
	@OneToMany(mappedBy="user")
	@OrderBy("id DESC")
	public List<Strategy> getStrategies(){
		return strategies;
	}
	
	public void setStrategies(List<Strategy> strategies){
		this.strategies = strategies;
	}
	
	
	@Override
	public String toString() {
		return "UserAccount [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", cashBalance=" + cashBalance + ", createdDate="
				+ createdDate + ", closedDate=" + closedDate + "]";
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getCashBalance() {
		return cashBalance;
	}
	public synchronized void setCashBalance(Double cashBalance) {
		this.cashBalance = cashBalance;
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
