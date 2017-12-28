import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;

/**
 * ���ʵ����
 */
public class PersonInMaze extends JTextField {
    private MazePoint point;
    private Toolkit tool;
    private String person = ConstantValue.PLAYER_IMAGE_NAME;

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


    /**
     * ������ҵ�ͼƬ
     */
    void setImage(String person) {
        this.person = person;
    }
}
