package edu.umb.cs680.hw01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class PrimeGeneratorTest {

	@Test
	public void Even() {
		PrimeGenerator GetPrime= new PrimeGenerator(8,13);
		boolean expected= true;
		boolean actual= GetPrime.isEven(8);
		assertTrue(GetPrime instanceof PrimeGenerator);
		assertEquals(expected, actual);
	}
	@Test
	public void Range() {
		try{
			PrimeGenerator GetPrime= new PrimeGenerator(13,8);
			
			fail("Wrong input values: from=13 to=8");
		}
		catch(RuntimeException ex){
//			System.out.println(ex.getMessage());
			assertEquals("Wrong input values: from=13 to=8", ex.getMessage());
		}
		
	}
	@Test
	public void Prime() {
		PrimeGenerator GetPrime= new PrimeGenerator(1,20);
		GetPrime.generatePrimes();
		long[] expected= {2,3,5,7,11,13,17,19}; 
		LinkedList<Long> actualList = GetPrime.getPrimes();
		Object[] actualObj= actualList.toArray();
		int length = actualObj.length;
	    long[] actual = new long[length];
	    for(int i=0; i<length; i++){
	    	actual[i] = (Long) actualObj[i];
	    }
	        
		assertTrue(GetPrime instanceof PrimeGenerator);
		assertArrayEquals(expected,actual);
	}
	@Test
	public void PrimeCheck() {
		PrimeGenerator GetPrime= new PrimeGenerator(1,20);
		GetPrime.isPrime(11);
		boolean expected= true;
		boolean actual= GetPrime.isPrime(11);
		assertTrue(GetPrime instanceof PrimeGenerator);
		assertEquals(expected, actual);
		
	}

}