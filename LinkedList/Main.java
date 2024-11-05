import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an instance of SinglyLinkedList
        SinglyLinkedList list = new SinglyLinkedList();

        // Adding elements to the list
        list.addFirst(10);
        list.addLast(20);
        list.add(15, 1); // Add 15 at index 1
        System.out.println("After adding elements: ");
        list.printList();

        // Size of the list
        System.out.println("Size of list: " + list.size());

        // Get first and last elements
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());

        // Check if the list contains a specific value
        System.out.println("List contains 15: " + list.contains(15));
        System.out.println("List contains 30: " + list.contains(30));

        // Remove first and last elements
        System.out.println("Removed first element: " + list.removeFirst());
        System.out.println("Removed last element: " + list.removeLast());
        System.out.println("After removing first and last elements: ");
        list.printList();

        // Add a collection of items
        Collection<Integer> collection = List.of(30, 40, 50);
        list.addAll(Collections.singleton(collection.size()));
        System.out.println("After adding a collection: ");
        list.printList();

        // Remove by index
        System.out.println("Removed element at index 1: " + list.remove(1));
        System.out.println("After removing element at index 1: ");
        list.printList();

        // Clone the list
        SinglyLinkedList clonedList = list.clone(list);
        System.out.println("Cloned list: ");
        list.printList();

        // Convert to array
        Object[] array = list.toArray(list,true);
        System.out.println("Array representation: " + Arrays.toString(array));

        // Reversed list
        SinglyLinkedList reversedList = list.reversed(list);
        System.out.println("Reversed list: ");
        list.printList();
    }
}
