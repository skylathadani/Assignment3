import java.util.*;

public class QueueImplementation<E> implements Queue<E> {

 // YOUR CODE HERE

	private ArrayList <E> queue;
	

	public QueueImplementation(){
		queue = new ArrayList <E> ();
		
	}

	public boolean isEmpty(){

		if(this.queue.size() != 0){
			return false;
		}

		return true;

	}

	public void enqueue(E value){
	this.queue.add(value);
	}

	public E dequeue(){

		E x = this.queue.get(0);

		this.queue.remove(0);

		return x;

	}

}

