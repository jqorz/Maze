import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * ǽ��·��ʵ����
 */
public class WallOrRoad extends JPanel {
    private boolean isRoad, isWall, isEnter, isOut;
    private String wallImage, roadImage;
    private Toolkit tool;//����IMAGE����

    WallOrRoad() {
        tool = getToolkit();
    }

    boolean getIsEnter() {
        return isEnter;
    }

    void setIsEnter() {
        isEnter = true;
        add(new JLabel("���"));//�����Թ������
    }

    boolean getIsOut() {
        return isOut;
    }

    void setIsOut() {
        isOut = true;
        add(new JLabel("����"));//�����Թ��ĳ���
    }

    boolean getIsRoad() {
        return isRoad;
    }

    void setIsRoad() {
        isRoad = true;
        setBorder(null);//������߿�Ϊ��
    }

    boolean getIsWall() {
        return isWall;
    }

    void setIsWall() {
        isWall = true;
        setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    }


    void setWallImage(String f) {
        wallImage = f;
    }

    void setRoadImage(String f) {
        roadImage = f;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getBounds().width;
        int h = getBounds().height;
        try {
            if (isRoad) {
                Image image = tool.getImage(roadImage);
                g.drawImage(image, 0, 0, w, h, this);
            } else if (isWall) {
                Image image = tool.getImage(wallImage);
                g.drawImage(image, 0, 0, w, h, this);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
