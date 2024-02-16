package termproject01;

import javax.swing.JPanel;
import java.io.Serializable;
import java.io.Serializable;
//此類用於多分頁創建，並使用ArrayList去儲存不同分頁並實例化它
class TabData implements Serializable{
	private static final long serialVersionUID = 1L;
    private String title;
    private int numTabs;  //哪一張分頁
    private JPanel component;  // Store the associated JPanel or component
    private String[] aNumber = {"1","2","3","4","5"};
	private String[] bNumber = {"1","2","3","4","5"};
    private int[] X_aPlayer = {1150,1210,1270,1330,1390};
    private int[] Y_aPlayer = {10,10,10,10,10};
    private int[] X_bPlayer = {50,110,170,230,290};
    private int[] Y_bPlayer = {10,10,10,10,10};
    private int X_ball = 740;
    private int Y_ball = 407;
    

    
    public TabData(String title, int numTabs, BasketballPanel panel) {
        this.title = title;
        this.component = panel;
        this.numTabs = numTabs;
    }

    public String getTitle() {
        return title;
    }

    public JPanel getComponent() {
        return component;
    }
    
    public int getNumTabs() {
    	return numTabs;
    }
    
    
    
    // get team a position
    public int[] getX_aPlayer() {
        return X_aPlayer;
    }

    public int getX_aPlayer(int i) {
        return X_aPlayer[i];
    }
    
    public void setX_aPlayer(int[] x_aPlayer) {
        this.X_aPlayer = x_aPlayer;
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

    public int[] getY_bPlayer() {
        return Y_bPlayer;
    }
    
    public int getY_bPlayer(int i) {
        return Y_bPlayer[i];
    }

    public void setY_bPlayer(int[] y_bPlayer) {
        this.Y_bPlayer = y_bPlayer;
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
    
    public String[] getPlayerNum(char team) {
    	if(team == 'A') {
    		return this.aNumber;
    	}
    	else if(team == 'b') {
    		return this.bNumber;
    	}
    	return null;
    }
    
    public void setPlayerNum(char team,int location,String number) {
    	if(team == 'a') {
    		this.aNumber[location] = number;
    	}
    	else if(team == 'b'){
    		this.bNumber[location] = number;
    	}
    }
    
    public String getPlayerNum(char team,int location) {
    	if(team == 'a') {
    		return this.aNumber[location];
    	}
    	else if(team == 'b'){
    		return this.bNumber[location];
    	}
		return null;
    }
    
    public ProjectData getProjectData() {
        return this.getProjectData();
    }

}

