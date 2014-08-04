package controllers;

import models.Note;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.note;
import views.html.success;

import java.util.HashMap;
import java.util.Map;

import static play.data.Form.form;

public class Application extends Controller {

    public static Note model;

    final static Form<Note> noteForm = form(Note.class);

    public static Map<String, String> mapDBs = new HashMap<String, String>();
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
                return badRequest(note.render(noteForm.bindFromRequest(), mapDBs));
            }
            notka = form(Note.class).bindFromRequest().get();
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
    public static Result ajax()
    {
        return ok("Here's my server-side data");
    }
    public static Result get()
    {
        Form<Note> boundForm = noteForm.bindFromRequest();

        Map<String, String> mapDBs = new HashMap<String, String>();
        mapDBs.put("1", "PostgreSQL");
        mapDBs.put("2", "MySql");
        mapDBs.put("3", "Oracle");
        mapDBs.put("4", "MsSql");

        if(boundForm.hasErrors())
        {
            return badRequest(boundForm.errorsAsJson());
        }
        else {
            return ok("dziala");
        }
        //Note notka = new Note();
//        Form<Note> note = noteForm.bindFromRequest();
//        Map parameters = request().body().asFormUrlEncoded();
//        String message = ((String[])parameters.get("value"))[0];
//        if(note.hasErrors())
//        {
//            return ok(note.toString());
//        }
//        //notka = form(Note.class).bindFromRequest().get();
////        Map str = request().queryString();
////        String strarr = ((String[])str.get("value"))[0];
////        strarr = strarr.substring(strarr.indexOf('=') + 1);
////        return ok(strarr);
//        return ok(message);
    }
}
