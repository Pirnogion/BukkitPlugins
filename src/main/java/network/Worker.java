package network;

import core.Permission;
import core.PermissionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;

public class Worker
{
    private final ArrayList<Permission> permissions;

    public final Client client;
    public final String serverAddress;
    public final String name;

    public Worker(Client client, String serverAddress, String name, ArrayList<Permission> permissions)
    {
        this.permissions = permissions;
        this.client = client;
        this.serverAddress = serverAddress;
        this.name = name;
    }

    private void checkPermission(Permission permission) throws PermissionException
    {
        if ( !permissions.contains(permission) ) throw new PermissionException(permission, name);
    }

    private <T> T handleResponse(Response response, Class<T> clazz) throws HTTPException
    {
        if (response.getStatusInfo().getFamily() != Family.SUCCESSFUL) throw new HTTPException(response.getStatus());

        return (clazz == Boolean.class) ? (T) new Boolean(true) : response.readEntity(clazz);
    }

    private boolean handleResponse(Response response) throws HTTPException
    {
        return handleResponse(response, Boolean.class);
    }

    private Invocation.Builder buildRequest(String... additionalPath)
    {
        WebTarget target = client.target(serverAddress).path(name);

        for (String path: additionalPath)
        {
            target = target.path(path);
        }

        return target.request(MediaType.APPLICATION_JSON);
    }

    public <T> T option(String additionalPath, Class<T> clazz) throws HTTPException
    {
        return handleResponse(buildRequest(additionalPath).options(), clazz);
    }

    public <T> T option(Class<T> clazz) throws HTTPException
    {
        return option("", clazz);
    }

    public <T> T get(String additionalPath, Class<T> clazz) throws PermissionException, HTTPException
    {
        checkPermission(Permission.GET);

        return handleResponse(buildRequest(additionalPath).get(), clazz);
    }

    public <T> T get(Class<T> clazz) throws PermissionException, HTTPException
    {
        return get("", clazz);
    }

    public boolean post(String additionalPath, Entity entity) throws PermissionException, HTTPException
    {
        checkPermission(Permission.POST);

        return handleResponse(buildRequest(additionalPath).post(entity));
    }

    public boolean post(Entity entity) throws PermissionException, HTTPException
    {
        return post("", entity);
    }

    public boolean delete(String additionalPath) throws PermissionException, HTTPException
    {
        checkPermission(Permission.DELETE);

        return handleResponse(buildRequest(additionalPath).delete());
    }

    public boolean delete() throws PermissionException, HTTPException
    {
        return delete("");
    }

    public boolean put(String additionalPath, Entity entity) throws PermissionException, HTTPException
    {
        checkPermission(Permission.PUT);

        return handleResponse(buildRequest(additionalPath).put(entity));
    }

    public boolean put(Entity entity) throws PermissionException, HTTPException
    {
        return put(entity);
    }

    public boolean patch(String additionalPath, Entity entity) throws PermissionException, HTTPException
    {
        checkPermission(Permission.PATCH);

        return handleResponse(buildRequest(additionalPath).method("PATCH", entity));
    }

    public boolean patch(Entity entity) throws PermissionException, HTTPException
    {
        return patch(entity);
    }

    public boolean hasPermissions(String permission)
    {
        return permissions.contains(Permission.valueOf(permission));
    }
    public boolean hasPermissions(Permission permission)
    {
        return permissions.contains(permission);
    }

    public String viewPermissions()
    {
        return permissions.toString();
    }
}
