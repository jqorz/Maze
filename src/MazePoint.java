class MazePoint {
    private int x, y; //分别表示容器坐标系中的x轴坐标和y轴坐标
    private WallOrRoad wallOrRoad = null;//存放一个WallorRoad的对象的引用表明WallorRoad对象在当前MazePoint对象上

    MazePoint(int x, int y) {//构造方法，用来创建MazePoint对象
        this.x = x;
        this.y = y;
    }


    int getX() {//返回其中的x坐标
        return x;
    }

    int getY() {
        return y;
    }

    boolean equals(MazePoint p) {//判断当前MazePoint对象是否和参数指定的MazePoint对象相同
        //如果MazePoint的对象p的x，y，与上面返回的x，y，相同，则返回true,否则false
        return p.getX() == this.getX() && p.getY() == this.getY();
    }

    WallOrRoad getWallOrRoad() {
        return wallOrRoad;
    }

    void setWallOrRoad(WallOrRoad obj) {//将参数指定的WallOrRoad对象放在当前MazePoint对象上
        wallOrRoad = obj;
    }
}
