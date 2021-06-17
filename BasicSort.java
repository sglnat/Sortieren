
/**
 * Base class for any implementation of ISorter.
 * 
 * @author Simon Gebert
 * @version 2017-03-13
 */
public abstract class BasicSort
{
    public static SortCanvas canvas;
    private int[] data;
    private String[] names;

    /**
     * Constructor for objects of class BasicSort
     */
    public BasicSort(SortCanvas p)
    {
        this.canvas = p;
        this.data = p.getData();
        this.names = p.getNames();
    }

    /**
     * Swap the elements with given position
     * @param i1    position 1
     * @param i2    position 2
     */
    protected void swap(int i1, int i2)
    {   
        //TODO: count swap operations
        swap( this.data, i1, i2 );
        swap( this.names, i1, i2);
    }

    protected void swap( int[] l, int i1, int i2)
    {
        int tmp = l[i1];
        l[i1] = l[i2];
        l[i2]= tmp;
    }

    protected void swap( String[] l, int i1, int i2)
    {
        String tmp = l[i1];
        l[i1] = l[i2];
        l[i2]= tmp;
    }

    /**
     * Compare two elements with given position
     * @param   i1  position 1
     * @param   i2  position 2
     * return       -1 if value of element i1 less than value of element i2, 0 if equal, else 1
     */
    protected int compare(int i1, int i2)
    {
        return compare( this.data, i1, i2 );
    }

    protected int compare( int[] l, int i1, int i2)
    {
        //TODO: count compare Operations
        if( l[i1] == l[i2] ) return 0;
        if( l[i1] > l[i2] ) return 1;
        return -1;
    }

    /**
     * counts the number of Swap operations used
     * @return  number of swap operations
     */
    public int countSwap(){
        //TODO: return counter attribute
        return -1;
    }

    /**
     * counts the number of compare operations used
     * @return  number of compare operations
     */
    public int countCompare(){
        //TODO: return counter attribute
        return -1;
    }

}
