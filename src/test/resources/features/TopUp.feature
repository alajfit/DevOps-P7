Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 110


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 230 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    #And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 250

  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    When Danny now tops up by <topUpAmount>
    Then The balance in his euro account should be <newBalance>
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |

  Scenario Outline: Split bill between two users
    Given Danny has a starting balance of <dannyStartBalance>
    And Freddie has a starting balance of <freddieStartBalance>
    When Bill is split between Danny and Freddie of <billValue> with their BankAccount
    Then Danny balance in his euro account should be <dannyEndBalance>
    Then Freddie balance in his euro account should be <freddieEndBalance>
    Examples:
      | dannyStartBalance | freddieStartBalance | billValue | dannyEndBalance | freddieEndBalance |
      | 200               | 400                 | 100       | 150             | 350               |
      | 110               | 300                 | 200       | 10              | 200               |
      | 2000              | 1000                | 300       | 1850            | 850               |

  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service
    #The scenarios below will need a payment service that accepts or rejects a request to add funds
    Scenario: Payment service rejects the request
      Given Danny has a starting balance of 200
      And Danny uses his DebitCard to send a Payments
      When Danny now sends 300 to Freddie
      Then The balance in his euro account should be 200

    Scenario: Payment service accepts the request
      Given Danny has a starting balance of 200
      And Danny uses his DebitCard to send a Payments
      When Danny now sends 100 to Freddie
      Then The balance in his euro account should be 100