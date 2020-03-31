package com.company;

import java.util.concurrent.locks.Lock;

public class LInked_locks extends Linked_list<Locknode> {
    //Linked_list<Locknode> linked_list;
    static int levels;
    static int n=0;

    LInked_locks()
    {
        super();
       //inked_list=new Linked_list<>();
        //int l=0;
        //int mynumper;
    }
    LInked_locks makefirsti(Peterson data)
    {
        Linked_list_node newhead;
        Linked_list_node first;

        first=new Locknode(data);
        first.make_next(head.next);
        LInked_locks r=new LInked_locks();
        r.head.make_next(first);
        r.tail=tail;
        return r;

    }
    LInked_locks addlock()
    {
        Peterson p=new Peterson();

        return makefirsti(p);

    }
    void print(int thread,String s,int lock)
    {
        System.out.printf("the thread %d %s the lock %d \n ",thread,s,lock);
    }
    void accuire(int mynumper)
    {
        if(mynumper==1)
            System.out.println("");
        Linked_list_node lock= head;

        int in=0;
        while (lock.next!=tail)
        {
            int i=mynumper/(1<<in);
            //System.out.printf("the thread %d tried to accuire the lock %d \n",mynumper,((Peterson)((lock.next).data)).id);
            print(mynumper,"tried to accuire",((Peterson)((lock.next).data)).id);
            /*if(Thread.currentThread().getId()==1&&((Peterson)((lock.next).data)).id==0)
                System.out.println("");*/
            ((Locknode)lock.next).acuire(i%2);
           // System.out.printf("the thread %d  accuired the lock %d \n",mynumper,((Peterson)((lock.next).data)).id);
            print(mynumper," accuired",((Peterson)((lock.next).data)).id);

            lock=(Locknode) lock.next;
            in++;

        }
        //((Locknode)(tail.prev)).arrive();


    }
    void giveup(int mynumper)
    {
        //giveup2( ((Locknode)(tail.prev)).getarrivenumper(),mynumper);
        giveup2(n,mynumper);
    }
    void giveup2(int i,int mynumper)
    {
        int i1=Main.firstone(i);
        int i2=-1;
        if(i==0)
        {
            i2=1000;
        }
        else
        try {
            i2=Main.firstzero(i);
        } catch (Exception e) {
            System.out.print("invaid input to give up");
        }
        if(i%2==1)
        {


            Linked_list_node lock= head;
            int in=0;
            while (lock.next!=tail)
            {
                if(in==i1)
                {
                    int indexlock=mynumper/(1<<in);
                    //System.out.printf("the thread %d gave up %d \n",mynumper,((Peterson)((lock.next).data)).id);
                    print(mynumper,"gave up",((Peterson)((lock.next).data)).id);

                    ((Locknode)lock.next).giveup(indexlock%2);
                    if(((Locknode)lock.next).singelson)
                    {
                        int t1=LInked_locks.n;
                        t1--;
                        LInked_locks.n|=t1;
                        giveup(mynumper);

                    }

                }
                lock=lock.next;
                in++;
            }
        }else {
            if(i==4)
                System.out.println();
            Linked_list_node lock= head;
            int in=0;

            int indexlock=mynumper/(1<<in);
            int arbitary=1;//to force the compilr not to rearrang the instructions
            while (lock.next!=tail)
            {
                //if(arbitary==1)
                   // System.out.println(1);
                arbitary++;
                if(in==i2+1)
                break;
                    indexlock=mynumper/(1<<in);


                    ((Locknode)lock.next).was3=(indexlock%2);

                   // System.out.println("the thread was3");
                    lock=lock.next;

                in++;
            }
            if(arbitary>=1) {
                //System.out.println(2);
                //System.out.printf("the thread %d gave up %d \n",mynumper,((Peterson)((head.next).data)).id);
                print(mynumper,"gave up",((Peterson)((head.next).data)).id);

                ((Locknode) head.next).giveup(mynumper % 2);
                if(((Locknode)head.next).singelson)
                {
                    int t1=LInked_locks.n;
                    t1--;
                    LInked_locks.n|=t1;
                    giveup(mynumper);

                }

            }


        }


    }

}
