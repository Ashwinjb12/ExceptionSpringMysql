package com.ashwin.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Account {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String accountName;

  private int balance;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getAccountName() {
	return accountName;
}

public void setAccountName(String accountName) {
	this.accountName = accountName;
}

public int getBalance() {
	return balance;
}

public void setBalance(int balance) {
	this.balance = balance;
}

 
}