package com.github.rwsargent.views;

import java.util.ArrayList;
import java.util.List;

import io.dropwizard.views.View;

public class AutograderView extends View {
	public List<Item> items = new ArrayList<>();
	
	public AutograderView() {
		super("example.ftl");
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
