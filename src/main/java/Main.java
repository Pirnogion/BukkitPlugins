import data.Account;
import data.Currency;
import network.AccountWorker;
import network.CurrencyWorker;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import javax.ws.rs.client.*;
import java.util.ArrayList;

public final class Main
{
    private static Client client;

    public static void main(String[] args)
    {
        client = ClientBuilder.newClient().property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

        try
        {
            AccountWorker accountWorker = new AccountWorker(client, "https://cloudmc.io/v1");
            CurrencyWorker currencyWorker = new CurrencyWorker(client, "https://cloudmc.io/v1");

            System.out.println("[Account] Permissions: " + accountWorker.viewPermissions());
            System.out.println("[Currency] Permissions: " + currencyWorker.viewPermissions());

            //System.out.println( currencyWorker.currencyAllGet()[0].getId() );
            currencyWorker.currencyPost(new Currency(1, 0, false, 0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
