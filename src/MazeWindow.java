import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ��Ϸ�������߼�����
 */
public class MazeWindow extends JFrame implements ActionListener {
    private String currentMap;
    private Maze maze;
    private JMenuItem wallImage, roadImage, defaultImage, playerImage1, heroItem, heroclear;//�����˵������
    private File mazeFile;
    private String wallImageFile = ConstantValue.WALL_IMAGE_NAME, roadImageFile = ConstantValue.ROAD_IMAGE_NAME;
    private JButton renew;//������ť����
    private String player = ConstantValue.PLAYER_IMAGE_NAME;
    private ShowRecord showRecord;   //ShowRecord�� ����һ�������
    private String[] mazeName;

    private MazeWindow() {
        JMenuBar bar = new JMenuBar();
        JMenu menuChoice = new JMenu("ѡ���Թ�");
        File dir = new File(".");
        File[] file = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("maze");
            }
        });
        if (file == null) {
            System.out.println("δ�ҵ���ͼ�ļ�");
            return;
        }
        mazeName = new String[file.length];
        for (int i = 0; i < file.length; i++) {
            mazeName[i] = file[i].getName().substring(0, file[i].getName().length() - 5);//substring(��ȡ����ʼλ�ã���ȡ�ĳ���)��
        }
        for (String aMazeName : mazeName) {
            JMenuItem item = new JMenuItem(aMazeName);
            item.addActionListener(this);
            menuChoice.add(item);
        }
        mazeFile = new File(file[0].getName());
        currentMap = mazeName[0];
        init();
        JMenu menuImage = new JMenu("ѡ��ǽ��·��ͼ��");
        wallImage = new JMenuItem("ǽ��ͼ��");//�����˵������
        roadImage = new JMenuItem("·��ͼ��");
        playerImage1 = new JMenuItem("���ͼ��");
        defaultImage = new JMenuItem("ǽ��·��Ĭ��ͼ��");
        menuImage.add(wallImage);//�˵�����ӵ��˵��ϣ���ͼ3-5
        menuImage.add(roadImage);
        menuImage.add(playerImage1);
        menuImage.add(defaultImage);
        JMenu hero = new JMenu("Ӣ�۰�");
        heroItem = new JMenuItem("�鿴Ӣ�۰�");
        heroclear = new JMenuItem("���Ӣ�۰�");
        hero.add(heroItem);
        hero.add(heroclear);
        bar.add(menuChoice);
        bar.add(menuImage);//�˵���ӵ��˵����ϣ���ͼ3-5
        bar.add(hero);
        setJMenuBar(bar);
        wallImage.addActionListener(this);//Ϊ�˵�����Ӽ�����
        roadImage.addActionListener(this);
        playerImage1.addActionListener(this);
        defaultImage.addActionListener(this);
        heroItem.addActionListener(this);
        heroclear.addActionListener(this);
        renew = new JButton("���¿�ʼ");//Ϊ�����¿�ʼ����ť��Ӽ�����
        Font font = new Font("����", Font.BOLD, 30);
        renew.setFont(font);
        renew.setVerticalAlignment(SwingConstants.TOP);
        renew.addActionListener(this);
        add(maze, BorderLayout.CENTER);//Ĭ�ϲ��֣���ͼ3-7
        add(renew, BorderLayout.SOUTH);
        Hashtable hashtable = new Hashtable();
        for (int i = 0; i < file.length; i++) {
            hashtable.put(mazeName[i] + "1", mazeName[i] + "#" + 100 + "#����1");
            hashtable.put(mazeName[i] + "2", mazeName[i] + "#" + 100 + "#����2");
            hashtable.put(mazeName[i] + "3", mazeName[i] + "#" + 100 + "#����3");
        }
        File file_HeroList = new File(ConstantValue.HERO_LIST_NAME);
        if (!file_HeroList.exists()) {//Ӣ�۰���File�����  boolean exists() ���Դ˳���·������ʾ���ļ���Ŀ¼�Ƿ���ڡ�

            try {
                FileOutputStream out = new FileOutputStream(file_HeroList);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(hashtable);  //void writeObject(Object obj)  ��ָ���Ķ���д�� ObjectOutputStream��

                objectOut.close();     //void close() �ر�����

                out.close();     //java.io �� FileOutputStrea�ķ���    void close() �رմ��ļ���������ͷ�������йص�����ϵͳ��Դ��

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showRecord = new ShowRecord(mazeName.length);


        setVisible(true);

        setBounds(60, 60, 510, 480);

        validate();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);/*EXIT_ON_CLOSE���˳�Ӧ�ó�����Ĭ�ϴ��ڹرղ���*/
    }

    public static void main(String args[]) {
        new MazeWindow();
    }

    String getCurrentMap() {
        return currentMap;
    }

    /**
     * ��ʼ�������Դ
     */
    private void init() {
        if (maze != null) {
            remove(maze);
            remove(maze.getHandleMove());
        }
        maze = new Maze(this);
        maze.setWallImage(wallImageFile);
        maze.setRoadImage(roadImageFile);
        maze.setMazeFile(mazeFile);
        maze.getPerson().setImage(player);
        add(maze, BorderLayout.CENTER);
        add(maze.getHandleMove(), BorderLayout.NORTH);
        validate();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == roadImage) {
            JFileChooser chooser = new JFileChooser();//�ļ��Ի���
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG&GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                roadImageFile = file.getName();
                maze.setRoadImage(roadImageFile);
            }
        } else if (e.getSource() == wallImage) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG&GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                wallImageFile = file.getName();
                maze.setWallImage(wallImageFile);
            }
        } else if (e.getSource() == playerImage1) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG&GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                player = file.getName();
                maze.getPerson().setImage(player);
            }
        } else if (e.getSource() == defaultImage) {
            wallImageFile = ("wall.jpg");
            roadImageFile = ("road.jpg");
            maze.setWallImage(wallImageFile);
            maze.setRoadImage(roadImageFile);
        } else if (e.getSource() == renew) {
            init();
        } else if (e.getSource() == heroItem)   //���Ӣ�۰�
        {
            showRecord.setMazeName(mazeName);
            showRecord.readAndShow();
            showRecord.setVisible(true);
        } else if (e.getSource() == heroclear) {
            File f = new File(ConstantValue.HERO_LIST_NAME);
            f.delete();
            JFrame t = new JFrame("��ʾ��");
            t.setFont(new Font("����", Font.BOLD, 30));
            t.setSize(300, 215);
            t.setLocationRelativeTo(null);
            JPanel pan = new JPanel();
            t.setContentPane(pan);
            BorderLayout bLayout = new BorderLayout(20, 20);
            pan.setLayout(bLayout);
            JLabel leb = new JLabel("Ӣ�۰�����գ�������������Ϸ��");
            pan.add(leb, BorderLayout.CENTER);
            t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            t.setVisible(true);
        } else {
            JMenuItem item = (JMenuItem) e.getSource();
            mazeFile = new File(item.getText() + ".maze");
            currentMap = item.getText();
            maze.getHandleMove().setMap(currentMap);
            init();
        }
    }
}
