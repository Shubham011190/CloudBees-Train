package com.Shubham.CloudBees.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Receipt implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String fromStation="London";
	private String toStation="France";
	
	@ManyToOne
	private UserData user;
	private double pricePaid=20.0;
	
	public Receipt() {
		super();
	}
	
	public Receipt(String from, String to, UserData user, double pricePaid) {
		super();
		this.fromStation = from;
		this.toStation = to;
		this.user = user;
		this.pricePaid = pricePaid;
	}

	public String getFrom() {
		return fromStation;
	}

	public void setFrom(String from) {
		this.fromStation = from;
	}

	public String getTo() {
		return toStation;
	}

	public void setTo(String to) {
		this.toStation = to;
	}

	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

}