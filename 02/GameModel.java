import java.util.*;


public class GameModel{
	private int width;
	private int height;
	private int clicked;

	private boolean [][] game;

	private Solution s;

	public GameModel(int width, int height){
		this.width = width;
		this.height = height;
		game  = new boolean[height][width];
		clicked = 0;
		s = null;
	}

	public void click(int i, int j){

		clicked++;
		game[i][j] = !game[i][j];

		if(i-1 < 0){
			game[i-1][j] = !game[i-1][j];
		}
		if(j-1<0){
			game[i][j-1] = !game[i][j-1];
		}

		if(i+1 < game.length){
			game[i+1][j] = !game[i+1][j];
		}

		if(j+1 < game[0].length){
			game[i][j+1] = !game[i][j+1];
		}

	}

	public int getNumberOfSteps(){
		return clicked;
	}

	public boolean isFinished(){
		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[0].length; j++){
				if(game[i][j] == false){
					return false;
				}
			}
		}
		return true;
	}

	public void randomize(){

		for(int i = 0; i < game.length; i++){
			for(int j = 0; j < game[0].length; j++){
				int random = (int)(Math.random() * ((2-1)+1)+1);

				if(random == 1){
					game[i][j] = true;
				}else{
					game[i][j] = false;
				}

			}
		}

		 ArrayList<Solution> y = LightsOut.solve(this);

		 if(y.size() == 0){
		 	randomize();
		 }
	}

	public void setSolution(){

		s = LightsOut.solveShortest(this);

	}

	public boolean solutionSelects(int i, int j){
		if(s != null){
			if(s.get(i,j)){
				return true;
			}
		}
		return false;
	}


	public int getHeight(){
		return height;
	}

	public int getWidth(){
		return width;
	}

	public boolean isON(int i, int j){

		if(game[i][j] == true){
			return true;
		}

		return false;

	}

	public void reset(){
		clicked = 0;
		for(int i  = 0; i < game.length; i++){
			for(int j = 0; j < game[0].length; j++){
				game[i][j] = false;
			}
		}
	}

	public void set(int i, int j, boolean value){

		//so i is the column and j is the row because why not!
		game[j][i] = value;
	}

	public String toString(){

		String aString = "[";
  		for(int i = 0; i < this.game[0].length; i++) {
  			aString +="[";
			for(int j = 0; j < this.game.length; j++ ) {
        		if(j < this.game.length - 1) {
        			aString += this.game[j][i] + ", ";
        		}
        		else {
        			aString += this.game[j][i];
        		}
    		}
    		if(i < this.game[0].length -1) {
    			aString += "],";
    			aString += "\r\n";
    		}
    		else {
    			aString += "]";
    		}
  		}
  		aString += "]";
  		return aString;



	}
}