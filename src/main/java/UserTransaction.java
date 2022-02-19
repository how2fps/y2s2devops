import java.sql.Date;

public class UserTransaction {
	
	protected Integer Id;
	protected Integer BuyingUserId;
	protected Integer SellingUserId;
	protected Integer ItemId;
	protected String ItemName;
	protected String ItemImage;
	protected Integer Quantity;
	protected double AmountDealt;
	protected Date Date;
	
	public UserTransaction(Integer id, Integer buyingUserId, Integer sellingUserId, Integer itemId, String itemName,
			String itemImage, Integer quantity, double amountDealt, java.sql.Date date) {
		super();
		Id = id;
		BuyingUserId = buyingUserId;
		SellingUserId = sellingUserId;
		ItemId = itemId;
		ItemName = itemName;
		ItemImage = itemImage;
		Quantity = quantity;
		AmountDealt = amountDealt;
		Date = date;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getBuyingUserId() {
		return BuyingUserId;
	}

	public void setBuyingUserId(Integer buyingUserId) {
		BuyingUserId = buyingUserId;
	}

	public Integer getSellingUserId() {
		return SellingUserId;
	}

	public void setSellingUserId(Integer sellingUserId) {
		SellingUserId = sellingUserId;
	}

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getItemImage() {
		return ItemImage;
	}

	public void setItemImage(String itemImage) {
		ItemImage = itemImage;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public double getAmountDealt() {
		return AmountDealt;
	}

	public void setAmountDealt(double amountDealt) {
		AmountDealt = amountDealt;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}
	
}
