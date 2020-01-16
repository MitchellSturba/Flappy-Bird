import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        Main main = Main.mainz;
        super.paintComponent(g);
        g.setFont(new Font("SF Atarian System",Font.PLAIN, 30));
        try {
            g.drawImage(main.background,0,0,1100,600,null);
            //paints the bird
            g.drawImage(main.bird,main.player.x - 18,main.player.y - 18,80,80,null);
            for (Pipes x: main.thepipes) {
                g.setColor(Color.black);
               g.drawImage(main.pipebotom,x.topPipe.x,x.topPipe.y,100,- 400,null);
               g.drawImage(main.pipebotom,x.bottomPipe.x,x.bottomPipe.y,100,450,null);
//                g.fillOval(x.topPipe.x,x.topPipe.y,10,10);
//                g.fillOval(x.bottomPipe.x,x.bottomPipe.y,10,10);
            }
            g.setColor(Color.BLACK);
            g.drawString("score: " + main.score, 9,36);
            g.setColor(Color.white);
            g.drawString("score: " + main.score, 10,35);
//            g.fillRect(main.player.x,main.player.y,10,10);
            if (main.over) {
                g.drawImage(main.dead,main.player.x - 18,main.player.y - 18,80,80,null);
                g.drawImage(main.gameover,0,50,800,500,null);
                g.setFont(new Font("SF Atarian System",Font.PLAIN, 55));
                g.setColor(Color.black);
                g.drawString("High Score: " + main.highscore, 278,366);
                g.setColor(Color.white);
                g.drawString("High Score: " + main.highscore, 280,365);
            }
        }catch (Exception r) {

        }
    }
}
