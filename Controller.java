import java.net.URL;
import java.io.File;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import processing.core.PApplet;
import processing.javafx.PSurfaceFX;

public class Controller implements Initializable {
    public static SortCanvas canvas;
    protected static Stage stage;
    public static PSurfaceFX surface;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="processing"
    private StackPane processing; // Value injected by FXMLLoader

    @FXML // fx:id="statusText"
    private Label statusText; // Value injected by FXMLLoader

    @FXML
    void loadData(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File base = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(base);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String relative = base.toURI().relativize(file.toURI()).getPath();
            
            System.out.println(relative);
            canvas.ladeTabelle(relative);
        }
    }

    @FXML
    void loadStandardData(ActionEvent event) {
        canvas.ladeTabelle("data/punkte.csv");
    }

    @FXML
    void quit(ActionEvent event) {

    }

    @FXML
    void runSort(ActionEvent event) {
        canvas.thread("runSort");
    }

    @FXML
    void setBubbleSort(ActionEvent event) {

    }

    @FXML
    void setInsertionSort(ActionEvent event) {
        ISorter s = new InsertionSort(canvas);
        canvas.setSorter(s);
        statusText.setText("Aktuelles Verfahren: Insertionsort");
    }

    @FXML
    void setMergeSort(ActionEvent event) {
        ISorter s = new MergeSort(canvas);
        canvas.setSorter(s);
        statusText.setText("Aktuelles Verfahren: Mergesort");
    }

    @FXML
    void setQuicksort(ActionEvent event) {

    }

    @FXML
    void setSelectionSort(ActionEvent event) {
        ISorter s = new SelectionSort(canvas);
        canvas.setSorter(s);
        statusText.setText("Aktuelles Verfahren: Selectionsort");
    }

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
        assert processing != null : "fx:id=\"processing\" was not injected: check your FXML file 'gui.fxml'.";
        assert statusText != null : "fx:id=\"statusText\" was not injected: check your FXML file 'sort.fxml'.";
        Canvas canvas = (Canvas) surface.getNative();
        surface.setGraphicsContext( canvas.getGraphicsContext2D() );
        canvas.widthProperty().bind(processing.widthProperty());
        canvas.heightProperty().bind(processing.heightProperty());
        processing.getChildren().add(canvas);
    }
}
