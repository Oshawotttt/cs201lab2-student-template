import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){

        int[][] swaps = getSwaps();

        for (int[] s : swaps) {
            swapTwoNodes(s);
// System.out.println(this.toString());
        }
        
        // swapTwoNodes(0, 2);
    }

    public int[][] getSwaps() {

        int numSwaps = size/2;
        int[][] output = new int[numSwaps][2];

        List<E> listOfLinkedList = new ArrayList<E>();
        Node<E> walk = head;
        while (walk != null) {
            listOfLinkedList.add(walk.getElement());
            walk = walk.getNext();
        }
        List<E> copy = new ArrayList<>(listOfLinkedList);
        copy.sort(Comparator.naturalOrder());

        int start = 0;
        int end = size-1;
        int i = 0;
        while (start < end) {
            output[i++] = new int[] {listOfLinkedList.indexOf(copy.get(start++)), listOfLinkedList.indexOf(copy.get(end--))};
            
        }

        return output;
    }

    public boolean swapTwoNodes(int[] swap) {
        if (swap[0] < swap[1]) return swapTwoNodes(swap[0], swap[1]);
        else return swapTwoNodes(swap[1], swap[0]);
    }

    // assume i1 < i2
    public boolean swapTwoNodes(int i1, int i2) {
// System.out.println(i1 + " " + i2);

        int i = 0;

        Node<E> walk = head;
        Node<E> prevNode = null;

        Node<E> firstNode = null;
        Node<E> prevOfFirstNode = null;


        while (walk != null) {

            if (i == i1) {
                // save this spot
                firstNode = walk;
                prevOfFirstNode = prevNode;
            }

            if (i == i2) {
                // swap nodes
                Node<E> nextNode = walk.getNext();

                if (prevOfFirstNode == null) {
                    head = walk;
                } else {
                    prevOfFirstNode.setNext(walk);
                }
                if (walk == firstNode.getNext()) {
                    walk.setNext(firstNode);
                }
                else walk.setNext(firstNode.getNext());

                prevNode.setNext(firstNode);
                firstNode.setNext(nextNode);

                if (i2 == size-1) {
                    // update tail
                    tail = firstNode;
                }
                
                return true;
            }

            prevNode = walk;
            walk = walk.getNext();
            i++;
        }

        return false;
    }
   
}

