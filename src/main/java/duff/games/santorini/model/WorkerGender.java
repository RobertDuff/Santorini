package duff.games.santorini.model;

public enum WorkerGender
{
    MALE,
    FEMALE;
    
    public WorkerGender opposite()
    {
        if ( this == MALE )
            return FEMALE;
        
        return MALE;
    }
}
