package duff.games.santorini.view;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import duff.games.santorini.model.Coordinate;
import duff.games.santorini.model.Player;
import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.actions.Action;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SantoriniController implements Initializable
{
    public static final String LEVEL_2_TEXTURE = "Level2Texture.jpg";

    public static final List<Paint> PLAYER_COLORS = Arrays.asList ( 
            Color.web ( "Red" ), 
            Color.web ( "Lime" ), 
            Color.web ( "Violet" ), 
            Color.web ( "LightBlue" )
            );
    
    @FXML
    public GridPane gameBoardGridPane;

    @FXML
    public HBox legendHBox;

    protected SantoriniModel model;
    protected Image level2Texture;
    
    protected boolean placementPhase;
    protected int currentPlayer;
    protected boolean finished = false;

    public SantoriniController ( SantoriniModel model )
    {
        this.model = model;
        level2Texture = new Image ( ClassLoader.getSystemResourceAsStream ( LEVEL_2_TEXTURE ) );
    }

    //
    @Override
    public void initialize ( URL location, ResourceBundle resources )
    {
        placementPhase = true;
        currentPlayer = 0;
        
        for ( int playerNumber = 0; playerNumber < model.players ().size (); playerNumber++ )
        {
            Player player = model.players ().get ( playerNumber );
            
            Shape colorBlock = new Rectangle ( 50, 30 );
            colorBlock.setStroke ( Color.BLACK );
            colorBlock.setFill ( PLAYER_COLORS.get ( playerNumber ) );
            
            Label playerNameLabel = new Label();
            playerNameLabel.setText ( player.name () );
            playerNameLabel.setFont ( Font.font ( 18 ) );
            
            legendHBox.getChildren ().addAll ( colorBlock, playerNameLabel );
        }

        gameBoardGridPane.setGridLinesVisible ( true );

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

        gameBoardGridPane.setOnMouseClicked ( event -> 
        { 
            switch ( event.getButton () )
            {
            case PRIMARY:
                gameStep();
                break;
                
            default:
                break;
            }
        } );
    }
    
    protected void gameStep()
    {
        if ( finished )
            return;
        
        List<Action> actions;
        
        try
        {
            if ( placementPhase )
            {
                actions = model.players ().get ( currentPlayer++ ).ai ().placeWorkers ( new SantoriniModel ( model ) );
                model.commitActions ( actions );
                
                if ( currentPlayer == model.players ().size () )
                {
                    placementPhase = false;
                    currentPlayer = 0;
                }
            }
            else
            {
                Player player = model.players ().get ( currentPlayer );

                if ( player.alive () )
                {
                    actions = player.ai ().takeTurn ( new SantoriniModel ( model ) );

                    if ( actions.isEmpty () )
                    {
                        Alert trappedAlert = new Alert ( AlertType.INFORMATION, "Player " + player.name () + " is Trapped" );
                        trappedAlert.showAndWait ();
                        player.trapped ( true );
                    }
                    else
                        model.commitActions ( actions );
                }

                currentPlayer = ( currentPlayer + 1 ) % model.players ().size ();
            }
        }
        catch ( SantoriniException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        drawBoard();

        if ( model.winner () != null )
            declareWinner();        
    }
    
    protected void declareWinner()
    {
        Player player = model.winner ();
        
        Alert winAlert = new Alert ( AlertType.INFORMATION, "Player " + player.name () + " Won!" );
        winAlert.showAndWait ();

        finished = true;
    }
    
    protected void drawBoard()
    {        
        for ( Node node : gameBoardGridPane.getChildren () )
        {
            if ( !node.getClass ().equals ( StackPane.class ) )
                continue;

            StackPane stackPane = ( StackPane ) node;

            int x = GridPane.getColumnIndex ( stackPane );
            int y = GridPane.getRowIndex ( stackPane );

            int level = model.board ().squares ()[ x ][ y ].level ();
            boolean isDomed = model.board ().squares ()[ x ][ y ].domed ();

            stackPane.getChildren ().clear ();

            if ( level >= 1 )
            {
                Rectangle level1Block = new Rectangle();
                level1Block.setFill ( Color.GRAY );
                level1Block.setStroke ( Color.BLACK );
                level1Block.widthProperty ().bind ( stackPane.widthProperty () );
                level1Block.heightProperty ().bind ( stackPane.heightProperty () );
                stackPane.getChildren ().add ( level1Block );
            }

            if ( level >= 2 )
            {
                Rectangle level2Block = new Rectangle ();
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

        Font workerFont = Font.font ( "Arial", FontWeight.BOLD, 72 );

        for ( int playerNumber = 0; playerNumber < model.players ().size (); playerNumber++ )
        {
            Player player = model.players ().get ( playerNumber );
            
            Paint paint = PLAYER_COLORS.get ( playerNumber );

            if ( player.male ().coordinate ().valid () )
            {                
                StackPane maleStackPane = paneAt ( player.male ().coordinate() );

                Text male = new Text ( "M" );
                male.setFill ( paint );
                male.setStroke ( Color.BLACK );
                male.setFont ( workerFont );            

                maleStackPane.getChildren ().add ( male );
            }

            if ( player.female ().coordinate ().valid () )
            {
                StackPane femaleStackPane = paneAt ( player.female ().coordinate() );

                Text female = new Text ( "F" );
                female.setFill (  paint );
                female.setStroke ( Color.BLACK );
                female.setFont ( workerFont );

                femaleStackPane.getChildren ().add ( female );
            }
        }
    }

    protected StackPane paneAt ( Coordinate coordinate )
    {
        for ( Node node : gameBoardGridPane.getChildren () )
            if ( GridPane.getColumnIndex ( node ) == coordinate.x() && GridPane.getRowIndex ( node ) == coordinate.y() )
                return ( StackPane ) node;

        return null;
    }
}
