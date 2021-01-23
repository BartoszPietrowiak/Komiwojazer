package prog;
import java.util.ArrayList; 
import java.util.Random;
import java.awt.*;
public class Graph {
    private final int size; //number of vertex
    private final ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>(); //2D array of Edges
    private final ArrayList<Vertex> vertexes = new ArrayList<Vertex>(); //array of vertex
    //construct the Graph passing on the size value
    public Graph(int siz)
    {
        this.size=siz;
    }
    //generate the coordinates of vertex and addvertex to the list of them, generate Edges and add them to the array.
    public void generate()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //variable that contain the size of user screen
        ArrayList<Integer> numbersx = new ArrayList<Integer>(); //avaiable coordinates on the axis x
        ArrayList<Integer> numbersy = new ArrayList<Integer>();//avaiable coordinates on the axis y
        Random randomGenerator = new Random(); //define the random generator
        for(int i=0;i<screenSize.width;i=i+(screenSize.width/size)) //generate the numbers from 0 to user screen width with the equal spacing
        {
            numbersx.add(i); //add number to the axis x coordinates list
        }
        for(int i=0;i<screenSize.height-100;i=i+((screenSize.height-100)/size))//generate the numbers from 0 to user screen height with the equal spacing
        {
            numbersy.add(i); //add number to the axis x coordinates list
        }    
        while (numbersx.size() > 0) //pick a random value from array of axis x and y then create new vertex,add the coords to them and 
        {
            int randomx = randomGenerator.nextInt(numbersx.size());//pick a random value from array of axis x
            int randomy = randomGenerator.nextInt(numbersy.size());//pick a random value from array of axis y
            Vertex v = new Vertex(); //create new Vertex
            v.addcoords(numbersx.get(randomx), numbersy.get(randomy)); //add coords to the Vertex
            this.addVertex(v); //add vertex to the list of them in graph
            numbersx.remove(randomx); //delete the used value of axis x
            numbersy.remove(randomy);//delete the used value of axis y
        }  
        for(int i=0;i<size;i++)
        {
            ArrayList<Edge> edges = new ArrayList<Edge>(); //create a row of edges
            for(int j=i+1;j<size;j++)
            {
                int tab1[]=vertexes.get(i).coords(); //get coords of one vertex
                int tab2[]=vertexes.get(j).coords();//get coords of second vertex
                Edge e =  new Edge(); //create new edge
                //count the length between two vertex using the cooridnate system and Pythagorean theorem
                double weight= Math.round(Math.sqrt(Math.pow((Math.abs(Double.valueOf(tab1[0])-Double.valueOf(tab2[0]))),2)+Math.pow((Math.abs(Double.valueOf(tab1[1])-Double.valueOf(tab2[1]))),2)));
                e.addedge(i, j, weight); //add values to the edge
                edges.add(e); //add edge to the row
            }
            this.addEdge(edges); //add row to the 2D arraylist
        }
    }
    public void addEdge(ArrayList<Edge> e1) //add edge to the arraylist of them
    {
        edges.add(e1);
    }
    public void addVertex(Vertex v1) //add vertex to the arraylist of them
    {
        vertexes.add(v1);
    }
    public Edge printEdge(int i, int j) //return the Edge from arraylist of them, its search by index of 2D arraylist
    {
        return edges.get(i).get(j);
    }
    public Vertex printVertex(int i) //return the Vertex from arraylist of them, its search by index
    {
        return vertexes.get(i);
    }
    public int[] printVertex2(int i)//return the Vertex coordinates from arraylist of them, its search by index
    {
        return  vertexes.get(i).coords();
    }
    public int printSize() //return the numbers of vertex in  Graph
    {
        return size;
    }
    
}
