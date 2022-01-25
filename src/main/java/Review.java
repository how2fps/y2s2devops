
public class Review {
	protected int Id;
	protected int UserId;
	protected String Content;
	protected int ItemID;
	protected String DateTime;
	
	public Review(int id, int userId, String content, int itemID, String dateTime) {
		super();
		Id = id;
		UserId = userId;
		Content = content;
		ItemID = itemID;
		DateTime = dateTime;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getItemID() {
		return ItemID;
	}
	public void setItemID(int itemID) {
		ItemID = itemID;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
}
