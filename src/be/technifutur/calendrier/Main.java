package be.technifutur.calendrier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        //objets
        StarFactory sf = new StarFactory();
        List<Star> stars = sf.getList();

        //System.out.println(star60(stars));



        System.out.println(starAvant(stars, stars.get(127+478)));
    }

    private static String star60(List<Star> liste)
    {
        int cpt = 0;
        int annee;

        for(Star s : liste)
        {
            annee = s.getBirthDate().getYear();
            if(annee < 1970 && annee >=1960)
                cpt++;
        }

        return cpt + " stars sont nees dans les annees 60";
    }

    private static int starAvant(List<Star> liste, Star s)
    {
        int cpt=0;
        LocalDate date = s.getBirthDate();

        for(Star search : liste)
            if(search.getBirthDate().isBefore(date))
                cpt++;

        return cpt;
    }
}
