package be.technifutur.calendrier;

import java.time.LocalDate;

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


    @Override
    public String toString()
    {
        return "Star{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
