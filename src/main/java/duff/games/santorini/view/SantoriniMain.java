package duff.games.santorini.view;

import java.net.URL;

import duff.games.santorini.model.SantoriniModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SantoriniMain extends Application
{
    public static final URL FXML = ClassLoader.getSystemResource ( "Santorini.fxml" );
    
    @Override
    public void start ( Stage stage ) throws Exception
    {
        SantoriniModel model = new SantoriniModel ();
        
        SantoriniController controller = new SantoriniController ( model );
        
        FXMLLoader fxmlLoader = new FXMLLoader ( FXML );
        fxmlLoader.setController ( controller );
        
        Pane parent = fxmlLoader.load ();
        
        Scene scene = new Scene ( parent, 600, 600 );
        
        stage.setScene ( scene );
        stage.show ();
    }

    public static void main ( String[] args )
    {
        launch ( args );
    }
}
