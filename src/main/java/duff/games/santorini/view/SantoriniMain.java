package duff.games.santorini.view;

import duff.games.santorini.model.Player;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.ai.RandomAI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SantoriniMain extends Application
{
    public static final int DEFAULT_BOARD_WIDTH = 5;
    public static final int DEFAULT_BOARD_HEIGHT = 5;
    
    public static final String FXML = "Santorini.fxml";
    public static final String CSS = "Santorini.css";
    
    @Override
    public void start ( Stage stage ) throws Exception
    {
        SantoriniModel model = new SantoriniModel ( DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT );
        
        Player player = new Player ( "Fred", new RandomAI ( 0 ) );
        model.players ().add ( player );
        
        player = new Player ( "Barney", new RandomAI ( 1 ) );
        model.players ().add ( player );
        
        player = new Player ( "Wilma", new RandomAI ( 2 ) );
        model.players ().add ( player );
        
        player = new Player ( "Betty", new RandomAI ( 3 ) );
        model.players ().add ( player );
        
        SantoriniController controller = new SantoriniController ( model );
        
        FXMLLoader fxmlLoader = new FXMLLoader ( ClassLoader.getSystemResource ( FXML ) );
        fxmlLoader.setController ( controller );
        
        Pane parent = fxmlLoader.load ();
        
        Scene scene = new Scene ( parent, 500, 500 );
        scene.getStylesheets ().add ( CSS );
        
        stage.setScene ( scene );
        stage.show ();
    }

    public static void main ( String[] args )
    {
        launch ( args );
    }
}
