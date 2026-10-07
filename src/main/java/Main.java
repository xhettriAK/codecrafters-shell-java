import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                    System.out.println(userArgument+" is "+temp);
                    break;
                }
            }
        }
        if(!exit)
        {
            System.out.println(userArgument+": not found");
        }

    }
public static boolean createProcess(String command, String[]  commandArgument)
{
    String pathEnv = System.getenv("PATH");
    String[] paths = pathEnv.split(File.pathSeparator);
    for(var i: paths)
    {
        String temp = i + "/" + command;
        Path path = Paths.get(temp);
        if (Files.exists(path)) {
            if (Files.isExecutable(path)) {
                try {
                    ProcessBuilder pb = new ProcessBuilder(commandArgument);
                    Process p = pb.start();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    p.waitFor();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        }
    }
    return false;
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
            String[] commandArgument = user.split(" " );
            String[] parts = user.split(" ",2);

            String command = parts[0];
            String userArgument = parts.length>1? parts[1]:" ";

            if(command.equals("echo")){
                System.out.println(userArgument);
            }

            else if(command.equals("type")) {
                if (userArgument.equals("type") || userArgument.equals("exit") || userArgument.equals("echo")) {
                    System.out.println(userArgument+ " is a shell builtin");
                }
                else {
                   FileExist(userArgument);
                }
            }
            else{
                if(!createProcess(command,commandArgument)){
                    System.out.println(command+": command not found");
                }
            }
        }
    }
}

