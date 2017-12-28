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
 * 游戏控制主逻辑窗口
 */
public class MazeWindow extends JFrame implements ActionListener {
    private String currentMap;
    private Maze maze;
    private JMenuItem wallImage, roadImage, defaultImage, playerImage1, heroItem, heroclear;//声明菜单项对象
    private File mazeFile;
    private String wallImageFile = ConstantValue.WALL_IMAGE_NAME, roadImageFile = ConstantValue.ROAD_IMAGE_NAME;
    private JButton renew;//声明按钮对象
    private String player = ConstantValue.PLAYER_IMAGE_NAME;
    private ShowRecord showRecord;   //ShowRecord类 创建一个类对象
    private String[] mazeName;

    private MazeWindow() {
        JMenuBar bar = new JMenuBar();
        JMenu menuChoice = new JMenu("选择迷宫");
        File dir = new File(".");
        File[] file = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("maze");
            }
        });
        if (file == null) {
            System.out.println("未找到地图文件");
            return;
        }
        mazeName = new String[file.length];
        for (int i = 0; i < file.length; i++) {
            mazeName[i] = file[i].getName().substring(0, file[i].getName().length() - 5);//substring(截取的起始位置，截取的长度)；
        }
        for (String aMazeName : mazeName) {
            JMenuItem item = new JMenuItem(aMazeName);
            item.addActionListener(this);
            menuChoice.add(item);
        }
        mazeFile = new File(file[0].getName());
        currentMap = mazeName[0];
        init();
        JMenu menuImage = new JMenu("选择墙和路的图像");
        wallImage = new JMenuItem("墙的图像");//创建菜单项对象
        roadImage = new JMenuItem("路的图像");
        playerImage1 = new JMenuItem("玩家图像");
        defaultImage = new JMenuItem("墙和路的默认图像");
        menuImage.add(wallImage);//菜单项添加到菜单上，如图3-5
        menuImage.add(roadImage);
        menuImage.add(playerImage1);
        menuImage.add(defaultImage);
        JMenu hero = new JMenu("英雄榜");
        heroItem = new JMenuItem("查看英雄榜");
        heroclear = new JMenuItem("清空英雄榜");
        hero.add(heroItem);
        hero.add(heroclear);
        bar.add(menuChoice);
        bar.add(menuImage);//菜单添加到菜单条上，如图3-5
        bar.add(hero);
        setJMenuBar(bar);
        wallImage.addActionListener(this);//为菜单项添加监听器
        roadImage.addActionListener(this);
        playerImage1.addActionListener(this);
        defaultImage.addActionListener(this);
        heroItem.addActionListener(this);
        heroclear.addActionListener(this);
        renew = new JButton("重新开始");//为“重新开始”按钮添加监听器
        Font font = new Font("楷体", Font.BOLD, 30);
        renew.setFont(font);
        renew.setVerticalAlignment(SwingConstants.TOP);
        renew.addActionListener(this);
        add(maze, BorderLayout.CENTER);//默认布局，如图3-7
        add(renew, BorderLayout.SOUTH);
        Hashtable hashtable = new Hashtable();
        for (int i = 0; i < file.length; i++) {
            hashtable.put(mazeName[i] + "1", mazeName[i] + "#" + 100 + "#匿名1");
            hashtable.put(mazeName[i] + "2", mazeName[i] + "#" + 100 + "#匿名2");
            hashtable.put(mazeName[i] + "3", mazeName[i] + "#" + 100 + "#匿名3");
        }
        File file_HeroList = new File(ConstantValue.HERO_LIST_NAME);
        if (!file_HeroList.exists()) {//英雄榜是File类对象；  boolean exists() 测试此抽象路径名表示的文件或目录是否存在。

            try {
                FileOutputStream out = new FileOutputStream(file_HeroList);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(hashtable);  //void writeObject(Object obj)  将指定的对象写入 ObjectOutputStream。

                objectOut.close();     //void close() 关闭流。

                out.close();     //java.io 类 FileOutputStrea的方法    void close() 关闭此文件输出流并释放与此流有关的所有系统资源。

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showRecord = new ShowRecord(mazeName.length);


        setVisible(true);

        setBounds(60, 60, 510, 480);

        validate();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);/*EXIT_ON_CLOSE：退出应用程序后的默认窗口关闭操作*/
    }

    public static void main(String args[]) {
        new MazeWindow();
    }

    String getCurrentMap() {
        return currentMap;
    }

    /**
     * 初始化相关资源
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
            JFileChooser chooser = new JFileChooser();//文件对话框
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
        } else if (e.getSource() == heroItem)   //点击英雄榜
        {
            showRecord.setMazeName(mazeName);
            showRecord.readAndShow();
            showRecord.setVisible(true);
        } else if (e.getSource() == heroclear) {
            File f = new File(ConstantValue.HERO_LIST_NAME);
            f.delete();
            JFrame t = new JFrame("提示！");
            t.setFont(new Font("楷体", Font.BOLD, 30));
            t.setSize(300, 215);
            t.setLocationRelativeTo(null);
            JPanel pan = new JPanel();
            t.setContentPane(pan);
            BorderLayout bLayout = new BorderLayout(20, 20);
            pan.setLayout(bLayout);
            JLabel leb = new JLabel("英雄榜已清空，请重新载入游戏。");
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
