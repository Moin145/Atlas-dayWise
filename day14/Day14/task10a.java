package java_tasks.Day14;

public class task10a {
    private int[] arr;
    private int front, rear, capacity;

    public task10a(int size){
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = 0;
    }
    public boolean isEmpty() {
        return front == rear;
    }
    public boolean isFull() {
        return rear == capacity;
    }
    public void enqueue(int item){
        if (isFull()){
            System.out.println("Queue is full! Cannot add " + item);
            return;
        }
        arr[rear] = item;
        rear++;
        System.out.println(item + " enqueued.");
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return -1;
        }
        int item = arr[front];
        for (int i = front; i < rear -1; i++){
            arr[i] = arr[i + 1];
        }
        rear--;
        return item;
    }
    public int peek(){
        if (isEmpty()){
            System.out.println("Queue is empty! Nothing to peek.");
            return -1;
        }
        return arr[front];
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("queue is empty.");
            return;
        }
        System.out.print("Queue: ");
        for (int i = front; i < rear; i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        task10a queue = new task10a(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.display();

        System.out.println("Peek: " + queue.peek());

        int removed = queue.dequeue();
        System.out.println("Dequeued: " + removed);

        queue.display();

        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.enqueue(70);

        queue.display();
    }
}
