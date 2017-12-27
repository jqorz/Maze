class MazePoint {
    private int x, y; //�ֱ��ʾ��������ϵ�е�x�������y������
    private WallOrRoad wallOrRoad = null;//���һ��WallorRoad�Ķ�������ñ���WallorRoad�����ڵ�ǰMazePoint������

    MazePoint(int x, int y) {//���췽������������MazePoint����
        this.x = x;
        this.y = y;
    }


    int getX() {//�������е�x����
        return x;
    }

    int getY() {
        return y;
    }

    boolean equals(MazePoint p) {//�жϵ�ǰMazePoint�����Ƿ�Ͳ���ָ����MazePoint������ͬ
        //���MazePoint�Ķ���p��x��y�������淵�ص�x��y����ͬ���򷵻�true,����false
        return p.getX() == this.getX() && p.getY() == this.getY();
    }

    WallOrRoad getWallOrRoad() {
        return wallOrRoad;
    }

    void setWallOrRoad(WallOrRoad obj) {//������ָ����WallOrRoad������ڵ�ǰMazePoint������
        wallOrRoad = obj;
    }
}
