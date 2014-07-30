package controllers;

import models.Note;
import play.*;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.HashMap;
import java.util.Map;

import static play.data.Form.form;

public class Application extends Controller {

    //public static Result index() {
        //return ok(index.render("Your new application is ready."));
    //}
    public static Note model;

    final static Form<Note> noteForm = form(Note.class);

    public static Result index()
    {
        return ok(index.render("home page"));
    }
    public static Result add()
    {
        Map<String, String> mapDBs = new HashMap<String, String>();
        mapDBs.put("1", "PostgreSQL");
        mapDBs.put("2", "MySql");
        mapDBs.put("3", "Oracle");
        mapDBs.put("4", "MsSql");

        return ok(note.render(noteForm, mapDBs));
    }
    @Transactional
    public static Result save() {
        Map<String, String> mapDBs = new HashMap<String, String>();
        mapDBs.put("1", "PostgreSQL");
        mapDBs.put("2", "MySql");
        mapDBs.put("3", "Oracle");
        mapDBs.put("4", "MsSql");
        Note notka = new Note();
        String result = "";
        try {
            if(noteForm.bindFromRequest().hasErrors())
            {
                return ok(note.render(noteForm.bindFromRequest(), mapDBs));
            }
            notka = form(Note.class).bindFromRequest().get();

            //notka.save();
            JPA.em().persist(notka);
            result = "Note created!";
        }
        catch(Exception e)
        {
            result = e.getMessage();
            return badRequest(note.render(noteForm.bindFromRequest(), mapDBs));
        }

        return ok(success.render(result));
    }
}
