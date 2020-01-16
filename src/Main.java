import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.*;


public class Main implements ActionListener, KeyListener {

    public static Main mainz;
    JFrame jframe = new JFrame("NewtonsGame");
    RenderPanel renderpanel = new RenderPanel();
    public Timer tim = new Timer(10,this);
    int ticks = 0, grav = 2,score = 0, highscore = 0;
    public final int Height = 600, Width = 800;
    public BufferedImage background, bird, pipetop, pipebotom , gameover, dead;
    boolean jump = false, over = false;
    Random r = new Random();

    public LinkedList<Pipes> thepipes = new LinkedList<>();
    public Point player = new Point(100,200);

    public Main() {

        //image importing
        try {
            background = ImageIO.read(getClass().getResource("background.png"));
            bird = ImageIO.read(getClass().getResource("bird.png"));
            pipetop = ImageIO.read(getClass().getResource("pipetop.png"));
            pipebotom = ImageIO.read(getClass().getResource("pipebottom.png"));
            gameover = ImageIO.read(getClass().getResource("GameOver.png"));
            dead = ImageIO.read(getClass().getResource("dead.png"));
        }catch (IOException e) {
        }

        //set up the frame
        jframe.setTitle("Flappy Bird");
        jframe.setSize(Width,Height);
        jframe.setLocationRelativeTo(null);
        renderpanel.setBackground(Color.black);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        jframe.add(renderpanel);
        jframe.setVisible(true);

        //first pipe ready to go
        int height = r.nextInt(200) + 100;
        Pipes newpipe = new Pipes(800,height,800, height + 180, height);
        thepipes.add(newpipe);

        tim.start();
    }

    public static void main(String[] args) {
        mainz = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        renderpanel.repaint();

        //check if game is over
        if (over || player.y >= Height - 65|| player.y <= 0) {
            if (score > highscore) {
                highscore = score;
            }
            over = true;
            grav = 0;
            jump = false;
        }

        //controls the jump
        if (jump && grav < 0) {
            player.y += grav;
            if (ticks % 2 == 0) {
                grav++;
            }
        }else if(grav == 0) {
            jump = false;
        }
        if (ticks % 2 == 0 && !jump) {
            player.y += grav;
            grav++;
        }

        //adds a new pipe
        if (ticks % 200 == 0 && !over) {
            int height = r.nextInt(200) + 100;
            Pipes newpipe = new Pipes(800,height,800, height + 180, height);
            thepipes.add(newpipe);
        }

        //moves every pipe and checks collision
        for (Pipes x: thepipes) {
            if (!over) {
                x.topPipe.x -= 2;
                x.bottomPipe.x -= 2;
            }

            //collision with top pipe
            if (player.x + 35 >= x.topPipe.x && player.x <= x.topPipe.x + 90 && player.y + 2 <= x.topPipe.y) {
                over = true;
            }else if (x.topPipe.x + 100 == 100) {
                score++;
            }
            //collision with bottom pipe
            if (player.x + 35 >= x.bottomPipe.x && player.x <= x.bottomPipe.x + 100 && player.y + 40 >= x.bottomPipe.y) {
                over = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int i = e.getKeyCode();

        if (i == KeyEvent.VK_SPACE) {
            if (!over){
                grav = 0;
                jump = true;
                grav = -10;
            }else {
                thepipes.clear();
                score = 0;
                player.y = 200;
                over = false;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
