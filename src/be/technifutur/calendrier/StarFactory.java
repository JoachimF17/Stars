package be.technifutur.calendrier;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StarFactory
{
    //methodes
    public ArrayList<Star> getList()
    {
        //objets
        ArrayList<Star> s = new ArrayList<>(extractFileOne());
        s.addAll(extractFileTwo());

        return s;
    }

    //methodes
    private ArrayList<Star> extractFileOne()
    {
        //objets
        ArrayList<Star> s = new ArrayList<>();
        String[] temp, jum;
        File f = new File("star1.txt");

        try(Scanner sc = new Scanner(f))
        {
            while(sc.hasNext())
            {
                temp = sc.nextLine().split(":");

                if(temp[1].contains("&"))
                {
                    jum = temp[1].split("&");
                    s.add(new Star(jum[0], affectDate(temp[0])));
                    s.add(new Star(jum[1], affectDate(temp[0])));
                }else
                    s.add(new Star(temp[1].trim(),affectDate(temp[0])));
            }
        }catch(FileNotFoundException e)
        {
            System.out.println("Pas de fichier");
        }

        return s;
    }

    private ArrayList<Star> extractFileTwo()
    {
        //objets
        ArrayList<Star> s = new ArrayList<>();
        File f = new File("star2.txt");
        String[] temp;

        try(Scanner sc = new Scanner(f))
        {
            while(sc.hasNext())
            {
                temp = sc.nextLine().split(":");

                if(temp[0].contains("("))
                    s.add(new Star(temp[0].substring(0, temp[0].indexOf("(")).trim(), affectDate(temp[1])));
                else
                    s.add(new Star(temp[0].trim(), affectDate(temp[1])));
            }

        }catch(FileNotFoundException e)
        {
            System.out.println("Pas de fichier");
        }

        return s;
    }

    private LocalDate affectDate(String s)
    {
        //objets
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

        s = trimDate(s);

        return LocalDate.parse(s.trim(), formatter);
    }

    private String trimDate(String s)
    {
        s = s.toLowerCase();

        if(s.contains("fevrier")) s = s.replaceFirst("fevrier", "février");
        else if(s.contains("aout")) s = s.replaceFirst("aout", "août");
        else if(s.contains("decembre")) s = s.replaceFirst("decembre", "décembre");

        if(s.contains("décédé")) s = s.substring(0, s.indexOf("décédé"));

        if(s.contains(" et")) s = s.substring(0, s.indexOf(" et"));

        if(s.contains("1er")) s = s.replaceFirst("er","");

        if(s.contains(")")) s = s.substring(0, s.indexOf(")"));

        if(s.contains(",")) s = s.substring(0, s.indexOf(","));

        s = s.replaceAll("  ", " ");

        return s;
    }
}
