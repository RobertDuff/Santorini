package duff.games.santorini.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duff.games.santorini.model.ai.RandomAI;

class PlayerTest
{

    @Test
    void testClone ()
    {
        Player origPlayer = new Player ( "Fred", new RandomAI ( 0 ) );
        
        origPlayer.male().coordinate ( new Coordinate ( 3,  5 ) );
        origPlayer.female().coordinate ( new Coordinate ( 0, 2 ) );
        
        Player newPlayer = new Player ( origPlayer );
        
        assertNotSame ( newPlayer, origPlayer );
        assertEquals ( origPlayer.name (), newPlayer.name () );
        
        assertNotSame ( origPlayer.male (), newPlayer.male () );
        assertNotSame ( origPlayer.female (), newPlayer.female () );
        
        assertNotSame ( origPlayer.male ().coordinate (), newPlayer.male ().coordinate () );
        assertNotSame ( origPlayer.female ().coordinate (), newPlayer.female ().coordinate () );
        
        assertEquals ( origPlayer.male ().coordinate (), newPlayer.male ().coordinate () );
        assertEquals ( origPlayer.female ().coordinate (), newPlayer.female ().coordinate () );
        
        assertSame ( origPlayer.ai (), newPlayer.ai () );
    }

}
