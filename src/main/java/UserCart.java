
public class UserCart {

	protected Integer Id;
	protected Integer ShoppingCartId;
	protected Integer ItemId;
	protected Integer ItemAmount;
	protected double TotalPrice;

	// Joined variables from 'item' table
	protected String Name;
	protected String Description;
	protected String Image;
	protected Integer Pricing;
	protected Integer Quantity;
	protected Integer UserId;
	protected String DateListed;
	
	
	public UserCart(Integer id, Integer shoppingCartId, Integer itemId, Integer itemAmount, double totalPrice,
			String name, String description, String image, Integer pricing, Integer quantity, Integer userId,
			String dateListed) {
		super();
		Id = id;
		ShoppingCartId = shoppingCartId;
		ItemId = itemId;
		ItemAmount = itemAmount;
		TotalPrice = totalPrice;
		Name = name;
		Description = description;
		Image = image;
		Pricing = pricing;
		Quantity = quantity;
		UserId = userId;
		DateListed = dateListed;
	}


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


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getImage() {
		return Image;
	}


	public void setImage(String image) {
		Image = image;
	}


	public Integer getPricing() {
		return Pricing;
	}


	public void setPricing(Integer pricing) {
		Pricing = pricing;
	}


	public Integer getQuantity() {
		return Quantity;
	}


	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}


	public Integer getUserId() {
		return UserId;
	}


	public void setUserId(Integer userId) {
		UserId = userId;
	}


	public String getDateListed() {
		return DateListed;
	}


	public void setDateListed(String dateListed) {
		DateListed = dateListed;
	}

//	public UserCart(Integer id, Integer shoppingCartId, Integer itemId, Integer itemAmount, double totalPrice,
//			java.lang.String image, java.lang.String name, Integer quantity, Integer userId) {
//		super();
//		Id = id;
//		ShoppingCartId = shoppingCartId;
//		ItemId = itemId;
//		ItemAmount = itemAmount;
//		TotalPrice = totalPrice;
//		Image = image;
//		Name = name;
//		Quantity = quantity;
//		UserId = userId;
//	}
	
	
	

	
}
