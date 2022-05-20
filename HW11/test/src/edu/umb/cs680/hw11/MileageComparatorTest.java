package edu.umb.cs680.hw11;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw11.Car;
import edu.umb.cs680.hw11.MileageComparator;

public class MileageComparatorTest {

	@Test
	public void mileageTestByIncreasingOrder() {
		LinkedList<Car> usedCars = new LinkedList<>();
		Car Car1 = new Car("Audi", "A4", 80000, 2012, 8000.0f);
		Car Car2 = new Car("Audi", "R8", 90000, 2011, 9000.0f);
		Car Car3 = new Car("Audi", "Q6", 70000, 2013, 7000.0f);
		usedCars.add(Car1);
		usedCars.add(Car2);
		usedCars.add(Car3);
		LinkedList<Car> expected = new LinkedList<>();
		expected.add(Car3);
		expected.add(Car1);
		expected.add(Car2);
		Collections.sort(usedCars, new MileageComparator());
		Assert.assertArrayEquals(expected.toArray(), usedCars.toArray());
	}
}