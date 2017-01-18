package max.snakegame;

import com.sun.xml.internal.ws.api.server.Adapter;
import objects.Apple;
import objects.Snake;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snakegame extends JPanel implements ActionListener {
    //  private static final long serialVersionUID =  -4201295877166777528L;
    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SPEED = 5;


    Apple a = new Apple((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
    Snake s = new Snake(5, 5, 9, 10);/////!!!!!?????
    Timer t = new Timer(1000 / SPEED, this);//скорость обновления экрана

    public Snakegame() {
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
    }

    public void Stop() {
        t.stop();
    }


    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH * SCALE, (HEIGHT + 1) * SCALE);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH * SCALE + 1, SCALE);

        for (int xx = SCALE; xx <= WIDTH * SCALE; xx += SCALE) {
            g.drawLine(xx, SCALE, xx, (HEIGHT + 1) * SCALE);
        }
        for (int yy = SCALE; yy <= (HEIGHT + 1) * SCALE; yy += SCALE) {
            g.drawLine(0, yy, WIDTH * SCALE, yy);
        }

        for (int d = 0; d < s.length; d++) {
            if (d == 0) {
                g.setColor(Color.BLUE);
                g.fillRect(s.snakeX[d] * SCALE + 1, s.snakeY[d] * SCALE + 1, SCALE - 1, SCALE - 1);
            } else {
                g.setColor(Color.green);
                g.fillRect(s.snakeX[d] * SCALE + 1, s.snakeY[d] * SCALE + 1, SCALE - 1, SCALE - 1);
            }
        }
        g.setColor(Color.RED);
        g.fillRect(a.posX * SCALE + 1, a.posY * SCALE + 1, SCALE - 1, SCALE - 1);

        for (int d = s.length - 1; d > 0; d--) {
            if ((s.snakeX[0] == s.snakeX[d]) & (s.snakeY[0] == s.snakeY[d])) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, WIDTH * SCALE, (HEIGHT + 2) * SCALE);
                g.setColor(Color.RED);
                t.stop();
            }
        }
        if (s.length == 100) {
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, WIDTH * SCALE, (HEIGHT + 30) * SCALE);
            Font font = new Font("TimesRoman", Font.BOLD, 30);
            g.setFont(font);
            g.setColor(Color.blue);
            g.drawString("Поздравляем вы Виграли", 15, 160);
            t.stop();
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(WIDTH*SCALE+7,HEIGHT*SCALE+30);
        f.setLocationRelativeTo(null);
        f.add(new Snakegame());
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        s.move();
        if ((s.snakeX[0] == a.posX) &(s.snakeY[0] == a.posY)){
            a.setRandomPos();
            s.length++;
        }

        for (int k = 1;k < s.length;k++){
             if ((s.snakeX[k] == a.posX) &(s.snakeY[k] == a.posY)){
                 a.setRandomPos();
             }
         }
        if ((s.snakeX[0] == a.posX) &(s.snakeY[0] == a.posY)){
            a.setRandomPos();
            s.length++;
        }
        /* if ((s.snakeX[0] == s.snakeX[1])&&(s.snakeY[1] == a.posY)){
            t.stop();
            JOptionPane.showMessageDialog(null,"Вы проиграли ");
            jFrame.setVisible(true);

        }*/
        repaint();
    }

    private class Keyboard extends KeyAdapter {
        public void keyPressed(KeyEvent kEvt) {
            int key = kEvt.getKeyCode();

            if ((key == KeyEvent.VK_RIGHT) & s.direction != 2) s.direction = 0;
            if ((key == KeyEvent.VK_DOWN) & s.direction != 3) s.direction = 1;
            if ((key == KeyEvent.VK_LEFT) & s.direction != 0) s.direction = 2;
            if ((key == KeyEvent.VK_UP) & s.direction != 1) s.direction = 3;
        }
    }
}
