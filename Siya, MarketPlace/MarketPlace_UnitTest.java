import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MaarketPace_test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ReturnCorrectShop() {
		MarketPlace wits = new MarketPlace();
		wits.addStore("Clothing");
		wits.addStore("Food");
		wits.addStore("Events");
		wits.addStore("Stationary");
	

		assertEquals("Clothing",wits.getStore(0).getName());
	}
	
	@Test
	public void ReturnsCorrectNumberOfMarkets(){
		MarketPlace wits = new MarketPlace();
		wits.addStore("Clothing");
		wits.addStore("Food");
		wits.addStore("Events");
		wits.addStore("Stationary");
		
		assertEquals(4,wits.get_Number_of_Stores());
	}
	
	@Test
	public void ReturnsCorrectNumberItemsInStore(){
		MarketPlace wits = new MarketPlace();
		wits.addStore("Clothing");
		wits.addStore("Food");
		wits.addStore("Events");
		wits.addStore("Stationary");
		
		wits.getStore(0).addItem("Nike", 1000);
		wits.getStore(0).addItem("Adidas", 550);
		
		assertEquals(2,wits.getStore(0).get_Number_of_Items());
	}
	
	@Test
	public void ReturnsCorrectItemsNameInStore(){
		MarketPlace wits = new MarketPlace();
		wits.addStore("Clothing");
		wits.addStore("Food");
		wits.addStore("Events");
		wits.addStore("Stationary");
		
		wits.getStore(0).addItem("Nike", 1000);
		wits.getStore(0).addItem("Adidas", 550);
		
		assertEquals("Nike",wits.getStore(0).getItem(0).getName());
	}
	
	@Test
	public void ReturnsCorrectItemsPriceInStore(){
		MarketPlace wits = new MarketPlace();
		wits.addStore("Clothing");
		wits.addStore("Food");
		wits.addStore("Events");
		wits.addStore("Stationary");
		
		wits.getStore(0).addItem("Nike", 1000);
		wits.getStore(0).addItem("Adidas", 550);
		
		assertEquals(1000,wits.getStore(0).getItem(0).getPrice());
	}

}
