package init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Worldgen {

    //generate noisemap
    public void genWorld() throws IOException, InterruptedException {
        String pythonScriptPath = System.getProperty("user.dir") + "/src/python/worldgen.py";
        Process process = Runtime.getRuntime().exec(new String[]{"py", pythonScriptPath, "world-map/tempworld.txt"});

        // get the input stream of the process
        InputStream inputStream = process.getInputStream();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = inputReader.readLine()) != null) {
            System.out.println(line);
        }

        // wait for the process to finish
        int exitCode = process.waitFor();

        // check the exit code to see if the process completed successfully
        if (exitCode == 0) {
            System.out.println("Python script executed successfully.");
        } else {
            System.out.println("Python script failed with exit code: " + exitCode);
        }
    }


}
