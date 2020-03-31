package com.company;

import java.util.HashMap;

public class Locknode extends Linked_list_node<Peterson> {



    volatile int was3=-1;
    HashMap<Integer,Integer> map;
    int l=-1;
    boolean singelson=false;
    Locknode(Peterson lock)

    {

        super(lock);
        //was3.set(-1);

    }
    int arrivers=0;
   /* void makeroot()
    {
        map=new HashMap<>();
    }
    void arrive()
    {
        map.put((int) Thread.currentThread().getId(),arrivers);
        arrivers++;
    }
    int getarrivenumper()
    {
        return map.get((int)Thread.currentThread().getId());
    }*/
    void acuire(int i)
    {

        if(i==was3)
            return;
        else {

            try {
                data.accuire(i);
            } catch (Exception e) {
                System.out.println("wrong index "+ i);
                e.printStackTrace();
            }


        }

    }
    void giveup(int i)
    {

        try {
            LInked_locks.n=Main.zeropreix(LInked_locks.n,l);

            data.giveup(i);
        } catch (Exception e) {
            System.out.println("wrong index "+ i);
            e.printStackTrace();
        }
    }
}
