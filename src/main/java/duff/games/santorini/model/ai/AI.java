package duff.games.santorini.model.ai;

import java.util.List;

import duff.games.santorini.model.Coordinate;
import duff.games.santorini.model.Player;
import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.Square;
import duff.games.santorini.model.actions.Action;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class AI
{
    public AI ( int playerNumber )
    {
        playerNumberProperty.set ( playerNumber );
    }
    
    public abstract List<Action> placeWorkers ( SantoriniModel model ) throws SantoriniException;
    public abstract List<Action> takeTurn ( SantoriniModel model ) throws SantoriniException;
    
    public boolean isWinner ( SantoriniModel model )
    {
        Player player = model.players ().get ( playerNumber() );
        
        if ( player.male ().alive () )
        {
            Coordinate c = player.male ().coordinate ();
            
            if ( c.valid () )
            {
                Square square = model.board ().at ( c );

                if ( square.level () == 3 )
                    return true;
            }
        }
        
        if ( player.female ().alive () )
        {
            Coordinate c = player.female ().coordinate ();
            
            if ( c.valid () )
            {
                Square square = model.board ().at ( c );

                if ( square.level () == 3 )
                    return true;
            }
        }
        
        return false;
    }

    //
    //
    //
    
    protected IntegerProperty playerNumberProperty = new SimpleIntegerProperty();
    
    public IntegerProperty playerNumberProperty()
    {
        return playerNumberProperty;
    }
    
    public int playerNumber()
    {
        return playerNumberProperty.get ();
    }
    
    public void playerNumber ( int playerNumber )
    {
        playerNumberProperty.set ( playerNumber );
    }
}
