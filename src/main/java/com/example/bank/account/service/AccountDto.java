package com.example.bank.account.service;

public class AccountDto {
	private String id;
	private String owner;
	private int rib;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getRib() {
		return rib;
	}

	public void setRib(int rib) {
		this.rib = rib;
	}

}
