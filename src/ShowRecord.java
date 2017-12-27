import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

class ShowRecord extends JDialog {
    private File file = new File("Ӣ�۰�.txt");
    private JLabel Hero[][];
    private JLabel Hero2[][];
    private JLabel Hero3[][];
    private String mazeName[];

    ShowRecord(int mazeNum) {//frame��MazeWindow�Ŀ�ܣ�mazeNum��mazeName��ͼ���ĳ���
        setTitle("�Թ�Ӣ�۰�");
        setBounds(200, 200, 720, 600);
        setResizable(false);
        setVisible(false);
        setModal(true);// public void setModal(boolean b)ָ���� dialog �Ƿ�Ӧ������ģʽ�ġ�
        Hero = new JLabel[mazeNum][3];
        Hero2 = new JLabel[mazeNum][3];
        Hero3 = new JLabel[mazeNum][3];
        for (int i = 0; i < mazeNum; i++) {//����15*3����ǩ
            for (int j = 0; j < 3; j++) {
                Hero[i][j] = new JLabel();
                Hero2[i][j] = new JLabel();
                Hero3[i][j] = new JLabel();
            }

        }
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(mazeNum * 3, 3));//����15*3������
        for (int i = 0; i < mazeNum; i++) {
            for (int j = 0; j < 3; j++) {
                pCenter.add(Hero[i][j]);
                pCenter.add(Hero2[i][j]);
                pCenter.add(Hero3[i][j]);
            }


        }
        pCenter.setBorder(BorderFactory.createTitledBorder("��  һ  ��" + "                                                          ��  ��  ��" + "                                                        ��  ��  ��"));

        add(pCenter, BorderLayout.CENTER);
    }

    void setMazeName(String[] mazeName) {
        this.mazeName = mazeName;
    }


    void readAndShow() {//��MazeWindow��*****������
        try {

            FileInputStream in = new FileInputStream(file);/*FileInputStream(File file)
            ͨ����һ����ʵ���ļ�������������һ�� FileInputStream�����ļ�ͨ���ļ�ϵͳ�е� File ���� file ָ��.
	          */
            ObjectInputStream object_in = new ObjectInputStream(in);//Ŀ��������
            Hashtable hashtable = (Hashtable) object_in.readObject();
            object_in.close();
            in.close();
            int mazeNum = 6;
            for (int i = 0; i < mazeNum; i++) {//����ǰ�����ɼ�
                String temp1 = (String) hashtable.get(mazeName[i] + "1");
                String temp2 = (String) hashtable.get(mazeName[i] + "2");
                String temp3 = (String) hashtable.get(mazeName[i] + "3");
                StringTokenizer fenxi1 = new StringTokenizer(temp1, "#");
                StringTokenizer fenxi2 = new StringTokenizer(temp2, "#");
                StringTokenizer fenxi3 = new StringTokenizer(temp3, "#");

/*StringTokenizer��
public StringTokenizer(String str,
                       String delim)Ϊָ���ַ�������һ�� string tokenizer��delim �����е��ַ����Ƿָ���ǵķָ������ָ����ַ�������Ϊ��ǡ� 
ע�⣬��� delim Ϊ null����˹��췽�����׳��쳣�����ǣ����ԶԵõ��� StringTokenizer ������������������׳� NullPointerException�� 


������
str - Ҫ�������ַ�����
delim - �ָ�����*/

                while (fenxi1.hasMoreTokens()) {/*hasMoreTokens��
                    public boolean hasMoreTokens()���Դ� tokenizer ���ַ������Ƿ��и���Ŀ��ñ�ǡ�����˷������� true����ô���������޲����� nextToken �������ɹ��ط���һ����ǡ�

					���أ�
					���ҽ������ַ����е�ǰλ�ú�������һ�����ʱ��Ϊ true������Ϊ false��
                                                */
                    for (int j = 0; j < 3; j++) {
                        Hero[i][j].setText(fenxi1.nextToken());/*nextToken��
                        public String nextToken()���ش� string tokenizer ����һ����ǡ�

						���أ�
						�� string tokenizer ����һ����ǡ� */


                    }
                }
                while (fenxi2.hasMoreTokens()) {
                    for (int j = 0; j < 3; j++) {
                        Hero2[i][j].setText(fenxi2.nextToken());


                    }
                }
                while (fenxi3.hasMoreTokens()) {
                    for (int j = 0; j < 3; j++) {
                        Hero3[i][j].setText(fenxi3.nextToken());


                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
