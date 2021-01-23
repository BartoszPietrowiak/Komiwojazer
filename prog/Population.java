package prog;
import java.util.ArrayList;
import java.util.Random;

public class Population {
    private ArrayList<ArrayList<Integer>> populations = new ArrayList<ArrayList<Integer>>(); //2D arraylist of possible ways to walk throught the graph
    private ArrayList<ArrayList<Double>> ways = new ArrayList<ArrayList<Double>>(); //2D arraylist of index of way and the length of this way
    private Graph g; //generated graph
    private int size; //number of vertexs
    //construct the new Population passing graph and size of graph
    Population(Graph g1,int siz)
    {
        this.g=g1;
        this.size=siz;
    }
    //generate 10 possible ways of walking throught the Graph
    public void starpopulation()
    {
        Random randomGenerator = new Random();  //define the random generator
        for (int i=0;i<10;i++)
        {
            ArrayList<Integer> permu = new ArrayList<Integer>(); //create a row to the populations arraylist
            while (permu.size() < size) //this fragment of code is running till the row size is not equal to size of graph
            {
                int random = randomGenerator.nextInt(size); //pick a random value from 0 to size
                    if (!permu.contains(random)) //check if a row contain this number
                    {
                        permu.add(random); //add a number
                    }
            }
            populations.add(permu); // add a row to the populations arraylist
        }
    }
    public void countway() //count a way from populations arraylist
    {
        for (int i=0;i<populations.size();i++) 
        {
            double way=0;
            ArrayList<Integer> one =new ArrayList<Integer>(); //row from populations arraylist
            ArrayList<Double> one_way =new ArrayList<Double>(); //row of the ways arraylist
            one = populations.get(i); //get one of the row from arraylist populations
            for (int j=0;j<size;j++)//count the length of way from one row of population arraylist
            {
                if(j==size-1)
                {
                             
                    int x = one.get(j); //get first vertex from row
                    int y = one.get(0); //get second vertex from row
                    if(x<y)
                    {
                        Edge e=g.printEdge(x,y-x-1); //get a edge between this two vertex
                        double part=e.print_length(); //get a length of this edge
                        way+=part; //add a length to total way of this walk
                    }
                    else
                    {
                        Edge e=g.printEdge(y,x-y-1);//get a edge between this two vertex
                        double part=e.print_length();//get a length of this edge
                        way+=part; //add a length to total way of this walk
                    }
                }
                else
                {
                    int x = one.get(j);//get first vertex from row
                    int y = one.get(j+1);//get second vertex from row
                    if(x<y)
                    {
                        Edge e=g.printEdge(x, y-x-1);//get a edge between this two vertex
                        double part=e.print_length();//get a length of this edge
                        way+=part;//add a length to total way of this walk
                    }
                    else
                    {
                        Edge e=g.printEdge(y, x-y-1);//get a edge between this two vertex
                        double part=e.print_length();//get a length of this edge
                        way+=part;//add a length to total way of this walk
                    }
                }
            }
            one_way.add(Double.valueOf(i)); //add index of row populations to row of arrraylist ways
            one_way.add(way);//add total length of walk of row populations to row of arrraylist ways
            ways.add(one_way); //add row to the arralist ways
        }
        ways.sort(new CustomComparator(1)); //sort the 2D arraylist ways by the second column (length)

    }
    //delete the worst 5 rows from arraylist populations and ways
    public void deletebadpopulations()
    {
        int start=populations.size()-5; //define the start index of delete proccess
        int end=populations.size();//define the end index of delete proccess
        if(start>=5) //check if start index is equal or greater than 5
        {
            for(int i=end-1;i>=start;i--) //start delete from end to start index
            {
                int index =ways.get(i).get(0).intValue();
                populations.remove(index); //remove the row populations 
                for (int j=0;j<ways.size();j++) //delete row from arralist of ways
                {
                    if(ways.get(i).get(0)<ways.get(j).get(0))
                    {
                        ways.get(j).set(0,ways.get(j).get(0)-1);//reduce the index of rows in ways arraylist
                    }
                }
            }
            int way = ways.size()-1; 
            for(int i=way;i>=start;i--)
            {
                ways.remove(i); //remove the row from arraylist ways
            }
        }
        else
        {
            System.out.println("Nie mozna usunac ostanich 5 elementow populacji!"); //print error when the arraylist of populations have only 5 or less rows.
        }
    }
    public void printpopulation() //print all the pupulations
    {
        for(int i=0;i<populations.size();i++)
        {
            System.out.print(populations.get(ways.get(i).get(0).intValue())); //print row from populations array
            System.out.print(" "); 
            System.out.print(ways.get(i).get(1)); //print the length of the populations walk
            System.out.println("");  
        }
        System.out.println("");
    }
    public ArrayList<Integer> printbest() //print best populations by first index in first row in ways arraylist
    {
        return populations.get(ways.get(0).get(0).intValue());
    }
    public void cross() //crossing the rows of populations arraylsit
    {
        for(int j=0;j<5;j++) //make 5 random crossing
        {
            Random randomGenerator = new Random(); //define random generator
            int one,two;
            do{//take first random row from populations arraylist //take second random row from populations arraylist
                one=  randomGenerator.nextInt(populations.size());
                two = randomGenerator.nextInt(populations.size());
            }while(one==two);//check if the rows are not the same, if they are pick again
                ArrayList<Integer> mut= new ArrayList<Integer>(); //deifne the crossing row 
                for(int i=0;i<populations.get(0).size()/2;i++) //take a half of values from first row and add it
                {
                        mut.add(populations.get(one).get(i));
                }
                int i=0;
                while(mut.size()<populations.get(0).size()) //then take the rest of missing values from the second row and add it in the order that they are in the second row
                {
                    if(!mut.contains(populations.get(two).get(i)))
                    {
                        mut.add(populations.get(two).get(i));
                        i++;
                    }
                    else
                    {
                        i++;
                    }
                }
                if (!populations.contains(mut)) //check if populations dont have the same row and add the row when dont have it.
                    {
                        populations.add(mut);
                        
                    }
                else
                {
                    j--;
                }
                
        }
    }
    public void clear() //clear the ways arraylist
    {
        ways.clear();
    }
    public void mutate() //mutate the random rows from populations arraylist and add it as its new
    {
        Random randomGenerator = new Random();  //define the random generator
        for (int i=0;i<2;i++) //make 2 mutations from populations arraylist
        {
            int random = randomGenerator.nextInt(size); //define the random index from populations arraylist.
            ArrayList<Integer> permu = populations.get(random); //get a row from populations arraylist
            ArrayList<Integer> permu1 = new ArrayList<Integer>(); //define the new row
            int random1,random2;
            do{ //pick a random index element from row//pick another random index element from row
                random1 = randomGenerator.nextInt(populations.get(0).size());
                random2 = randomGenerator.nextInt(populations.get(0).size());
            }while(random1==random2);//check if the values are not the same, if they are pick again
            for(int j=0;j<permu.size();j++) //add values from picked row to the now row but replace the elements picked in random1 and random2 variables
            {
                if(permu.get(j)==random1)
                {
                   permu1.add(random2); 
                }
                else if(permu.get(j)==random2)
                {
                   permu1.add(random1); 
                }
                else
                {
                    permu1.add(permu.get(j));
                }

            }
            if(!populations.contains(permu1)) //check if the populations arraylist dont contain the same row that we want to add
            {
                populations.add(permu1); //add new row to populaions arraylist 
            }
            else
            {
                i--;
            }
        }
    }
}
