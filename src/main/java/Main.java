import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Uncomment the code below to pass the first stage

        Scanner input = new Scanner(System.in);
        String[] built_in_commands = {"type","echo","exit"};
        String user;
        while(true) {
            System.out.print("$ ");
            user = input.nextLine();

            if(user.equals("exit"))
            {
                break;
            }
            if(user.startsWith("echo"))
            {
                System.out.println(user.substring(5));
            }
            else if(user.startsWith(built_in_commands[0]))
            {
                String temp = user.substring(5);
                boolean check = false;
                for(var i : built_in_commands)
                {
                    if(temp.equals(i))
                    {
                        System.out.println(temp + " is a shell built in command");
                        check = true;
                        break;
                    }
                }
                if(!check)
                {
                    System.out.println(user+": command not found");
                }
            }
            else{
                System.out.println(user+": command not found");
            }



        }


    }

}
