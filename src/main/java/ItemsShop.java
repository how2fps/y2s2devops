
public class ItemsShop {

	protected Integer Id;
	protected String Name;
	protected String Description;
	protected String Image;
	protected String Pricing;
	protected Integer Quantity;
	protected Integer UserId;
	protected String DateListed;
	
	public ItemsShop(Integer id, String name, String description, String image, String pricing, Integer quantity,
			Integer userId, String dateListed) {
		super();
		Id = id;
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
	public String getPricing() {
		return Pricing;
	}
	public void setPricing(String pricing) {
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

	

}
