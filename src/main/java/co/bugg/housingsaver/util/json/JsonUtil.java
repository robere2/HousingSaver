package co.bugg.housingsaver.util.json;

import co.bugg.housingsaver.util.MessageBuilder;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    private static final Gson gson = new Gson();
    private static final String path = "housingsaver/";


    /**
     * Reads a player's file to get their last save coordinates
     * @param uuid Minecraft user ID, the file's name
     * @return Coordinate class containing the player's last save coordinates
     */
    public static Coordinates read(String uuid) {
        // Parse the Json file into a string
        String json = null;
        try {
            json = Files.toString(new File(path + uuid + ".json"), Charsets.UTF_8);
            System.out.println("Saving user " + uuid + "'s coordinates");
        } catch (IOException e) {
            String err = "Failed saving coordinates for " + uuid;
            MessageBuilder.send(MessageBuilder.buildError(err));
            System.out.println(err);

            e.printStackTrace();
        }

        // Convert the Json into an object
        return gson.fromJson(json, Coordinates.class);
    }

    /**
     * Write user save data to a file, and creates the file if it doesn't exist already.
     * @param uuid Minecraft user ID to name the file
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public static void write(String uuid, double x, double y, double z) {
        File file = new File(path + uuid + ".json");

        // Create the file if it doesn't exist already
        if(!file.exists() && !file.isDirectory()) {
            System.out.println("Created new file for " + uuid);
            try {
                if(file.createNewFile()) {
                    System.out.println("Created new file for user " + uuid);
                } else {
                    System.out.println("Didn't create file for user " + uuid + ": File exists");
                }
            } catch (IOException e) {
                String err = "Unable to create file for " + uuid;
                MessageBuilder.send(MessageBuilder.buildError(err));
                System.out.println(err);

                e.printStackTrace();
            }
        }

        // Insert the coordinates into a Coordinates object, then convert it to Json
        Coordinates coords = new Coordinates(x, y, z);
        String json = gson.toJson(coords);

        // Write to the file
        try {
            Files.write(json.getBytes(), file);
            System.out.println("Updated save for " + uuid);
            // Not including the UUID, because it should be clear who the save is for. If really necessary, logs can be checked.
            MessageBuilder.send(MessageBuilder.buildSuccess("Save complete."));
        } catch (IOException e) {
            String err = "Unable to update save for " + uuid;
            MessageBuilder.send(MessageBuilder.buildError(err));
            System.out.println(err);
            e.printStackTrace();
        }
    }

    /**
     * Creates the housing saver directory if it doesn't exist already. Runs at preInit
     */
    public static void createDir() {
        File dir = new File(path);

        if(!dir.isDirectory()) {
            boolean success = dir.mkdir();

            if(success) {
                System.out.println("Created Housing Saver directory.");
            } else {
                System.out.println("------------------------------------------------------------");
                System.out.println("UNABLE TO CREATE HOUSING SAVER DIRECTORY! MOD WILL NOT WORK.");
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}
