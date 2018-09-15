package puzzle;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import canvas.MyCanvas;

public class Puzzle extends JFrame{

	private static final long serialVersionUID = 1L;
	MyCanvas myCanvas;  //定义拼图面板，继承与JPanel
    Container container;//容器，得到内容面板
    public Puzzle() {     //初使化
        container=this.getContentPane();
        myCanvas=new MyCanvas();
        container.add(myCanvas,BorderLayout.CENTER);
        this.setLocation(300,200);
        this.setSize(400,430);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myCanvas.Start();
    }
}
