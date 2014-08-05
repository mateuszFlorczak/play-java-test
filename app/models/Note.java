package models;

import org.hibernate.validator.constraints.Email;
import play.data.format.Formats;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Note //extends Model
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    @Required
    @Column(length = 100)
    @MaxLength(100)
    @Pattern("[A-Za-z ]*")
    private String name;
    @Required
    @Column(length = 100)
    @MaxLength(100)
    @Pattern("[A-Za-z ]*")
    private String surname;
    @Required
    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date birthday;
    @Required
    @Column(length = 200)
    @MaxLength(200)
    @Email
    private String email;
    @Required
    @Pattern("[1-9]*")
    private String prefDatabase;
    @Column(length = 5000)
    @MaxLength(5000)
    private String notes;

    public Note() {}

    public int getid()
    {
        return id;
    }
    protected void setid(int id)
    {
        this.id = id;
    }
    public void setname(String name)
    {
        this.name = name;
    }
    public String getname()
    {
        return this.name;
    }
    public void setsurname(String surname)
    {
        this.surname = surname;
    }
    public String getsurname()
    {
        return this.surname;
    }
    public Date getbirthday()
    {
        return this.birthday;
    }
    public void setbirthday(Date birthday)
    {
        this.birthday = birthday;
    }
    public String getemail()
    {
        return this.email;
    }
    public void setemail(String email)
    {
        this.email = email;
    }
    public String getnotes()
    {
        return this.notes;
    }
    public void setnotes(String notes)
    {
        this.notes = notes;
    }
    public void setprefDatabase(String prefDatabase)
    {
        this.prefDatabase = prefDatabase;
    }
    public String getprefDatabase()
    {
        return this.prefDatabase;
    }
}