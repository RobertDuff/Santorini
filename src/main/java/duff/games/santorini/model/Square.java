package duff.games.santorini.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Square
{
    public Square ()
    {
        // TODO Auto-generated constructor stub
    }
    
    public Square ( Square other )
    {
        level ( other.level () );
        domed ( other.domed () );
    }
    
    //
    //
    //
    
    protected IntegerProperty levelProperty = new SimpleIntegerProperty();
    
    public IntegerProperty levelProperty()
    {
        return levelProperty;
    }
    
    public Integer level()
    {
        return levelProperty.get ();
    }
    
    public void level ( Integer value )
    {
        levelProperty.set ( value );
    }

    //
    //
    //
    
    protected BooleanProperty domedProperty = new SimpleBooleanProperty();
    
    public BooleanProperty domedProperty()
    {
        return domedProperty;
    }
    
    public Boolean domed()
    {
        return domedProperty.get ();
    }
    
    public void domed ( Boolean value )
    {
        domedProperty.set ( value );
    }

    @Override
    public String toString ()
    {
        return level() + ( domed()? "D" : "-" );
    }
}