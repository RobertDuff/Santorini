package duff.games.santorini.model;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class SantoriniModel
{
    public SantoriniModel ( int width, int height )
    {
        board ( new Board ( width, height ) );
    }

    //
    //
    //
    
    protected ObjectProperty<Board> boardProperty = new SimpleObjectProperty<> ();
    
    public ObjectProperty<Board> boardProperty()
    {
        return boardProperty;
    }
    
    public Board board()
    {
        return boardProperty.get ();
    }
    
    public void board ( Board value )
    {
        boardProperty.set ( value );
    }

    //
    //
    //
    
    protected ListProperty<Actor> actorsProperty = new SimpleListProperty<> ( FXCollections.observableArrayList () );
    
    public ListProperty<Actor> actorsProperty()
    {
        return actorsProperty;
    }

    public List<Actor> actors()
    {
    	return actorsProperty;
   	}}
