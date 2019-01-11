package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public abstract class model
{
    private Date date;

    public abstract void setDate(Date date);

    public abstract String getDate();

    public abstract String adjust_mood();
}
