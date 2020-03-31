package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    static int firstone(int i)
    {
        int r1=(i^i+1);
        int r2=r1&(i+1);

        double d3=  Math.log(r2);
        d3/=Math.log(2);
        int r3=(int)(Math.round(d3));
        return r3;

    }
    static int firstzero(int i) throws Exception {
        if(i<=0)
            throw (new Exception());
        int r1=firstone(i-1);
        return r1;

    }
    static volatile int counte=0;
    static Thread generatetest(int i,LInked_locks lock)
    {
        return  new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("the thread "+i+" running");
                Random random=new Random();
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                   lock.accuire(i);
                    System.out.println("the thread "+i+" enter");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("the thread "+i+" increades "+ counte++);
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                System.out.println("the thread exit "+i);
                try {
                    lock.giveup(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
    static int nofthreads=0;
    static ArrayList<Thread> threads=new ArrayList<>();
    static void dfs(int level,int n,LInked_locks lock)
    {
        int noflevel=(int)Math.ceil((Math.log(n)/Math.log(2)));
        ((Locknode)lock.head.next).l=noflevel-level;
        if(level==noflevel)
        {
            threads.add(generatetest(nofthreads,lock));
            nofthreads++;
            if(nofthreads<n)
            {
                threads.add(generatetest(nofthreads,lock));
            nofthreads++;}
            else {
                ((Locknode)lock.head.next).singelson =true;
            }
            return;
        }

        dfs(level+1,n,lock.addlock());
        if(nofthreads<n)
            dfs(level+1,n,lock.addlock());
        else {
            ((Locknode)lock.head.next).singelson =true;
        }

    }
    static void generatesthreads(int n)
    {
        int noflevel=(int)Math.ceil((Math.log(n)/Math.log(2)));
        LInked_locks.levels=noflevel;

        LInked_locks l0=new LInked_locks();

        l0=l0.addlock();
        //((Locknode)(l0.head.next)).makeroot();

        dfs(1,n,l0);


    }
    static int zeropreix(int x,int n)
    {
        x/=(1<<n);
        x|=1;
        x=x<<n;
        return x;

    }

    public static void main(String[] args)  {
	// write your code here

       /* int notest=100;

        while ((notest--)>0)
        {
            Peterson p=new Peterson();
            Thread t1= generatetest(0,p);
            Thread t2=generatetest(1,p);
            t1.start();

            t2.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
       /*Linked_list<Integer> t=new Linked_list();
       for(int i=0;i<10;i++)
       {

           t=t.makefirst(i);
           Linked_list_node<Integer> head=t.head;
           while (head.next!=t.tail)
           {
               System.out.print(head.next.data);
               head=head.next;
           }
           System.out.println();
           Linked_list_node<Integer> tail=t.tail;
           while (tail.prev!=t.head)
           {
               System.out.print(tail.prev.data);
               tail=tail.prev;
           }
           System.out.println();
       }*/
        /*

        LInked_locks l0=new LInked_locks();

        l0=l0.addlock();
        //((Locknode)(l0.head.next)).makeroot();
        LInked_locks l1=l0.addlock();
        LInked_locks l11=l1.addlock();
        LInked_locks l12=l1.addlock();
        LInked_locks l2=l0.addlock();
        LInked_locks l21=l2.addlock();
        LInked_locks l22=l2.addlock();


        Thread t0=generatetest(0,l11);
        Thread t1=generatetest(1,l11);

        Thread t2=generatetest(2,l12);
        Thread t3=generatetest(3,l12);
        Thread t4=generatetest(4,l21);
        Thread t5=generatetest(5,l21);
        Thread t6=generatetest(6,l22);
        Thread t7=generatetest(7,l22);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();*/
       generatesthreads(40);
        for(int i=0;i<threads.size();i++)
            threads.get(i).start();

    }
}
