package duff.games.santorini.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duff.games.santorini.model.ai.RandomAI;

class SantoriniModelTest
{

    @Test
    void testClone ()
    {
        SantoriniModel origModel = new SantoriniModel ( 5, 5 );
        Player player = new Player ( "Fred", new RandomAI ( 0 ) );
        origModel.players ().add ( player );
        
        SantoriniModel newModel = new SantoriniModel ( origModel );
        
        assertNotSame ( origModel, newModel );
        assertNotSame ( origModel.board (), newModel.board () );
        assertEquals ( 1, origModel.players ().size () );
        assertEquals ( origModel.players ().size (), newModel.players ().size () );
        
        assertNotSame ( origModel.players ().get ( 0 ), newModel.players ().get ( 0 ) );
    }
}
