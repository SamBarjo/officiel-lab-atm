package ca.ulaval.glo4002.atm.infrastructure.persistence;

import ca.ulaval.glo4002.atm.application.jpa.EntityManagerProvider;
import ca.ulaval.glo4002.atm.domain.accounts.Account;
import ca.ulaval.glo4002.atm.domain.accounts.StandardAccount;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class HibernateAccountRepositoryITest {
    private static EntityManagerFactory entityManagerFactory;

    private static final int ACCOUNT_ID = 3;
    private static final double BALANCE = 200.0;

    private HibernateAccountRepository repository;

    @BeforeClass
    public static void setupClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("atm-test");
    }

    @Before
    public void setup() {
        EntityManagerProvider.setEntityManager(entityManagerFactory.createEntityManager());
        repository = new HibernateAccountRepository();
    }

    @Test
    public void account_persist_accountIsRetrievable() {
        Account account = new StandardAccount(ACCOUNT_ID, BALANCE);

        repository.persist(account);
        Account result = repository.findByNumber(ACCOUNT_ID);

        assertEquals(result, account);
    }
}
