class Resources extends Thread{
	String gun = "gun";
	String bomb = "bomb";
	String knife = "knife";
	
	public void run(){
		resource();
		/*
		knife();
		gun();
		bomb();
		*/
	}
	
	void resource() {
		try {
			synchronized(gun) {
				System.out.println(Thread.currentThread().getName() + " has acquired " + gun);
				Thread.sleep(3000);
				synchronized(bomb) {
					System.out.println(Thread.currentThread().getName() + " has acquired " + bomb);
					Thread.sleep(3000);
					synchronized(knife) {
						System.out.println(Thread.currentThread().getName() + " has acquired " + knife);
						Thread.sleep(3000);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 //Deadlock, this will happen as resource1 will be waiting for resource2 to release the resource and vice versa as synchronized locked the resource when it has been executed
	 //until the resource has been released/dead state
	 	void resource1() {
		try {
			synchronized(gun) {
				System.out.println(Thread.currentThread().getName() + " has acquired " + gun);
				Thread.sleep(3000);
				synchronized(bomb) {
					System.out.println(Thread.currentThread().getName() + " has acquired " + bomb);
					Thread.sleep(3000);
					synchronized(knife) {
						System.out.println(Thread.currentThread().getName() + " has acquired " + knife);
						Thread.sleep(3000);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	 	void resource2() {
		try {
			synchronized(knife) {
				System.out.println(Thread.currentThread().getName() + " has acquired " + gun);
				Thread.sleep(3000);
				synchronized(bomb) {
					System.out.println(Thread.currentThread().getName() + " has acquired " + bomb);
					Thread.sleep(3000);
					synchronized(gun) {
						System.out.println(Thread.currentThread().getName() + " has acquired " + knife);
						Thread.sleep(3000);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	 
	 
	 
	 */
	
	/*
	synchronized void knife() {
		System.out.println(Thread.currentThread().getName() + " has acquired a knife!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized void gun() {
		System.out.println(Thread.currentThread().getName() + " has acquired a gun!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized void bomb() {
		System.out.println(Thread.currentThread().getName() + " has acquired a bomb!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}

public class BattleRoyale {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resources r1 = new Resources();
		Resources r2 = new Resources();
		
		r1.setName("Player 1");
		r2.setName("Player 2");
		
		r1.start();
		r2.start();
	}

}
