package com.Shubham.CloudBees.entity;

import java.io.Serial;
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

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	final private String fromStation="London";
	final private String toStation="France";

	@ManyToOne
	private UserData user;
	final private double pricePaid=20.0;

	public Receipt() {
		super();
	}

	public Receipt(String from, String to, UserData user, double pricePaid) {
		super();
		this.user = user;
	}

	public String getFrom() {
		return fromStation;
	}

	public String getTo() {
		return toStation;
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

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", fromStation=" + fromStation + ", toStation=" + toStation + ", user=" + user
				+ ", pricePaid=" + pricePaid + "]";
	}


}
