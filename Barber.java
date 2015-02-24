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

	public synchronized void run() {
		int barberSleep;
		int barberWork;
		Customer nextId;
		int minSleep = MIN_BARBER_SLEEP;
		int maxSleep = MAX_BARBER_SLEEP;
		int minWork = MIN_BARBER_WORK;
		int maxWork = MAX_BARBER_WORK;
		while (active){
			barberSleep = minSleep+(int)(Math.random()*(maxSleep-minSleep+1));
			barberWork = minWork+(int)(Math.random()*(maxWork-minWork+1));
			try {
				Thread.sleep(barberSleep);
				if (queue.inqueue() && gui.barberIsAwake(this.pos)) {
					nextId = queue.NextCustomer();
					gui.fillBarberChair(this.pos, nextId);
					gui.println("Barber is barbing..")
					Thread.sleep(barberWork);
					gui.emptyBarbeerChari(this.pos, nextId);					
				}
				else {
						gui.println("Barber" + Integer.toString(pos) + " is waiting for customers...");
						gui.barberIsSleeping(this.pos);
						queue.wait();
						gui.println("Barber" + Integer.toString(pos) + " was notified of a new customer");
				}

				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// Add more methods as needed
}

