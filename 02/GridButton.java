// YOUR IMPORT HERE
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.*;
import java.awt.Image;

public class GridButton extends JButton {


    int column;
    int row;

    ImageIcon img;


    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     * 
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row) {

        img = new ImageIcon("Icons/Light-1.png");

        setIcon(img);

        this.column = column;
        this.row = row;
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {

        // YOUR CODE HERE
        if(isOn){
            img = new ImageIcon("Icons/Light-0.png");     

        }

        if(isOn && isClicked){
            img = new ImageIcon("Icons/Light-2.png");
        }

        if(!isOn && !isClicked){
            img = new ImageIcon("Images/Light-1.png");
        }

        if(!isOn && isClicked){
            img = new ImageIcon("Image/Light-3.png");
        }
        

        setIcon(img);

    }

 

    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
        // YOUR CODE HERE

        return this.row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
        // YOUR CODE HERE

        return this.column;
    }

    // YOUR OTHER METHODS HERE

    
}
