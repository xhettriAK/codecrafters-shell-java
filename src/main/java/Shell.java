import java.util.Scanner;

public class Shell {

    public static void run( Scanner input)
    {
        String user;
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

            if(BuiltinCommands.isThisBuiltin(command))
            {
                BuiltinCommands.runCommands(command, userArgument);
            }
            else{
                ExternalCommands.runCommands(command,commandArgument);
            }
        }
    }
}
