import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;

/**
 * 玩家实体类
 */
public class PersonInMaze extends JTextField {
    private MazePoint point;
    private Toolkit tool;
    private String person = ConstantValue.PLAYER_IMAGE_NAME;

    PersonInMaze() {
        tool = getToolkit();  //获得工具包
        setEditable(false);//不可见
        setBorder(null);//无边框
        setOpaque(false);//非透明的
        setToolTipText("单击我，然后按键盘方向键");//提示信息，如图3-1所示。
    }

    MazePoint getAtMazePoint() {
        return point;
    }

    void setAtMazePoint(MazePoint p) {
        point = p;
    }

    public void paintComponent(Graphics g) {//设置走迷宫者的图片
        super.paintComponent(g);
        int w = getBounds().width;
        int h = getBounds().height;
        Image image = tool.getImage(person);//获得image图像
        g.drawImage(image, 0, 0, w, h, this);//绘制image图像
    }


    /**
     * 更改玩家的图片
     */
    void setImage(String person) {
        this.person = person;
    }
}
