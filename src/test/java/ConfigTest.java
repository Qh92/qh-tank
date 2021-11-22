import java.io.IOException;
import java.util.Properties;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-11-22 20:52
 */
public class ConfigTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(ConfigTest.class.getClassLoader().getResourceAsStream("config/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String initTankCount = (String) properties.get("initTankCount");
        System.out.println(initTankCount);
    }
}
