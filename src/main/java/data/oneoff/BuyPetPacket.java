package data.oneoff;

public class BuyPetPacket
{
    private int currencyId;
    private int accountId;

    public BuyPetPacket(int currencyId, int accountId)
    {
        this.currencyId = currencyId;
        this.accountId = accountId;
    }

    public int getCurrencyId()
    {
        return currencyId;
    }

    public int getAccountId()
    {
        return accountId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Buy request: currency_id = ");
        builder.append(currencyId);
        builder.append(", account_id = ");
        builder.append(accountId);
        builder.append(".");

        return builder.toString();
    }
}
