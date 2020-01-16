import java.awt.Point;

public class Pipes {

    Point topPipe = new Point(0,0);
    Point bottomPipe = new Point(0,0);
    int height;

    public Pipes(int topx, int topy, int bottomx, int bottomy, int height) {
        topPipe.x = topx;
        topPipe.y = topy;
        bottomPipe.x = bottomx;
        bottomPipe.y = bottomy;
        this.height = height;
    }

}
