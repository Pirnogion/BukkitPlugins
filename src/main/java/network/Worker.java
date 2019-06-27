package network;

import core.Permission;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.Response.Status.Family;
import java.util.ArrayList;

public class Worker
{
    protected final ArrayList<Permission> permissions;

    protected final Client client;
    protected final String serverAddress;
    protected final String name;

    protected Worker(Client client, String serverAddress, String name, String[] permissions)
    {
        this.permissions = new ArrayList<Permission>()
        {{
            for (String permissionName: permissions)
                add(Permission.valueOf(permissionName));
        }};

        this.client = client;
        this.serverAddress = serverAddress;
        this.name = name;
    }

    protected Worker(Client client, String serverAddress, String name) throws Exception
    {
        this.client = client;
        this.serverAddress = serverAddress;
        this.name = name;

        this.permissions = new ArrayList<Permission>()
        {{
            for (String permissionName: permissionsOption())
                add(Permission.valueOf(permissionName));
        }};
    }

    private String[] permissionsOption() throws Exception
    {
        return handleResponse(optionResponse(""), String[].class);
    }

    private boolean checkPermission(Permission permission)
    {
        if ( permissions.contains(permission) )
        {
            return true;
        }

        System.err.println("The category as '" + name + "' doesn't have the permission.");

        return false;
    }

    protected <T> T handleResponse(Response response, Class<T> clazz)
    {
        StatusType status = response.getStatusInfo();
        if (status.getFamily() != Family.SUCCESSFUL)
        {
            System.err.println( status.getStatusCode() + " - " + status.getReasonPhrase() );

            return null;
        }

        return response.readEntity(clazz);
    }

    protected boolean handleResponse(Response response)
    {
        StatusType status = response.getStatusInfo();
        if (status.getFamily() != Family.SUCCESSFUL)
        {
            System.err.println( status.getStatusCode() + " - " + status.getReasonPhrase() );

            return false;
        }

        return true;
    }

    protected Response optionResponse(String additionalPath) throws Exception
    {
        WebTarget webTarget = client.target(serverAddress).path(name).path(additionalPath);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        return invocationBuilder.options();
    }

    protected Response getResponse(String additionalPath) throws Exception
    {
        if ( checkPermission(Permission.GET) )
        {
            WebTarget webTarget = client.target(serverAddress).path(name).path(additionalPath);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            return invocationBuilder.get();
        }

        return null;
    }

    protected Response postResponse(Entity entity, String additionalPath) throws Exception
    {
        if ( checkPermission(Permission.POST) )
        {
            WebTarget webTarget = client.target(serverAddress).path(name).path(additionalPath);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            return invocationBuilder.post(entity);
        }

        return null;
    }

    protected Response deleteResponse(String additionalPath) throws Exception
    {
        if ( checkPermission(Permission.DELETE) )
        {
            WebTarget webTarget = client.target(serverAddress).path(name).path(additionalPath);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);

            return invocationBuilder.delete();
        }

        return null;
    }

    protected Response putResponse(Entity entity, String additionalPath) throws Exception
    {
        if ( checkPermission(Permission.PUT) )
        {
            WebTarget webTarget = client.target(serverAddress).path(name).path(additionalPath);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            return invocationBuilder.put(entity);
        }

        return null;
    }

    protected Response patchResponse(Entity entity, String additionalPath) throws Exception
    {
        if ( checkPermission(Permission.PATCH) )
        {
            WebTarget webTarget = client.target(serverAddress).path(name).path(additionalPath);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            return invocationBuilder.method("PATCH", entity);
        }

        return null;
    }

    public Client getClient()
    {
        return client;
    }

    public String getServerAddress()
    {
        return serverAddress;
    }

    public String getName()
    {
        return name;
    }

    public boolean hasPermissions(String permission)
    {
        return permissions.contains(Permission.valueOf(permission));
    }

    public String viewPermissions()
    {
        return permissions.toString();
    }
}
