import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Maze extends JLayeredPane{
      File mazeFile;
      MazePoint[][] mazePoint;
      WallOrRoad[][] wallOrRoad;
      PersonInMaze person;
      HandleMove handleMove;
      File wallImage,roadImage;
      int distance=26,m=0,n=0;
      public Maze(){
          setLayout(null);
          wallImage=new File("wall.gif");
          roadImage=new File("road.jpg");
          person=new PersonInMaze();
          handleMove=new HandleMove();
          handleMove.initSpendTime();
          person.addKeyListener(handleMove);
          setLayer(person,JLayeredPane.DRAG_LAYER);
     }
     public void setMazeFile(File f){
          mazeFile=f;
          char [][]a;
          RandomAccessFile in=null;
          String lineWord=null;
          try{ in=new RandomAccessFile(mazeFile,"r"); //创建RandomAccessFile类的只读对象
    /*RandomAccessFile是用来访问那些保存数据记录的文件的，这样你就可以用seek( )方法来访问记录，并进行读写了*/
               long length=in.length();
               long position=0;
               in.seek(position);
               while(position<length){
                  String str=in.readLine().trim();
                  if(str.length()>=n)
                      n=str.length();
                  position=in.getFilePointer();
                 m++;
               }
               a=new char[m][n];
               position=0;
               in.seek(position);
               m=0;
               while(position<length){
                   String str=in.readLine();
                   a[m]=str.toCharArray();
                   position=in.getFilePointer();
                   m++;
               }
               in.close();
               wallOrRoad=new WallOrRoad[m][n];
               for(int i=0;i<m;i++){
                   for(int j=0;j<n;j++){
                     wallOrRoad[i][j]=new WallOrRoad();
                     if(a[i][j]=='1'){
                       wallOrRoad[i][j].setIsWall(true);
                       wallOrRoad[i][j].setWallImage(wallImage);
                       wallOrRoad[i][j].repaint();
                     }
                    else if(a[i][j]=='0'){
                      wallOrRoad[i][j].setIsRoad(true);
                      wallOrRoad[i][j].setRoadImage(roadImage);
                      wallOrRoad[i][j].repaint();
                     }
                     else if(a[i][j]=='*'){
                       wallOrRoad[i][j].setIsEnter(true);
                       wallOrRoad[i][j].setIsRoad(true);
                       wallOrRoad[i][j].repaint();
                    }
                     else if(a[i][j]=='#'){
                       wallOrRoad[i][j].setIsOut(true);
                       wallOrRoad[i][j].setIsRoad(true);
                       wallOrRoad[i][j].repaint();
                    }
                 }
              }
              mazePoint=new MazePoint[m][n];
              int Hspace=distance,Vspace=distance;
              for(int i=0;i<m;i++){
                  for(int j=0;j<n;j++){
                     mazePoint[i][j]=new MazePoint(Hspace,Vspace);
                     Hspace=Hspace+distance;
                  }
                  Hspace=distance;
                  Vspace=Vspace+distance;
              }
              for(int i=0;i<m;i++){
                  for(int j=0;j<n;j++){
                    add(wallOrRoad[i][j]);
                    wallOrRoad[i][j].setSize(distance,distance);
                    wallOrRoad[i][j].setLocation(mazePoint[i][j].getX(),mazePoint[i][j].getY());
                    wallOrRoad[i][j].setAtMazePoint(mazePoint[i][j]);
                    mazePoint[i][j].setWallOrRoad(wallOrRoad[i][j]);
                    mazePoint[i][j].setIsWallOrRoad(true);
                    if(wallOrRoad[i][j].getIsEnter()){
                       person.setAtMazePoint(mazePoint[i][j]);
                       add(person);
                       person.setSize(distance,distance);
                       person.setLocation(mazePoint[i][j].getX(),mazePoint[i][j].getY());
                       person.requestFocus();
                       person.repaint();
                     }
                   }
                }
                handleMove.setMazePoint(mazePoint);
          }
          catch(IOException exp){
                JButton mess=new JButton("无效的迷宫文件");
                add(mess);
                mess.setBounds(30,30,100,100);
                mess.setFont(new Font("宋体",Font.BOLD,30));
                System.out.println(exp+"mess");
           }
    }
    public void setWallImage(File f){
       wallImage=f;
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
             if(wallOrRoad[i][j].getIsWall())
               wallOrRoad[i][j].setWallImage(wallImage);
               wallOrRoad[i][j].repaint();
        }
      }
    }
public void setRoadImage(File f){
       roadImage=f;
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
             if(wallOrRoad[i][j].getIsRoad())
                wallOrRoad[i][j].setRoadImage(roadImage);
                wallOrRoad[i][j].repaint();
            }
        }
    }




    public HandleMove getHandleMove(){
         return handleMove;
    }
}

