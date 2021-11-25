// Explore Thread Fundamentals

public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in a new thread
                System.out.println("we are now in thread "+Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        //Helpful for debugging when there are a lot of threads
        thread.setName("New Worker Thread");

        // You can also pass 1 to 10 into it
        thread.setPriority(Thread.MAX_PRIORITY);
     
        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName()+ " after starting a new thread");
    	
    }
}