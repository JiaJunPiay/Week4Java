import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class FileInputOutput {
	public static void main(String[] args) throws Exception {
		/*
		//1 byte of data
		FileInputStream fis = new FileInputStream("C://Users//jpiay//OneDrive - DXC Production//Desktop//Training//Java//input.txt");
		FileOutputStream fos = new FileOutputStream("C://Users//jpiay//OneDrive - DXC Production//Desktop//Training//Java//output.txt");
		
		int a;
		while((a = fis.read()) != -1) {
			System.out.println(a);
			fos.write(a);
		}
		*/
		
		//FileInputStream is Byte Based, it can be used to read bytes. FileReader is Character Based, it can be used to read characters.
		//2 byte of data
		FileReader fr = new FileReader("C://Users//jpiay//OneDrive - DXC Production//Desktop//Training//Java//input.txt");
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("C://Users//jpiay//OneDrive - DXC Production//Desktop//Training//Java//output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		/*
		 * The writers are usually buffered so it waits for the buffer to be filled before it writes it to the file. 
		 * Flush tells to write the buffer even though it might not be filled yet. 
		 * It's usually useful when you finish the writing since the last buffer may not be full but you want to finish the writing.
		 */
		
		String a;
		//read() -> read Int/readLine() -> read String
		while((a = br.readLine()) != null) {
			System.out.println(a);
			bw.write(a);
		}
		bw.flush();
		
	}
}
