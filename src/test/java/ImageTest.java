import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @author Qh
 * @version 1.0
 * @date 2021-11-09 22:13
 */
public class ImageTest {

    /**
     * 测试读取硬盘上的文件
     */
    @Test
    public void t1() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream("C:\\Users\\Qh\\Pictures\\Saved Pictures\\Archive\\01.jpg"));
        assertNotNull(bufferedImage);

        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/tankL.gif"));
        assertNotNull(image);
    }
}
