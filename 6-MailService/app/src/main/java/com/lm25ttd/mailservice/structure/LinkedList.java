package com.lm25ttd.mailservice.structure;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {
    public static final String LIST_SPLIT = " -> ";

    private Node head;

    public LinkedList(){
        head = null;
    }

    /**
     * Add a node to the begin of list
     * @param node
     */
    public void add(Node node) {
        if(head == null){
            head = node;
            node.setNext(null);
        } else {
            node.setNext(head);
            head = node;
        }
    }

    /**
     * Add a node to the end of list
     * @param node
     */
    public void push(Node node) {
        node.setNext(null);

        if(head == null){
            head = node;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(node);
        }
    }

    /**
     * Build a string with the current content of the list
     * @return
     */
    public String print(){
        StringBuffer sb = new StringBuffer();
        Node current = head;

        while (current != null){
            sb.append(current.getData());
            sb.append(LIST_SPLIT);

            current = current.getNext();
        }

        return sb.toString();
    }

    /**
     * Remove all duplicate entries in the list
     */
    public void removeDuplicates()
    {
        Set<String> uniques = new HashSet<>();

        Node current = head;
        Node previous = null;

        while (current != null)
        {
            String currentData = current.getData();

            if (uniques.contains(currentData)) {
                // When the item was previously found, we remove it from the list
                previous.setNext(current.getNext());
            } else {
                // Otherwise, we store it in the hashTable for future comparison
                uniques.add(currentData);
                previous = current;
            }

            current = current.getNext();
        }

    }
}
