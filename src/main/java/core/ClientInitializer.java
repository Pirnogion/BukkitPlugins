package core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public final class ClientInitializer
{
    /* Client is accepting empty array as null object */
    public static Client getClient()
    {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider()
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        ClientConfig config = new ClientConfig(jacksonJsonProvider);

        return ClientBuilder.newClient(config)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
    }
}
