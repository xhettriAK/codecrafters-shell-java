import java.io.File;

public class BuiltinCommands {

    public final static  String[]listCommands= {"echo","exit","pwd","type","cd"};

    public static boolean isThisBuiltin(String userArgument )
    {
        for(var command: listCommands) {
            if(userArgument.equals(command)) {
                return true;
            }
        }
        return false;
    }
    public static void runCommands( String command, String userArgument)
    {
        switch (command) {
            case "echo" -> echoFunction(userArgument);
            case "type" -> typeFunction(userArgument);
            case "pwd" -> pwdFunction();
            case "cd" ->cdFunction();
        }
    }
    public static void echoFunction(String userArgument)
    {
        System.out.println(userArgument);
    }

    public static void pwdFunction()
    {
        String path = new File("").getAbsolutePath();
        System.out.println(path);
    }
    public static void typeFunction(String userArgument)
    {
        if (isThisBuiltin(userArgument)) {
            System.out.println(userArgument + " is a shell builtin");
        } else {
            FindPath.filePath(userArgument);
        }
    }
    public static void cdFunction()
    {
        System.out.println("t");
        //placeholder for now, will implement function
    }
}
