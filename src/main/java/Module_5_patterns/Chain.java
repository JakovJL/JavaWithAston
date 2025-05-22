package Module_5_patterns;

import java.util.Scanner;

public class Chain {

    public static void main(String[] args) {

        ValidateTransaction firstValidation =new NumericCheck() ;
        ValidateTransaction secondValidation = new MaxLimit();
        ValidateTransaction thirdValidation = new MinLimit();

        firstValidation.setNext(secondValidation);
        secondValidation.setNext(thirdValidation);

        try(Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            firstValidation.check(new MoneyTransaction(input));
        } catch (TransactionValidationException e) {
            System.err.println("Ошибка валидации: " + e.getMessage());
        }
    }
}

class MoneyTransaction {
    private final String inputValue;
    private Double amount;

    public MoneyTransaction(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getInputValue() {
        return inputValue;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

abstract class ValidateTransaction {
    private ValidateTransaction next;

    public void setNext(ValidateTransaction next) {
        this.next = next;
    }

    protected void checkNext(MoneyTransaction moneyTransaction) {
        if (next != null) {
            next.check(moneyTransaction);
        }
    }

    public abstract void check(MoneyTransaction moneyTransaction);
}


class NumericCheck extends ValidateTransaction {
    @Override
    public void check(MoneyTransaction moneyTransaction) {
        String input = moneyTransaction.getInputValue();
        try {
            double amount = Double.parseDouble(input);
            moneyTransaction.setAmount(amount);
        } catch (NumberFormatException e) {
            throw new TransactionValidationException("Введено не числовое значение");
        }
        checkNext(moneyTransaction);
    }
}

class MinLimit extends ValidateTransaction {
    @Override
    public void check(MoneyTransaction moneyTransaction) {

        if (moneyTransaction.getAmount() < 10) {
            throw new TransactionValidationException("Сумма меньше 10");
        }
        checkNext(moneyTransaction);
    }
}

class MaxLimit extends ValidateTransaction {
    @Override
    public void check(MoneyTransaction moneyTransaction) {
        if (moneyTransaction.getAmount() > 1_000_000) {
            throw new TransactionValidationException("Превышен максимальный размер");
        }
        checkNext(moneyTransaction);
    }
}

class TransactionValidationException extends RuntimeException {
    public TransactionValidationException(String message) {
        super(message);
    }
}