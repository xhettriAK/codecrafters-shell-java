import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Uncomment the code below to pass the first stage

        Scanner input = new Scanner(System.in);
        String user;
        while(true) {
            System.out.print("$ ");
            user = input.nextLine();
            String[] split =  user.split(" ");

            if(split[0].equals("exit"))
            {
                break;
            }
            if(split[0].equals("echo")){
                for(int i = 1; i<split.length; i++)
                {

                    System.out.print(split[i]);
                    System.out.print(" ");
                }

            }
            else
            {
                System.out.println(user+ ": command not found");
            }
            System.out.println();


        }


    }

}
