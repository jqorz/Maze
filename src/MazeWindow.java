import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;

import javax.swing.filechooser.*;

public class MazeWindow extends JFrame implements ActionListener {
    Maze maze;
    JMenuBar bar;//�����˵�������
    JMenu menuChoice, menuImage, hero;//�����˵�����
    JMenuItem wallImage, roadImage, defaultImage, playerImage1, heroItem, heroclear;//�����˵������
    File mazeFile, wallImageFile, roadImageFile;
    JButton renew;//������ť����
    String player = "person.gif";
    ShowRecord showRecord;   //ShowRecord�� ����һ�������
    File Ӣ�۰� = new File("Ӣ�۰�.txt");
    File file[] = null;
    static String[] mazeName;
    static String currentMap;
    Hashtable hashtable = null;  //��ϣ��

    MazeWindow() {
        wallImageFile = new File("wall.gif");
        roadImageFile = new File("road.jpg");
        bar = new JMenuBar();//�����˵�������
        menuChoice = new JMenu("ѡ���Թ�");
        File dir = new File(".");
        file = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("maze");
            }
        });
        mazeName = new String[file.length];
        for (int i = 0; i < file.length; i++) {
            mazeName[i] = file[i].getName().substring(0, file[i].getName().length() - 5);//substring(��ȡ����ʼλ�ã���ȡ�ĳ���)��
        }
        for (int i = 0; i < mazeName.length; i++) {
            JMenuItem item = new JMenuItem(mazeName[i]);
            item.addActionListener(this);
            menuChoice.add(item);
        }
        mazeFile = new File(file[0].getName());
        currentMap = mazeName[0];
        init();
        menuImage = new JMenu("ѡ��ǽ��·��ͼ��");
        wallImage = new JMenuItem("ǽ��ͼ��");//�����˵������
        roadImage = new JMenuItem("·��ͼ��");
        playerImage1 = new JMenuItem("���ͼ��");
        defaultImage = new JMenuItem("ǽ��·��Ĭ��ͼ��");
        menuImage.add(wallImage);//�˵�����ӵ��˵��ϣ���ͼ3-5
        menuImage.add(roadImage);
        menuImage.add(playerImage1);
        menuImage.add(defaultImage);
        hero = new JMenu("Ӣ�۰�");
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
        hashtable = new Hashtable();
        for (int i = 0; i < file.length; i++) {
            hashtable.put(mazeName[i] + "1", mazeName[i] + "#" + 100 + "#����1");
            hashtable.put(mazeName[i] + "2", mazeName[i] + "#" + 100 + "#����2");
            hashtable.put(mazeName[i] + "3", mazeName[i] + "#" + 100 + "#����3");
        }
        if (!Ӣ�۰�.exists()) {//Ӣ�۰���File�����  boolean exists() ���Դ˳���·������ʾ���ļ���Ŀ¼�Ƿ���ڡ�

            try {
                FileOutputStream out = new FileOutputStream(Ӣ�۰�);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(hashtable);  //void writeObject(Object obj)  ��ָ���Ķ���д�� ObjectOutputStream��

                objectOut.close();     //void close() �ر�����

                out.close();     //java.io �� FileOutputStrea�ķ���    void close() �رմ��ļ���������ͷ�������йص�����ϵͳ��Դ��

            } catch (IOException e) {
            }
        }
        showRecord = new ShowRecord(this, mazeName.length);


        setVisible(true);

        setBounds(60, 60, 510, 480);

        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*EXIT_ON_CLOSE���˳�Ӧ�ó�����Ĭ�ϴ��ڹرղ���*/
    }

    public void init() {
        if (maze != null) {
            remove(maze);
            remove(maze.getHandleMove());
        }
        maze = new Maze();
        maze.setWallImage(wallImageFile);
        maze.setRoadImage(roadImageFile);
        maze.setMazeFile(mazeFile);
        maze.person.setImage(player);
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
                roadImageFile = file;
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
                wallImageFile = file;
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
                maze.person.setImage(player);
            }
        } else if (e.getSource() == defaultImage) {
            wallImageFile = new File("wall.JPG");
            roadImageFile = new File("road.jpg");
            maze.setWallImage(wallImageFile);
            maze.setRoadImage(roadImageFile);
        } else if (e.getSource() == renew) {
            init();
        } else if (e.getSource() == heroItem)   //���Ӣ�۰�
        {
            showRecord.setMazeName(mazeName);
            showRecord.readAndShow(hashtable);
            showRecord.setVisible(true);
        } else if (e.getSource() == heroclear) {
            File f = new File("Ӣ�۰�.txt");
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
            t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            t.setVisible(true);
        } else {
            JMenuItem item = (JMenuItem) e.getSource();
            mazeFile = new File(item.getText() + ".maze");
            currentMap = item.getText();
            maze.getHandleMove().setMap(currentMap);
            init();
        }
    }

    public static void main(String args[]) {
        new MazeWindow();
    }
}
