package duff.games.santorini.model.actions;

import duff.games.santorini.model.Coordinate;
import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.Square;

public class BuildAction implements Action
{
    protected Coordinate coordinate;
    
    public BuildAction ( Coordinate coordinate )
    {
        this.coordinate = coordinate;
    }

    @Override
    public void act ( SantoriniModel model ) throws SantoriniException
    {
        if ( model.occupied ( coordinate ) )
            throw new SantoriniException ( "Location " + coordinate + " is Occupied or Domed" );
        
        Square square = model.board ().squares ()[ coordinate.x () ][ coordinate.y () ];
        
        if ( square.level () == 3 )
            throw new SantoriniException ( "Square " + coordinate + " is already at maximum height" );
        
        square.level ( square.level () + 1 );
    }

    @Override
    public String toString ()
    {
        return "Build at " + coordinate;
    }
}
