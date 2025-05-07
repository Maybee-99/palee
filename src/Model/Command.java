
package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {
     public static void runCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuilder errorMsg = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                errorMsg.append(line).append("\n");
            }
            throw new RuntimeException("Command failed: " + command + "\n" + errorMsg.toString());
        }
    }
}
