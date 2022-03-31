
class Bathroom implements Runnable{
	//A piece of logic marked with synchronized becomes a synchronized block, allowing only one thread to execute at any given time.
	synchronized public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " has enter the bathroom!");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " is using the bathroom!");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " has exited the bathroom!");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


public class BathroomProgram {
	public static void main(String[] args) {
		Bathroom b = new Bathroom();
		
		Thread boy = new Thread(b);
		Thread girl = new Thread(b);
		Thread they = new Thread(b);
		
		boy.setName("Boy");
		girl.setName("Girl");
		they.setName("They");
		
		boy.start();
		girl.start();
		they.start();
	}
}
