import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuiltinCommands {

    public final static  String[]listCommands= {"echo","exit","pwd","type","cd"};
    private static Path p = Path.of("");
    private static String path= p.toAbsolutePath().toString();
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
        Path tempPath = Paths.get(userArgument);
        Path absolutePath = p.resolve(tempPath);
        boolean isDirectory = false;
        try{
            absolutePath = absolutePath.toRealPath();
            isDirectory = true;
        } catch (IOException e) {
            System.out.println("cd: " +userArgument +": No such file or directory");
        }
        if(isDirectory){
           p = absolutePath;
           path = p.toString();
        }
    }
}
