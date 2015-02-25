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
		int barberSleep;
		int barberWork;
		Customer nextId = null;
		int minSleep = MIN_BARBER_SLEEP;
		int maxSleep = MAX_BARBER_SLEEP;
		int minWork = MIN_BARBER_WORK;
		int maxWork = MAX_BARBER_WORK;
		while (active){
			//uncomment to get variable speed.
			barberSleep = Globals.barberSleep;//minSleep+(int)(Math.random()*(maxSleep-minSleep+1));
			barberWork = Globals.barberWork;//minWork+(int)(Math.random()*(maxWork-minWork+1));
			try {
				synchronized (queue) {
					if (queue.inqueue()) { 
						nextId = queue.NextCustomer();
					} else {
						gui.println("Barber" + Integer.toString(pos) + " is waiting for customers...");
						queue.wait();
						gui.println("Barber" + Integer.toString(pos) + " was notified of a new customer");
					}
				}
				if (nextId != null) {
					gui.fillBarberChair(this.pos, nextId);
					gui.println("Barber" + Integer.toString(pos) + " is barbing..");
					Thread.sleep(barberWork);
					gui.emptyBarberChair(this.pos);
					gui.println("Barber" + Integer.toString(pos) +" is done barbing");
					gui.println("Barber" + Integer.toString(pos) +" is daydreaming");
					gui.barberIsSleeping(this.pos);
					Thread.sleep(barberSleep);
					gui.println("Barber" + Integer.toString(pos) +" is ready to work");
					gui.barberIsAwake(this.pos); 
					nextId = null;
				}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}

