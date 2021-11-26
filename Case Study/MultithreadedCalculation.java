import java.math.BigInteger;

public class MultithreadedCalculation {
	
    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
        thread1.start();
        thread2.start();
        try {
	        thread1.join();
	        thread2.join();
        } catch (InterruptedException e) {}
        
        result = thread1.getResult().add(thread2.getResult());
        
        return result;   
    }
	
	public static void main(String[] args) {
		BigInteger base1 = new BigInteger("880");
		BigInteger power1 = new BigInteger("200");
		BigInteger base2 =  new BigInteger("990");
		BigInteger power2 = new BigInteger("300");
		System.out.println("The result is \n" + calculateResult(base1, power1, base2, power2));
	}
	
	
    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
    
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
    
        @Override
        public void run() {
            /*
            Implement the calculation of result = base ^ power
            */
        	for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) { 
        		result = result.multiply(base);
        	}
        }
    
        public BigInteger getResult() { return result; }
    }
}