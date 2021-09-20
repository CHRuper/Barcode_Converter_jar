import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String buttonName, int width, int height) {
        super(buttonName);
        setPreferredSize(new Dimension(width, height));
    }
}
