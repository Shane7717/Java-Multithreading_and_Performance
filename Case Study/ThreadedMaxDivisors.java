package divisors;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;


public class ThreadedMaxDivisors implements Runnable {
	
	private long min;
	private long max;
	private Entry<Long,Long> result;
	
	public ThreadedMaxDivisors(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public void run() {
		Entry<Long,Long> result = CountDivisors.maxDivisors(min, max);
		this.setResult(result); 
	}
	

	public static void main(String[] args) {
		
		long min = 100_000;
		long max = 200_000;
				
		Set<Thread> threadSet = new HashSet<Thread>();
		Set<ThreadedMaxDivisors> divisorsSet = new HashSet<ThreadedMaxDivisors>();
		long startTime = System.currentTimeMillis();
				
		int threadNum = 20;
		long temp = min;
		long interval = (max - min) / threadNum;
		for (int i = 1; i <= threadNum; ++i) {
			ThreadedMaxDivisors task = new ThreadedMaxDivisors(temp, min + interval * i);
			temp = min + interval * i;
			Thread thread = new Thread(task);
			divisorsSet.add(task);
			threadSet.add(thread);
			thread.start();
		}
				
 		/* join() tells a thread to wait until it's complete before the rest of the code and proceed.
		 * if we do that for all the threads, then then we can get the results of the threads once
		 * all of them are done
		 */
		for (Thread t: threadSet) {
			try {
				t.join();
				System.out.print("Done");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// at this point, all threads have been completed, since we
		// called the "join()" method on all the threads we created,
		// which forces the code to wait until the thread is finished
		// before we continue
		
		long maxNumber = -1;
		long maxDivisors = -1;
		for (ThreadedMaxDivisors tmd : divisorsSet) {
			// presumably you've recorded the results of
			// each ThreadedMaxDivisors run. Pick
			// the largest number with the largest number of
			// divisors
			Entry<Long,Long> e = tmd.getResult();
			long number = e.getKey();
			long numDivisors = e.getValue();
			if (numDivisors > maxDivisors) {
				maxDivisors = numDivisors;
				maxNumber = number;
			} else if (numDivisors == maxDivisors && number > maxNumber) {
				maxNumber = number;
			}
		}
		System.out.println("\n" + maxNumber + ": " + maxDivisors);
		long endTime = System.currentTimeMillis();
		System.out.println("Threaded elapsed time: " + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		Entry<Long,Long> e = CountDivisors.maxDivisors(min, max);
		
		long number = e.getKey();
		long numDivisors = e.getValue();
		
		System.out.println("\n" + number + ": " + numDivisors);
		endTime = System.currentTimeMillis();
		
		System.out.println("Non-threaded elapsed time: " + (endTime - startTime));
				
	}

	public Entry<Long,Long> getResult() {
		return result;
	}

	public void setResult(Entry<Long,Long> result) {
		this.result = result;
	}
}
