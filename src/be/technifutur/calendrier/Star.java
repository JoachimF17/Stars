package be.technifutur.calendrier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Star
{
    //attributs
    private String name;
    private LocalDate birthDate;

    //methodes
    //getters
    public String getName()
    {
        return name;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }
    //fin getters

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }
    //fin setters

    //constructeur
    public Star(String name, LocalDate birthDate)
    {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString()
    {
        return "Nom = '" + name + '\'' +
                ", Date de naissance = " + birthDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
