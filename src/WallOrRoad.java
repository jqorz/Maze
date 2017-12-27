import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.io.*;
public class WallOrRoad extends JPanel{
   boolean isRoad,isWall,isEnter,isOut,havePassed;
   MazePoint point;//ȷ��λ��
   File wallImage,roadImage,personImage;
   Toolkit tool;//����IMAGE����
   WallOrRoad(){//���wallroad�Ķ����ʼ��
       tool=getToolkit();
   } 
   public void setIsEnter(boolean boo){
       isEnter=boo;
       if(isEnter==true)
         add(new JLabel("���"));//�����Թ������
   }
   public boolean getIsEnter(){
       return isEnter;
   }
   public void setIsOut(boolean boo){
       isOut=boo;
       if(isOut==true)
         add(new JLabel("����"));//�����Թ��ĳ���
   }
   public boolean getIsOut(){
       return isOut;
   }
   public void setIsRoad(boolean boo){
       isRoad=boo;
       if(isRoad==true){
          setBorder(null);//������߿�Ϊ��
       }
   }
   public boolean  getIsRoad(){
       return isRoad;
   }
   public void setIsWall(boolean boo){
       isWall=boo;
       if(isWall==true)
          setBorder(new SoftBevelBorder(BevelBorder.RAISED));
   }
   public boolean  getIsWall(){
       return isWall;
   }
   public void setAtMazePoint(MazePoint p){
      point=p;
   }
   public MazePoint getAtMazePoint(){
      return point;
   }
   public void setWallImage(File f){
       wallImage=f;
   } 
   public void setRoadImage(File f){
       roadImage=f;
   }
   public void setPassed(boolean b) {
	   havePassed = true;
   }
   public void paintComponent(Graphics g){
      super.paintComponent(g);  
      int w=getBounds().width;
      int h=getBounds().height;
      try{ 
         if(isRoad==true){
            Image image=tool.getImage(roadImage.toURI().toURL());    
            g.drawImage(image,0,0,w,h,this);
         }
         else if(isWall==true){       
            Image image=tool.getImage(wallImage.toURI().toURL());
            g.drawImage(image,0,0,w,h,this); 
         }
      }
      catch(Exception exp){}
   }
}
