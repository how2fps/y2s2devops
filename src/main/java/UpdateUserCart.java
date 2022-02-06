
public class UpdateUserCart {
	
	protected String Id;
	protected String ShoppingCartId;
	protected String ItemId;
	protected String ItemAmount;
	protected String TotalPrice;
	
	public UpdateUserCart(String id, String shoppingCartId, String itemId, String itemAmount, String totalPrice) {
		super();
		Id = id;
		ShoppingCartId = shoppingCartId;
		ItemId = itemId;
		ItemAmount = itemAmount;
		TotalPrice = totalPrice;
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getShoppingCartId() {
		return ShoppingCartId;
	}
	public void setShoppingCartId(String shoppingCartId) {
		ShoppingCartId = shoppingCartId;
	}
	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public String getItemAmount() {
		return ItemAmount;
	}
	public void setItemAmount(String itemAmount) {
		ItemAmount = itemAmount;
	}
	public String getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

}
