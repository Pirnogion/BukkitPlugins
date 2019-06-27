package data;

import java.util.ArrayList;

public class Account
{
    private int id;
    private String uuid;
    private String created_at;
    private String updated_at;
    private ArrayList<String> balances;

    public Account() {}

    public Account(int id, String uuid, String created_at, String updated_at, ArrayList<String> balances)
    {
        this.id = id;
        this.uuid = uuid;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.balances = balances;
    }

    public Account(int id, String uuid, ArrayList<String> balances)
    {
        this.id = id;
        this.uuid = uuid;
        this.balances = balances;
    }

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

    public ArrayList<String> getBalances()
    {
        return balances;
    }
}
