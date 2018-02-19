package com.github.rwsargent.views;

import java.util.ArrayList;
import java.util.List;

public class AutograderView {
	public List<Item> items = new ArrayList<>();
	
	public AutograderView() {
		for(int i = 0; i < 3; i++) {
			items.add(new Item(i));
		}
	}
	
	public static class Item {
		String name;
		
		public Item(int name) {
			this.name = String.valueOf(name);
		}
	}

}
