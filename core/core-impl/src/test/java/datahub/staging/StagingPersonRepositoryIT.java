package datahub.staging;

import com.e2open.datahub.DXApplication;
import com.e2open.datahub.metadata.entity.order.MetadataPerson;
import com.e2open.datahub.staging.dao.StagingPersonRepository;
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
@Transactional("stagingTransactionManager")
public class StagingPersonRepositoryIT {

    @Inject
    StagingPersonRepository sut;
    // 1. @Inject @Qualifier("stagingEntityManagerFactory")
    // 2. @PersistenceContext(unitName = "staging")
    @Inject
    EntityManager stagingEntityManager;
    @Inject
    private JdbcTemplate stagingJdbcTemplate;
    @Inject
    private DataSource stagingDataSource;

    @Test
    public void checkEntityManager() {
        StagingPerson entity = new StagingPerson(null, "foo", "bar");

        stagingEntityManager.persist(entity);

        Assert.assertNotNull(entity.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEntityManagerWithOtherEntities() {

        stagingEntityManager.persist(new MetadataPerson(null, "foo", "bar"));
    }

    @Test
    public void checkConnection() throws Exception {

        Connection connection = stagingDataSource.getConnection();

        try (Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("select * FROM stagingperson"));
        }
    }

    @Test(expected = Exception.class)
    public void checkConnectionWithOtherEntities() throws Exception {

        Connection connection = stagingDataSource.getConnection();

        try (Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("select * FROM metadataperson"));
        }
    }

    @Test
    public void checkJdbcTemplate() {

        List<StagingPerson> persons = stagingJdbcTemplate.query("SELECT * FROM StagingPerson", new StagingJdbcTemplateService.CustomerRowMapper());

        Assert.assertNotNull(persons);
    }

    @Test(expected = BadSqlGrammarException.class)
    public void checkJdbcTemplateWithOtherEntities() {

        stagingJdbcTemplate.query("SELECT * FROM MetadataPerson", new StagingJdbcTemplateService.CustomerRowMapper());
    }
}