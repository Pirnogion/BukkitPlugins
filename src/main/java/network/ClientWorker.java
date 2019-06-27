package network;

import javax.ws.rs.client.Client;

public class ClientWorker extends Worker
{
    public ClientWorker(Client client, String serverAddress) throws Exception
    {
        super(client, serverAddress, "allows", new String[] {"GET"});
    }

    public String[] categoriesGet() throws Exception
    {
        return handleResponse(getResponse(""), String[].class);
    }
}
