package duff.games.santorini.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Board
{
    public Board ( int width, int height )
    {
        width ( width );
        height ( height );
        
        squares ( new Square[ width() ][ height() ] );
        
        for ( int x = 0; x < width(); x++ )
            for ( int y = 0; y < height(); y++ )
                squares()[ x ][ y ] = new Square();
    }
    
    public Board ( Board other )
    {
        width ( other.width() );
        height ( other.height() );
        
        squares ( new Square[ width() ][ height() ] );
        
        for ( int x = 0; x < width(); x++ )
            for ( int y = 0; y < height(); y++ )
                squares()[ x ][ y ] = new Square ( other.squares ()[ x ][ y ] );
    }
    
    //
    //
    //
    
    protected IntegerProperty widthProperty = new SimpleIntegerProperty();
    
    public IntegerProperty widthProperty()
    {
        return widthProperty;
    }
    
    public Integer width()
    {
        return widthProperty.get ();
    }
    
    public void width ( Integer value )
    {
        widthProperty.set ( value );
    }

    //
    //
    //
    
    protected IntegerProperty heightProperty = new SimpleIntegerProperty();
    
    public IntegerProperty heightProperty()
    {
        return heightProperty;
    }
    
    public Integer height()
    {
        return heightProperty.get ();
    }
    
    public void height ( Integer value )
    {
        heightProperty.set ( value );
    }

    //
    //
    //
    
    protected ObjectProperty<Square[][]> squaresProperty = new SimpleObjectProperty<> ();
    
    public ObjectProperty<Square[][]> squaresProperty()
    {
        return squaresProperty;
    }
    
    public Square[][] squares()
    {
        return squaresProperty.get ();
    }
    
    public void squares ( Square[][] value )
    {
        squaresProperty.set ( value );
    }

}
