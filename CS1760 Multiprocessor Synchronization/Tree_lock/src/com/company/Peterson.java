package com.company;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Peterson {
    volatile int turn;
    volatile boolean intersted0=false;
    volatile boolean intersted1=false;// for Cache Coherence
    static int count=0;
    int id;
    Peterson()
    {
        id=count++;
    }
    void accuire(int i
    ) throws Exception {

        if(i==0)
        {

            intersted0=true;
            turn=0;
            boolean first=true;
            while (turn==i&&intersted1)
            {
                if(first)
                {
                    first=false;
                   // System.out.println("the thread "+i+ " waiting in lock "+id );
                }
            };
            //System.out.println("the thread "+i+ "stopped waiting in lock "+id );
        }
        else if (i==1) {

            intersted1=true;
            turn=1;
            boolean first=true;
            while (turn==i&&intersted0)
            {

                if(first)
                {
                    first=false;
                   // System.out.println("the thread "+i+ " waiting in lock "+id );
                }
            };
            //System.out.println("the thread "+i+ "stopped waiting in lock "+id );
        }
        else
        {
            throw (new Exception());
        }




    }
    void giveup(int i) throws Exception {


        if(i==0)
        {
           intersted0=false;
            //System.out.println("the thread "+i+ "gave up lock "+id );


        }
        else if (i==1) {
           // System.out.println("the thread "+i+ "gave up lock "+id );
           intersted1=false;
        }
        else
        {
            throw (new Exception());
        }



    }


}
