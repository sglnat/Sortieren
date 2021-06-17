/**
 * Interface Sorter
 * 
 * @author Simon Gebert
 * @version 2017-02-13
 */
public interface ISorter  
{

    /**
     * sorts the data loaded in canvas
     * to animate and highlight the process, use
     *   canvas.hlA, canvas.hlB, canvas.hlC, canvas.hlD : assign index of element to be highlighted by eihter color A,B,C or D).
     *   canvas.redraw() : force redraw of the canvas (changes will be updated on canvas).
     *   canvas.delay() : canvas will be redrawn about every 60fps. Delay redraw for (initially) 1 second. 
     *                    canvas.delay(period: int) will delay for period milliseconds.
     *   cavas.reset() : reset highlighted entries to none.
     */
    public void sort();

    /**
     * counts the number of Swap operations used
     * @return  number of swap operations
     */
    public int countSwap();

    /**
     * counts the number of compare operations used
     * @return  number of compare operations
     */
    public int countCompare();
}
