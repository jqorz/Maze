import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class Maze extends JLayeredPane {
    private WallOrRoad[][] wallOrRoad;
    private PersonInMaze person;
    private HandleMove handleMove;
    private String wallImage, roadImage;
    private int m = 0;
    private int n = 0;

    public PersonInMaze getPerson() {
        return person;
    }

    Maze(MazeWindow mazeWindow) {
        setLayout(null);
        wallImage = ConstantValue.WALL_IMAGE_NAME;
        roadImage = ConstantValue.ROAD_IMAGE_NAME;
        person = new PersonInMaze();
        handleMove = new HandleMove(mazeWindow);
        handleMove.initSpendTime();
        person.addKeyListener(handleMove);
        setLayer(person, JLayeredPane.DRAG_LAYER);
    }

    void setMazeFile(File f) {
        char[][] a;
        RandomAccessFile in;
        try {
            in = new RandomAccessFile(f, "r"); //创建RandomAccessFile类的只读对象
    /*RandomAccessFile是用来访问那些保存数据记录的文件的，这样你就可以用seek( )方法来访问记录，并进行读写了*/
            long length = in.length();
            long position = 0;
            in.seek(position);
            while (position < length) {
                String str = in.readLine().trim();
                if (str.length() >= n)
                    n = str.length();
                position = in.getFilePointer();
                m++;
            }
            a = new char[m][n];
            position = 0;
            in.seek(position);
            m = 0;
            while (position < length) {
                String str = in.readLine();
                a[m] = str.toCharArray();
                position = in.getFilePointer();
                m++;
            }
            in.close();
            wallOrRoad = new WallOrRoad[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    wallOrRoad[i][j] = new WallOrRoad();
                    if (a[i][j] == '1') {
                        wallOrRoad[i][j].setIsWall();
                        wallOrRoad[i][j].setWallImage(wallImage);
                        wallOrRoad[i][j].repaint();
                    } else if (a[i][j] == '0') {
                        wallOrRoad[i][j].setIsRoad();
                        wallOrRoad[i][j].setRoadImage(roadImage);
                        wallOrRoad[i][j].repaint();
                    } else if (a[i][j] == '*') {
                        wallOrRoad[i][j].setIsEnter();
                        wallOrRoad[i][j].setIsRoad();
                        wallOrRoad[i][j].repaint();
                    } else if (a[i][j] == '#') {
                        wallOrRoad[i][j].setIsOut();
                        wallOrRoad[i][j].setIsRoad();
                        wallOrRoad[i][j].repaint();
                    }
                }
            }
            MazePoint[][] mazePoint = new MazePoint[m][n];
            int distance = 26;
            int Hspace = distance, Vspace = distance;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mazePoint[i][j] = new MazePoint(Hspace, Vspace);
                    Hspace = Hspace + distance;
                }
                Hspace = distance;
                Vspace = Vspace + distance;
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    add(wallOrRoad[i][j]);
                    wallOrRoad[i][j].setSize(distance, distance);
                    wallOrRoad[i][j].setLocation(mazePoint[i][j].getX(), mazePoint[i][j].getY());
                    mazePoint[i][j].setWallOrRoad(wallOrRoad[i][j]);
                    if (wallOrRoad[i][j].getIsEnter()) {
                        person.setAtMazePoint(mazePoint[i][j]);
                        add(person);
                        person.setSize(distance, distance);
                        person.setLocation(mazePoint[i][j].getX(), mazePoint[i][j].getY());
                        person.requestFocus();
                        person.repaint();
                    }
                }
            }
            handleMove.setMazePoint(mazePoint);
        } catch (IOException exp) {
            JButton mess = new JButton("无效的迷宫文件");
            add(mess);
            mess.setBounds(30, 30, 100, 100);
            mess.setFont(new Font("宋体", Font.BOLD, 30));
            System.out.println(exp + "mess");
        }
    }

    void setWallImage(String f) {
        wallImage = f;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (wallOrRoad[i][j].getIsWall())
                    wallOrRoad[i][j].setWallImage(wallImage);
                wallOrRoad[i][j].repaint();
            }
        }
    }

    void setRoadImage(String f) {
        roadImage = f;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (wallOrRoad[i][j].getIsRoad())
                    wallOrRoad[i][j].setRoadImage(roadImage);
                wallOrRoad[i][j].repaint();
            }
        }
    }


    HandleMove getHandleMove() {
        return handleMove;
    }
}

