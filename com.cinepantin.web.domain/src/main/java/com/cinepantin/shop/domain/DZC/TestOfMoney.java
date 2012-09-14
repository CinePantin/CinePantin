package com.cinepantin.shop.domain.DZC;

import java.util.ArrayList;
import java.util.List;

public class TestOfMoney {

//	private List<? extends Money<?>> wallet;
//	public List<? extends Money<?>> getWallet() {
//		return this.wallet;
//	}
//	public void setWallet(List<? extends Money<?>> wallet) {
//		this.wallet = wallet;
//	}
	
//	private List<? extends Money<?>> wallet;
//	public List<? extends Money<?>> getWallet() {
//		return this.wallet;
//	}
//	public <T extends Money<T>> void setWallet(List<T> wallet) {
//		this.wallet = wallet;
//	}
	
	private List<? super Dollar> wallet;
	public List<? super Dollar> getWallet() {
		return this.wallet;
	}
	public void setWallet(List<? super Dollar> wallet) {
		this.wallet = wallet;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TestOfMoney test = new TestOfMoney();
		test.runTests();
		
	}

	private void runTests() {
		
		Euro euro1 = new Euro(3);
		System.out.println(euro1.add(1));
		
		Euro euro2 = new Euro(2);
		System.out.println(euro1.add(euro2));
		
		Dollar dollar1 = new Dollar(1);
//		euro1.add(dollar1); // Nok : "The method add(int) in the type Monney<Euro> is not applicable for the arguments (Dollar)"
		
		Money<Dollar> dollar2 = new Money<Dollar>(2);
		
		this.setWallet(new ArrayList<Dollar>());
		wallet.add(dollar1);
		// wallet.add(dollar2);
//		wallet.add(euro1);
//		wallet.add(new Money<Euro>(2));
	}

}
