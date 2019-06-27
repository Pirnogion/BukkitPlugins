import core.Permission;
import data.Account;
import data.Currency;
import network.Worker;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import javax.ws.rs.client.*;
import java.util.ArrayList;
import java.util.Arrays;

public final class Main
{
    private static Client client;

    private static String serverAddress = "https://cloudmc.io/v1";

    public static void main(String[] args)
    {
        client = ClientBuilder.newClient().property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

        try
        {
            /* Query permissions */
            Worker clientWorker = new Worker(client, serverAddress, "", new ArrayList<>());

            String[] rawAccountPermissions = clientWorker.option("Account", String[].class);
            ArrayList<Permission> accountPermissions = new ArrayList<>();
            for (String permissionName: rawAccountPermissions)
                accountPermissions.add(Permission.valueOf(permissionName));

            String[] rawCurrencyPermissions = clientWorker.option("Currency", String[].class);
            ArrayList<Permission> currencyPermissions = new ArrayList<>();
            for (String permissionName: rawCurrencyPermissions)
                currencyPermissions.add(Permission.valueOf(permissionName));

            /* TEST */
            //Account getAccount = new Account(1, "", new ArrayList<>());
            Account postAccount = new Account(100, "exxxxx", new ArrayList<>());
            //Account putAccount = new Account(1, "lallalal(+)", new ArrayList<>());
            //Account patchAccount = new Account(5, "zuma(lox)", new ArrayList<>());
            //Account deleteAccount = new Account(3, "", new ArrayList<>());

            Worker accountWorker = new Worker(client, serverAddress, "Account", accountPermissions);
            Worker currencyWorker = new Worker(client, serverAddress, "Currency", currencyPermissions);

            System.out.println("[Account/Permissions] " + accountWorker.viewPermissions());
            System.out.println("[Currency/Permissions] " + currencyWorker.viewPermissions());

            System.out.println("[GET/All Account] " + Arrays.toString(accountWorker.get(Account[].class)));
            //System.out.println("[POST/Currency] " + currencyWorker.post(Entity.json(new Currency())));

            System.out.println("[GET/Account] " + accountWorker.get("1", Account.class).getUuid());
            System.out.println("[POST/Account] " + accountWorker.post(Entity.json(postAccount)));
            //System.out.println("[PUT/Account] " + accountWorker.put("1", Entity.json(putAccount)));
            //System.out.println("[PATCH/Account] " + accountWorker.patch("5", Entity.json(patchAccount)));
            //System.out.println("[DELETE/Account] " + accountWorker.delete("3"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
