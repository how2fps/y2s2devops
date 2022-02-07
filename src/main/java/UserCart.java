
public class UserCart {

	protected Integer Id;
	protected Integer ShoppingCartId;
	protected Integer ItemId;
	protected Integer ItemAmount;
	protected double TotalPrice;

	// Joined variables from 'item' table
	protected String Image;
	protected String Name;
	
	protected String String;
	protected String String2;
	protected String String3;
	protected String String4;
	protected String String5;
	protected String Image2;
	protected String Name2;

	public UserCart(Integer id, Integer shoppingCartId, Integer itemId, Integer itemAmount, double totalPrice,
			String image, String name) {
		super();
		Id = id;
		ShoppingCartId = shoppingCartId;
		ItemId = itemId;
		ItemAmount = itemAmount;
		TotalPrice = totalPrice;
		Image = image;
		Name = name;
	}
	
	

	public UserCart(java.lang.String string, java.lang.String string2, java.lang.String string3,
			java.lang.String string4, java.lang.String string5, java.lang.String image2, java.lang.String name2) {
		super();
		String = string;
		String2 = string2;
		String3 = string3;
		String4 = string4;
		String5 = string5;
		Image2 = image2;
		Name2 = name2;
	}



	/*
	 * public UserCart(String string, String string2, String string3, String
	 * string4, String string5, String image2, String name2) { // TODO
	 * Auto-generated constructor stub super(); string = string; string2 =
	 * shoppingCartId; string3 = itemId; string4 = itemAmount; string5 = totalPrice;
	 * image2 = image; name2 = name;
	 * 
	 * }
	 */

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getShoppingCartId() {
		return ShoppingCartId;
	}

	public void setShoppingCartId(Integer shoppingCartId) {
		ShoppingCartId = shoppingCartId;
	}

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}

	public Integer getItemAmount() {
		return ItemAmount;
	}

	public void setItemAmount(Integer itemAmount) {
		ItemAmount = itemAmount;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getString() {
		return String;
	}

	public void setString(String string) {
		String = string;
	}

	public String getString2() {
		return String2;
	}

	public void setString2(String string2) {
		String2 = string2;
	}

	public String getString3() {
		return String3;
	}

	public void setString3(String string3) {
		String3 = string3;
	}

	public String getString4() {
		return String4;
	}

	public void setString4(String string4) {
		String4 = string4;
	}

	public String getString5() {
		return String5;
	}

	public void setString5(String string5) {
		String5 = string5;
	}

	public String getImage2() {
		return Image2;
	}

	public void setImage2(String image2) {
		Image2 = image2;
	}

	public String getName2() {
		return Name2;
	}

	public void setName2(String name2) {
		Name2 = name2;
	}	
	
	
	

}
