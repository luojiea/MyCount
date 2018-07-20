package shixunup.io;

import shixunup.swing.ConfigJFrame;
import shixunup.swing.LoginFrame;
import shixunup.till.ApplicationConfig;

public class Run {
    public static void main(String...args){
        if (ApplicationConfig.isFirstRun()){
            new ConfigJFrame();
        } else {
            new LoginFrame();
        }
    }
}
