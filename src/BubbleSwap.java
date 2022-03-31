import java.util.*;

class Counting{
	int countNumberOfOne(int n) {
		int totalOnes = 0;
		while(n != 0) {
			if(n%2 == 1) {
				totalOnes++;
			}
			n /= 2;
		}	
		return totalOnes;
	}
	
	int[] sortByBits(int[] arr) {
		for(int i =0; i<arr.length; i++) {
			for(int j=0; j< arr.length-1; j++) {
				if(countNumberOfOne(arr[j]) > countNumberOfOne(j+1)) {
					int temp = arr[j];
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
}


public class BubbleSwap {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {0,1,2,3,4,7,8,10,11};
		
		Counting c = new Counting();
		int arr2[] = c.sortByBits(arr);
		
		System.out.println(Arrays.toString(arr2));

	}
}
