import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Serializing an object that output and input
//Serializable is a marker interface/tag interface (Nothing inside it)
// A marker interface is an interface that has no methods or constants inside it. It provides run-time type information about objects, so the compiler and JVM have additional information about the object.

class Actor implements Serializable{
	String name;
	int age;
	float height;
	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Actor(String name, int age, float height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
	}
	
	void display() {
		System.out.println(name + " "+ age + " " + height);
	}
}


public class ActorProgram {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Actor a = new Actor("Actor A", 21, 50);
		//a.display();
		
		FileOutputStream fos = new FileOutputStream("C://Users//jpiay//OneDrive - DXC Production//Desktop//Training//Java//actoroutput.txt");
		
		//Write object into a external file
		ObjectOutputStream oos =  new ObjectOutputStream(fos);
		oos.writeObject(a);
		
		oos.close();
		
		//Read object from a external file
		FileInputStream fis = new FileInputStream("C://Users//jpiay//OneDrive - DXC Production//Desktop//Training//Java//actoroutput.txt");
		ObjectInputStream ois =  new ObjectInputStream(fis);
		Actor fisActor  = (Actor) ois.readObject();
		fisActor.display();
		
		ois.close();
	}

}
