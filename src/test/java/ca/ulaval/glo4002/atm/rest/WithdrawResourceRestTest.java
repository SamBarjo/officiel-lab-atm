package ca.ulaval.glo4002.atm.rest;

import ca.ulaval.glo4002.atm.application.ServiceLocator;
import ca.ulaval.glo4002.atm.application.banking.BankingService;
import ca.ulaval.glo4002.atm.application.banking.ReceiptDto;
import ca.ulaval.glo4002.atm.domain.accounts.AccountNotFoundException;
import ca.ulaval.glo4002.atm.suites.ResourceITestSuite;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

public class WithdrawResourceRestTest {
    private static final ReceiptDto DTO = new ReceiptDto(true, 25.0);

    private BankingService bankingService;

    @Before
    public void setup() {
        bankingService = ServiceLocator.resolve(BankingService.class);
    }

    @Test
    public void validAccountNumber_putWithdraw_responseContainsNeededFields() {
        willReturn(DTO).given(bankingService).withdrawMoney(anyInt(), any());
        RestAssured.given().port(ResourceITestSuite.PORT).contentType(ContentType.JSON)

            .when().put("/accounts/123/withdraw")

            .then().body("accepted", equalTo(DTO.accepted))
            .and().body("amount", equalTo((float)DTO.amount));
    }

    @Test
    public void invalidAccountNumber_putWithdraw_responseHasStatusCodeNotFound() {
        willThrow(new AccountNotFoundException()).given(bankingService).withdrawMoney(anyInt(), any());
        RestAssured.given().port(ResourceITestSuite.PORT).contentType(ContentType.JSON)

            .when().put("/accounts/123/withdraw")

            .then().statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
