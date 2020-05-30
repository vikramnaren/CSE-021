package oop;

public class Runner {

	public static void main(String[] args) {
		testCounter();
		testCounter7Statements();
		testModNCounter();
		testModNCounter2();
		testDecrementableCounter();
		testSeasonCounter();
	}
	public static void testCounter() {
		System.out.println("-- Testing Counter");

		Counter c = new Counter();
		c.increment();
		c.increment();
		c.increment();
		System.out.println(c.value());
	}


	public static void testCounter7Statements() {
		Counter c = new Counter();
		System.out.println();
		System.out.println("-- Testing 7 statements");

		c.reset();
		c.increment();
		c.reset();
		c.increment();
		c.reset();
		c.increment();
		c.reset();
		c.increment();
		c.reset();
		c.reset();
		c.reset();
		c.increment();
		c.increment();
		c.increment();

		System.out.println("current value: " + c.value());
	}


	public static void testModNCounter() {
		System.out.println();
		ModNCounter c = new ModNCounter(2);
		System.out.print(c.value());
		c.increment();
		System.out.print(c.value());
		c.increment();
		System.out.println(c.value());
		c.increment();

	}

	public static void testModNCounter2() {
		System.out.println();
		ModNCounter2 c = new ModNCounter2(2);

		System.out.print(c.value());
		c.increment();
		System.out.print(c.value());
		c.increment();
		System.out.println(c.value());
		c.increment();
	}

	public static void testDecrementableCounter() {
		DecrementableCounter c = new DecrementableCounter();

		System.out.println();
		c.increment();
		System.out.print(c.value());
		c.increment();
		System.out.print(c.value());
		c.increment();
		System.out.print(c.value());
		c.decrement();
		System.out.print(c.value());
		c.decrement();
		System.out.print(c.value());
		c.decrement();
		System.out.print(c.value());
		c.decrement();
		System.out.println(c.value());

	}

	public static void testSeasonCounter() {
		SeasonCounter c = new SeasonCounter();
		System.out.println();
		System.out.print(c.toString() + " ");
		c.increment();
		System.out.print(c.toString() + " ");
		c.increment();
		System.out.print(c.toString() + " ");
		c.increment();
		System.out.print(c.toString() + " ");
		c.increment();
		System.out.print(c.toString());

	}


}