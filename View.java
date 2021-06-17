import processing.core.PApplet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import processing.javafx.PSurfaceFX;

/**
 * Klasse View.
 * Beschreibung: Erstellt die Grafische Oberfläche
 *
 * @author S.Gebert 
 * @version Mai 2020
 */
public class View extends Application {
    public static PSurfaceFX surface;

    /**
     * Startet die Grafische Oberfläche. Verändere nur die kommentierten Zeilen und passe sie an dein ProcessingFX Projekt an.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
                Platform.exit();
                System.exit(0);
            });
        Controller.stage = primaryStage;
        surface.setStage(primaryStage);
        Controller.surface = surface;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sort.fxml")); //Gui-Datei "gui.fxml
        Parent root = loader.load();

        Scene scene = new Scene(root, 1280, 720); //Breite und Höhe des Anwendungsfensters
        primaryStage.setTitle("Suchen Demo"); //Titel des Anwendungsfensters

        primaryStage.setScene(scene);
        primaryStage.show();      

    }

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
        PApplet.main(new String[] {SortCanvas.class.getName() });
    }
}