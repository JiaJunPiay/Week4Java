import java.io.*;
import java.util.*;

public class Collection {

	public static void main(String[] args) {
		/*
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		al.add(1);
		al.add(2);
		
		
		
		System.out.println("ArrayList");
		System.out.println("------------------------------------------");
		//Print everything
		System.out.println(al);
		
		//Another way to print arrayList 1 by 1
		for(Object o:al) {
			System.out.println(o);
		}
		
		//add into specific index
		al.add(1, 3);
		System.out.println(al);
		
		//Print 1 by 1
		Iterator ite = al.iterator();
		
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		
		System.out.println("ListIterator");
		System.out.println("------------------------------------------");
		//Reverse print
		ListIterator ll = al.listIterator(al.size());
		while(ll.hasPrevious()) {
			System.out.println("Backwards");
			System.out.println(ll.previous());
		}
		
		//Able to add element for ListIterator but Literator doesn't allow
		//ListIterator allows to go both forward and back while Iteratror only allows forward
		//Add to the next index after the first index
		ll.next();
		ll.add(1000);
		
		while(ll.hasPrevious()) {
			//Starting from 1000 and countdown
			System.out.println("Backwards 2");
			System.out.println(ll.previous());
		}

		
		//Check if exists in object
		System.out.println(al.contains(2));
		System.out.println(al.contains(3));
		
		//Clone Arraylist into an object
		Object al2 = al.clone();
		System.out.println(al2);
		
		//Typecast works as well
		ArrayList al3 = (ArrayList) al.clone();
		System.out.println(al3);
		al3.clear();
		System.out.println(al3);
		
		System.out.println("LinkedList");
		System.out.println("------------------------------------------");
		//Linked List
		LinkedList llist = new LinkedList();
		llist.add(11);
		llist.add(22);
		llist.add(33);
		System.out.println(llist);
		
		
		System.out.println(llist.getLast());
		System.out.println(llist.getFirst());
		
		llist.addAll(al);
		System.out.println(llist);
		
		
		//Print 1 by 1
		Iterator itr = llist.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		System.out.println("PriorityQueue");
		System.out.println("------------------------------------------");
		PriorityQueue pq = new PriorityQueue();
		
		pq.add(1000);
		pq.add(90);
		pq.add(400);
		pq.add(200);
		pq.add(800);
		pq.add(10);
		pq.add(190);
		pq.add(4500);
		pq.add(70);
		
		System.out.println(pq);
		
		
		System.out.println("TreeSet");
		System.out.println("------------------------------------------");
		TreeSet ts = new TreeSet();
		
		ts.add(1000);
		ts.add(90);
		ts.add(400);
		ts.add(200);
		ts.add(800);
		ts.add(10);
		ts.add(190);
		ts.add(4500);
		ts.add(70);
		
		System.out.println(ts);
		
		
		System.out.println("BinarySearch");
		System.out.println("------------------------------------------");
		//Return the index of where 1 was found
		System.out.println(Collections.binarySearch(al, 1));
		*/
		LinkedList bti = new LinkedList();
		bti.add(0);
		bti.add(1);
		bti.add(2);
		bti.add(3);
		bti.add(7);
		bti.add(8);
		bti.add(10);
		bti.add(11);
		
		/*
		Iterator itrs = bti.iterator();
		LinkedList bti2 = new LinkedList();
		while(itrs.hasNext()) {
			int a = (int) itrs.next();
			String b = Integer.toBinaryString(a);
			//System.out.println(b);
			int count = 0;
			for(int i =0; i< b.length(); i++){
				if(b.charAt(i) == '1') {
					count++;
				}
			}
			bti2.add(count);
		}
		*/
		
		
		
		
	}

}
