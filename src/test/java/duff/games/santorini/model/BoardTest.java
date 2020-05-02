package duff.games.santorini.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest
{
    @Test
    void testClone ()
    {
        Board origBoard = new Board ( 5, 5 );
        
        origBoard.squares ()[ 3 ][ 2 ].level ( 1 );
        origBoard.squares ()[ 0 ][ 4 ].domed ( true );
        
        Board newBoard = new Board ( origBoard );
        
        assertNotSame ( newBoard, origBoard );
        assertEquals ( origBoard.width (),  newBoard.width ()  );
        assertEquals ( origBoard.height (), newBoard.height () );
        
        for  ( int x = 0; x < origBoard.width (); x++ )
            for ( int y = 0; y < origBoard.height (); y++ )
            {
                Square origSquare = origBoard.squares ()[ x ][ y ];
                Square newSquare  = newBoard.squares ()[ x ][ y ];
                
                assertNotSame ( newSquare, origSquare, "Square " + x + "/" + y );
                assertEquals ( origSquare.level (), newSquare.level (), "Square " + x + "/" + y );
                assertEquals ( origSquare.domed (), newSquare.domed (), "Square " + x + "/" + y );
            }
    }
}
