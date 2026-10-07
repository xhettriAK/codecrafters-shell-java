
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FindPath {
    public static String checkExecutable(String filename) {
        String pathEnv = System.getenv("PATH");
        String[] paths = pathEnv.split(File.pathSeparator);
        for (var i : paths) {
            String temp = i + "/" + filename;
            java.nio.file.Path path = Paths.get(temp);
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
            System.out.println(userArgument + " is " + path);
        } else {
            System.out.println(userArgument + ": not found");
        }
    }
}
