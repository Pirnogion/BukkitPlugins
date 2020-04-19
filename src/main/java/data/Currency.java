package data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class Currency
{
    private int id;
    private int changeType;
    private boolean isDefault;
    private int saleExchangeRate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date created_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updated_at;

    public Currency() {}

    public Currency(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public int getChangeType()
    {
        return changeType;
    }

    public boolean getIsDefault()
    {
        return isDefault;
    }

    public int getSaleExchangeRate()
    {
        return saleExchangeRate;
    }

    public Date getUpdatedAt()
    {
        return updated_at;
    }

    public Date getCreatedAt()
    {
        return created_at;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Currency: id = ");
        builder.append(id);
        builder.append(", changeType = ");
        builder.append(changeType);
        builder.append(", isDefault = ");
        builder.append(isDefault);
        builder.append(", saleExchangeRate = ");
        builder.append(saleExchangeRate);
        builder.append(", updated_at = ");
        builder.append(updated_at);
        builder.append(", created_at = ");
        builder.append(created_at);
        builder.append(".");

        return builder.toString();
    }
}
