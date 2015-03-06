/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gamplayCore;

import com.jme3.system.AppSettings;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import javax.imageio.ImageIO;

/**
 *  Initializes the Appsettings
 * @author Aismael
 */

public class configInitializer {
    AppSettings settings;
    String title="Dream1";
    public configInitializer(){
        settings= new AppSettings(true);
        settings.setIcons(this.getIcons("Assets/Interface/Icons/","icon","png"));
        try {
            settings.load("settings.pref");
        } catch (BackingStoreException ex) {
            Logger.getLogger(configInitializer.class.getName()).log(Level.SEVERE, null, ex);
            this.settingInitiale();
        }
        if(!settings.getTitle().equals(title)){
            this.settingInitiale();
        }
            
    }
    /**
     * Loads the Appicons for 256,128,64,32 and 16 Pixel
     * @param path path of the Icons
     * @param iconname name of the icons before Pixel String 256,128,64,32 and 16
     * @param picturekind kind of the picture jpg,png etc.
     * @return 
     */
    private BufferedImage[] getIcons(String path,String iconname,String picturekind){
        BufferedImage[] BufferedIconImageArrey=new BufferedImage[5];
        for(int i=5;i>0;i--){
            Double j=Math.pow(2,(i+3));
            Integer k=j.intValue();    
            try {
                BufferedIconImageArrey[5-i]=ImageIO.read(new File(path+iconname+k.toString()+"."+picturekind));
            } catch (IOException ex) {
                Logger.getLogger(configInitializer.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(path+iconname+k.toString()+"."+picturekind);
            }
        }
        return BufferedIconImageArrey;
    }
    /**
     * 
     * @return the Actuall Apsetting 
     */
    public AppSettings getSettings(){
        return settings;
    }
    /**
     * initialize the settings from Monitor possibilities
     */
    private void settingInitiale(){
            settings.setSettingsDialogImage("Interface/Title.png");
            settings.setTitle(title);
            GraphicsDevice device = GraphicsEnvironment.
            getLocalGraphicsEnvironment().getDefaultScreenDevice();
            DisplayMode[] modes = device.getDisplayModes();
            settings.setResolution(modes[modes.length-1].getWidth(),modes[modes.length-1].getHeight());
            settings.setFrequency(modes[modes.length-1].getRefreshRate());
            settings.setBitsPerPixel(32);
            settings.setFullscreen(device.isFullScreenSupported());
            settings.setRenderer(AppSettings.LWJGL_OPENGL3);
    }
}
