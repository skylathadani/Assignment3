import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
// YOUR OTHER IMPORTS HERE IF NEEDED

/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, ItemListener {

    // YOUR VARIABLES HERE

    private GameModel model;

    private GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {

        // YOUR CODE HERE

        model = new GameModel(width,height);
        view = new GameView(model,this);
    }


    /**
     * Callback used when the user clicks a button (reset, 
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
        // YOUR CODE HERE
        if(e.getActionCommand().equals("Reset")) {
            model.reset();
            update();
        }
        else if(e.getActionCommand().equals("Random")) {
            model.randomize();
            update();
        }
        else if(e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
        else {
            //System.err.println("Unknown action: " + e.getActionCommand());
            //System.exit(0);

            Object source = e.getSource();
            if (source instanceof GridButton) {
                GridButton t = (GridButton) source;
                model.click(t.getRow(),t.getColumn());
                update();
                //t.setState(model.isON(t.getRow(),t.getColumn()),false);
                
            }
            
            //model.isON(e.getSource().getHeight(),e.getSource().getWidth());



           //e.getSource(e.getSource().getHeight(),e.getSource().getWidth())
        }
        if(model.isFinished()) {
            JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION);
        }

    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void  itemStateChanged(ItemEvent e){

        // YOU CODE HERE
    }

    public boolean click(int i, int j){

        return model.isON(i,j);

    }
    public GameModel model (){
        return model;
    }

    // YOUR OTHER METHODS HERE

    public void update() {
        view.update();
    }

    public static void main(String[] args) {
        GameModel m = new GameModel(4,4);

        GameController c = new GameController(4,4);



        //GameView controller;
        //controller = new GameView(m,c);
    }
}
