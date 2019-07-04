import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 移动控制类
 */
public class HandleMove extends JPanel implements KeyListener, ActionListener {
    private MazePoint[][] p;
    private int spendTime = 0;
    private javax.swing.Timer recordTime;
    private JTextField showTime;
    private String maze;  //MazeWindow类创建一个对象指向currentMap 。字符串
    private Sound sound;

    HandleMove(MazeWindow mazeWindow) {
        recordTime = new javax.swing.Timer(1000, this);
        showTime = new JTextField(16);
        sound = new Sound();
        maze = mazeWindow.getCurrentMap();
        showTime.setEditable(false);
        showTime.setHorizontalAlignment(JTextField.CENTER);
        showTime.setFont(new Font("楷体_GB2312", Font.BOLD, 16));
        JLabel hitMess = new JLabel("单击走迷宫者，按键盘方向键", JLabel.CENTER);
        hitMess.setFont(new Font("楷体_GB2312", Font.BOLD, 18));
        add(hitMess);
        add(showTime);
        setBackground(Color.red);
    }

    void setMazePoint(MazePoint[][] point) {
        p = point;
    }

    void setMap(String maze) {
        this.maze = maze;
    }

    void initSpendTime() {
        recordTime.stop();
        spendTime = 0;
        showTime.setText(null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        recordTime.start();
        PersonInMaze person;
        person = (PersonInMaze) e.getSource();
        int m = -1, n = -1;
        MazePoint startPoint = person.getAtMazePoint();
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (startPoint.equals(p[i][j])) {
                    m = i;
                    n = j;
                    break;
                }
            }
        }
        if (spendTime > 3) {
            person.setImage(ConstantValue.PIG_IMAGE_NAME);

        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            int k = Math.max(m - 1, 0);
            if (p[k][n].getWallOrRoad().getIsRoad()) {//逻辑真时，走迷宫者不能穿进墙
                if (sound.isPlaying()) {
                    sound.stop();
                }
                sound.makeSound(ConstantValue.WALK_SOUND_NAME_1);

                person.setAtMazePoint(p[k][n]);
                person.setLocation(p[k][n].getX(), p[k][n].getY());
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            int k = Math.min(m + 1, p.length - 1);
            if (p[k][n].getWallOrRoad().getIsRoad()) {
                if (sound.isPlaying()) {
                    sound.stop();
                }
                sound.makeSound(ConstantValue.WALK_SOUND_NAME_2);
                person.setAtMazePoint(p[k][n]);
                person.setLocation(p[k][n].getX(), p[k][n].getY());
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            int k = Math.max(n - 1, 0);
            if (p[m][k].getWallOrRoad().getIsRoad()) {
                if (sound.isPlaying()) {
                    sound.stop();
                }
                sound.makeSound(ConstantValue.WALK_SOUND_NAME_3);
                person.setAtMazePoint(p[m][k]);
                person.setLocation(p[m][k].getX(), p[m][k].getY());
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            int k = Math.min(n + 1, p[0].length - 1);
            if (p[m][k].getWallOrRoad().getIsRoad()) {
                if (sound.isPlaying()) {
                    sound.stop();
                }
                sound.makeSound(ConstantValue.WALK_SOUND_NAME_4);
                person.setAtMazePoint(p[m][k]);
                person.setLocation(p[m][k].getX(), p[m][k].getY());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spendTime++;
        showTime.setText("您的用时：" + spendTime + "秒");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        PersonInMaze person = (PersonInMaze) e.getSource();
        MazePoint endPoint = person.getAtMazePoint();
        if (endPoint.getWallOrRoad().getIsOut()) {
            recordTime.stop();
            if (spendTime > 10) {
                JOptionPane.showMessageDialog(this, "恭喜您闯关成功，亲！但用时超过10秒钟，速度慢了些哦！用时：" + spendTime + "秒", "消息框", JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(this, "恭喜您闯关成功，亲！用时：" + spendTime + "秒", "消息框", JOptionPane.INFORMATION_MESSAGE);

            Record record = new Record();
            if (record.isBrokenRecord(maze, spendTime))//判断是否破纪录           	  {
                record.setMaze(maze);
            record.setTime(spendTime);
            record.setVisible(true);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
