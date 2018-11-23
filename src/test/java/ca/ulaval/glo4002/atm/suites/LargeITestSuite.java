package ca.ulaval.glo4002.atm.suites;

import ca.ulaval.glo4002.atm.AtmServer;
import ca.ulaval.glo4002.atm.contexts.LargeTestContext;
import ca.ulaval.glo4002.atm.rest.WithdrawLargeTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ WithdrawLargeTest.class })
public class LargeITestSuite {
    public static final int PORT = 8214;

    private static AtmServer server;

    @BeforeClass
    public static void setUpClass() {
        server = new AtmServer();
        server.start(PORT, new LargeTestContext());
    }

    @AfterClass
    public static void tearDownClass() {
        if (server != null) {
            server.stop();
        }
    }
}
