import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Record extends JDialog implements ActionListener{
   int time=0;
   String maze;
   String message="";
   JTextField textName; 
   JLabel label=null; 
   JButton 确定,取消;
   public Record(){
      setTitle("记录你的成绩");
      setBounds(200,200,320,300);
      setResizable(false);
      setModal(true); 
      确定=new JButton("确定");
      取消=new JButton("取消");
      textName=new JTextField(15);
      textName.setText("匿名");
      确定.addActionListener(this);
      取消.addActionListener(this);
      setLayout(new GridLayout(2,1));
      label=new JLabel("您现在是...高手,输入您的大名上榜啊");
      add(label);
      JPanel p=new JPanel();
      p.add(textName);
      p.add(确定);
      p.add(取消);
      add(p);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }
  public void setMaze(String maze){
      this.maze=maze;
      label.setText(" 恭喜！你破纪录了！请输入您的名字");
  }
  public void setTime(int time){
      this.time=time;
  }
  public void actionPerformed(ActionEvent e){
     if(e.getSource()==确定){
         message=maze+"#"+time+"#"+" "+textName.getText(); 
         writeRecord(maze,message);
         setVisible(false);
     }
     if(e.getSource()==取消){
         setVisible(false);
     }  
  }
  public boolean isBrokenRecord(String key,int time)//在HandleMove中调用，key是迷宫名
  {     
	    boolean b=false;
	     File f=new File("英雄榜.txt");
	     try{ 
	    	 int n=0;
	    	    Hashtable hashtable = null;//构造一个哈希表
	    		 FileInputStream in=new FileInputStream(f);
	          ObjectInputStream object_in=new ObjectInputStream(in);
	        hashtable =(Hashtable)object_in.readObject();
	          object_in.close();
	          in.close(); 
	          String temp3=(String)hashtable.get(key+"3");   //返回此哈希表中指定键所映射到的值
	         StringTokenizer fenxi=new StringTokenizer(temp3,"#");
	           fenxi.nextToken();
	          n=Integer.parseInt(fenxi.nextToken()); 
	          if(time<n)
	        	 b=true;
	     }
	     catch(Exception e) {
	         System.out.println(e);
	     }
	     return b;
  }
  public void  writeRecord(String key,String message){//key是迷宫文件名，message是记录地图名、时间、用户名的字符串
     File f=new File("英雄榜.txt");
     try{ 
    	 int n1=0,n2=0,n3=0;
    	 String a;
    	    Hashtable hashtable = null;
    		 FileInputStream in=new FileInputStream(f);
          ObjectInputStream object_in=new ObjectInputStream(in);
        hashtable =(Hashtable)object_in.readObject();
          object_in.close();
          in.close(); 
          String temp1=(String)hashtable.get(key+"1");
	String temp2=(String)hashtable.get(key+"2");
	String temp3=(String)hashtable.get(key+"3");
         
         StringTokenizer fenxi1=new StringTokenizer(temp1,"#");
StringTokenizer fenxi2=new StringTokenizer(temp2,"#");
StringTokenizer fenxi3=new StringTokenizer(temp3,"#");

/*StringTokenizer类
         public StringTokenizer(String str,
         String delim)为指定字符串构造一个 string tokenizer。delim 参数中的字符都是分隔标记的分隔符。分隔符字符本身不作为标记。 
注意，如果 delim 为 null，则此构造方法不抛出异常。但是，尝试对得到的 StringTokenizer 调用其他方法则可能抛出 NullPointerException。 


参数：
str - 要分析的字符串。
delim - 分隔符。*/
           fenxi1.nextToken();//nextToken() 返回此 string tokenizer 的下一个标记。
               n1=Integer.parseInt(fenxi1.nextToken()); 
           fenxi2.nextToken();
               n2=Integer.parseInt(fenxi2.nextToken());     
           fenxi3.nextToken();
               n3=Integer.parseInt(fenxi3.nextToken());   	 
          if(time<n1)

{            a=temp2;temp2=temp1;temp3=a; 
        	  hashtable.put(key+"1",message);//将指定 key 映射到此哈希表中的指定 value
       hashtable.put(key+"2",temp2);
       hashtable.put(key+"3",temp3);
                FileOutputStream out=new FileOutputStream(f);
                ObjectOutputStream object_out=new ObjectOutputStream(out);
                object_out.writeObject(hashtable);
                object_out.close();
                out.close();

                
          }
         else if(time<n2)
{          temp3=temp2;   
 hashtable.put(key+"2",message);//将指定 key 映射到此哈希表中的指定 value
     hashtable.put(key+"3",temp3);
                FileOutputStream out=new FileOutputStream(f);
                ObjectOutputStream object_out=new ObjectOutputStream(out);
                object_out.writeObject(hashtable);
                object_out.close();
                out.close();
              
          }


        else if(time<n3)
{               hashtable.put(key+"3",message);//将指定 key 映射到此哈希表中的指定 value
                FileOutputStream out=new FileOutputStream(f);
                ObjectOutputStream object_out=new ObjectOutputStream(out);
                object_out.writeObject(hashtable);
                object_out.close();
                out.close();
               
          }

     }
     catch(Exception e) {
         System.out.println(e);
     }
   }
}
