public class Review {
	public Review(int id, int userId, String displayName, String content, int itemId, String time) {
		super();
		Id = id;
		UserId = userId;
		DisplayName = displayName;
		Content = content;
		ItemId = itemId;
		Time = time;
	}
	protected int Id;
	protected int UserId;
	protected String DisplayName;
	protected String Content;
	protected int ItemId;
	protected String Time;

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
	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
}