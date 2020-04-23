package com.lm25ttd.listintersection;


import com.lm25ttd.listintersection.structure.LinkedList;
import com.lm25ttd.listintersection.structure.Node;

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

    @Test
    public void count_isCorrect() {

        int expected = 4;

        LinkedList list = new LinkedList();

        list.push(new Node("A"));
        list.push(new Node("B"));
        list.push(new Node("C"));
        list.push(new Node("D"));

        int actual = list.getCount();

        assertEquals(4, actual);
    }


    @Test
    public void intersection_point_isCorrect() {

        Node c = new Node("C");
        Node a = new Node("A");
        Node e = new Node("E");
        Node h = new Node("H");

        Node d = new Node("D");
        Node f = new Node("F");

        Node j = new Node("J"); // <- Intersection point
        Node b = new Node("B");
        Node a2 = new Node("A");


        LinkedList listA = new LinkedList();
        listA.push(c);
        listA.push(a);
        listA.push(e);
        listA.push(h);
        listA.push(j);
        listA.push(b);
        listA.push(a2);

        LinkedList listB = new LinkedList();
        listB.push(d);
        listB.push(f);
        listB.push(j);
        listB.push(b);
        listB.push(a2);



        Node actual = listA.getIntersectionWith(listB);

        assertEquals(j, actual);
    }

    @Test
    public void no_intersection_point_isCorrect() {

        Node c = new Node("C");
        Node a = new Node("A");
        Node e = new Node("E");
        Node h = new Node("H");
        Node d = new Node("D");
        Node f = new Node("F");
        Node j = new Node("J");
        Node b = new Node("B");
        Node a2 = new Node("A");


        LinkedList listA = new LinkedList();
        listA.push(c);
        listA.push(a);
        listA.push(e);
        listA.push(h);

        LinkedList listB = new LinkedList();
        listB.push(d);
        listB.push(f);
        listB.push(j);
        listB.push(b);
        listB.push(a2);

        Node actual = listA.getIntersectionWith(listB);

        assertEquals(null, actual);
    }


}