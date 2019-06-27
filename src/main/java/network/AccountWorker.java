package network;

import data.Account;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;

public class AccountWorker extends Worker
{
    public AccountWorker(Client client, String serverAddress) throws Exception
    {
        super(client, serverAddress, "Account");
    }

    public Account[] accountAllGet() throws Exception
    {
        return handleResponse(getResponse(""), Account[].class);
    }

    public Account accountGet(int id) throws Exception
    {
        return handleResponse(getResponse(Integer.toString(id)), Account.class);
    }

    public boolean accountPost(Account account) throws Exception
    {
        return handleResponse( postResponse(Entity.json(account), "") );
    }

    public boolean accountDelete(int id) throws Exception
    {
        return handleResponse( deleteResponse(Integer.toString(id)) );
    }

    public boolean accountPut(Account account) throws Exception
    {
        return handleResponse( putResponse(Entity.json(account), Integer.toString(account.getId())) );
    }

    public boolean accountPatch(Account account) throws Exception
    {
        return handleResponse( patchResponse(Entity.json(account), Integer.toString(account.getId())) );
    }
}
