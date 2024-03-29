import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import java.awt.Image;
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

    private Solution s;
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
            view.change();
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
        	//please ignore fake GridButton it is for a very special surprise ;)
            GridButton fake = new GridButton(2,2);
            int play = JOptionPane.showConfirmDialog(null, "Congratulations, you won in " + model.getNumberOfSteps() + " steps! Would you like to play again?", "WAAAHHHH", JOptionPane.YES_NO_OPTION,0,fake.getBaguette());
            if(play == JOptionPane.YES_OPTION) {
            	model.reset();
            	view.change();
            	update();
            }
            else {
            	System.exit(0);
            }
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
        if(e.getStateChange() == ItemEvent.SELECTED) {
            s = sol();
        }
        update();
    }

    public  Solution sol() {
        s = LightsOut.solveShortest(model);
        return s;
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
        int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);

        GameModel m = new GameModel(width,height);

        GameController c = new GameController(width,height);



        //GameView controller;
        //controller = new GameView(m,c);
    }
}
