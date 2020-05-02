package duff.games.santorini.model;

import java.util.Iterator;
import java.util.List;

import duff.games.santorini.model.actions.Action;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class SantoriniModel
{
    public SantoriniModel ( int width, int height )
    {
        board ( new Board ( width, height ) );
    }
    
    public SantoriniModel ( SantoriniModel other )
    {
        board ( new Board ( other.board () ) );
        
        for ( Player player : other.players() )
            players().add ( new Player ( player ) );
    }
    
    public boolean occupied ( Coordinate coordinate )
    {
        if ( board ().squares ()[ coordinate.x() ][ coordinate.y() ].domed () )
            return true;
        
        for ( Player player : players() )
            if ( player.male.coordinate ().equals ( coordinate ) ||
                 player.female.coordinate ().equals ( coordinate ) )
                return true;
        
        return false;
    }
    
    public void removeOccupied ( List<Coordinate> coordinates )
    {
        Iterator<Coordinate> i = coordinates.iterator ();
        
        while ( i.hasNext () )
        {
            Coordinate c = i.next ();
            if ( occupied ( c ) )
                i.remove ();
        }
    }
    
    public List<Coordinate> moveTargets ( Coordinate center )
    {
        List<Coordinate> moveTargets = board().moveTargets ( center );
        removeOccupied ( moveTargets );
        return moveTargets;
    }
    
    public List<Coordinate> placementTargets ()
    {
        List<Coordinate> placementTargets = board().placementTargets();
        removeOccupied ( placementTargets );
        return placementTargets;
    }
    
    public List<Coordinate> buildTargets ( Coordinate center )
    {
        List<Coordinate> buildTargets = board().buildTargets ( center );
        removeOccupied ( buildTargets );
        return buildTargets;
    }
    
    public void commitActions ( List<Action> actions ) throws SantoriniException
    {
        for ( Action action : actions )
            commit ( action );
    }
    
    public void commit ( Action action ) throws SantoriniException
    {
        System.out.println ( "Applying: " + action );
        apply ( action );
    }
    
    public void apply ( Action action ) throws SantoriniException
    {
        action.act ( this );
    }
    
    public Player winner()
    {
        Player livingPlayer = null;
        int aliveCount = 0;
        
        for ( Player p : players() )
        {
            if ( p.ai ().isWinner ( this ) )
                return p;
            
            if ( p.alive () )
            {
                livingPlayer = p;
                aliveCount++;
            }
        }
        
        if ( aliveCount == 1 )
            return livingPlayer;
        
        return null;
    }
    
    //
    //
    //
    
    protected ObjectProperty<Board> boardProperty = new SimpleObjectProperty<> ();
    
    public ObjectProperty<Board> boardProperty()
    {
        return boardProperty;
    }
    
    public Board board()
    {
        return boardProperty.get ();
    }
    
    public void board ( Board value )
    {
        boardProperty.set ( value );
    }

    //
    //
    //
    
    protected ListProperty<Player> playersProperty = new SimpleListProperty<> ( FXCollections.observableArrayList () );
    
    public ListProperty<Player> playersProperty()
    {
        return playersProperty;
    }

    public List<Player> players()
    {
    	return playersProperty;
   	}

    @Override
    public String toString ()
    {
        StringBuilder builder = new StringBuilder ();
        
        builder.append ( board() );
        
        for ( Player player : players() )
            builder.append ( player ).append ( "\n" );
        
        return builder.toString ();
    }
}
