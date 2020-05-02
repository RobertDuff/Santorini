package duff.games.santorini.model.actions;

import duff.games.santorini.model.SantoriniException;
import duff.games.santorini.model.SantoriniModel;

public interface Action
{
    public void act ( SantoriniModel model ) throws SantoriniException;
}
