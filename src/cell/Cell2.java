package cell;

import javax.swing.Icon;
import javax.swing.JButton;

public class Cell2 extends JButton{

	private static final long serialVersionUID = 1L;
	public Cell2(Icon icon){//实际为ICON
    	super(icon);
	    this.setSize(100,100);
	}
	public void move(String direction,int sleep){//方格的移动
        if(direction=="UP"){
            this.setLocation(this.getBounds().x,this.getBounds().y-100);
        }
        else if(direction=="DOWN"){
            this.setLocation(this.getBounds().x,this.getBounds().y+100);
        }
        else if(direction=="LEFT"){
            this.setLocation(this.getBounds().x-100,this.getBounds().y);
        }
        else{
            this.setLocation(this.getBounds().x+100,this.getBounds().y);
	    }
	}
	public void moveto(int x,int y){//方格跳动
        this.setLocation(x,y);        
	}
}
