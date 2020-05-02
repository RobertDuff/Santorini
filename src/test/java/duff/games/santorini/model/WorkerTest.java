package duff.games.santorini.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WorkerTest
{
    @Test
    void testClone ()
    {
        Worker origWorker = new Worker ( WorkerGender.FEMALE );
        origWorker.coordinate ( new Coordinate ( 2, 3 ) );
        
        Worker newWorker = new Worker ( origWorker );
        
        assertNotSame ( origWorker, newWorker );
        assertNotSame ( origWorker.coordinate (), newWorker.coordinate () );
        
        assertEquals ( origWorker.gender(), newWorker.gender () );
    }
}
