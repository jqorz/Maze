import javax.swing.*;
import java.awt.*;

public class PersonInMaze extends JTextField {
    private MazePoint point;
    private Toolkit tool;
    private String person = "person.gif";

    PersonInMaze() {
        tool = getToolkit();  //��ù��߰�
        setEditable(false);//���ɼ�
        setBorder(null);//�ޱ߿�
        setOpaque(false);//��͸����
        setToolTipText("�����ң�Ȼ�󰴼��̷����");//��ʾ��Ϣ����ͼ3-1��ʾ��
    }

    MazePoint getAtMazePoint() {
        return point;
    }

    void setAtMazePoint(MazePoint p) {
        point = p;
    }

    public void paintComponent(Graphics g) {//�������Թ��ߵ�ͼƬ
        super.paintComponent(g);
        int w = getBounds().width;
        int h = getBounds().height;
        Image image = tool.getImage(person);//���imageͼ��
        g.drawImage(image, 0, 0, w, h, this);//����imageͼ��
    }


    void setImage(String person) {
        this.person = person;
    }
}
