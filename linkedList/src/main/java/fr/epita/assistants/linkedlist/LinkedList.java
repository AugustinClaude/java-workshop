package fr.epita.assistants.linkedlist;

public class LinkedList<T extends Comparable<T>> {
    private class Node {
        T data;
        Node next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    /**
     * Initializes the list
     **/
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Inserts the specified element into the list.
     * The elements must be sorted in ascending order.
     * null elements should be at the end of the list.
     *
     * @param e Element to be inserted
     **/
    public void insert(T e) {
        if (head == null) {
            head = new Node(e);
            size++;
            return;
        }

        Node tmp = head;
        Node prev = null;
        Node res = tmp;
        while (tmp != null && tmp.data.compareTo(e) < 0)
        {
            prev = tmp;
            tmp = tmp.next;
        }

        Node newNode = new Node(e);
        newNode.next = tmp;
        if (prev != null)
        {
            prev.next = newNode;
            head = res;
        }
        else
            head = newNode;

        size++;
    }

    /**
     * Returns the n-th element in the list.
     *
     * @param i Index
     * @return The element at the given index
     * @throws IndexOutOfBoundsException if there is no element at this
     *                                   index.
     **/
    public T get(int i) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException();

        Node tmp = head;
        int n = 0;
        while (tmp != null && n != i) {
            tmp = tmp.next;
            n++;
        }

        if (tmp == null)
            throw new IndexOutOfBoundsException();
        return tmp.data;
    }

    /**
     * Removes the first occurrence of the specified element in the list.
     *
     * @param e Element to remove
     * @return returns the element that has been removed or null
     **/
    public T remove(T e) {
        Node tmp = head;
        Node prev = null;
        while (tmp != null) {
            if (tmp.data.equals(e)) {
                size--;
                if (prev == null) {
                    head = tmp.next;
                    return e;
                }

                prev.next = tmp.next;
                return e;
            }

            prev = tmp;
            tmp = tmp.next;
        }

        return null;
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of elements in the list
     **/
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the list.
     **/
    public void clear() {
        head = null;
        size = 0;
    }
}
