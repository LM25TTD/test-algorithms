package com.lm25ttd.removeduplicates;

import com.lm25ttd.removeduplicates.structure.LinkedList;
import com.lm25ttd.removeduplicates.structure.Node;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the {@link LinkedList} class for expected behaviors
 *
 */
public class LinkedListUnitTest {
    @Test
    public void add_node_isCorrect() {

        String expected = "D -> C -> B -> A -> ";

        LinkedList list = new LinkedList();

        list.add(new Node("A"));
        list.add(new Node("B"));
        list.add(new Node("C"));
        list.add(new Node("D"));

        String actual = list.print();

        assertEquals(expected, actual);
    }

    @Test
    public void push_node_isCorrect() {

        String expected = "A -> B -> C -> D -> ";

        LinkedList list = new LinkedList();

        list.push(new Node("A"));
        list.push(new Node("B"));
        list.push(new Node("C"));
        list.push(new Node("D"));

        String actual = list.print();

        assertEquals(expected, actual);
    }

    @Test
    public void push_duplicates_isCorrect() {

        String expected = "A -> B -> B -> B -> C -> C -> D -> D -> ";

        LinkedList list = new LinkedList();

        list.push(new Node("A"));
        list.push(new Node("B"));
        list.push(new Node("B"));
        list.push(new Node("B"));
        list.push(new Node("C"));
        list.push(new Node("C"));
        list.push(new Node("D"));
        list.push(new Node("D"));

        String actual = list.print();

        assertEquals(expected, actual);
    }

    @Test
    public void remove_duplicates_isCorrect() {

        String expected = "A -> B -> B -> B -> C -> C -> D -> D -> ";

        LinkedList list = new LinkedList();

        list.push(new Node("A"));
        list.push(new Node("B"));
        list.push(new Node("B"));
        list.push(new Node("B"));
        list.push(new Node("C"));
        list.push(new Node("C"));
        list.push(new Node("D"));
        list.push(new Node("D"));

        String actual = list.print();

        // Expected before remove duplicates
        assertEquals(expected, actual);

        expected = "A -> B -> C -> D -> ";

        list.removeDuplicates();

        actual = list.print();

        // Expected after remove duplicates
        assertEquals(expected, actual);
    }


}