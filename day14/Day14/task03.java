
class task03 {
    int data;
    task03 next;

    public task03(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedListExample {
    task03 head;
    public void add(int data) {
        task03 newNode = new task03(data);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            task03 current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head;
        }
    }
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        task03 current = head;
        System.out.print("Circular Linked List elements: ");
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedListExample list = new CircularLinkedListExample();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        list.display();
    }
}