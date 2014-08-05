import com.google.common.collect.ImmutableMap;
import controllers.routes;
import controllers.Application;
import models.Note;
import models.dao.NoteDao;
import play.data.format.Formats;
import play.test.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;

import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.test.Helpers.fakeApplication;

public class ApplicationTest {

    NoteDao noteDaoMock = mock(NoteDao.class);

    String validName = "Mateusz";
    String validSurname = "Mak";
    String validBirthday = "1999-09-09";
    String validEmail = "qwe@wp.pl";
    String validPrefDB = "1";
    String notes = "q";

    String empty = "";
    String invalidName = "qwe123";
    String invalidSurname = "qwe123";
    String invalidBirthday = "1999-13-13";
    String invalidEmail = "qwe";
    String invalidPrefDB = "0";

    @Before
    public void setup()
    {
        doNothing().when(noteDaoMock).persist(any());
        Application.setNoteDao(noteDaoMock);
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void testNoteCreation_OK()
    {
        ImmutableMap<String, String> map = new ImmutableMap.Builder<String, String>()
            .put("name", validName)
            .put("surname", validSurname)
            .put("birthday", validBirthday)
            .put("email", validEmail)
            .put("prefDatabase", validPrefDB)
            .put("notes", notes)
            .build();

        Result result = callAction(
            routes.ref.Application.save(),
            fakeRequest()
                .withFormUrlEncodedBody(map));
        assertEquals(200, status(result));
    }

    @Test
    public void testNoteCreation_InvalidValues()
    {
        ImmutableMap<String, String> map = new ImmutableMap.Builder<String, String>()
            .put("name", invalidName)
            .put("surname", validSurname)
            .put("birthday", validBirthday)
            .put("email", invalidEmail)
            .put("prefDatabase", validPrefDB)
            .put("notes", notes)
            .build();

        Result result = callAction(
            routes.ref.Application.save(),
            fakeRequest()
                .withFormUrlEncodedBody(map));
        assertEquals(400, status(result));
    }

    @Test
    public void badRoute() {
        Result result = route(fakeRequest(GET, "/bad"));
        assertThat(result).isNull();
    }

    @Test
    public void rootRoute() {
        Result result = route(fakeRequest(GET, "/"));
        assertThat(result).isNotNull();
    }
}
