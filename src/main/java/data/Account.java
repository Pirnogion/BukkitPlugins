package data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class Account
{
    private int id;
    private String uuid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date created_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updated_at;
    private Map<Integer, Integer> balances;

    public Account() {}

    public Account(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getUuid()
    {
        return uuid;
    }

    public Date getCreatedAt()
    {
        return created_at;
    }

    public Date getUpdatedAt()
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
