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
   JButton ȷ��,ȡ��;
   public Record(){
      setTitle("��¼��ĳɼ�");
      setBounds(200,200,320,300);
      setResizable(false);
      setModal(true); 
      ȷ��=new JButton("ȷ��");
      ȡ��=new JButton("ȡ��");
      textName=new JTextField(15);
      textName.setText("����");
      ȷ��.addActionListener(this);
      ȡ��.addActionListener(this);
      setLayout(new GridLayout(2,1));
      label=new JLabel("��������...����,�������Ĵ����ϰ�");
      add(label);
      JPanel p=new JPanel();
      p.add(textName);
      p.add(ȷ��);
      p.add(ȡ��);
      add(p);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }
  public void setMaze(String maze){
      this.maze=maze;
      label.setText(" ��ϲ�����Ƽ�¼�ˣ���������������");
  }
  public void setTime(int time){
      this.time=time;
  }
  public void actionPerformed(ActionEvent e){
     if(e.getSource()==ȷ��){
         message=maze+"#"+time+"#"+" "+textName.getText(); 
         writeRecord(maze,message);
         setVisible(false);
     }
     if(e.getSource()==ȡ��){
         setVisible(false);
     }  
  }
  public boolean isBrokenRecord(String key,int time)//��HandleMove�е��ã�key���Թ���
  {     
	    boolean b=false;
	     File f=new File("Ӣ�۰�.txt");
	     try{ 
	    	 int n=0;
	    	    Hashtable hashtable = null;//����һ����ϣ��
	    		 FileInputStream in=new FileInputStream(f);
	          ObjectInputStream object_in=new ObjectInputStream(in);
	        hashtable =(Hashtable)object_in.readObject();
	          object_in.close();
	          in.close(); 
	          String temp3=(String)hashtable.get(key+"3");   //���ش˹�ϣ����ָ������ӳ�䵽��ֵ
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
  public void  writeRecord(String key,String message){//key���Թ��ļ�����message�Ǽ�¼��ͼ����ʱ�䡢�û������ַ���
     File f=new File("Ӣ�۰�.txt");
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

/*StringTokenizer��
         public StringTokenizer(String str,
         String delim)Ϊָ���ַ�������һ�� string tokenizer��delim �����е��ַ����Ƿָ���ǵķָ������ָ����ַ�������Ϊ��ǡ� 
ע�⣬��� delim Ϊ null����˹��췽�����׳��쳣�����ǣ����ԶԵõ��� StringTokenizer ������������������׳� NullPointerException�� 


������
str - Ҫ�������ַ�����
delim - �ָ�����*/
           fenxi1.nextToken();//nextToken() ���ش� string tokenizer ����һ����ǡ�
               n1=Integer.parseInt(fenxi1.nextToken()); 
           fenxi2.nextToken();
               n2=Integer.parseInt(fenxi2.nextToken());     
           fenxi3.nextToken();
               n3=Integer.parseInt(fenxi3.nextToken());   	 
          if(time<n1)

{            a=temp2;temp2=temp1;temp3=a; 
        	  hashtable.put(key+"1",message);//��ָ�� key ӳ�䵽�˹�ϣ���е�ָ�� value
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
 hashtable.put(key+"2",message);//��ָ�� key ӳ�䵽�˹�ϣ���е�ָ�� value
     hashtable.put(key+"3",temp3);
                FileOutputStream out=new FileOutputStream(f);
                ObjectOutputStream object_out=new ObjectOutputStream(out);
                object_out.writeObject(hashtable);
                object_out.close();
                out.close();
              
          }


        else if(time<n3)
{               hashtable.put(key+"3",message);//��ָ�� key ӳ�䵽�˹�ϣ���е�ָ�� value
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
