public class Money implements Expression {
    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    Expression plus(Money addend) {
        return new Money(amount + addend.amount, currency);
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    @Override
    public boolean equals(Object o) {
        Money money = (Money) o;
        return amount == money.amount && currency().equals(money.currency());
    }

    @Override
    public int hashCode() {
        return amount;
    }

    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    String currency() {
        return currency;
    }


}
