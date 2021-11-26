// Explore the "join" method

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayWithJoin {
	
	public static void main(String[] args) throws InterruptedException {
		List<Long> inputNumbers = Arrays.asList(10000000L, 3435L, 35L, 23L, 46L, 23L, 56L);
		List<FactorialThread> threads = new ArrayList<>();
		
		for (long l : inputNumbers) {
			threads.add(new FactorialThread(l));
		}
		
		for (Thread thread : threads) {
			thread.setDaemon(true);
			thread.start();
		}
		
		//When we invoke the join() method on a thread, the calling thread goes into a waiting state. 
		//It remains in a waiting state until the referenced thread terminates.
		
		//It's always good practice to specify how long we're willing to wait for each of the worker threads.
		for (Thread thread: threads) {
			thread.join(2000);
		}
		
		for (int i = 0; i < threads.size(); ++i) {
			if (threads.get(i).isFinished)
				System.out.println("Factorial of " + inputNumbers.get(i) + " is: \n" + threads.get(i).getResult());
			else
				System.out.println("The calculation for factorial of " + inputNumbers.get(i) + " is still in progress.");
		}
		
	}
	
	public static class FactorialThread extends Thread {
		private long inputNumber;
		private BigInteger result = BigInteger.ZERO;
		private boolean isFinished = false;
		
		public FactorialThread (Long input) {
			this.inputNumber = input;
		}
		
		@Override
		public void run() {
			this.result = factorial(inputNumber);
			this.isFinished = true;
		}
		
		public BigInteger factorial(long n) {
			BigInteger tempResult = BigInteger.ONE;
			
			for (long i = n; i >= 1; i--) {
				tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
			}
				
			return tempResult;
		}
		
		public boolean isFinished() {
			return isFinished;
		}
		
		public BigInteger getResult() {
			return result;
		}
	}
}