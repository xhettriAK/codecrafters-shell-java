import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void FileExist(String userArgument) {
        String pathEnv = System.getenv("PATH");
        String[] paths = pathEnv.split(File.pathSeparator);
        boolean exit = false;

        for (var i : paths) {
            String temp = i + "/" + userArgument;
            Path path = Paths.get(temp);
            if (Files.exists(path)) {
                if (Files.isExecutable(path)) {
                    exit = true;
                    System.out.println(userArgument + " is " + temp);
                    break;
                }
            }
        }

        if (!exit) {
            System.out.println(userArgument + ": not found");
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        String user;
        while(true) {
            System.out.print("$ ");
            user = input.nextLine();

            if(user.equals("exit")) {
                break;
            }
            String[] parts = user.split(" ",2 );
            String command = parts[0];
            String userArgument = parts.length>1?parts[1]:" ";

            if(command.equals("echo")){
                System.out.println(userArgument);
            }

            else if(command.equals("type")) {
                if (userArgument.equals("type") || userArgument.equals("exit") || userArgument.equals("echo")) {
                    System.out.println(userArgument + " is a shell builtin");
                }
                else {
                   FileExist(userArgument);
                }
            }
            else{
                System.out.println(user+": command not found");
            }
        }
    }
}

