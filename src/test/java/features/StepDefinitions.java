package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    PaymentService topUpMethod;
    PaymentService paymentMethod;
    Person danny;
    Person freddie;

    @Before
    public void setUp(){
        danny = new Person("Danny");
        freddie = new Person("Freddie");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @Given("Danny uses his {paymentService} to send a Payments")
    public void danny_uses_his_debit_card_to_send_payments(PaymentService paymentSource) {
        paymentMethod = paymentSource;
    }

    @Given("Danny has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(double startBalance) {
        danny.getAccount("EUR").setBalance(startBalance);
    }

    @Given("Freddie has a starting balance of {double}")
    public void freddie_has_a_starting_balance_of(double startBalance) {
        freddie.getAccount("EUR").setBalance(startBalance);
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
        //throw new io.cucumber.java.PendingException();
    }

    @When("Danny now tops up by {double}")
    public void danny_tops_up_by_amount(double amount) {
        danny.getAccount("EUR").addFunds(amount);
    }

    @When("Danny now sends {double} to Freddie")
    public void danny_sends_amount_to_freddie(double amount) {
        paymentMethod.sendPayment(danny, freddie, amount);
    }

    @When("Bill is split between Danny and Freddie of {double} with their {paymentService}")
    public void bill_to_split(double amount, PaymentService paymentService) {
        paymentMethod = paymentService;
        paymentMethod.splitBill(danny, freddie, amount);
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Then("Danny balance in his euro account should be {double}")
    public void danny_balance_in_his_account(double newBalance) {
        Assert.assertEquals(danny.getAccount("EUR").getBalance(), newBalance, 0);
    }

    @Then("Freddie balance in his euro account should be {double}")
    public void freddie_balance_in_his_account(double newBalance) {
        Assert.assertEquals(freddie.getAccount("EUR").getBalance(), newBalance, 0);
    }

    @Then("The balance in his euro account should be {double}")
    public void the_balance_in_his_euro_account_should_be(double newBalance) {
        Assert.assertEquals(danny.getAccount("EUR").getBalance(), newBalance, 0);
    }
}
