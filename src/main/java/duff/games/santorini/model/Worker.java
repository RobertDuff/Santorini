package duff.games.santorini.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Worker
{        
    public Worker ( WorkerGender gender )
    {
        genderProperty.set ( gender );
        coordinate ( new Coordinate () );
        alive ( true );
    }
    
    public Worker ( Worker other )
    {
        genderProperty.set ( other.gender () );
        coordinate ( new Coordinate ( other.coordinate () ) );
        alive ( other.alive () );
    }

    //
    //
    //
    
    protected ObjectProperty<WorkerGender> genderProperty = new SimpleObjectProperty<> ();
    
    public ReadOnlyObjectProperty<WorkerGender> genderProperty()
    {
        return genderProperty;
    }
    
    public WorkerGender gender()
    {
        return genderProperty.get ();
    }
    
    //
    //
    //
    
    protected ObjectProperty<Coordinate> coordinateProperty = new SimpleObjectProperty<>();
    
    public ObjectProperty<Coordinate> coordinateProperty()
    {
        return coordinateProperty;
    }
    
    public Coordinate coordinate()
    {
        return coordinateProperty.get ();
    }
    
    public void coordinate ( Coordinate value )
    {
        coordinateProperty.set ( value );
    }

    //
    //
    //
    
    protected BooleanProperty aliveProperty = new SimpleBooleanProperty( true );
    
    public BooleanProperty aliveProperty()
    {
        return aliveProperty;
    }
    
    public Boolean alive()
    {
        return aliveProperty.get ();
    }
    
    public void alive ( Boolean value )
    {
        aliveProperty.set ( value );
    }

    @Override
    public String toString ()
    {
        return gender() + "@" + coordinate() + ":" + ( alive()? "Alive" : "Dead" );
    }

}
