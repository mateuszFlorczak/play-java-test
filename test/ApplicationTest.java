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
            .put("Name", ValidName)
            .put("Surname", ValidSurname)
            .put("Birthday", ValidBirthday)
            .put("Email", ValidEmail)
            .put("PrefDatabase", ValidPrefDB)
            .put("Notes", Notes)
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
            .put("Name", InvalidName)
            .put("Surname", ValidSurname)
            .put("Birthday", ValidBirthday)
            .put("Email", InvalidEmail)
            .put("PrefDatabase", ValidPrefDB)
            .put("Notes", Notes)
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
