package edu.umb.cs680.hw03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {
	
	private String[] carToStringArray(Car car) {
		String[] carStringArray = new String[3];
		carStringArray[0] = car.getMake();
		carStringArray[1] = car.getModel();
		carStringArray[2] = car.getYear() + "";
		return carStringArray;
	}
	
	
	@Test
	public void verifyCarEqualityWithMakeModelYear() {
		String[] expected = {"Dodge", "Challenger", "2020"};
		Car actual = new Car("Dodge", "Challenger", 2020);
		assertArrayEquals(expected, carToStringArray(actual));
	}

}
