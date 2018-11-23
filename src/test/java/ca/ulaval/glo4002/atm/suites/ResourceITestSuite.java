package ca.ulaval.glo4002.atm.suites;

import ca.ulaval.glo4002.atm.AtmServer;
import ca.ulaval.glo4002.atm.contexts.ApiOnlyTestContext;
import ca.ulaval.glo4002.atm.rest.WithdrawResourceRestTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({WithdrawResourceRestTest.class})
public class ResourceITestSuite {
    public static final int PORT = 8214;

    private static AtmServer server;

    @BeforeClass
    public static void setupClass() {
        server = new AtmServer();
        server.start(PORT, new ApiOnlyTestContext(), false);
    }

    @AfterClass
    public static void tearDownClass() {
        if (server != null) {
            server.stop();
        }
    }
}
