package duff.games.santorini.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Coordinate
{
    public Coordinate ()
    {
    }
    
    public Coordinate ( int x, int y )
    {
        x ( x );
        y ( y );
        
        validProperty.bind ( xProperty.greaterThanOrEqualTo ( 0 ).and ( yProperty.greaterThanOrEqualTo ( 0 ) ) );
    }
    
    //
    //
    //
    
    public Coordinate ( Coordinate other )
    {
        x ( other.x () );
        y ( other.y () );

        validProperty.bind ( xProperty.greaterThanOrEqualTo ( 0 ).and ( yProperty.greaterThanOrEqualTo ( 0 ) ) );
    }

    protected IntegerProperty xProperty = new SimpleIntegerProperty ( -1 );
    
    public IntegerProperty xProperty()
    {
        return xProperty;
    }
    
    public Integer x()
    {
        return xProperty.get ();
    }
    
    public void x ( Integer value )
    {
        xProperty.set ( value );
    }

    //
    //
    //
    
    protected IntegerProperty yProperty = new SimpleIntegerProperty ( -1 );
    
    public IntegerProperty yProperty()
    {
        return yProperty;
    }
    
    public Integer y()
    {
        return yProperty.get ();
    }
    
    public void y ( Integer value )
    {
        yProperty.set ( value );
    }

    //
    //
    //
    
    protected BooleanProperty validProperty = new SimpleBooleanProperty();
    
    public ReadOnlyBooleanProperty validProperty()
    {
        return validProperty;
    }
    
    public Boolean valid()
    {
        return validProperty.get ();
    }
    
    @Override
    public boolean equals ( Object obj )
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass () != obj.getClass () ) return false;
        Coordinate other = ( Coordinate ) obj;
        
        if ( xProperty == null )
        {
            if ( other.xProperty != null ) return false;
        }
        else if ( xProperty.get () != other.xProperty.get () ) return false;
        
        if ( yProperty == null )
        {
            if ( other.yProperty != null ) return false;
        }
        else if ( yProperty.get() != other.yProperty.get () ) return false;
        
        return true;
    }

    @Override
    public String toString ()
    {
        return xProperty.get () + "/" + yProperty.get ();
    }
}
