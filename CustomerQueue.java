/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */

	private int queueLength;

    public CustomerQueue(int queueLength, Gui gui) {
		// Incomplete
		this.queueLength = queueLength;
	}

	public int getQueueLength(){
		return queueLength;
	}

	// Add more methods as needed
}
