import java.util.Collection;
import java.util.NoSuchElementException;

public class SinglyLinkedList {

    /*
        * This class is a simple implementation of a singly linked list.
        * It includes all the methods form the predefined linked list class.
    */

    // The private properties of a typical linked list class.
    private Node head;
    private Node tail;
    private int size;

    // Node class represents each element in the linked list.
    private class Node {

        // These properties are declared private as they should not be accessed outside the class.
        private int value;
        private Node next;

        // Constructor which creates the node objects by taking the value and next node as parameters.
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        // Constructor which creates the node objects by taking the value of the node as the only parameter.
        public Node(int value) {
            this.value = value;
        }

    }

    public void addFirst(int value) {
        Node node = new Node(value);
        if(head == null)
            head = tail = node;
        else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addLast(int value) {
        Node node = new Node(value);
        if(head == null)
            head = tail = node;
        else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int getFirst() {
        if(size == 0)
            throw new NoSuchElementException();
        else
            return head.value;
    }

    public int getLast() {
        if (size == 0)
            throw new NoSuchElementException();
        else
            return tail.value;
    }

    public boolean contains(int value) {
        Node temp = head;
        while(temp != null) {
            if(temp.value == value)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }



    public void add(int value, int index) {
        if(index == 0)
            addFirst(value);
        else if(index == size)
            addLast(value);
        else if( index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        else {
            Node node = new Node(value);
            Node temp = head;
            for(int i=0;i<index-1;i++)
                temp = temp.next;
            node.next = temp.next;
            temp.next = node;
            size++;
        }
    }

    public boolean add(int value) {
       addLast(value);
       return true;
    }

    public boolean addAll(int index, Collection<?  extends Integer> items) {
        if( index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        else if(items.isEmpty())
            return false;
        else if(index == 0) {
            for(int item : items)
                addFirst(item);
        }
        else if(index == size) {
            for(int item : items)
                addLast(item);
        }
        else {
            Node temp = head;
            for(int i=0;i<index-1;i++)
                temp = temp.next;
            for(int item : items) {
                Node node = new Node(item);
                node.next = temp.next;
                temp.next = node;
                temp = node;
            }
            size += items.size();
        }
        return true;
    }

    public boolean addAll(Collection <? extends Integer> items ) {
        for(int item : items)
            addLast(item);
        return true;
    }

    public SinglyLinkedList clone(SinglyLinkedList list) {
        SinglyLinkedList clone= new SinglyLinkedList();
        Node temp = list.head;
        while(temp != null) {
                clone.addLast(temp.value);
            temp = temp.next;
        }
        return clone;
    }

    public boolean offer(int value) {
        addLast(value);
        return true;
    }

    public boolean offerFirst(int value) {
        addFirst(value);
        return true;
    }

    public boolean offerLast(int value) {
        addLast(value);
        return true;
    }

    public int peek() {
        return head.value;
    }

    public int peekFirst() {
        return head.value;
    }

    public int peekLast() {
        return tail.value;
    }

    public Integer poll () {
        if(head == null)
            return null;
        int value = head.value;
        head = head.next;
        if(head == null)
            tail = null;
        size--;
        return value;
    }

    public Integer pollFirst() {
        return poll();
    }

    public Integer pollLast() {
        if(tail == null)
            return null;
        int value = tail.value;
        if(head == tail)
            head = tail = null;
        else {
            Node temp = head;
            for (int i = 0; i < size - 1; i++)
                temp = temp.next;
            tail = temp;
        }
            size--;
        return value;
    }

    public Integer pop() {
        return poll();
    }

    public void push(int value) {
        addLast(value);
    }

    public Integer remove() {
        return pollLast();
    }

    public Integer remove(int index) {
        int value;
        if(index ==0)
            return poll();
        else if(index == size-1)
            return pollLast();
        else if(index < 0 || index >= size)
            return null;
        else {
            Node temp = head;
            for(int i=0;i<index-2;i++)
                temp = temp.next;
            value = temp.next.value;
            temp.next = temp.next.next;
        }
        return value;
    }

    public Integer removeFirst(){
        return poll();
    }

    public Integer removeLast() {
        return pollLast();
    }

    public boolean removeFirstOccurrence(int value) {
            if (head == null) return false;

            if (head.value == value) {
                head = head.next;
                size--;
                return true;
            }

            Node current = head;
            while (current.next != null) {
                if (current.next.value == value) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
                current = current.next;
            }
            return false;
    }

    public boolean removeLastOccurrence(int value) {
        if (head == null)
            return false;
        Node current = head;
        Node lastOccurrence = null;
        Node previousToLast = null;
        while (current != null) {
            if (current.value == value) {
                lastOccurrence = current;
                previousToLast = (previousToLast == null) ? head : previousToLast.next;
            }
            current = current.next;
        }
        if (lastOccurrence != null) {
            if (lastOccurrence == head)
                head = head.next;
            else if (lastOccurrence == tail) {
                tail = previousToLast;
                tail.next = null;
            }
            else
                previousToLast.next = lastOccurrence.next;
            size--;
            return true;
        }
        return false;
    }

    public Integer set(int index, int value) {
            if (index < 0 || index >= size)
                return null;
            Node current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
            int oldValue = current.value; // Store the old value to return later
            current.value = value; // Set the new value
            return oldValue; // Return the old value
    }

    public SinglyLinkedList reversed (SinglyLinkedList list) {
        SinglyLinkedList l = new SinglyLinkedList();
        Node temp = list.head;
        while(temp!= null) {
            l.addFirst(temp.value);
            temp = temp.next;
        }
        return l;
    }

    public int[] toArray(SinglyLinkedList list) {
        if(list.size == 0)
            return new int[0];
        int[] array = new int[list.size];
        Node temp = list.head;
        array[0] = list.head.value;
        for(int i=1;i<array.length;i++) {
            temp = temp.next;
            array[i] = temp.value;
        }
        return array;
    }
    public Object[] toArray(SinglyLinkedList list, boolean isSpecific) {
        if(list.size == 0)
            return new Object[0];
        Object[] array = new Object[list.size];
        Node temp = list.head;
        array[0] = list.head.value;
        for(int i=1;i<array.length;i++) {
            temp = temp.next;
            array[i] = temp.value;
        }
        return array;
    }

    public void printList() {
        Node temp = head;
        System.out.print("Start -> ");
        while(temp != null) {
            System.out.print(temp.value+" -> ");
            temp = temp.next;
        }
        System.out.println("End");
    }

}
