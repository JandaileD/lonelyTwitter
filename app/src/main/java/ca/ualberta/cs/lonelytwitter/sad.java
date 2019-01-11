package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class sad extends model
{
    private String current_date;

    public sad()
    {
        this.current_date = this.getDate();
    }

    public void setDate(Date date)
    {
        this.current_date = date.toString();
    }

    public String getDate()
    {
        return this.current_date;
    }

    public String adjust_mood()
    {
        return this.current_date + "Sad.";
    }
}
