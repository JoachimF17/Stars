package be.technifutur.calendrier;

import java.util.Comparator;

public class DateComparator implements Comparator<Star>
{

    @Override public int compare(Star o1, Star o2)
    {
        if(o1.getBirthDate().compareTo(o2.getBirthDate()) != 0) return o1.getBirthDate().compareTo(o2.getBirthDate());
        else return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
    }
}
