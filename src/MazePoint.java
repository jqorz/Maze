public class MazePoint{
    int x,y; //�ֱ��ʾ��������ϵ�е�x�������y������
    boolean haveWallOrRoad;                 
    WallOrRoad wallOrRoad=null;//���һ��WallorRoad�Ķ�������ñ���WallorRoad�����ڵ�ǰMazePoint������
    public MazePoint(int x,int y){//���췽������������MazePoint����
        this.x=x;
        this.y=y;
    }
    public boolean isHaveWallOrRoad(){//�÷�������һ��boolean���ݣ�haveWallOrRoad���滹�Ǽ�
        return haveWallOrRoad;
    }
    public void setIsWallOrRoad(boolean boo){
        haveWallOrRoad=boo;
    }
    public int getX(){//�������е�x����
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean equals(MazePoint p){//�жϵ�ǰMazePoint�����Ƿ�Ͳ���ָ����MazePoint������ͬ
        if(p.getX()==this.getX()&&p.getY()==this.getY())//���MazePoint�Ķ���p��x��y�������淵�ص�x��y����ͬ���򷵻�true,����false
           return true;
        else
           return false; 
    }
    public void setWallOrRoad(WallOrRoad obj){//������ָ����WallOrRoad������ڵ�ǰMazePoint������
        wallOrRoad=obj;
    }
    public WallOrRoad getWallOrRoad(){
        return wallOrRoad;
    }
}
