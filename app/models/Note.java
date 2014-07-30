package models;

import org.hibernate.validator.constraints.Email;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.validation.Constraints.*;
import play.db.jpa.JPA;
import play.db.jpa.*;
import java.lang.Object;
import play.db.jpa.JPAPlugin.*;

import javax.annotation.Generated;
import javax.annotation.RegEx;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static play.data.format.Formats.DateTime;

@Entity
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    @Required
    @Column(length = 100)
    @MaxLength(100)
    @Pattern("[A-Za-z ]*")
    private String Name;
    @Required
    @Column(length = 100)
    @MaxLength(100)
    @Pattern("[A-Za-z ]*")
    private String Surname;
    @Required
    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date Birthday;
    @Required
    @Column(length = 200)
    @MaxLength(200)
    @Email
    private String Email;
    @Required
    @Pattern("[1-9]*")
    private String PrefDatabase;    //4 elementy do wyboru
    @Column(length = 5000)
    @MaxLength(5000)
    private String Notes;

    public Note() {}

    public int getId()
    {
        return Id;
    }
    protected void setId(int id)
    {
        Id = id;
    }
    public void setName(String name)
    {
        Name = name;
    }
    public String getName()
    {
        return Name;
    }
    public void setSurname(String surname)
    {
        Surname = surname;
    }
    public String getSurname()
    {
        return Surname;
    }
    public Date getBirthday() {
        return Birthday;
    }
    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getNotes() {
        return Notes;
    }
    public void setNotes(String notes) {
        Notes = notes;
    }
    public void setPrefDatabase(String prefDatabase)
    {
        PrefDatabase = prefDatabase;
    }
    public String getPrefDatabase() { return PrefDatabase; }
}