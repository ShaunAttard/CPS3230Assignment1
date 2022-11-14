package um.cps3230.Interfaces;

public interface Website{

    public static boolean online = true;
    public static boolean offline = false;

    public boolean getStatus();
}