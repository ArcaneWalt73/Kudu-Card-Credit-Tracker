

public class Item {

	    private String name;
	    private int price;

	    Item(String ItemName, int ItemPrice){
	        this.name = ItemName;
	        this.price = ItemPrice;
	    }

	    public String getName(){
	        return this.name;
	    }

	    public int getPrice(){
	        return this.price;
	    }
	}

