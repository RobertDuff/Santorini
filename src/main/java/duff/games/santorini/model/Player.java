package duff.games.santorini.model;

import duff.games.santorini.model.ai.AI;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player
{
    protected Worker male   = new Worker ( WorkerGender.MALE   );
    protected Worker female = new Worker ( WorkerGender.FEMALE );
    
    public Player ( String name, AI ai )
    {
        name ( name );
        ai ( ai );
        aliveProperty.bind ( male.aliveProperty.or ( female.aliveProperty ).and ( trappedProperty.not () ) );
    }

    public Player ( Player other )
    {
        name ( other.name () );
        trapped ( other.trapped () );
        
        male   = new Worker ( other.male ()   );
        female = new Worker ( other.female () );
        
        ai ( other.ai () );

        aliveProperty.bind ( male.aliveProperty.or ( female.aliveProperty ) );
    }

    public Worker male()
    {
        return male;
    }

    public Worker female()
    {
        return female;
    }
    
    public Worker worker ( WorkerGender gender )
    {
        if ( gender == WorkerGender.FEMALE )
            return female;
        
        return male;
    }
    
    //
    //
    //
    
    protected StringProperty nameProperty = new SimpleStringProperty();
    
    public StringProperty nameProperty()
    {
        return nameProperty;
    }
    
    public String name()
    {
        return nameProperty.get ();
    }
    
    public void name ( String value )
    {
        nameProperty.set ( value );
    }

    //
    //
    //
    
    protected BooleanProperty aliveProperty = new SimpleBooleanProperty();
    
    public ReadOnlyBooleanProperty aliveProperty()
    {
        return aliveProperty;
    }
    
    public Boolean alive()
    {
        return aliveProperty.get ();
    }

    //
    //
    //
    
    protected BooleanProperty trappedProperty = new SimpleBooleanProperty ( false );
    
    public BooleanProperty trappedProperty()
    {
        return trappedProperty;
    }
    
    public Boolean trapped()
    {
        return trappedProperty.get ();
    }
    
    public void trapped ( Boolean value )
    {
        trappedProperty.set ( value );
    }

    //
    //
    //
    
    protected ObjectProperty<AI> aiProperty = new SimpleObjectProperty<> ();
    
    public ObjectProperty<AI> aiProperty()
    {
        return aiProperty;
    }
    
    public AI ai()
    {
        return aiProperty.get ();
    }
    
    public void ai ( AI ai )
    {
        aiProperty.set ( ai );
    }

    @Override
    public String toString ()
    {
        return "Player " + name() + " " + ( alive()? "Alive" : "Dead" ) + " " + male + " " + female + " AI=" + aiProperty.getValue ();
    }
}
