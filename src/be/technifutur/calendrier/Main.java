package be.technifutur.calendrier;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        //objets
        StarFactory sf = new StarFactory();
        Map<Character, TreeSet<Star>> repertoire = repertoireTelephonique(sf.getList());

        //star60
        //System.out.println(star60(sf.getList()));

        //stars triees (nom ou date)
        /*
        TreeSet<Star> starsTrieesParNom = triSurNom(sf.getList());
        TreeSet<Star> starsTrieesParDate = triSurDate(sf.getList());
        for (Star s : starsTrieesParDate)
            System.out.println(s);*/

        //affichage du repertoire (triees par premiere lettre)
        /*for(Map.Entry<Character, TreeSet<Star>> e : repertoire.entrySet())
        {
            System.out.println(e.getKey());
            for(Star s : e.getValue())
                System.out.println("    "+s);
        }*/

        //recherche annees 60 a un caractere
        Scanner sc = new Scanner(System.in);
        boolean mauvaisInput = true;
        while(mauvaisInput)
        {
            try
            {
                System.out.print("Entrez la première lettre du nom : ");
                rechercheParCaractere(sc.nextLine().toUpperCase().charAt(0), repertoire);
                mauvaisInput = false;
            } catch (StringIndexOutOfBoundsException e)
            {
                System.out.println("Erreur : Entrez quelque chose !");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        //rechercher une star par son nom
        /*String st = "Wejdene";
        Star starRecherche = starAvecNom(st, repertoire);*/

        //nb de stars avant une star dont on passe le nom
        /*int nbStars = nbStarsAvantNom(starRecherche, starsTrieesParDate);
        System.out.println(nbStars);*/
    }

    private static int star60(Collection<Star> liste)
    {
        int cpt = 0;
        int annee;

        for (Star s : liste)
        {
            annee = s.getBirthDate().getYear();
            if (annee < 1970 && annee >= 1960)
                cpt++;
        }

        return cpt;
    }

    private static TreeSet<Star> triSurNom(Collection<Star> liste)
    {
        TreeSet<Star> set = new TreeSet<>(parNom());

        set.addAll(liste);

        return set;
    }

    private static Comparator<Star> parNom()
    {
        return Comparator.comparing(Star::getName)
                         .thenComparing(Star::getBirthDate);
    }

    private static TreeSet<Star> triSurDate(Collection<Star> liste)
    {
        TreeSet<Star> set = new TreeSet<>(parDate());

        set.addAll(liste);

        return set;
    }

    private static Comparator<Star> parDate()
    {
        return Comparator.comparing(Star::getBirthDate)
                         .thenComparing(Star::getName);
    }

    private static TreeMap<Character, TreeSet<Star>> repertoireTelephonique(Collection<Star> liste)
    {
        TreeMap<Character, TreeSet<Star>> map = new TreeMap<>();
        TreeSet<Star> temp;

        for (Star s : liste)
        {
            temp = map.get(s.getName().charAt(0));

            if (temp == null)
            {
                temp = new TreeSet<>(parNom());
                map.put(s.getName().charAt(0), temp);
            }

            temp.add(s);
        }

        return map;
    }

    private static void rechercheParCaractere(char c, Map<Character, TreeSet<Star>> repertoire) throws Exception
    {
        //variables
        int nbStars;
        //objets
        TreeSet<Star> search = repertoire.get(c);

        if (search == null)
        {
            if (c >= 'A' && c <= 'Z')
                System.out.printf("Il n'y a pas de stars commencant par %s dans la liste%n", c);
            else
                throw new Exception("Erreur : Entrez une lettre !");
        } else
        {
            nbStars = star60(search);
            if (nbStars == 0)
                System.out.printf("Il n'y a pas de stars commencant par %s nee dans les annees 60%n", c);
            else if (nbStars == 1)
                System.out.printf("Il y a 1 star nee dans les annees 60 dont le nom commence par %s%n", c);
            else
                System.out.printf("Il y a %s stars nees dans les annees 60 dont le nom commence par %s%n", nbStars, c);
        }
    }

    private static Star starAvecNom(String st, Map<Character, TreeSet<Star>> map)
    {
        TreeSet<Star> starCarac = map.get(st.charAt(0));

        for (Star s : starCarac)
            if (s.getName().equals(st))
                return s;

        return null;
    }

    private static int nbStarsAvantNom(Star s, TreeSet<Star> liste)
    {
        return liste.subSet(liste.first(), s).size();
    }
}
