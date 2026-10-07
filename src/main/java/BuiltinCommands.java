import java.io.File;

public class BuiltinCommands {

    public final static  String[]listCommands= {"echo","exit","pwd","type","cd"};
    private static String path= new File("").getAbsolutePath();
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
            case "cd" ->cdFunction(userArgument);
        }
    }
    public static void echoFunction(String userArgument)
    {
        System.out.println(userArgument);
    }

    public static void pwdFunction()
    {
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
    public static void cdFunction(String userArgument)
    {
        File thiss = new File(userArgument);
        if(!thiss.isDirectory())
        {
            System.out.println("cd: " +userArgument +": No such file or directory" );
        }
        else {
           path= new File(userArgument).getAbsolutePath();
        }

    }
}
