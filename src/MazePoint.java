public class MazePoint{
    int x,y; //分别表示容器坐标系中的x轴坐标和y轴坐标
    boolean haveWallOrRoad;                 
    WallOrRoad wallOrRoad=null;//存放一个WallorRoad的对象的引用表明WallorRoad对象在当前MazePoint对象上
    public MazePoint(int x,int y){//构造方法，用来创建MazePoint对象
        this.x=x;
        this.y=y;
    }
    public boolean isHaveWallOrRoad(){//该方法返回一个boolean数据，haveWallOrRoad是真还是假
        return haveWallOrRoad;
    }
    public void setIsWallOrRoad(boolean boo){
        haveWallOrRoad=boo;
    }
    public int getX(){//返回其中的x坐标
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean equals(MazePoint p){//判断当前MazePoint对象是否和参数指定的MazePoint对象相同
        if(p.getX()==this.getX()&&p.getY()==this.getY())//如果MazePoint的对象p的x，y，与上面返回的x，y，相同，则返回true,否则false
           return true;
        else
           return false; 
    }
    public void setWallOrRoad(WallOrRoad obj){//将参数指定的WallOrRoad对象放在当前MazePoint对象上
        wallOrRoad=obj;
    }
    public WallOrRoad getWallOrRoad(){
        return wallOrRoad;
    }
}
