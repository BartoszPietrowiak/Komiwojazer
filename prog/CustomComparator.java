package prog;
import java.util.ArrayList;
import java.util.Comparator; 
//Compare two two arrays with id of way and length of this way by the second column (length)
public class CustomComparator implements Comparator<ArrayList<Double>> {
    private final int index;
    public CustomComparator(int index) 
    {
        this.index = index;
    }
    public int compare(ArrayList<Double> first, ArrayList<Double> second) 
    {
        return first.get(index).compareTo(second.get(index));
    }
}
