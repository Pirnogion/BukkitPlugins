package data;

public class BuyPetPacket
{
    private int currency_id;
    private int account_id;

    public BuyPetPacket(int currency_id, int account_id)
    {
        this.currency_id = currency_id;
        this.account_id = account_id;
    }

    public int getCurrency_id()
    {
        return currency_id;
    }

    public int getAccount_id()
    {
        return account_id;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Buy request: currency_id = ");  builder.append(currency_id);
        builder.append(", account_id = ");  builder.append(account_id);
        builder.append(".");

        return builder.toString();
    }
}
