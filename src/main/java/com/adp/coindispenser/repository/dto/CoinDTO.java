package com.adp.coindispenser.repository.dto;

public class CoinDTO {
	
	private int id;
	
	private float type;
	
	private int remainingQuantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getType() {
		return type;
	}

	public void setType(float type) {
		this.type = type;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	@Override
	public String toString() {
		return "CoinDTO [id=" + id + ", type=" + type + ", remainingQuantity=" + remainingQuantity + "]";
	}
	
}
