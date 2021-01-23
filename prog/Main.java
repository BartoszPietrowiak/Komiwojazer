package prog;
import java.util.Scanner; 
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
public class Main {
    public static void main (String args[])
    { 
        //define the user interface
        int size=0;
        String s;
        boolean work=true;
        Scanner scanner = new Scanner(System.in);
        while(work)
        {
            try
            { 
                //enter the size of graph excluded the not integer values
                System.out.println("Podaj liczbe wierzcholkow grafu");
                s=scanner.nextLine();
                size=Integer.parseInt(s);
                work=false;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Podaj liczbe nie ciag znakow!");
            } 
        }
        Graph g = new Graph(size); //make graph
        g.generate(); //generate the coordinates and edges
        System.out.println("Graf o danej liczbie wierzcholkow zostal utworzony!");
        System.out.println("Wybierz dzialanie z listy:");
        System.out.println("1.Utworz populacje poczatkowa");
        System.out.println("2.Zamknij program");
        work=true;
        int option;
        String opt;
        while(work)
        {   
            try
            {
                //enter the options of action excluded not integer values
                opt=scanner.nextLine();
                option=Integer.parseInt(opt);
                switch(option)
                {
                    case 1:
                    {
                        Population p = new Population(g,size); //create population object
                        p.starpopulation(); //make start population
                        p.countway(); //count a ways for start population
                        //enter the options of action excluded not integer values
                        while(work)
                        {
                            System.out.println("Wybierz dzialanie z listy:");
                            System.out.println("1.Usun 5 najgorszych elementow populacji");
                            System.out.println("2.Krzyzuj ze soba elementy populacji");
                            System.out.println("3.Mutuj populacje");
                            System.out.println("4.Wypisz populacje");
                            System.out.println("5.Narysuj graf z droga przejscia");
                            System.out.println("6.Zamknij program");
                            try
                            {
                                //enter the options of action excluded not integer values
                                System.out.println("Podaj dzialanie");
                                opt=scanner.nextLine();
                                option=Integer.parseInt(opt);
                                switch(option)
                                {
                                    case 1:
                                    {
                                        p.deletebadpopulations(); //delete 5 worst rows from population
                                        p.clear(); //clear the ways arraylist
                                        p.countway(); //count new ways from the rest rows of population
                                        break;
                                    }
                                    case 2:
                                    {
                                        p.cross(); //cross 5 new rows of population
                                        p.clear(); //clear the ways arraylist
                                        p.countway();//count new ways from the rows of population
                                        break;
                                    }
                                    case 3:
                                    {
                                        p.mutate();//mutate 2 new rows of population
                                        p.clear();//clear the ways arraylist
                                        p.countway();//count new ways from the rows of population
                                        break;
                                    }

                                    case 4:
                                    {
                                        p.printpopulation(); //print all population rows
                                        break;
                                    }
                                    case 5:
                                    {
                                        ArrayList<Integer> best;
                                        //visualize the graph with the shortest way to walk throught it
                                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                        JFrame frame = new JFrame("My graph");
                                        Canvas canvas = new GraphCanvas(p.printbest(),g);
                                        canvas.setSize(screenSize.width,screenSize.height-100);
                                        frame.add(canvas);
                                        frame.pack();
                                        frame.setVisible(true);
                                        System.out.println("Najlepsza populacja");
                                        best=p.printbest();
                                        System.out.println(best);
                                        break;
                                    }
                                    case 6:
                                    {
                                        //exit the program
                                        System.exit(0);
                                    }
                                    default:
                                    {
                                        System.out.println("Nie ma takiej opcji wpisz poprawnie!");
                                        break;
                                    }
                                }
                            }
                            catch(NumberFormatException e)
                            {
                                System.out.println("Podaj liczbe nie ciag znakow!");
                            }
                        }
                        break;
                    }

                    case 2:
                    {
                        System.exit(0);
                    }
                    default:
                    {
                        System.out.println("Nie ma takiej opcji wpisz poprawnie!");
                        break;
                    }
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Podaj liczbe nie ciag znakow!");
            }
        }       
        scanner.close(); 
    }  
}
