import java.sql.Date;

public class ItemsShop {

	protected Integer Id;
	protected String Name;
	protected String Description;
	protected String Image;
	protected double Pricing;
	protected Integer Quantity;
	protected Integer UserId;
	protected Date DateListed;
	
	public ItemsShop(Integer id, String name, String description, String image, double pricing, Integer quantity,
			Integer userId, Date dateListed) {
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
	public double getPricing() {
		return Pricing;
	}
	public void setPricing(double pricing) {
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
	public Date getDateListed() {
		return DateListed;
	}
	public void setDateListed(Date dateListed) {
		DateListed = dateListed;
	}

	

}
