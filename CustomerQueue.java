import java.util.LinkedList;
import java.util.Queue;

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
	private Gui gui;
	private Queue<Customer> queue;
	
    public CustomerQueue(int queueLength, Gui gui) {
		this.queueLength = queueLength;
		this.gui = gui;
		this.queue = new LinkedList<Customer>();
		
    	// Incomplete
	}
    
    public void NewCustomer(Customer c) {
    	this.queue.add(c);
    }
    public Customer NextCustomer() {
    	return this.queue.remove();
    }

    // AddCustomer
    // queue length
    // PopCustomer
    // 
    
	// Add more methods as needed
}
