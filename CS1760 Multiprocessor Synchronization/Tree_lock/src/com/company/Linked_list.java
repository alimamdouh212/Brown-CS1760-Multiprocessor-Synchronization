package com.company;

public class Linked_list<T> {
     Linked_list_node head;
     Linked_list_node tail;
    Linked_list()
    {
        tail=new Linked_list_node(null);
        head=new Linked_list_node(null);
        head.make_next(tail);

    }
    Linked_list makefirst(T data)
    {
        Linked_list_node newhead;
        Linked_list_node first;

        first=new Linked_list_node(data);
        first.make_next(head.next);
        Linked_list r=new Linked_list();
        r.head.make_next(first);
        r.tail=tail;
        return r;

    }
}
