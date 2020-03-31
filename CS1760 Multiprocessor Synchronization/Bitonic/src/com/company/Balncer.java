package com.company;

public class Balncer {
    boolean value=false;
    int rotation=0;
    synchronized int travel()
    {
        try{
            if(value) {
                rotation++;
                return 1;
            }
            else{

                return 0;}
        }
        finally {
            value=!value;
        }
    }
    synchronized int getoutput()
    {
        int r=rotation;
        return r*2+travel();
    }

}
