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
            if(user.startsWith("echo"))
            {
                System.out.println(user.substring(5));
            }
            else{
                System.out.println(user+": command not found");
            }



        }


    }

}
