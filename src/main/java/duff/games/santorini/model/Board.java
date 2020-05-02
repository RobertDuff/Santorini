package duff.games.santorini.model;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<Coordinate> moveTargets ( Coordinate start )
    {
        int startLevel = squares ()[ start.x() ][ start.y() ].level ();

        List<Coordinate> moveTargets = new ArrayList<> ();
        
        for ( int xOffset = -1; xOffset < 2; xOffset++ )
            for ( int yOffset = -1; yOffset < 2; yOffset++ )
            {                
                if ( xOffset == 0 && yOffset == 0 )
                    continue;
                
                int x = start.x () + xOffset;
                int y = start.y () + yOffset;
                
                if ( x < 0 || x >= width () || y < 0 || y >= height () )
                    continue;
                
                Square square = squares ()[ x ][ y ]; 
                
                if ( square.level () <= startLevel + 1 && !square.domed () )
                    moveTargets.add ( new Coordinate ( x, y ) );
            }
        
        return moveTargets;
    }
    
    public List<Coordinate> buildTargets ( Coordinate center )
    {
        List<Coordinate> buildTargets = new ArrayList<> ();
        
        for ( int xOffset = -1; xOffset < 2; xOffset++ )
            for ( int yOffset = -1; yOffset < 2; yOffset++ )
            {                
                if ( xOffset == 0 && yOffset == 0 )
                    continue;
                
                int x = center.x () + xOffset;
                int y = center.y () + yOffset;
                
                if ( x < 0 || x >= width () || y < 0 || y >= height () )
                    continue;
                
                if ( !squares ()[ x ][ y ].domed () )
                    buildTargets.add ( new Coordinate ( x, y ) );
            }
        
        return buildTargets;
    }
    
    public List<Coordinate> placementTargets()
    {
        List<Coordinate> available = new ArrayList<> ();
        
        for ( int x = 0; x < width (); x++ )
            for ( int y = 0; y < height (); y++ )
            {                
                if ( !squares ()[ x ][ y ].domed () )
                    available.add ( new Coordinate ( x, y ) );
            }
        
        return available;
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

    public Square at ( Coordinate coordinate )
    {
        return squares ()[ coordinate.x () ][ coordinate.y () ];
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

    @Override
    public String toString ()
    {
        StringBuilder builder = new StringBuilder();
        
        builder.append ( "Board:\n" );
        
        for ( int y = 0; y < height (); y++ )
        {
            for ( int x = 0; x < width (); x++ )
                builder.append ( squares()[ x ][ y ] );
            builder.append ( "\n" );
        }
        
        return builder.toString ();
    }
}
