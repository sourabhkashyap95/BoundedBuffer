public class BlockingQueue<T> {

    T[] array;
    int size=0;
    int capacity;
    int head=0;
    int tail=0;

    public BlockingQueue(int capacity){
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while(size == capacity){
            wait();
        }

        if(tail ==  capacity){
            tail=0;
        }

        array[tail] = item;
        ++tail;
        ++size;
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        T item = null;

        while(size == 0){
            wait();
        }

        if(head == capacity){
            head=0;
        }

        item = array[head];
        array[head] = null;
        head++;
        size--;

        notifyAll();

        return item;
    }
}