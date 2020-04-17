package data;

public class Currency
{
    private int id;
    private int changeType;
    private boolean isDefault;
    private int saleExchangeRate;
    private String created_at;
    private String updated_at;

    public Currency() {}

    public Currency(int id, int changeType, boolean isDefault, int saleExchangeRate, String created_at, String updated_at)
    {
        this.id = id;
        this.changeType = changeType;
        this.isDefault = isDefault;
        this.saleExchangeRate = saleExchangeRate;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Currency(int id, int changeType, boolean isDefault, int saleExchangeRate)
    {
        this.id = id;
        this.changeType = changeType;
        this.isDefault = isDefault;
        this.saleExchangeRate = saleExchangeRate;
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

    public String getUpdated_at()
    {
        return updated_at;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Currency: id = "); builder.append(id);
        builder.append(", changeType = "); builder.append(changeType);
        builder.append(", isDefault = "); builder.append(isDefault);
        builder.append(", saleExchangeRate = "); builder.append(saleExchangeRate);
        builder.append(", updated_at = "); builder.append(updated_at);
        builder.append(", created_at = "); builder.append(created_at);
        builder.append(".");

        return builder.toString();
    }
}
