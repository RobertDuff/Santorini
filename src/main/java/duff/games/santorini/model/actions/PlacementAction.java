package duff.games.santorini.model.actions;

import duff.games.santorini.model.Coordinate;
import duff.games.santorini.model.Player;
import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.Worker;
import duff.games.santorini.model.WorkerGender;

public class PlacementAction implements Action
{
    protected int playerNumber;
    protected WorkerGender gender;
    protected Coordinate coordinate;
    
    public PlacementAction ( int playerNumber, WorkerGender gender, Coordinate coordinate )
    {
        this.playerNumber = playerNumber;
        this.gender = gender;
        this.coordinate = coordinate;
    }

    @Override
    public void act ( SantoriniModel model ) throws SantoriniException
    {
        if ( model.occupied ( coordinate ) )
            throw new SantoriniException ( "Location " + coordinate + " is Occupied or Domed" );

        Player player = model.players ().get ( playerNumber );
        
        Worker worker = gender == WorkerGender.MALE? player.male () : player.female ();
        worker.coordinate ( coordinate );
    }

    @Override
    public String toString ()
    {
        return "Place/Move Player " + playerNumber + "'s " + gender.name () + " Worker to " + coordinate;
    }
}
