package shixunup.till;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GetScreenCenterLocation {
    public static int[] getLocation(int width,int heigth){
        //定义工具包
        Toolkit kit = Toolkit.getDefaultToolkit();
        //获取屏幕的尺寸
        Dimension screenSize = kit.getScreenSize();
        //获取屏幕的宽
        int screenWidth = screenSize.width;
        //获取屏幕的高
        int screenHeight = screenSize.height;
        int[] values ={screenWidth/2 - width/2, screenHeight/2 - heigth/2};
        return values;
    }
}
