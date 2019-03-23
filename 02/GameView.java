import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// your other import here if needed

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>GridButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

    // your variables here
    GameModel gameModel;
    GameController gameController;

    GridButton[][] grid;





    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        // YOUR CODE HERE
        super("LightsOut");

        this.gameModel = gameModel;
        this.gameController = gameController;

        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setLayout (new GridLayout(2,2));

        JPanel buttons = new JPanel(new GridLayout(gameModel.getHeight(),gameModel.getWidth())); 

        grid = new GridButton[gameModel.getHeight()][gameModel.getWidth()];

        for(int i = 0; i < gameModel.getHeight(); i++){
            for(int j = 0; j < gameModel.getWidth(); j++){
                GridButton temp = new GridButton(j,i);
                temp.addActionListener(gameController);
                grid[i][j] = temp;
                buttons.add(grid[i][j]);
            }
        }

        JButton reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(10, 10));
        reset.addActionListener(gameController);
        JButton random = new JButton("Random");
        random.setPreferredSize(new Dimension(10, 10));
        random.addActionListener(gameController);
        JButton quit = new JButton("Quit");
        quit.setPreferredSize(new Dimension(10, 10));
        quit.addActionListener(gameController);

        JPanel control = new JPanel(new GridLayout(4,1));

        JCheckBox solve = new JCheckBox("Solution");

        control.add(reset);
        control.add(random);
        control.add(solve);
        control.add(quit);
        control.setMaximumSize(new Dimension(100, 100));

        JLabel steps = new JLabel("Number of steps",SwingConstants.CENTER);
        steps.setSize(100,100);

        this.add(buttons);
        this.add(control);
        this.add(steps);

        setSize(700,500);


        setVisible(true);










    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

        // YOUR CODE HERE

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                GameModel m = gameController.model();

                boolean o = m.isON(i,j);

                grid[i][j].setState(o,false);



                
            }
        }

    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){

        // YOUR CODE HERE

        return true;

    }

    /*public static void main(String[] args) {

        GameModel m = new GameModel(4,4);

        GameController c = new GameController(4,4);



        GameView controller;
        controller = new GameView(m,c);
    }*/

}
