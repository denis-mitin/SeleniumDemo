package auto.Config;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Config {

    // set default value for environment
    public static String ENVIRONMENT = "prod";
    public JSONObject config;

    private static Config instance = null;

    public static Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private Config() {

        // set environment -Denv=dev
        try {
            String env = System.getProperty("env");

           if ( ! env.equals(null)) {
                ENVIRONMENT = env;
               System.out.println("env found at command line, using env =" + ENVIRONMENT);
           }
            else {
               System.out.println("env not found at command line, " +
                       "using default = " + ENVIRONMENT);
           }
        }
        catch (Exception e) {

            //e.printStackTrace();
            System.out.println("env not found at command line, exception thrown, " +
                    "using default = " + ENVIRONMENT);
        }

        try {
            System.out.println("Read config file:");

            String path = "EnvConfig/" + ENVIRONMENT + "/GeneralConfig.json";
            config = new JSONObject(readFile(path));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        try {
            return config.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getUsers() {
        try {
            return config.getJSONObject("users");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readFile(String name) {

        File file = getFileOrResource(name);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder out = new StringBuilder();
        String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                out.append(line).append(System.lineSeparator());
            }

            System.out.println(out.toString()); // Prints the string content
            // read from input stream
            scanner.close();

        return out.toString();
    }

    public static File getFileOrResource(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                return file;
            }
            file = new ClassPathResource(path).getFile();
            if (file.exists()) {
                return file;
            }
            throw new FileNotFoundException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
