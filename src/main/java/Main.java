import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Uncomment the code below to pass the first stage

        Scanner input = new Scanner(System.in);
        String user;
        while(true) {
            System.out.print("$ ");
            user = input.next();
            if(user.equals("exit"))
            {
                break;
            }
            System.out.println(user+ ": command not found");

        }


    }

}
