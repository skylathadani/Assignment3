public class GameModel{
	private int width;
	private int height;

	private boolean [][] game;

	public GameModel(int width, int height){
		this.width = width;
		this.height = height;
		game  = new boolean[height][width];
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