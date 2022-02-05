
public class UserCart {
	
	protected Integer Id;
	protected Integer ShoppingCartId;
	protected Integer ItemId;
	protected Integer ItemAmount;
	protected String TotalPrice;

	public UserCart(Integer id, Integer shoppingCartId, Integer itemId, Integer itemAmount, String totalPrice) {
		super();
		Id = id;
		ShoppingCartId = shoppingCartId;
		ItemId = itemId;
		ItemAmount = itemAmount;
		TotalPrice = totalPrice;
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

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

}
