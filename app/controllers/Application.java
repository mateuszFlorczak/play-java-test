package controllers;

import com.google.common.collect.ImmutableMap;
import models.Note;
import models.dao.NoteDao;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.note;
import views.html.success;

import java.util.Map;

import static play.data.Form.form;

public class Application extends Controller {

    public static Note model;
    final static Form<Note> noteForm = form(Note.class);
    public static Map<String, String> mapDBs = ImmutableMap.of(
            "1", "PostgreSQL",
            "2", "MySql",
            "3", "Oracle",
            "4", "MsSql"
    );
    private static NoteDao noteDao;

    private static NoteDao getNoteDao()
    {
        return noteDao == null ? new NoteDao(): noteDao;
    }
    public static void setNoteDao(NoteDao noteDao)
    {
        Application.noteDao = noteDao;
    }

    public static Result index()
    {
        return ok(index.render("home page"));
    }
    public static Result add()
    {
        return ok(note.render(noteForm, mapDBs));
    }
    @Transactional
    public static Result save()
    {
        Note notka = new Note();
        String result = "";
        try
        {
            if(noteForm.bindFromRequest().hasErrors())
            {
                return badRequest(note.render(noteForm.bindFromRequest(), mapDBs));
            }
            notka = form(Note.class).bindFromRequest().get();
            //JPA.em().persist(notka);
            getNoteDao().persist(notka);
            result = "Note created!";
        }
        catch(Exception e)
        {
            result = e.getMessage();
            return badRequest(note.render(noteForm.bindFromRequest(), mapDBs));
        }

        return ok(success.render(result));
    }
    public static Result validate()
    {
        Form<Note> boundForm = noteForm.bindFromRequest();

        if(boundForm.hasErrors())
        {
            return badRequest(boundForm.errorsAsJson());
        }
        else
        {
            return ok();
        }
    }
}
