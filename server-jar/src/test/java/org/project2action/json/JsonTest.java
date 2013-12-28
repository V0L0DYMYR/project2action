package org.project2action.json;

import org.fest.assertions.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.project2action.config.Config;
import org.project2action.config.GoogleAuthorization;
import org.project2action.domain.Person;

import static com.yammer.dropwizard.testing.JsonHelpers.*;

@Ignore
public class JsonTest {

    @Test
    public void jsonTicket() throws Exception{
        fromJson(jsonFixture("json/Ticket.json"), Person.class);
        asJson(new Person(null, "Title", 1L, 1L));
    }

    @Test
    public void jsonToConfig() throws Exception{
        Config config = fromJson(jsonFixture("conf/in-queue.json"), Config.class);
        GoogleAuthorization google = config.getAuthorization().getGoogle();
        Assertions.assertThat(google.getOauth2Url()).isNotEmpty();
    }
}