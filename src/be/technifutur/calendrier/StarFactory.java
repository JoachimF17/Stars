package be.technifutur.calendrier;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class StarFactory
{
    public ArrayList<Star> starBuilder()
    {
        //variables
        
        //objets
        ArrayList<Star> s;
        ArrayList<Star> s1;
        ArrayList<Star> s2;

        s1 = extractFileOne();
        s2 = extractFileTwo();

        for()

        return s;
    }

    //methodes
    private ArrayList<Star> extractFileOne()
    {
        //variables
        int i, jour, mois = 0, annee;
        //objets
        ArrayList<Star> s = new ArrayList<>();
        Scanner temp;
        String line, tempSt;
        File f = new File("star1.txt");

        try(Scanner sc = new Scanner(f))
        {
            i = 0;
            while(sc.hasNext())
            {
                s.add(new Star());

                if(sc.hasNextInt())
                    line = sc.nextLine();
                else
                    line = sc.nextLine().replaceFirst("er","");

                line = line.replaceAll(":", "");
                temp = new Scanner(line);

                //affectation du jour
                jour = temp.nextInt();

                //affectation du mois
                switch(temp.next())
                {
                    case "janvier":
                        mois = 1;
                        break;
                    case "février":
                        mois = 2;
                        break;
                    case "mars":
                        mois = 3;
                        break;
                    case "avril":
                        mois = 4;
                        break;
                    case "mai":
                        mois = 5;
                        break;
                    case "juin":
                        mois = 6;
                        break;
                    case "juillet":
                        mois = 7;
                        break;
                    case "août":
                        mois = 8;
                        break;
                    case "septembre":
                        mois = 9;
                        break;
                    case "octobre":
                        mois = 10;
                        break;
                    case "novembre":
                        mois = 11;
                        break;
                    case "décembre":
                        mois = 12;
                        break;
                }

                //affectation annee
                annee = temp.nextInt();

                //transformation de la date de naissance
                s.get(i).setBirthDate(LocalDate.of(annee, mois, jour));

                //affectation nom
                tempSt = temp.next();

                if(!temp.hasNext())
                    s.get(i).setName(tempSt);
                else
                {
                    tempSt = tempSt + " " + temp.next();

                    if(!temp.hasNext())
                        s.get(i).setName(tempSt);
                    else if(temp.hasNext("&"))
                    {
                        s.get(i).setName(tempSt);
                        i++;
                        s.add(new Star());
                        s.get(i).setBirthDate(LocalDate.of(annee, mois, jour));
                        temp.next();
                        s.get(i).setName(temp.nextLine().trim());
                    }else
                        s.get(i).setName(tempSt + temp.nextLine());
                }

                i++;
            }
        }catch(FileNotFoundException e)
        {
            System.out.println("Pas de fichier");
        }

        return s;
    }

    private ArrayList<Star> extractFileTwo()
    {
        //variables
        int i, jour, mois = 0, annee;
        //objets
        ArrayList<Star> s = new ArrayList<>();
        File f = new File("star2.txt");
        Scanner tempSc;
        String[] temp;

        try(Scanner sc = new Scanner(f))
        {
            i = 0;
            while(sc.hasNext())
            {
                s.add(new Star());
                temp = sc.nextLine().split(":");

                //affectation du nom
                if(temp[0].contains("("))
                    temp[0] = temp[0].substring(0, (temp[0].indexOf("(")-1));

                s.get(i).setName(temp[0].trim());

                //affectation de la date
                if(temp[1].contains(")"))
                    temp[1] = temp[1].substring(0, (temp[1].indexOf(")")-1));
                else if(temp[1].contains(","))
                    temp[1] = temp[1].substring(0, (temp[1].indexOf(",")-1));

                tempSc = new Scanner(temp[1].replaceAll("er", ""));
                //affectation du jour
                jour = tempSc.nextInt();

                //affectation du mois
                switch(tempSc.next())
                {
                    case "JANVIER":
                        mois = 1;
                        break;
                    case "FEVRIER":
                        mois = 2;
                        break;
                    case "MARS":
                        mois = 3;
                        break;
                    case "AVRIL":
                        mois = 4;
                        break;
                    case "MAI":
                        mois = 5;
                        break;
                    case "JUIN":
                        mois = 6;
                        break;
                    case "JUILLET":
                        mois = 7;
                        break;
                    case "AOUT":
                        mois = 8;
                        break;
                    case "SEPTEMBRE":
                        mois = 9;
                        break;
                    case "OCTOBRE":
                        mois = 10;
                        break;
                    case "NOVEMBRE":
                        mois = 11;
                        break;
                    case "DECEMBRE":
                        mois = 12;
                        break;
                }

                //affectation annee
                annee = tempSc.nextInt();

                s.get(i).setBirthDate(LocalDate.of(annee, mois, jour));

                i++;
            }

        }catch(FileNotFoundException e)
        {
            System.out.println("Pas de fichier");
        }

        return s;
    }
}
