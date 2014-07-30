import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import controllers.routes;
import models.Note;
import org.junit.*;

import play.data.Form;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static org.junit.Assert.assertEquals;
import static play.data.Form.form;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    String ValidName = "Mateusz";
    String ValidSurname = "Mak";
    String ValidBirthday = "1999-09-09";
    String ValidEmail = "qwe@wp.pl";
    String ValidPrefDB = "1";
    String Notes = "q";

    String Empty = "";
    String InvalidName = "qwe123";
    String InvalidSurname = "qwe123";
    String InvalidBirthday = "1999-13-13";
    String InvalidEmail = "qwe";
    String InvalidPrefDB = "0";


    @Test
    public void testNoteCreation_OK()
    {
        ImmutableMap<String, String> map = new ImmutableMap.Builder<String, String>()
                .put("Name", ValidName)
                .put("Surname", ValidSurname)
                .put("Birthday", ValidBirthday)
                .put("Email", ValidEmail)
                .put("PrefDatabase", ValidPrefDB)
                .put("Notes", Notes)
                .build();

        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = callAction(
                        routes.ref.Application.save(),
                        fakeRequest()
                                .withFormUrlEncodedBody(map));
                assertEquals(200, status(result));
            }
        });
    }

    @Test
    public void testNoteCreation_InvalidValues()
    {
        ImmutableMap<String, String> map = new ImmutableMap.Builder<String, String>()
                .put("Name", ValidName)
                .put("Surname", ValidSurname)
                .put("Birthday", ValidBirthday)
                .put("Email", InvalidEmail)
                .put("PrefDatabase", ValidPrefDB)
                .put("Notes", ValidNotes)
                .build();

        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = callAction(
                        routes.ref.Application.save(),
                        fakeRequest()
                                .withFormUrlEncodedBody(map));
                assertEquals(400, status(result));
            }
        });
    }

    /*@Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }
    // controller
    @Test
    public void callIndex() {
        Result result = controllers.Application.index();
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("<a href=\"/Application/add\">add</a>");
    }
    @Test
    public void badRoute() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/bad"));
                assertThat(result).isNull();
            }
        });
    }
    @Test
    public void rootRoute() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/"));
                assertThat(result).isNotNull();
            }
        });
    }
    public void renderTemplate() {    public void renderTemplate() {

        final Form<Note> notForm = form(Note.class);

        running(fakeApplication(), new Runnable() {
            public void run() {
                Content html = views.html.index.render(notForm);
                assertThat(contentType(html)).isEqualTo("text/html");
                assertThat(contentAsString(html)).contains("Name:");
                assertThat(contentAsString(html)).contains("Surname:");
                assertThat(contentAsString(html)).contains("Email:");
                assertThat(contentAsString(html)).contains("Year of birth:");
                assertThat(contentAsString(html)).contains("Your favourite database:");
                assertThat(contentAsString(html)).contains("Notes:");
                assertThat(contentAsString(html)).contains("<input type=\"text\" id=\"surname\" name=\"surname\" value=\"\" />");
            }
        });

    }
        11:27
*/
    }
