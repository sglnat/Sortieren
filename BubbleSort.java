
/**
 * Write a description of class BubbleSort here.
 * 
 * @author Simon Gebert 
 * @version 2017-02-19
 */
public class BubbleSort extends BasicSort implements ISorter
{
    int curI, curK;

    /**
     * Constructor
     */
    public BubbleSort(SortCanvas p )
    {
        super(p);
    }

    /**
     * sorts the data loaded in canvas
     * to animate and highlight the process, use
     *   canvas.hlA, canvas.hlB, canvas.hlC, canvas.hlD : assign index of element to be highlighted by eihter color A,B,C or D).
     *   canvas.redraw() : force redraw of the canvas (changes will be updated on canvas).
     *   canvas.delay() : canvas will be redrawn about every 60fps. Delay redraw for (initially) 1 second. 
     *                    canvas.delay(period: int) will delay for period milliseconds.
     *   cavas.reset() : reset highlighted entries to none.
     */
    public void sort(){
        int[] data = canvas.getData();
        int akt;
        int max=data.length;
        for(max=data.length; max>0;max--){
            for(akt=1; akt<max ;akt++){
                if(data[akt]<data[akt-1]){
                    swap(akt, akt-1);
                }
                canvas.hlA=akt;
                canvas.redraw();canvas.delay();
            }    
            //TODO: implement sort algorithm. You may use curI, curK as loop variables or define your own attributes.
            canvas.reset();
            canvas.redraw();
            canvas.hlB=max;
            canvas.redraw();canvas.delay();
        }
    }
}
