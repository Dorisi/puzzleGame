package canvas;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cell.Cell2;



public class MyCanvas2 extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
    boolean hasAddActionListener=false;//设置方格的动作监听器的标志位，TRUE为已经添加上动作事件，FALSE是尚未添加动作事件
    Cell2 cell[];//定义方格
    Rectangle cellNull;//定义空方格区域
    public static int pictureID=2;//当前选择的图片代号
    ImageIcon imagels;
    public MyCanvas2() {
        this.setLayout(null);
        this.setSize(400,400);
        cellNull=new Rectangle(300,300,100,100);//空方格区域在第三行每三列
        cell=new Cell2[16];
        //Icon icon;
        for (int i = 0; i < 4; i++) {//为9个方格加载图片，并初使化坐标，形成三行三列
            for(int j=0;j<4;j++){
            	imagels=new ImageIcon("images//pt"+MyCanvas2.pictureID+"_"+(i*4+j+1)+".gif");  //实例化图标
                imagels.setImage(imagels.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));                
                cell[i*4+j]=new Cell2(imagels);
                cell[i*4+j].setLocation(j*100,i*100);
                this.add(cell[i*4+j]);
            }
        }
        this.remove(cell[15]);//移除最后一个多余的方格
    }
    public void reLoadPictrue(){//当选择其它图形进行拼图时，需重新加载新图片
	    for (int i = 0; i < 4; i++) {
	        for(int j=0;j<4;j++){
	        	imagels=new ImageIcon("images//pt"+MyCanvas2.pictureID+"_"+(i*4+j+1)+".gif");  //实例化图标
                imagels.setImage(imagels.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));   
	            cell[i*4+j].setIcon(imagels);
	        }
	    }
	}
	public boolean isFinish(){//判断是否拼合成功
        for(int i=0;i<15;i++){
            int x=cell[i].getBounds().x;
            int y=cell[i].getBounds().y;
            if(y/100*4+x/100!=i)
                return false;
        }
	    return true;
	}
	public void Start(){//对方格进行重新排列，打乱顺序
        while(cell[0].getBounds().x<=100&&cell[0].getBounds().y<=100){//当第一个方格距左上角较近时
            int x=cellNull.getBounds().x;
            int y=cellNull.getBounds().y;
            int direction=(int)(Math.random()*4);//产生0-4，对应空方格的上下左右移动
            if(direction==0){//空方格左移动，与左侧方格互换位置，左侧方格右移动
                x-=100;
                if(test(x,y)){
                    for(int j=0;j<15;j++){
                        if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){//依次寻找左侧的按钮
                            cell[j].move("RIGHT",100);
                            cellNull.setLocation(x,y);                            
                            break;//找到后跳出for循环
                        }
                    }
                }
            }
            else if(direction==1){//RIGHT
                x+=100;
                if(test(x,y)){
                    for(int j=0;j<15;j++){
                        if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){
                            cell[j].move("LEFT",100);
                            cellNull.setLocation(x,y);
                            break;
                        }
                    }
                }
            }
            else if(direction==2){//UP
                y-=100;
                if(test(x,y)){
                    for(int j=0;j<15;j++){
                        if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){
                            cell[j].move("DOWN",100);
                            cellNull.setLocation(x,y);
                            break;
                        }
                    }
               	}
            }
            else{//DOWN
                y+=100;
                if(test(x,y)){
                    for(int j=0;j<15;j++){
                        if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){
                            cell[j].move("UP",100);
                            cellNull.setLocation(x,y);
                            break;
                        }
                    }
                }
            }
            this.repaint();
        }
	    if(!hasAddActionListener)//如果尚未添加动作事件，则添加
	        for(int i=0;i<15;i++)//为第个方格添加动作事件，这样单击按钮就能移动了
	            cell[i].addMouseListener(this);
        hasAddActionListener=true;
    }
	private boolean test(int x,int y){
        if((x>=0&&x<=300)||(y>=0&&y<=300))
            return true;
        else
            return false;
	}
	public void mouseClicked(MouseEvent e) {
		Cell2 button=(Cell2)e.getSource();
		if (e.getModifiers() == InputEvent.BUTTON1_MASK && e.getClickCount() >= 2)
		{
			int x1=button.getBounds().x;//得到所单击方格的坐标
	        int y1=button.getBounds().y;
	        
	        int x2=cellNull.getBounds().x;//得到空方格的坐标
	        int y2=cellNull.getBounds().y;
	        button.setLocation(x2,y2);
	        cellNull.setLocation(x1,y1);       
		}			
	}
	public void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) {//方格的鼠标事件，因为用到了MyCanvas中的一些方法，因此没有在Cell类中处理鼠标事件
        Cell2 button=(Cell2)arg0.getSource();
        int x1=button.getBounds().x;//得到所单击方格的坐标
        int y1=button.getBounds().y;
        int x2=cellNull.getBounds().x;//得到空方格的坐标
        int y2=cellNull.getBounds().y;
        if(x1==x2&&y1-y2==100)//进行比较，如果满足条件则进行交换
            button.move("UP",100);
        else if(x1==x2&&y1-y2==-100)
            button.move("DOWN",100);
        else if(x1-x2==100&y1==y2)
            button.move("LEFT",100);
        else if(x1-x2==-100&&y1==y2)
            button.move("RIGHT",100);
        else
            return;//不满足就不进行任何处理
        cellNull.setLocation(x1,y1);
        this.repaint();
        if(this.isFinish()){//进行是否完成的判断
            JOptionPane.showMessageDialog(this,"恭喜你完成拼图,加油！");
            for(int i=0;i<8;i++)
                cell[i].removeMouseListener(this);//如果已完成，撤消鼠标事件，鼠标单击方格不在起作用
            hasAddActionListener=false;
        }
	}
}
