package java_tasks.Day14;

public class task10 {
    private int[] arr;
    private int front, rear, size, capacity;

    public task10(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }
    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot enqueue " + item);
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
        System.out.println("Enqueued: " + item);
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Nothing to peek.");
            return -1;
        }
        return arr[front];
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue elements: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        task10 queue = new task10(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();

        System.out.println("Peek element: " + queue.peek());

        int removed = queue.dequeue();
        System.out.println("Dequeued element: " + removed);
        queue.display();

        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.display();
        queue.enqueue(70);

        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }

        queue.display();
    }
}
