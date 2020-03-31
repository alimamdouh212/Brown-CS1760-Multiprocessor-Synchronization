package com.company;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Condions_queue {
    Lock lock;
    LinkedList<node> queue;
    Condions_queue(Lock l)
    {
        lock=l;
        queue=new LinkedList<node>();

    }
   Condition add(boolean aob)
    {
        node clan;
        if(getsize()!=0&&queue.getLast().aliceorbob==aob)
        {
            clan=queue.getLast();
        }
        else
       {
            clan=new node(lock,aob);
            queue.add(clan);

        }

            clan.add();
        return clan.condition;

    }
    HashSet<Long> getfree()
    {

        node n=queue.removeFirst();
        n.condition.signalAll();
        return n.set;

    }
    int getsize()
    {
        return queue.size();
    }

    private class node
    {
        HashSet<Long> set;
        Condition condition;
        boolean aliceorbob;

        node(Lock l,boolean aob)
        {
            condition=l.newCondition();
            aliceorbob=aob;
            set=new HashSet<>();

        }
        void add()
        {

            set.add(Thread.currentThread().getId());

        }


    }

}
