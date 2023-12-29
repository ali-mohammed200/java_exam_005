package com.defaultp;
import com.bill.*;

public class TestClient {

	public static void main(String[] args) {
//		Creating customer type array
		Customer[] customers = {new RegularCustomer(1, "Mo", 123123123), new PremiumCustomer(2, "Ol", 124124124)};

		for(int i = 0; i < customers.length; i++) {
			float price = (float) customers[i].calculateBill(35);
					
//			Cast, set and print bill
			switch(customers[i].getClass().getSimpleName()) {
			  case "RegularCustomer":
					((RegularCustomer) customers[i]).setBillAmount(price);
					System.out.println(((RegularCustomer) customers[i]).getBillAmount());
			    break;
			  case "PremiumCustomer":
					((PremiumCustomer) customers[i]).setBillAmount(price);
					System.out.println(((PremiumCustomer) customers[i]).getBillAmount());
			    break;
			}
		}
	}

}
