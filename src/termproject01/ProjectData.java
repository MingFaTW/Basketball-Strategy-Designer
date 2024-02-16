package termproject01;
import java.awt.Color;
import java.io.Serializable;

public class ProjectData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String[] aNumber = {"1","2","3","4","5"};
	private String[] bNumber = {"1","2","3","4","5"};
    private int[] X_aPlayer = {1150,1210,1270,1330,1390};
    private int[] Y_aPlayer = {10,10,10,10,10};
    private int[] X_bPlayer = {50,110,170,230,290};
    private int[] Y_bPlayer = {10,10,10,10,10};
    private int X_ball = 740;
    private int Y_ball = 407;
    private Status status = Status.NULL;
    private OBJType obj2create = OBJType.NULL;
    private BasketballPanel basketballPanel;
    private Color color;
    private int font=18;
    public ProjectData(mainWindow mainWin) {
        // Initialize the player positions based on the mainWindow
        //X_aPlayer = mainWin.getX_aPlayer();
        //Y_aPlayer = mainWin.getY_aPlayer();
        //X_bPlayer = mainWin.getX_bPlayer();
        //Y_bPlayer = mainWin.getY_bPlayer();
    }
    // get team a position
    public int getFont()
    {
    	return font;
    }
    public void setFont(int font)
    {
    	this.font=font;
    }
    
    public int[] getX_aPlayer() {
        return X_aPlayer;
    }

    public int getX_aPlayer(int i) {
        return X_aPlayer[i];
    }
    
    public void setX_aPlayer(int[] x_aPlayer) {
        this.X_aPlayer = x_aPlayer;
    }
    
    public void setX_aPlayer(int location,int x) {
    	this.X_aPlayer[location] = x;
    }

    public int[] getY_aPlayer() {
        return Y_aPlayer;
    }
    
    public int getY_aPlayer(int i) {
        return Y_aPlayer[i];
    }

    public void setY_aPlayer(int[] y_aPlayer) {
        this.Y_aPlayer = y_aPlayer;
    }

    public void setY_aPlayer(int location,int y) {
    	this.Y_aPlayer[location] = Y_aPlayer[location];
    }
    // get team b position
    public int[] getX_bPlayer() {
        return X_bPlayer;
    }
    
    public int getX_bPlayer(int i) {
        return X_bPlayer[i];
    }

    public void setX_bPlayer(int[] x_bPlayer) {
        this.X_bPlayer = x_bPlayer;
    }
    public void setX_bPlayer(int location, int x) {
    	System.out.println(X_bPlayer[location]);
    	this.X_bPlayer[location] = x;
    }

    public int[] getY_bPlayer() {
        return Y_bPlayer;
    }
    
    public int getY_bPlayer(int i) {
        return Y_bPlayer[i];
    }

    public void setY_bPlayer(int[] y_bPlayer) {
        this.Y_bPlayer = y_bPlayer;
    }
    public void setY_bPlayer(int location,int y) {
    	this.Y_bPlayer[location] = y;
    }
    
    // get ball position
    public void setX_ball(int x) {
    	this.X_ball = x;
    }
    
    public int getX_ball() {
    	return X_ball;
    }
    
    public void setY_ball(int y) {
    	this.Y_ball = y;
    }
    
    public int getY_ball() {
    	return Y_ball;
    }
    // set and get each team's player number
    public void setPlayerNum(char team,int playerNum,String Pnumber) {
    	if(team == 'A') {
    		this.aNumber[playerNum] = Pnumber;
    	}
    	else if(team == 'B'){
    		this.bNumber[playerNum] = Pnumber;
    	}
    }
    
    public String[] getPlayerNum(char team) {
    	if(team == 'A') {
    		return this.aNumber;
    	}
    	else if(team == 'B') {
    		return this.bNumber;
    	}
    	return null;
    }
    
    public String getPlayerNum(char team,int location) {
    	if(team == 'A') {
    		return this.aNumber[location];
    	}
    	else if(team == 'B'){
    		return this.bNumber[location];
    	}
		return null;
    }
    
    public Status getStatus() {
    	return ProjectData.this.status;
    }
    
    public void setStatus(Status status) {
    	this.status = status;
    }
    public OBJType getOBJType()
    {
    	return ProjectData.this.obj2create;
    }
    public void setOBJType(OBJType obj2create)
    {
    	this.obj2create=obj2create;
    }
    
    public Status getStatus(BasketballPanel basketballPanel) {
    	System.out.println("the get Status has been called! Status: "+ProjectData.this.status);
    	return ProjectData.this.status;
    }
    
    public Color setColor(Color color) {
    	return this.color = color;
    }
    
    public Color getColor(BasketballPanel basketballPanel) {
    	//System.out.println("The get Color has been called! Color: "+this.color);
    	return ProjectData.this.color;
    }
    
    public Color getColor() {
    	return ProjectData.this.color;
    }
	public void setX_shape(int x) {
		// TODO Auto-generated method stub
		
	}
	public void setY_shape(int y) {
		// TODO Auto-generated method stub
		
	}
    
}



