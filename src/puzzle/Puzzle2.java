package puzzle;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import canvas.MyCanvas2;



public class Puzzle2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
    MyCanvas2 myCanvas;//panel容器,用于拼图
    JPanel panelNorth,panelPreview;//定义上方的面板，及预览所需的面板
    Button start,preview,set;//定义开始，预览，设定按钮
    Container container;//容器，得到内容面板
    ImageIcon imagels;
    public Puzzle2() {//初使化
        container=this.getContentPane();
        start=new Button("开始");
        start.addActionListener(this);
        preview=new Button("预览");
        preview.addActionListener(this);
        set = new Button("设置");
        set.addActionListener(this);
        panelPreview=new JPanel();
        panelPreview.setLayout(null);        
        imagels=new ImageIcon("images//pt"+MyCanvas2.pictureID+".gif");  //实例化图标
        imagels.setImage(imagels.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT));
        JLabel label=new JLabel(imagels);
        label.setBounds(0,0,400,400);
        panelPreview.add(label);
        panelNorth=new JPanel();
        panelNorth.setBackground(Color.gray);
        panelNorth.add(start);
        panelNorth.add(preview);
        panelNorth.add(set);
        myCanvas=new MyCanvas2();
        container.add(myCanvas,BorderLayout.CENTER);
        container.add(panelNorth,BorderLayout.NORTH);
        this.setTitle("拼图小游戏-电商B14-2邓小雪 2016-12-7");
        this.setLocation(300,200);
        this.setSize(408,465);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }
    public void actionPerformed(ActionEvent arg0) {//对三个按钮事件的处理
        Button button=(Button)arg0.getSource();
        if(button==start){
            myCanvas.Start();
        }
        else if(button==preview){
            if(button.getLabel()=="预览"){
                container.remove(myCanvas);
                container.add(panelPreview);
                panelPreview.updateUI();
                container.repaint();
                button.setLabel("返回");
            }
            else
            {
                container.remove(panelPreview);
                container.add(myCanvas);
                container.repaint();
                button.setLabel("预览");
            }
        }
        else if(button==set){//修改所选图片
            Choice pic = new Choice();
            pic.add("圣诞");
            pic.add("仙境");
            pic.add("城市");
            int i=JOptionPane.showConfirmDialog(this, pic, "选择图片", JOptionPane.OK_CANCEL_OPTION);
            if(i==JOptionPane.YES_OPTION){
                MyCanvas2.pictureID=pic.getSelectedIndex()+2;
                myCanvas.reLoadPictrue();                
                imagels=new ImageIcon("images//pt"+MyCanvas2.pictureID+".gif");  //实例化图标
                imagels.setImage(imagels.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT));
                JLabel label=new JLabel(imagels);
                label.setBounds(0,0,400,400);
                panelPreview.removeAll();
                panelPreview.add(label);
                panelPreview.repaint();
            }
        }
    }
}
