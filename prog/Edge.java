package prog;
public class Edge {  
    private int to,from;
    private double length;
    //contruct Edge with default value
    public Edge()
    {
        this.to=0;
        this.from=0;
        this.length=0;
    }
    //add values to Edge
    public void addedge(int to, int from, double len)
    {
        this.to=to;
        this.from=from;
        this.length=len;
    }
    //print Edge to and form values
    public int[] printedge()
    {
        return new int[]{to,from};
    }
    //print Edge length
    public double print_length()
    {
        return length;
    }
}
