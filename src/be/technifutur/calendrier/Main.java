package be.technifutur.calendrier;

import be.technifutur.calendrier.comparators.*;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        //objets
        StarFactory sf = new StarFactory();
        List<Star> stars = sf.getList();
        TreeSet<Star> starsTrieesParNom = triNom(stars);
        TreeSet<Star> starsTrieesParDate = triDate(stars);
        Map<Character, TreeSet<Star>> repertoire = repertoireTelephonique(stars);
        Scanner sc = new Scanner(System.in);

        //star60
        //System.out.println(star60(stars));

        //stars triees (nom ou date)
        for(Star s : starsTrieesParDate)
            System.out.println(s);

        //affichage du repertoire (triees par premiere lettre)
        /*for(Map.Entry<Character, TreeSet<Star>> e : repertoire.entrySet())
        {
            System.out.println(e.getKey());
            for(Star s : e.getValue())
                System.out.println("    "+s);
        }*/

        //recherche annees 60 a un caractere
        /*System.out.print("Entrez la première lettre du nom : ");
        rechercheParCaractere(sc.nextLine().toUpperCase().charAt(0), repertoire);*/

        //rechercher une star par son nom
        String st = "Reine Elizabeth II";
        Star starRecherche = starAvantNom(st, repertoire);

        //nb de stars avant une star dont on passe le nom
        int nbStars = nbStarsAvantNom(starRecherche, starsTrieesParDate);

        System.out.println(nbStars);
    }

    private static int star60(Collection<Star> liste)
    {
        int cpt = 0;
        int annee;

        for(Star s : liste)
        {
            annee = s.getBirthDate().getYear();
            if(annee < 1970 && annee >=1960)
                cpt++;
        }

        return cpt;
    }

    private static TreeSet<Star> triNom(Collection<Star> liste)
    {
        TreeSet<Star> set = new TreeSet<>(new NomComparator());

        set.addAll(liste);

        return set;
    }

    private static TreeSet<Star> triDate(Collection<Star> liste)
    {
        TreeSet<Star> set = new TreeSet<>(new DateComparator());

        set.addAll(liste);

        return set;
    }

    private static TreeMap<Character, TreeSet<Star>> repertoireTelephonique(Collection<Star> liste)
    {
        TreeMap<Character, TreeSet<Star>> map = new TreeMap<>();
        TreeSet<Star> temp;

        for(Star s : liste)
        {
            temp = map.get(s.getName().charAt(0));

            if(temp == null)
            {
                temp = new TreeSet<>(new NomComparator());
                map.put(s.getName().charAt(0), temp);
            }

            temp.add(s);
        }

        return map;
    }

    private static void rechercheParCaractere(char c, Map<Character, TreeSet<Star>> repertoire)
    {
        //variables
        int nbStars;
        //objets
        TreeSet<Star> search = repertoire.get(c);

        if(search == null)
        {
            if(c >= 'A' && c <= 'Z')
                System.out.printf("Il n'y a pas de stars commencant par %s dans la liste%n", c);
            else
                System.out.println("Input invalide");
        }
        else
        {
            nbStars = star60(search);
            if (nbStars == 0)
                System.out.printf("Il n'y a pas de stars commencant par %s nee dans les annees 60%n", c);
            else if (nbStars == 1)
                System.out.printf("Il y a 1 star nee dans les annees 60 dont la lettre commence par %s%n", c);
            else
                System.out.printf("Il y a %s stars nees dans les annees 60 dont la lettre commence par %s%n", nbStars, c);
        }
    }

    private static Star starAvantNom(String st, Map<Character, TreeSet<Star>> map)
    {
        TreeSet<Star> starCarac = map.get(st.charAt(0));

        for(Star s : starCarac)
            if(s.getName().equals(st))
                return s;

        return null;
    }

    private static int nbStarsAvantNom(Star s, TreeSet<Star> liste)
    {
        return liste.subSet(liste.first(), s).size();
    }
}
