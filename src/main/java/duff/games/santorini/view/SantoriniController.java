package duff.games.santorini.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import duff.games.santorini.model.SantoriniModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SantoriniController implements Initializable
{
    @FXML
    public GridPane gameBoardGridPane;
    
    protected SantoriniModel model;
    
    public SantoriniController ( SantoriniModel model )
    {
        this.model = model;
    }

    @Override
    public void initialize ( URL location, ResourceBundle resources )
    {
        gameBoardGridPane.getChildren ().clear ();
        
        for ( int x = 0; x < model.board ().width (); x++ )
            for ( int y = 0; y < model.board ().height (); y++ )
            {
                StackPane stackPane = new StackPane();
                stackPane.setId ( "square" );
                stackPane.setMinWidth ( 0 );
                stackPane.setMinHeight ( 0 );
                
                GridPane.setConstraints ( stackPane, x, y, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS );                
                gameBoardGridPane.getChildren ().add ( stackPane );
            }
        
        drawBoard ();
        
        gameBoardGridPane.setOnMouseClicked ( event -> drawBoard() );
    }
    
    protected void drawBoard()
    {        
        Random random = new Random();
        
        for ( Node node : gameBoardGridPane.getChildren () )
        {
            StackPane stackPane = ( StackPane ) node;
            
            int x = GridPane.getColumnIndex ( stackPane );
            int y = GridPane.getRowIndex ( stackPane );
            
            int level = random.nextInt ( 4 ); //model.board ().squares ()[ x ][ y ].level ();
            boolean isDomed = random.nextBoolean (); // model.board ().squares ()[ x ][ y ].domed ();
            
            stackPane.getChildren ().clear ();

            if ( level >= 1 )
            {
                Rectangle level1Block = new Rectangle();
                level1Block.setFill ( Color.WHITE );
                level1Block.setStroke ( Color.BLACK );
                level1Block.widthProperty ().bind ( stackPane.widthProperty () );
                level1Block.heightProperty ().bind ( stackPane.heightProperty () );
                stackPane.getChildren ().add ( level1Block );
            }

            if ( level >= 2 )
            {
                Rectangle level2Block = new Rectangle();
                level2Block.setFill ( Color.WHITE );
                level2Block.setStroke ( Color.BLACK );
                level2Block.widthProperty ().bind ( stackPane.widthProperty ().subtract ( 10 ) );
                level2Block.heightProperty ().bind ( stackPane.heightProperty ().subtract ( 10 ) );
                stackPane.getChildren ().add ( level2Block );
            }

            if ( level == 3 )
            {
                Circle level3Block = new Circle();
                level3Block.setFill ( Color.WHITE );
                level3Block.setStroke ( Color.BLACK );
                level3Block.radiusProperty ().bind ( Bindings.min ( stackPane.widthProperty (), stackPane.heightProperty () ).subtract ( 10 ).divide ( 2 ) );
                stackPane.getChildren ().add ( level3Block );
            }
            
            if ( isDomed )
            {
                Circle domeBlock = new Circle();
                domeBlock.setFill ( Color.BLUE );
                domeBlock.setStroke ( Color.BLACK );
                domeBlock.radiusProperty ().bind ( Bindings.min ( stackPane.widthProperty (), stackPane.heightProperty () ).subtract ( 10 ).divide ( 2 ) );
                stackPane.getChildren ().add ( domeBlock );
            }
        }
    }
    
    protected StackPane paneAt ( int x, int y )
    {
        System.out.println ( gameBoardGridPane.getChildren ().size () );
        for ( Node node : gameBoardGridPane.getChildren () )
        {
            System.out.println ( node.getClass ().getCanonicalName () );
            System.out.println ( GridPane.getColumnIndex ( node ) + "/" +  GridPane.getRowIndex ( node ) );
//            if ( GridPane.getRowIndex ( node ) == y && GridPane.getColumnIndex ( node ) == x )
//                return ( StackPane ) node;
        }
        
        return null;
    }
}
