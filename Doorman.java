import java.util.Timer;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman implements Runnable,Constants {
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	
	private CustomerQueue queue;
	private Gui gui;
	private boolean active;
	
	public Doorman(CustomerQueue queue, Gui gui) {
		this.queue = queue;
		this.gui = gui;
		this.active = true;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		new Thread(new Doorman(queue,gui)).start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		this.active = false;
	}

	public void run() {
		int r;
		int min = MIN_DOORMAN_SLEEP;
		int max = MAX_DOORMAN_SLEEP;
		while(active) {
			r = min+(int)(Math.random()*(max-min+1));
			try {
				synchronized (queue) {
					if (queue.openspot()) {
						gui.println("Adding new customer to the queue.");
						queue.NewCustomer(new Customer());
						queue.notifyAll();
					}
					else {
						gui.println("Queue is full. Doorman going to sleep...");
						queue.wait();
					}
					Thread.sleep(r);
				}				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
