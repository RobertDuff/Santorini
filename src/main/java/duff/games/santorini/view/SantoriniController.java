package duff.games.santorini.view;

import java.net.URL;
import java.util.ResourceBundle;

import duff.games.santorini.model.SantoriniModel;
import javafx.fxml.Initializable;

public class SantoriniController implements Initializable
{
    protected SantoriniModel model;
    
    public SantoriniController ( SantoriniModel model )
    {
        this.model = model;
    }

    @Override
    public void initialize ( URL location, ResourceBundle resources )
    {
    }
}
