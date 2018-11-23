package ca.ulaval.glo4002.atm.rest;

import ca.ulaval.glo4002.atm.application.ServiceLocator;
import ca.ulaval.glo4002.atm.application.banking.BankingService;
import ca.ulaval.glo4002.atm.application.banking.ReceiptDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

public class WithdrawResourceITest {
    private static BankingService bankingService;

    private static final ReceiptDto DTO = new ReceiptDto(true, 25.0);

    @Before
    public void setup() {
        bankingService = ServiceLocator.resolve(BankingService.class);
    }

    @Test
    public void validAccountNumber_putWithdraw_responseContainsNeededFields() {
        BDDMockito.given(bankingService.withdrawMoney(anyInt(), any())).willReturn(DTO);
        RestAssured.given().port(ResourceITestSuite.PORT).contentType(ContentType.JSON)

            .when().put("/accounts/123/withdraw")

            .then().body("accepted", equalTo(DTO.accepted))
            .and().body("amount", equalTo((float)DTO.amount));
    }
}
