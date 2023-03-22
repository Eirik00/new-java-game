package init;

import object.tile.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Worldgen {

    private int worldGenProgress = 0;

    //generate noise-map
    public void genWorld(String worldName, Consumer<Integer> worldGenProgress) throws IOException, InterruptedException {
        String pythonScriptPath = System.getProperty("user.dir") + "/src/python/worldgen.py";
        Process process = Runtime.getRuntime().exec(new String[]{"py", pythonScriptPath, "world-map/" + worldName + ".txt"});

        // get the input stream of the process
        InputStream inputStream = process.getInputStream();
        Scanner inputScanner = new Scanner(inputStream);

        // read outputs from the Python script
        String line;
        while (inputScanner.hasNextLine() && !(line = inputScanner.nextLine()).equals("EOF")) {
            worldGenProgress.accept(Integer.parseInt(line));
        }

        // wait for the process to finish
        int exitCode = process.waitFor();

        // close the inputScanner
        inputScanner.close();

        // check the exit code to see if the process completed successfully
        if (exitCode == 0) {
            System.out.println("Python script executed successfully.");
        } else {
            System.out.println("Python script failed with exit code: " + exitCode);
        }
    }

    public ArrayList<ArrayList<Tile>> getWorldLayout(String worldName) {
        try {
            Tilegen tilegen = new Tilegen();
            ArrayList<ArrayList<Tile>> outputWorldTile = new ArrayList<>();
            String worldLayoutPath = System.getProperty("user.dir") + "/world-map/" + worldName + ".txt";
            File readFile = new File(worldLayoutPath);
            Scanner reader = new Scanner(readFile);

            while(reader.hasNextLine()){
                String data = reader.nextLine();
               //System.out.println(data);
                ArrayList<Tile> linedoutputWorldTile = new ArrayList<>();
                for(String character : data.split("")){
                    switch (character) {
                        case "A" -> linedoutputWorldTile.add(tilegen.genForrest());
                        case "#" -> linedoutputWorldTile.add(tilegen.genMountain());
                        case "-" -> linedoutputWorldTile.add(tilegen.genGrass());
                        case "r" -> linedoutputWorldTile.add(tilegen.genRiver());
                    }
                }
                //System.out.println(linedoutputWorldTile);
                outputWorldTile.add(linedoutputWorldTile);
            }
            return outputWorldTile;
        } catch(FileNotFoundException e){
            System.out.println("Couldn't find that world name");
            e.printStackTrace();
            return null;
        }
    }
}
