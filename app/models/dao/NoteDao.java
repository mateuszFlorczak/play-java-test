package models.dao;

import play.db.jpa.JPA;
import models.Note;
import javax.persistence.EntityManager;
/**
 * Created by mateuszf on 2014-08-05.
 */
public class NoteDao {
    private EntityManager getEm()
    {
        return JPA.em();
    }
    public void persist(Note note)
    {
        getEm().persist(note);
    }
}
