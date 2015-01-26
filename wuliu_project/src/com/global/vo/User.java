package com.global.vo;

/**
 * Use generated by MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userid;

	private Deliveryspot deliveryspot;

	private Employee employee;

	private Power power;

	private String username;

	private String passowrd;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Deliveryspot deliveryspot, Employee employee, Power power,
			String username, String passowrd) {
		this.deliveryspot = deliveryspot;
		this.employee = employee;
		this.power = power;
		this.username = username;
		this.passowrd = passowrd;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Deliveryspot getDeliveryspot() {
		return this.deliveryspot;
	}

	public void setDeliveryspot(Deliveryspot deliveryspot) {
		this.deliveryspot = deliveryspot;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Power getPower() {
		return this.power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return this.passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

}