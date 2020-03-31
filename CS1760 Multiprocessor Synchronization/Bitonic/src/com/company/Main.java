package com.company;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    static int realcounter=0;
    static int n=64;
    static Merger m=new Merger(n);
    static Bitonic bit=new Bitonic(n);
    static AtomicInteger[] reals;

     static Thread gentherad(int id1)
     {

         return new Thread(new Runnable() {
             @Override
             public void run() {
                 Random r=new Random();
                 try {
                     Thread.sleep(r.nextInt(100));
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                int get=bit.travel(id1);
                reals[get].getAndIncrement();

                // m.getoutput(id1);

             }});
     }
    public static void main(String[] args) {

       Balncer b1=new Balncer();
       reals=new AtomicInteger[n];

       for(int i=0;i<n;i++)
       {
           reals[i]=new AtomicInteger(0);
       }
        /*for(int i=0;i<n;i++)
        {
          threads[i]=  new Thread(new Runnable() {
                @Override
                public void run() {
                    Random r=new Random();
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int loop=r.nextInt(4);
                    for(int i=0;i<loop;i++)
                    {
                        System.out.println(b1.getoutput());


                    }
                    synchronized (b1)
                    {

                        realcounter+=loop;
                    }

                }
            });
          threads[i].start();
        }
        for(int i=0;i<n;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(realcounter);*/


        int nt1=2;
        int nt2=63;
        int mid=n/2;
        Thread []threads=new Thread[nt1+nt2];

      /*  final int [] numpers=new int[nt1+nt2];
        for (int i=0;i<nt1;i++)
        {
            final int threadid=i%(mid);

            threads[i]=gentherad(threadid);
            threads[i].start();
        }
          for (int i=0;i<nt2;i++)
          {

              final int threadid=i%(mid)+mid;

              threads[i+nt1]=gentherad(threadid);
              threads[i+nt1].start();
          }
          for (int i=0;i<nt1+nt2;i++)
          {
              try {
                  threads[i].join();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          for(int i=0;i<n;i++)
          System.out.println(reals[i].get());*/
      int t=300;
      for(int i=0;i<t;i++)
      {
              threads[i%n]=gentherad(i%n);
              threads[i%n].start();
      }
        for (int i=0;i<t;i++)
        {
            try {
                threads[i%n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<n;i++)
            System.out.println(reals[i].get());



    }
}
