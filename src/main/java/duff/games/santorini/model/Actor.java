package duff.games.santorini.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

public class Actor
{    
    public Actor ( Paint color )
    {
        color  ( color );
    }

    //
    //
    //
    
    protected ObjectProperty<Paint> colorProperty = new SimpleObjectProperty<> ();
    
    public ObjectProperty<Paint> colorProperty()
    {
        return colorProperty;
    }
    
    public Paint color()
    {
        return colorProperty.get ();
    }
    
    public void color ( Paint value )
    {
        colorProperty.set ( value );
    }

    //
    //
    //
    
    protected IntegerProperty xProperty = new SimpleIntegerProperty();
    
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
    
    protected IntegerProperty yProperty = new SimpleIntegerProperty();
    
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

}
