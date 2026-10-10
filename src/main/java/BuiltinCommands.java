import java.io.IOException;
import java.nio.file.Path;

public class BuiltinCommands {

    public final static  String[]listCommands= {"echo","exit","pwd","type","cd"};
    
    private static String path= Path.of("").toAbsolutePath().toString();
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
        StringBuilder userArgumentBuilder = new StringBuilder();
        char  quoteMatch = ' ';
        boolean spaceReserved = false;
        int spaceReservedLoop = 0;
        int firstQuote = 0;
        for(int i = 0; i<userArgument.length(); i++)
        {
            char c = userArgument.charAt(i);
            if(c== '\'')
            {
                if(firstQuote == 0)
                {
                    quoteMatch = c;
                    spaceReserved = true;
                    firstQuote++;
                }
                else {
                    spaceReserved = false;
                    firstQuote = 0;
                    quoteMatch=' ';
                }
            }
            else if (c == ' ' && spaceReserved)
            {
                userArgumentBuilder.append(c);
            }
            else if(c == ' ')
            {
                //space resevered for only one
                if(spaceReservedLoop==0) {
                    userArgumentBuilder.append(c);
                    spaceReservedLoop++;
                }

            }
            else{

                userArgumentBuilder.append(c);
                spaceReservedLoop=0;

            }

        }
        userArgument= userArgumentBuilder.toString();
        System.out.println(userArgument);
//        if(insideQuote)
//        {
//            StringBuilder userArgumentBuilder2 = new StringBuilder(userArgumentBuilder.toString());
//            Scanner n = new Scanner(System.in);
//            String temp = "";
//            while (!temp.endsWith(String.valueOf(checker))) {
//                temp = n.nextLine();
//                userArgumentBuilder.append("\n").append(temp);
//
//            }
//
//        }
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
        Path tempPath;
        String username = System.getenv("USER");

        if(userArgument.equals("~"+username)) {
            tempPath = Path.of(System.getenv("HOME"));
        }
        else if(userArgument.startsWith("~")){
            tempPath = Path.of( System.getenv("HOME")+userArgument.substring(1));
        }
        else{
            tempPath    = Path.of(userArgument);
        }
        Path absolutePath = Path.of(path).resolve(tempPath);
        boolean isExist = false;

        try{
            absolutePath = absolutePath.toRealPath();
            isExist = true;
        } catch (IOException e) {
            System.out.println("cd: " +userArgument +": No such file or directory");
        }
        if(isExist){
            path = absolutePath.toString();
        }
    }
}
