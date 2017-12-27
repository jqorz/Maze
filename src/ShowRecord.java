import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

class ShowRecord extends JDialog {
    private File file = new File("英雄榜.txt");
    private JLabel Hero[][];
    private JLabel Hero2[][];
    private JLabel Hero3[][];
    private String mazeName[];

    ShowRecord(int mazeNum) {//frame是MazeWindow的框架，mazeNum是mazeName地图名的长度
        setTitle("迷宫英雄榜");
        setBounds(200, 200, 720, 600);
        setResizable(false);
        setVisible(false);
        setModal(true);// public void setModal(boolean b)指定此 dialog 是否应该是有模式的。
        Hero = new JLabel[mazeNum][3];
        Hero2 = new JLabel[mazeNum][3];
        Hero3 = new JLabel[mazeNum][3];
        for (int i = 0; i < mazeNum; i++) {//创建15*3个标签
            for (int j = 0; j < 3; j++) {
                Hero[i][j] = new JLabel();
                Hero2[i][j] = new JLabel();
                Hero3[i][j] = new JLabel();
            }

        }
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(mazeNum * 3, 3));//设置15*3个网格
        for (int i = 0; i < mazeNum; i++) {
            for (int j = 0; j < 3; j++) {
                pCenter.add(Hero[i][j]);
                pCenter.add(Hero2[i][j]);
                pCenter.add(Hero3[i][j]);
            }


        }
        pCenter.setBorder(BorderFactory.createTitledBorder("第  一  名" + "                                                          第  二  名" + "                                                        第  三  名"));

        add(pCenter, BorderLayout.CENTER);
    }

    void setMazeName(String[] mazeName) {
        this.mazeName = mazeName;
    }


    void readAndShow() {//在MazeWindow的*****处调用
        try {

            FileInputStream in = new FileInputStream(file);/*FileInputStream(File file)
            通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定.
	          */
            ObjectInputStream object_in = new ObjectInputStream(in);//目标输入流
            Hashtable hashtable = (Hashtable) object_in.readObject();
            object_in.close();
            in.close();
            int mazeNum = 6;
            for (int i = 0; i < mazeNum; i++) {//设置前三名成绩
                String temp1 = (String) hashtable.get(mazeName[i] + "1");
                String temp2 = (String) hashtable.get(mazeName[i] + "2");
                String temp3 = (String) hashtable.get(mazeName[i] + "3");
                StringTokenizer fenxi1 = new StringTokenizer(temp1, "#");
                StringTokenizer fenxi2 = new StringTokenizer(temp2, "#");
                StringTokenizer fenxi3 = new StringTokenizer(temp3, "#");

/*StringTokenizer类
public StringTokenizer(String str,
                       String delim)为指定字符串构造一个 string tokenizer。delim 参数中的字符都是分隔标记的分隔符。分隔符字符本身不作为标记。 
注意，如果 delim 为 null，则此构造方法不抛出异常。但是，尝试对得到的 StringTokenizer 调用其他方法则可能抛出 NullPointerException。 


参数：
str - 要分析的字符串。
delim - 分隔符。*/

                while (fenxi1.hasMoreTokens()) {/*hasMoreTokens类
                    public boolean hasMoreTokens()测试此 tokenizer 的字符串中是否还有更多的可用标记。如果此方法返回 true，那么后续调用无参数的 nextToken 方法将成功地返回一个标记。

					返回：
					当且仅当该字符串中当前位置后至少有一个标记时才为 true；否则为 false。
                                                */
                    for (int j = 0; j < 3; j++) {
                        Hero[i][j].setText(fenxi1.nextToken());/*nextToken类
                        public String nextToken()返回此 string tokenizer 的下一个标记。

						返回：
						此 string tokenizer 的下一个标记。 */


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
