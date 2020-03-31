package com.company;



import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static Lock l;
    static int alics=0;
    static int bobs=0;
    static HashSet<Long> permeted;
    static Condions_queue queue;
    static void strated(boolean bor)
    {
        System.out.printf("%s s thred %d started \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());
    }
    static void entred(boolean bor)
    {

        System.out.printf("%s s thred %d enterd \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());


    }
    static void waiting(boolean bor)
    {
        System.out.printf("%s s thred %d waiting \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());
    }
    static void stopwaiting(boolean bor)
    {
        System.out.printf("%s s thred %d stopwaiting \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());
    }
    static void enteredwaithoutwaiting(boolean bor)
    {
        System.out.printf("%s s thred %d enteredwaithoutwaiting \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());
    }
    static void freed(boolean bor)
    {
        System.out.printf("%s s thred %d freed \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());
    }
    static void leaving(boolean bor)
    {
        System.out.printf("%s s thred %d leaving \n",bor? "bob": "Alice",(int)Thread.currentThread().getId());
    }
    static void waitfortime(int t)
    {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static Runnable generate(boolean boa)
    {
        return new Runnable() {
            @Override
            public void run() {

                //strated(boa);
                l.lock();
                boolean waited=false;
                while (((boa&&alics!=0)||((!boa)&&bobs!=0))||((queue.getsize()>0)&&(permeted==null||(!permeted.contains(Thread.currentThread().getId())))))
                {
                    waiting(boa);
                    waited=true;
                    Condition c=queue.add(boa);
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                if(!waited)
                {
                    enteredwaithoutwaiting(boa);
                    if(boa)
                        bobs++;
                    else
                        alics++;
                }
                else {
                    stopwaiting(boa);

                }
                l.unlock();


                entred(boa);
                Random r=new Random();
                System.out.println(bobs+"   "+alics);
                waitfortime(r.nextInt(1000));








               l.lock();
               leaving(boa);
               boolean free=false;
               if (boa)
               {
                   bobs--;
                   if(bobs==0)
                       free=true;
               }
                  else {

                   alics--;
                   if(alics==0)
                       free=true;
                        }
               if(free&&queue.getsize()!=0)
               {
                   freed(boa);
                   permeted=null;
                   HashSet s=queue.getfree();
                   if(boa)
                       alics=s.size();
                   else
                       bobs=s.size();
                if(queue.getsize()!=0)
                {
                    permeted=s;
                }

               }


               l.unlock();


            }
        };

    }
    public static void main(String[] args) {


	    l=new ReentrantLock();
         queue=new Condions_queue(l);
        new Thread(generate(true)).start();
        new Thread(generate(true)).start();
        new Thread(generate(true)).start();
        new Thread(generate(false)).start();
        new Thread(generate(true)).start();
        new Thread(generate(false)).start();

        new Thread(generate(true)).start();
        new Thread(generate(false)).start();

        new Thread(generate(true)).start();
        new Thread(generate(false)).start();




    }
}
