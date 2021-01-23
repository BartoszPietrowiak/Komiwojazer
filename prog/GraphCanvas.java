package prog;

import java.awt.*;
import java.util.ArrayList;

public class GraphCanvas extends Canvas {
    private ArrayList<Integer> bestpopulation;
    Graph grap;
    //Construct GraphCanvas with bestpopulation array and Graph object.
    public GraphCanvas(ArrayList<Integer> best, Graph g)
    {
        this.bestpopulation=best;
        this.grap=g;
    }
    @Override
    //use print function to visualize Graph with the shortest way
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        //print Rectangle uses coordinates from graph vertex and then write the index of the vertex
        for (int i=0;i<grap.printSize();i++)
        {
            int tab[] =grap.printVertex2(i);
            g.setColor(Color.BLACK);
            g.fillRect(tab[0], tab[1], 20, 20);
            g.setColor(Color.WHITE);
            if(i>8)
            {
                g.drawString(String.valueOf(i), tab[0]+3,tab[1]+13); 
            }
            else if (i<9)
            {
                g.drawString(String.valueOf(i), tab[0]+7,tab[1]+13); 
            }
            else if(i>98)
            {
                g.drawString(String.valueOf(i), tab[0],tab[1]+13); 
            }
        }
        //get coordinates of two vertex of a way and print the line to connect them
        for(int i =0;i<bestpopulation.size();i++)
        {
            int x1,x2=0;
            double weight=0;
            g.setColor(Color.BLUE);
            if(i<bestpopulation.size()-1)
            {
                 x1 = bestpopulation.get(i);
                 x2 = bestpopulation.get(i+1);
            }
            else
            {
                 x1 = bestpopulation.get(i);
                 x2 = bestpopulation.get(0);
            }
            int tab1[] =grap.printVertex2(x1);
            int tab2[] =grap.printVertex2(x2);
            //specify the position of the two vertex
            if(tab1[0]<tab2[0])
            {
                if(tab1[1]<tab2[1])
                {
                    tab1[0]=tab1[0]+20;
                    tab1[1]=tab1[1]+20;
                }
                else if (tab1[1]>tab2[1])
                {
                    tab2[1]=tab2[1]+20;
                    tab1[0]=tab1[0]+20;
                }
                else
                {
                    tab1[0]=tab1[0]+20; 
                    tab1[1]=tab1[1]+10;
                    tab2[1]=tab2[1]+10;
                }
            }
            else if(tab1[0]>tab2[0])
            {
                if(tab1[1]<tab2[1])
                {
                    tab1[1]=tab1[1]+20;
                    tab2[0]=tab2[0]+20;
                }
                else if (tab1[1]>tab2[1])
                {
                    tab2[1]=tab2[1]+20;
                    tab2[0]=tab2[0]+20;
                }
                else
                {
                    tab2[0]=tab2[0]+20; 
                    tab2[1]=tab2[1]+10;
                    tab1[1]=tab1[1]+10;
                }
            }
            else 
            {
                if(tab1[1]<tab2[1])
                {
                    tab1[1]=tab1[1]+20;
                    tab1[0]=tab1[0]+10;
                    tab2[0]=tab2[0]+10;
                }
                else if (tab1[1]>tab2[1])
                {
                    tab2[1]=tab2[1]+20;
                    tab2[0]=tab2[0]+10;
                    tab1[0]=tab1[0]+10;
                }
            }
            //drawing a line
            g.drawLine(tab1[0], tab1[1], tab2[0],tab2[1]);
            g.setColor(Color.BLACK);
            //get length of a line between two vertex
            if(x1<x2)
            {   
                weight=grap.printEdge(x1, x2-x1-1).print_length();
            }
            else
            {
                weight=grap.printEdge(x2, x1-x2-1).print_length();
            }
            //count the position of the center of a line and draw the length of this line
            g.drawString(String.valueOf(weight), ((tab1[0]+tab2[0])/2), (tab1[1]+tab2[1])/2);
        }
    }
}

