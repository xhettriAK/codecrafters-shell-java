import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class shell {

    public static String checkExecutable(String filename) {
        String pathEnv = System.getenv("PATH");
        String[] paths = pathEnv.split(File.pathSeparator);
        for (var i : paths) {
            String temp = i + "/" + filename;
            Path path = Paths.get(temp);
            if (Files.exists(path)) {
                if (Files.isExecutable(path)) {
                    return temp;

                }
            }
        }
        return "";
    }

    public static void filePath(String userArgument) {
        String path = checkExecutable(userArgument);

        if (!path.isEmpty()) {
            System.out.println(userArgument + " is "+ path);
        }
        else{
            System.out.println(userArgument+": not found");
        }


    }

    public static boolean createProcess(String command, String[] commandArgument) {

        if (!checkExecutable(command).isEmpty()) {
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
            return true;
        }
        return false;
    }

    public static boolean equal(String userArgument,String...builtInCommands )
    {
        for(var command: builtInCommands) {
            if(userArgument.equals(command)) {
                return true;
            }
        }
        return false;

    }
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        String user;
        String[] builtInCommands = {"echo","exit","pwd","type"};
        while (true) {
            System.out.print("$ ");
            user = input.nextLine();

            if (user.equals("exit")) {
                break;
            }
            String[] commandArgument = user.split(" ");
            String[] parts = user.split(" ", 2);

            String command = parts[0];
            String userArgument = parts.length > 1 ? parts[1] : " ";

            if (command.equals("echo")) {
                System.out.println(userArgument);
            }
            else if (command.equals("type")) {
                if(equal(userArgument,builtInCommands))
                {
                    System.out.println(userArgument+" is a shell builtin");
                }
                else {
                    filePath(userArgument);
                }
            }
            else {
                if (!createProcess(command, commandArgument)) {
                    System.out.println(command + ": command not found");
                }
            }
        }
    }
}

