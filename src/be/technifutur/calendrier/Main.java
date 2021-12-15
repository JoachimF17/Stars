package be.technifutur.calendrier;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //variables
        int i;
        //objets
        ArrayList<Star> s1;
        ArrayList<Star> s2;

        //programme
    }

    private static ArrayList<Star> extractFileTwo()
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
