//Wait method

class Producer extends Thread{
	Queue a;
	
	Producer(Queue q){
		a = q;
	}
	public void run() {
		try {
			int i = 1;
			while(true) {
				a.put(i);
				i++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

class Queue{
	int x;
	boolean presentValue = false;
	
	synchronized void put(int i) {
		try {
			if(!presentValue) {
				x=i;
				System.out.println("Inserted into i : "+ i);
				presentValue = true;
				notify();
			}
			else {
				wait();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	synchronized void get() {
		try {
			if(presentValue) {
				System.out.println("Taken " + x);
				presentValue = false;
				notify();
			}
			else {
				wait();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

class Consumer extends Thread{
	Queue b;
	
	Consumer(Queue q){
		b = q;
	}
	public void run() {
		try {
			while(true) {
				b.get();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

public class QueueSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue q = new Queue();
		Producer p = new Producer(q);
		Consumer c = new Consumer(q);
		
		p.start();
		c.start();
	}

}
