import java.sql.Date;
import java.util.Comparator;

public class Item {

	protected int Id;

	protected String Name;

	protected String Description;

	protected String Image;

	protected double Pricing;

	protected int Quantity;

	protected int UserId;

	// I'll use SQL Date instead of Java Date. This means that there will only be
	// date and no time
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

	public void setPricing(double pricing) {
		Pricing = pricing;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
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

	public Item(int id, String name, String description, String image, double pricing, int quantity, int userId,
			Date dateListed) {
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

	// This is the function that will sort the functional
	public static Comparator<Item> priceComparator = new Comparator<Item>() {
		@Override
		public int compare(Item i1, Item i2) {
			return Double.compare(i1.getPricing(), i2.getPricing());
		}
	};

}
