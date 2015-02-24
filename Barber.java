/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber implements Runnable,Constants {
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	private CustomerQueue queue;
	private Gui gui;
	private int pos;
	private boolean active;

	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
		this.active = true;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		new Thread(new Barber(queue,gui,pos)).start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		this.active = false;
	}

	public void run() {
		int r;
		int min = MIN_BARBER_SLEEP;
		int max = MAX_BARBER_SLEEP;
		while (){
			r = min+(int)(Math.random()*(max-min+1));
			try {
				Thread.sleep(r);
				if () {
					gui.println("Barber" + str(pos) + " was notified of a new customer");
					
				}
				else {
					synchronized ( ) {
						gui.println("Barber" + str(pos) + " is waiting for customers...");
						queue.wait();
					}
				}

				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// Add more methods as needed
}

