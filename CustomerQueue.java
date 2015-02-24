import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue implements Constants {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	private int queueLength;
	private Gui gui;
	private Queue<Customer> queue;
    private int pos;
	
    public CustomerQueue(int queueLength, Gui gui) {
		this.queueLength = queueLength;
		this.gui = gui;
		this.queue = new LinkedList<Customer>();
        this.pos = NOF_CHAIRS;
		
    	// Incomplete
	}
    
    private int position() {
        return (getCustomerID() % this.pos)+1;
    }

    public synchronized void NewCustomer(Customer c) {
    	this.queue.add(c);
        BarbershopGui.fillLoungeChair(position(c), c)
    	this.notifyAll();
    }


    public synchronized Customer NextCustomer() {
    	this.notifyAll();
    	return this.queue.remove();
    }


    public boolean openspot() {
    	if (queue.size() < queueLength) {
    		return true;
    	}
    	return false;
    }

    // AddCustomer
    // queue length
    // PopCustomer
    // 
    
	// Add more methods as needed
}
