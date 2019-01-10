package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class happy extends model
{
    private String current_date;

    public void setDate()
    {
        Date date = new Date();
        this.current_date = date.toString();
    }

    public void adjust_mood()
    {
        this.setDate();
        String current_mood = this.current_date + "Happy!";

    }
}
