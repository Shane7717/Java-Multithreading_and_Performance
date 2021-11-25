/*
 * Exploring daemon threads
 */
import java.math.BigInteger;

public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));

		// If you think you can't handle the thread interruptions gracefully
		// You can just set the daemon property of the thread to be true
        
        // Daemon threads are background threads that do not prevent the application from exiting if the main thread terminates.
        // You can see, even if the thread is not finished, when the main thread finishes, the whole application still terminates.
        // One great example would be that the GC thread is a daemon thread.
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("Exiting from : " + Thread.currentThread().getName());
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }
}
