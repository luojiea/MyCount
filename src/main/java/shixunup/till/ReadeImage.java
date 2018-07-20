package shixunup.till;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ReadeImage {
    public static Image readImage(String path)
    {
        ImageIcon icon = new ImageIcon(path);
        return icon.getImage();
    }
}
