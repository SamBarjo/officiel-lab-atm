package ca.ulaval.glo4002.atm.contexts;

import ca.ulaval.glo4002.atm.application.ServiceLocator;
import ca.ulaval.glo4002.atm.application.banking.BankingService;
import ca.ulaval.glo4002.atm.application.banking.ReceiptAssembler;
import ca.ulaval.glo4002.atm.application.jpa.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.atm.application.jpa.EntityManagerProvider;
import ca.ulaval.glo4002.atm.domain.accounts.AccountRepository;
import ca.ulaval.glo4002.atm.domain.accounts.StandardAccount;
import ca.ulaval.glo4002.atm.domain.dispensers.CashDispenser;
import ca.ulaval.glo4002.atm.infrastructure.dispensers.FakeCashDispenser;
import ca.ulaval.glo4002.atm.infrastructure.persistence.HibernateAccountRepository;

import javax.persistence.EntityManager;

public class LargeTestContext implements Context {

    @Override
    public void apply() {
        ServiceLocator.registerSingleton(ReceiptAssembler.class, new ReceiptAssembler());
        ServiceLocator.registerSingleton(AccountRepository.class, new HibernateAccountRepository());
        ServiceLocator.registerSingleton(CashDispenser.class, new FakeCashDispenser());
        ServiceLocator.registerSingleton(BankingService.class, new BankingService());

        fillDatabase();
    }

    private void fillDatabase() {
        EntityManager entityManager = EntityManagerFactoryProvider.getFactory().createEntityManager();
        EntityManagerProvider.setEntityManager(entityManager);

        AccountRepository accountRepository = ServiceLocator.resolve(AccountRepository.class);

        accountRepository.persist(new StandardAccount(123, 1000));
        accountRepository.persist(new StandardAccount(456, 1));

        EntityManagerProvider.clearEntityManager();
    }
}
