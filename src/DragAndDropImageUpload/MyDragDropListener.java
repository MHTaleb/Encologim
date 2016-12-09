/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DragAndDropImageUpload;

import MyLibrairies.MouseEvents.ShowImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MyDragDropListener implements DropTargetListener {
private JPanel Scrollable;

    public void setScrollable(JPanel Scrollable) {
        this.Scrollable = Scrollable;
    }

    private final void AddMignature( final String Path){
            int width = 120,height=145;
            Dimension d = new Dimension(width, height);
            
                JLabel mignatures ;
            if ((Path.toLowerCase().contains(".bmp"))
                        || (Path.toLowerCase().contains(".jpg"))
                        || (Path.toLowerCase().contains(".png"))
                        ) {

                    ImageIcon imageIcon = new ImageIcon(Path);
                    Image image = imageIcon.getImage(); // transform it 
                    Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    imageIcon = new ImageIcon(newimg);
                    mignatures = new JLabel(imageIcon);
                    mignatures.setPreferredSize(d);
                    mignatures.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent me) {
                             System.out.println("click");
                            if (me.getButton()==1) {
                                new ShowImage().ShowFullImage(Path);
                            }
                            else{
                                System.out.println("button"+me.getButton());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent me) {
                            
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {
                           
                        }

                        @Override
                        public void mouseEntered(MouseEvent me) {
                            
                        }

                        @Override
                        public void mouseExited(MouseEvent me) {
                          
                        }
                    });
                    Scrollable.add(mignatures);
                } else {
                String[] fileNames = Path.split("\\");
                String fileName = fileNames[fileNames.length-1];
                    JOptionPane.showMessageDialog(Scrollable.getParent(), "Fichier Invalide", "Le fichier : "
                            + fileName + " est invalide\nVeuillez selectionner une image!", JOptionPane.ERROR_MESSAGE);
                }
                Scrollable.validate();
                Scrollable.revalidate();
                Scrollable.repaint();
 
    }
    
    @Override
    public void drop(DropTargetDropEvent event) {

        // Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = event.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                    List files = (List) transferable.getTransferData(flavor);

                    // Loop them through
                    Object[] myFiles = files.toArray();
                    
                    for (Object myFile : myFiles) {
                        File file = (File) myFile;
                        System.out.println("cur file : "+file.getName());
                        
                        
                        AddMignature(file.getPath());
                    }
                    
                }

            } catch (Exception e) {

                // Print out the error stack
                e.printStackTrace();

            }
        }

        // Inform that the drop is complete
        event.dropComplete(true);

    }

    @Override
    public void dragEnter(DropTargetDragEvent event) {
    }

    @Override
    public void dragExit(DropTargetEvent event) {
    }

    @Override
    public void dragOver(DropTargetDragEvent event) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event) {
    }

}