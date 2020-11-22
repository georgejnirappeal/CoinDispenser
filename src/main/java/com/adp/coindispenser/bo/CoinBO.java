package com.adp.coindispenser.bo;

public class CoinBO {
	
	private float coinType;
	
	private int numberOfCoins;

	public float getCoinType() {
		return coinType;
	}

	public void setCoinType(float coinType) {
		this.coinType = coinType;
	}

	public int getNumberOfCoins() {
		return numberOfCoins;
	}

	public void setNumberOfCoins(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}

	@Override
	public String toString() {
		return "CoinBO [coinType=" + coinType + ", numberOfCoins=" + numberOfCoins + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(coinType);
		result = prime * result + numberOfCoins;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinBO other = (CoinBO) obj;
		if (Float.floatToIntBits(coinType) != Float.floatToIntBits(other.coinType))
			return false;
		if (numberOfCoins != other.numberOfCoins)
			return false;
		return true;
	}
	
}
