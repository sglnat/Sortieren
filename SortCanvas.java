import javafx.application.Application;
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.core.PSurface;
import processing.javafx.PSurfaceFX;

/**
 * Klasse SortCanvas.
 * Beschreibung: 
 *
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SortCanvas extends PApplet
{    
    // Liste mit allen Werten //<>//
    int[]    zahlen;    
    String[] namen;

    public int verzoegerung=1000;  // Geschwindigkeit der Ausführung

    // Schriften
    PFont kleineSchrift;  
    PFont grosseSchrift;  

    private ISorter sorter;

    // highlighted entries
    public int hlA = -1;
    public int hlB = -1;
    public int hlC = -1;
    public int hlD = -1;

    int TEXT_COLOR = 0xffffffff;
    int STANDARD_COLOR = 0xff1419a5 ;
    int COLOR_A = 0xffea0408;
    int COLOR_B = 0xff17ea04;
    int COLOR_C = 0xffffff00;
    int COLOR_D = 0xfff600ff;

    /**
     * settings() Methode 
     * Fenstergröße size(int width, int height) und smooth(int level) muss hier eingestellt werden.
     */
    @Override
    public void settings()
    {
        size(1000,700,FX2D); 
    }        

    /**
     * Die setup() Methode wird einmal aufgerufen, wenn das Programm startet.
     * Hier werden Einstellungen wie die Hintergrundfarbe vorgenommen
     * und Medien wie Bilder und Schriftarten geladen.
     */
    @Override
    public void setup()
    {     
        Controller.canvas = this; // Lasse den Controller wissen, dass dieser Sketch in der Gui eingebunden werden soll

        background(0);
        // Schriften laden
        kleineSchrift = loadFont("KleineSchrift.vlw");
        grosseSchrift = loadFont("GrosseSchrift.vlw");

        // CSV-Datei laden und anzeigen
        ladeTabelle("data/punkte.csv");

        zeichneBalken();
    }

    /**
     * Die draw() Methode wird nach der setup() Methode aufgerufen
     * und führt den Code innerhalb ihres Blocks fortlaufend aus,
     * bis das Programm gestoppt oder noLoop() aufgerufen wird.
     */
    @Override
    public void draw()
    {
        zeichneBalken();
    }

    /**
     * Die initSurface()-Methode startet die Gui und bindet den Sketch ein.
     * @return  Gibt die Zeichenfläche des Sketches zurück.
     */
    @Override
    protected PSurface initSurface() {
        g = createPrimaryGraphics();
        PSurface genericSurface = g.createSurface();
        PSurfaceFX fxSurface = (PSurfaceFX) genericSurface;
        fxSurface.setSketch(this);
        View.surface = fxSurface;
        new Thread(() -> Application.launch(View.class)).start();

        while (fxSurface.getStage() == null) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }

        this.surface = fxSurface;
        return fxSurface;
    }

    public void setSorter( ISorter sorter ){
        this.sorter = sorter;
    }

    public int[] getData(){
        return this.zahlen;
    }

    public String[] getNames(){
        return this.namen;
    }

    public void ladeTabelle(String name) {
        // Tabelle aus CSV-Datei laden
        Table csv = loadTable(name, "header,csv");

        if (csv != null  && csv.getColumnCount()==2) {

            // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
            zahlen = new int[csv.getRowCount()];
            namen = new String[csv.getRowCount()];

            // Fülle die Arrays mit Werten aus der Tabelle
            for (int i = 0; i < zahlen.length; i++) {
                // Lies Wert aus der i. Zeile und der Spalte "Punkte" bzw. "Name"
                zahlen[i] = csv.getInt(i, "Punkte");
                namen[i] = csv.getString(i, "Name");
            }
        }
    }

    public void zeichneBalken() {

        clear();

        // Überschrift
        fill(TEXT_COLOR);
        textFont(grosseSchrift);
        text("Punkte", 2, 20);
        textFont(kleineSchrift);  

        // Alle Einträge darstellen
        if (zahlen != null) {
            for (int i = 0; i< zahlen.length; i++) {

                fill(STANDARD_COLOR);
                if( i == hlA ) fill (COLOR_A);
                if( i == hlB ) fill (COLOR_B);
                if( i == hlC ) fill (COLOR_C);
                if( i == hlD ) fill (COLOR_D);

                // Balkendiagramm zeichnen
                if (zahlen[i]>=0) rect(120, 25+i*15, zahlen[i]+1, 13);

                // Beschriftung
                fill(TEXT_COLOR);
                text(namen[i], 2, 35+i*15);
                text(""+zahlen[i], 70, 35+i*15);
            }
        }
    }

    public void runSort(){
        this.sorter.sort();        
    }

    /**
     * delay by (initially) 1 second
     * delay period can be changed by changing the attribute verzoegerung
     */
    public void delay(){
        delay(verzoegerung);
    }

    /**
     * resets highlighted entries to none.
     */
    public void reset(){
        hlA = -1;
        hlB = -1;
        hlC = -1;
        hlD = -1;
    }

    /**
     * counts the number of Swap operations used
     * @return  number of swap operations
     */
    public int countSwap(){
        return this.sorter.countSwap();
    }

    /**
     * counts the number of compare operations used
     * @return  number of compare operations
     */
    public int countCompare(){
        return this.sorter.countCompare();
    }

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
        PApplet.main(new String[] {SortCanvas.class.getName() });
    }

}
