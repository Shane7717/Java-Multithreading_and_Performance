// Explore with daemon threads

import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		Thread thread = new Thread(new WaitingForUserInput());
		thread.setDaemon(true);
		thread.setName("InputWaitingThread");
		thread.start();
		//thread.interrupt();
	}
	
	public static class WaitingForUserInput implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Current Thread has been interrupted.");
						return;
					}
					char input = (char) System.in.read();
					if (input == 'q') {
						return;
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
			
		}	
	}
}