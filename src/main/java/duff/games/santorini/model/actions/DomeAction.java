package duff.games.santorini.model.actions;

import duff.games.santorini.model.Coordinate;
import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.Square;

public class DomeAction implements Action
{
    protected Coordinate coordinate;
    
    public DomeAction ( Coordinate coordinate )
    {
        this.coordinate = coordinate;
    }

    @Override
    public void act ( SantoriniModel model ) throws SantoriniException
    {
        if ( model.occupied ( coordinate ) )
            throw new SantoriniException ( "Square " + coordinate + " is occupied or already domed" );
        
        Square square = model.board ().squares ()[ coordinate.x () ][ coordinate.y () ];
        square.domed ( true );
    }

    @Override
    public String toString ()
    {
        return "Build Dome at " + coordinate;
    }
}
