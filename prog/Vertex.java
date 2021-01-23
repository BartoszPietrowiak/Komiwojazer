package prog;
public class Vertex {
    private int x,y;
    //Construct Vertex with default values
    public Vertex()
    {
        this.x=0;
        this.y=0;
    }
    //add coordinates to Vertex
    public void addcoords(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    //print coords of Vertex
    public int[] coords()
    {
        return new int[] {x,y};
    }
}
