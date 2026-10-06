import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Uncomment the code below to pass the first stage

        Scanner input = new Scanner(System.in);
        String user;
        while(true) {
            System.out.print("$ ");
            user = input.nextLine();

            if(user.equals("exit"))
            {
                break;
            }
            String[] parts = user.split(" ",2 );
            String command = parts[0];
            String user_argument = parts.length>1?parts[1]:" ";

            if(command.contains("echo")){
                System.out.println(user_argument);
            }
            else if(command.contains("type")) {
                if (user_argument.contains("type") || user_argument.contains("exit") || user_argument.contains("echo")) {
                    System.out.println(user_argument + " is a shell builtin");
                }

                String pathEnv = System.getenv("PATH");
                String[] paths = pathEnv.split(File.pathSeparator);
                boolean exit = false;
                for (var i : paths) {
                    String temp = i + "/" + user_argument;
                    Path path = Paths.get(temp);
                    if (Files.exists(path)) {
                        if (Files.isExecutable(path)) {
                            exit = true;
                            System.out.println(user_argument+" is "+ temp);
                            break;
                        }
                    }
                }
                if(!exit)
                {
                    System.out.println(user_argument+": not found");
                }
            }
            else{
                System.out.println(user+": command not found");

            }
        }

    }
}

