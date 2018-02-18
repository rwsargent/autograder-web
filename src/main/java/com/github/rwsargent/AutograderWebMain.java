package com.github.rwsargent;

public class AutograderWebMain {

	public static void main(String[] args) {
		try {
			new AutograderWebApplication().run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
