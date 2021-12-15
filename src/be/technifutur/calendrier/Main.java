package be.technifutur.calendrier;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        //objets
        StarFactory sf = new StarFactory();
        ArrayList<Star> stars = sf.starBuilder();

        for(Star s : stars)
            System.out.println(s);
    }
}
