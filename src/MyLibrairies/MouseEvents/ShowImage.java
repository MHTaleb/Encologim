/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLibrairies.MouseEvents;
   
import FrameDecorator.MyDecorator;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



/**
 *
 * @author polina
 */
public class ShowImage {

   
 private MyPanel contentPane;
    public void ShowFullImage(String imagePath){
        JFrame frame = new JFrame("Image Full Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        contentPane = new MyPanel(imagePath);
        JScrollPane jsp = new JScrollPane(contentPane);
        
        frame.setContentPane(jsp);
        frame.pack();
        frame.setLocationByPlatform(true);
        MyDecorator.Decorate(frame, (JFrame) MyDecorator.getLastAddedFrame());
        
    }
    
    
 
/**
 * Created with IntelliJ IDEA.
 * User: Gagandeep Bali
 * Date: 7/1/14
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
}
