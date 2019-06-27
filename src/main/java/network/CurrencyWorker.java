package network;

import data.Currency;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;

public class CurrencyWorker extends Worker
{
    public CurrencyWorker(Client client, String serverAddress) throws Exception
    {
        super(client, serverAddress, "Currency");
    }

    public Currency[] currencyAllGet() throws Exception
    {
        return handleResponse(getResponse(""), Currency[].class);
    }

    public boolean currencyPost(Currency currency) throws Exception
    {
        return handleResponse( postResponse(Entity.json(currency), "") );
    }
}
