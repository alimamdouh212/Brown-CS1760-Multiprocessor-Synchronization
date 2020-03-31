package com.company;

public class Linked_list_node<T> {
    T data;
    Linked_list_node next;
    Linked_list_node prev;
    Linked_list_node(T d)
    {

        data=d;

    }
    void make_next(Linked_list_node n)
    {
        next=n;
        n.prev=this;
    }

}
