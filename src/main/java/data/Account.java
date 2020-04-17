package data;

import java.util.Map;

public class Account
{
    private int id;
    private String uuid;
    private String created_at;
    private String updated_at;
    private Map<Integer, Integer> balances;

    public Account() {}

    public int getId()
    {
        return id;
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public String getUpdated_at()
    {
        return updated_at;
    }

    public Map<Integer, Integer> getBalances()
    {
        return balances;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Account: id = ");
        builder.append(id);
        builder.append(", uuid = ");
        builder.append(uuid);
        builder.append(", created_at = ");
        builder.append(created_at);
        builder.append(", updated_at = ");
        builder.append(updated_at);
        builder.append(". \nBalances: ");

        if (balances != null)
        {
            for (int currency_id : balances.keySet())
            {
                builder.append("\n - currency_id = ");
                builder.append(currency_id);
                builder.append(", amount = ");
                builder.append(balances.get(currency_id));
            }
            builder.append(".");
        }
        else
        {
            builder.append("none. ");
        }

        return builder.toString();
    }
}
