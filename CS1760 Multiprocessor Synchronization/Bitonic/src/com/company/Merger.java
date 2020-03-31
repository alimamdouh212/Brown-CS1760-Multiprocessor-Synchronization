package com.company;

public class Merger {
    int width;
    Balncer[] fronts;
    Merger[] half;
    Merger(int width)
    {
        this.width=width;
        fronts=new Balncer[width/2];
        for(int i=0;i<width/2;i++)
            fronts[i]=new Balncer();
        if(width>2)
        {
            half=new Merger[]{new Merger(width/2),new Merger(width/2)};
        }
    }
    int travel(int input)
    {
        int ouput;
        if(width==2)
        {
            return fronts[0].travel();
        }
        if(input>=width/2)
        {
            ouput=half[input%2].travel(input/2);
        }else {

           ouput= half[1-input%2].travel(input/2);

        }
        return 2*ouput+fronts[ouput].travel();

    }

}
