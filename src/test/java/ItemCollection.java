import java.util.ArrayList;
import java.util.List;

public class ItemCollection {

	private ArrayList<Item> items = new ArrayList<>();
	private int capacity;

	public ItemCollection() {
		/*
		 * songs.add(new Song("0001","good 4 u","Olivia Rodrigo",3.59)); songs.add(new
		 * Song("0002","Peaches","Justin Bieber",3.18)); songs.add(new
		 * Song("0003","MONTERO","Lil Nas X",2.3)); songs.add(new
		 * Song("0004","bad guy","Billie Eilish",3.14));
		 */

		this.capacity = 20;
	}

	public ItemCollection(int capacity) {
		this.capacity = capacity;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		if (items.size() != capacity) {
			items.add(item);
		}
	}
}