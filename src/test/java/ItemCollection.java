import java.util.ArrayList;
import java.util.Collections;
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

		items.add(new Item(0, "Item1", "Description it is", "This image Rofl", 13.01, 10, 1,
				java.sql.Date.valueOf("2020-03-11")));
		items.add(new Item(2, "Item1", "Description it is", "This image Rofl", 100.00, 10, 1,
				java.sql.Date.valueOf("2020-03-12")));
		items.add(new Item(3, "Item1", "Description it is", "This image Rofl", 1.90, 10, 1,
				java.sql.Date.valueOf("2020-03-13")));
		items.add(new Item(4, "Item1", "Description it is", "This image Rofl", 65.86, 10, 1,
				java.sql.Date.valueOf("2020-03-14")));
		items.add(new Item(5, "Item1", "Description it is", "This image Rofl", 67.90, 10, 1,
				java.sql.Date.valueOf("2020-03-15")));
		items.add(new Item(6, "Item1", "Description it is", "This image Rofl", 2.09, 10, 1,
				java.sql.Date.valueOf("2020-03-16")));
	}

	public ItemCollection(int capacity) {
		this.capacity = capacity;
	}

	public List<Item> getItems() {
		return items;
	}

	public ArrayList<Item> sortItemByPricing() {
		Collections.sort(items, Item.priceComparator);
		return items;
	}
}