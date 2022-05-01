package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Util {

    private static final int LEFT_BORDER = 3000;
    private static final int RIGHT_BORDER = 8000;
    private static final Random RAND = new Random();

    //private constructor.
    private Util() {
    }

    //Method time() generates random time of talking operator with client
    public static int time() {
        int time;
        time = LEFT_BORDER + RAND.nextInt(RIGHT_BORDER);
        return time;
    }

    /*Method getProperty() uses to get information about Object.
     *All information is contained in property files in directory "resources"
     *Parameters of this method are path to property file, key and position of value.
     *All values are separated by a ", ".
     *Method contain values to array and returns some element of this array
     */
    public static String getProperty(String path, String key, int position) throws IOException {

        try (FileInputStream fis = new FileInputStream(path)) {
            Properties property = new Properties();
            property.load(fis);
            String[] values = property.get(key).toString().split(", ");
            return values[position];
        }

    }


}
