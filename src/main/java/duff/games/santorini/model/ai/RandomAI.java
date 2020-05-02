package duff.games.santorini.model.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import duff.games.santorini.model.Coordinate;
import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;
import duff.games.santorini.model.Worker;
import duff.games.santorini.model.WorkerGender;
import duff.games.santorini.model.actions.Action;
import duff.games.santorini.model.actions.BuildAction;
import duff.games.santorini.model.actions.DomeAction;
import duff.games.santorini.model.actions.PlacementAction;

public class RandomAI extends AI
{
    public RandomAI ( int playerNumber )
    {
        super ( playerNumber );
    }

    protected Random random = new Random();
    
    @Override
    public List<Action> placeWorkers ( SantoriniModel model ) throws SantoriniException
    {
        List<Action> actions = new ArrayList<> ();
        
        List<Coordinate> squares = model.placementTargets ();
        
        int r = random.nextInt ( squares.size () );
        Coordinate location = squares.get ( r );
        
        actions.add ( new PlacementAction ( playerNumber (), WorkerGender.MALE, location ) );
        
        squares.remove ( r );
        
        r = random.nextInt ( squares.size () );
        location = squares.get ( r );
        
        actions.add ( new PlacementAction ( playerNumber (), WorkerGender.FEMALE, location ) );
        
        return actions;
    }

    @Override
    public List<Action> takeTurn ( SantoriniModel model ) throws SantoriniException
    {
        WorkerGender gender = random.nextBoolean ()? WorkerGender.FEMALE : WorkerGender.MALE;

        List<Action> actions = new ArrayList<> ();
        
        Coordinate newLocation = tryMove ( model, gender );
        
        if ( newLocation == null )
        {
            gender = gender.opposite ();
            newLocation = tryMove ( model, gender );
            
            if ( newLocation == null )
                return Collections.emptyList ();
        }

        Action move = new PlacementAction ( playerNumber (), gender, newLocation );
        actions.add ( move );
        
        model.apply ( move );
        
        List<Coordinate> squares = model.buildTargets ( newLocation );
        
        if ( squares.size () == 0 )
            return Collections.emptyList ();
        
        Coordinate buildLocation = squares.get ( random.nextInt ( squares.size () ) );
        
        int level = model.board ().at ( buildLocation ).level ();
        
        if ( level == 3 )
            actions.add ( new DomeAction ( buildLocation ) );
        else
            actions.add ( new BuildAction ( buildLocation ) );
        
        return actions;
    }
    
    protected Coordinate tryMove ( SantoriniModel model, WorkerGender gender )
    {
        Worker worker = model.players ().get ( playerNumber () ).worker ( gender );
        
        List<Coordinate> squares = model.moveTargets ( worker.coordinate () );
        
        if ( squares.size () == 0 )
            return null;
        
        return squares.get ( random.nextInt ( squares.size () ) );
    }
    
    @Override
    public String toString()
    {
        return "Random AI";
    }
}
