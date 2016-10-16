package datahub.metadata;

import com.e2open.datahub.DXApplication;
import com.e2open.datahub.metadata.dao.MetadataPersonRepository;
import com.e2open.datahub.metadata.entity.order.MetadataPerson;
import com.e2open.datahub.metadata.service.MetadataJdbcTemplateService;
import com.e2open.datahub.staging.entity.StagingPerson;
import com.e2open.datahub.staging.service.StagingJdbcTemplateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {DXApplication.class})
@ActiveProfiles("test")
@Transactional("metadataTransactionManager")
public class MetadataPersonRepositoryIT {

    @Inject
    MetadataPersonRepository sut;

    @Inject
    JdbcTemplate metadataJdbcTemplate;

    @Inject
    DataSource metadataDataSource;


    // 1. @Inject @Qualifier("metadataEntityManagerFactory")
    // 2. @PersistenceContext(unitName = "staging")
    @Inject
    EntityManager metadataEntityManager;

    @Test
    public void checkEntityManager() {
        MetadataPerson entity = new MetadataPerson(null, "foo", "bar");

        metadataEntityManager.persist(entity);

        Assert.assertNotNull(entity.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEntityManagerWithOtherEntities() {

        metadataEntityManager.persist(new StagingPerson(null, "foo", "bar"));
    }

    @Test
    public void checkConnection() throws Exception {

        Connection connection = metadataDataSource.getConnection();

        try (Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("select * FROM metadataperson"));
        }
    }

    @Test(expected = Exception.class)
    public void checkConnectionWithOtherEntities() throws Exception {

        Connection connection = metadataDataSource.getConnection();

        try (Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("select * FROM stagingperson"));
        }
    }

    @Test
    public void checkJdbcTemplate() {

        List<MetadataPerson> persons = metadataJdbcTemplate.query("SELECT * FROM MetadataPerson", new MetadataJdbcTemplateService.CustomerRowMapper());

        Assert.assertNotNull(persons);
    }

    @Test(expected = BadSqlGrammarException.class)
    public void checkJdbcTemplateWithOtherEntities() {

        metadataJdbcTemplate.query("SELECT * FROM StagingPerson", new StagingJdbcTemplateService.CustomerRowMapper());
    }

    @Test
    public void checkRepository() {

        MetadataPerson entity = sut.save(new MetadataPerson(null, "foo", "bar"));

        List<MetadataPerson> byLastName = sut.findByLastName(entity.getLastName());

        Assert.assertEquals (entity, byLastName.get (0));
    }
}