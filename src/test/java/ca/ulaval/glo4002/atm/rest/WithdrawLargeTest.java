package ca.ulaval.glo4002.atm.rest;

import ca.ulaval.glo4002.atm.application.banking.WithdrawalRequest;
import ca.ulaval.glo4002.atm.suites.LargeITestSuite;
import io.restassured.http.ContentType;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class WithdrawLargeTest {
    private static final int ACCOUNT_NUMBER = 123;
    private static final WithdrawalRequest REQUEST = new WithdrawalRequest(50);

    @Test
    public void canWithdrawMoney() {
        Map<String, Integer> pathParams = new HashMap<>();
        pathParams.put("accountNumber", ACCOUNT_NUMBER);
        given().port(LargeITestSuite.PORT).contentType(ContentType.JSON).body(REQUEST)

            .when().put("/accounts/{accountNumber}/withdraw", pathParams)

            .then().statusCode(Response.Status.OK.getStatusCode());
    }
}
