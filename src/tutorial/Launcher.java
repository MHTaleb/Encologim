/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author polina
 */
public class Launcher {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Courbe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel() {
            private int getFunctionY(int x) {
                // ici on prepare la fonction l idée et de dessiner dans une boucle dans le programme principal
                return (int) ( Math.pow(x, 2) );
            }

            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
                // dessin de la ligne verticale
                grphcs.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
                // dessin de la ligne horizentale
                grphcs.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
                // ici je me positionne au millieu du graphe
                int pointZeroX = getWidth() / 2;
                int pointZeroY = getHeight() / 2;
                System.out.println("middle : "+pointZeroX+","+pointZeroY);
                //ici je cherche le premier point de dessin
                int currentPointDrawX = -11+pointZeroX;
                int currentPointDrawY = -getFunctionY(-11)+pointZeroY;
                
                for (int i = -10; i <= 11; i++) {
                    int X=i+pointZeroX;
                    int Y=-getFunctionY(i)+pointZeroY;
                    System.out.println("\n****\ncurrentPointDrawX ="+currentPointDrawX+" , currentPointDrawY = "+currentPointDrawY+",\n X ="+X+" , Y ="+Y+"\n****");
                    grphcs.drawLine(currentPointDrawX, currentPointDrawY, X, Y);
                    currentPointDrawX=X;
                    currentPointDrawY=Y;
                }

            }

        };
        frame.add(p);
        frame.setVisible(true);
        frame.pack();

    }

}
