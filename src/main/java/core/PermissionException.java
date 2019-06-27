package core;

public class PermissionException extends Exception
{
    private static final String errorMessage = "You don't have permission to do this. Permission: %s, Category: %s.";

    public final Permission permission;
    public final String category;

    public PermissionException(Permission permission, String category)
    {
        super(String.format(errorMessage, permission.name(), category));

        this.permission = permission;
        this.category = category;
    }

    public Permission getPermission()
    {
        return permission;
    }

    public String getCategory()
    {
        return category;
    }
}
