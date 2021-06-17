
/**
 * Write a description of class MergeSort here.
 * 
 * @author Simon Gebert 
 * @version 2017-02-19
 */
public class MergeSort extends BasicSort implements ISorter
{
    int curI, curJ, curK;
    int[] data;

    /**
     * Constructor
     */
    public MergeSort(SortCanvas p )
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
        data = canvas.getData();

        sortHelper(0, data.length -1);
        //TODO: implement sort algorithm. You may use curI, curK as loop variables or define your own attributes.
        canvas.reset();
        canvas.redraw();
    }

    private int[] sortHelper( int l, int r){
        if (l < r) {
            int q = (l + r) / 2;
            canvas.hlA=q;
            sortHelper(l, q);
            sortHelper(q + 1, r);
            canvas.redraw();canvas.delay();

            merge(l, q, r);
        }
        return data;
    } 

    private void merge(int l, int q, int r){
        int[] arr = new int[data.length];
        int i, j;
        for (i = l; i <= q; i++) {
            arr[i] = data[i];
        }
        for (j = q + 1; j <= r; j++) {
            arr[r + q + 1 - j] = data[j];
        }
        i = l;
        j = r;
        canvas.hlB = l;
        canvas.hlC = r;
        canvas.hlD = q;
        canvas.redraw();canvas.delay();
        for (int k = l; k <= r; k++) {
            if (arr[i] <= arr[j]) {
                data[k] = arr[i];
                i++;
            } else {
                data[k] = arr[j];
                j--;
            }
        }
        canvas.redraw();canvas.delay();
    }
}
