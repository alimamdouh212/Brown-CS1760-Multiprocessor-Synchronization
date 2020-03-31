package com.company;

public class Bitonic {
    int width;
    Bitonic[]half;
    Merger merger;
    Bitonic(int width)
    {
        this.width=width;
        merger=new Merger(width);
        if(width>2)
        {
            half=new Bitonic[2];
            half[0]=new Bitonic(width/2);
            half[1]=new Bitonic(width/2);

        }
    }
    int travel(int input)
    {
        int output=0;


        if(width>2)
        {
            int in=input/(width/2);
            int inputt=input-in*(width/2);
            output=half[in].travel(inputt);
            output+=in*(width/2);

        }
        return merger.travel(output);
    }

}
