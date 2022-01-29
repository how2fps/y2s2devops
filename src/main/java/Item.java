import java.util.Date;

public class Item {

	protected int Id;

	protected String Name;

	protected String Description;

	protected String Image;

	protected double Pricing;

	protected int UserId;

	// I dont know if we need to import Date from Java or SQL, i just imported Java
	protected Date DateListed;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
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

	public void setPricing(float pricing) {
		Pricing = pricing;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public Date getDateListed() {
		return DateListed;
	}

	public void setDateListed(Date dateListed) {
		DateListed = dateListed;
	}

	public Item(int id, String name, String description, String image, double pricing, int userId, Date dateListed) {
		super();
		Id = id;
		Name = name;
		Description = description;
		Image = image;
		Pricing = pricing;
		UserId = userId;
		DateListed = dateListed;
	}

}
