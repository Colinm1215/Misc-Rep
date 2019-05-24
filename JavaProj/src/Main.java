import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;


class Main extends JPanel implements ActionListener, KeyListener {

    private static JPanel panel;
    private File file = null;
    private final JFileChooser choosePlayer = new JFileChooser();
    private long startTime;
    private double timePlayed;
    private Player player;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private ArrayList<Enemy> enemies;
    private int frameCounter;
    private ArrayList<Projectile> projectiles;
    private boolean choosingImg = false;
    private boolean startMenu = true;
    private boolean playing = false;
    private boolean restart = false;
    private final JButton startButton = new JButton("Start");
    private final JButton restartButton = new JButton("Restart");
    private final JButton chImage = new JButton("Upload Image");
    private final JButton deImage = new JButton("Default Image");


     private Main(int w, int h) {
        setSize(w, h);
        addKeyListener(this);
        startButton.setBounds(getWidth() / 2 - 75, getHeight() / 2 + 50, 150, 150);
        restartButton.setBounds(getWidth() / 2 - 75, getHeight() / 2 + 50, 150, 150);
        chImage.setBounds(100, 500, 200, 50);
        deImage.setBounds(500, 500, 200, 50);

        startButton.addActionListener(this);
        startButton.setActionCommand("Start");

        restartButton.addActionListener(this);
        restartButton.setActionCommand("Restart");

        chImage.addActionListener(this);
        chImage.setActionCommand("chooseImg");

        deImage.addActionListener(this);
        deImage.setActionCommand("defImg");
    }

    public static void main(String[] args) {


        JFrame window = new JFrame("Dodge Game - Colin M");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int w = 800;
        int h = 800;
        window.setBounds(0, 0, w, h + 22); //(x, y, w, h) 22 due to title bar.

        panel = new Main(w, h);
        panel.setLayout(null);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
    }

    private void Start(File img) {
        startTime = System.nanoTime();
        playing = true;
        choosingImg = false;
        startMenu = false;
        restart = false;
        if (img == null)
            player = new Player(getWidth() / 2, getHeight() / 2, null);
        else
            player = new Player(getWidth() / 2, getHeight() / 2, img);
        player.score = 0;
        player.health = 100;
        projectiles = new ArrayList<>();
        enemies = new ArrayList<>();  //empty list of enemies!

        Timer timer = new Timer(1000 / 60, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playing) {
            gameCode();
        } else {
            if (e != null) {
                if (e.getActionCommand() != null) {
                    switch (e.getActionCommand()) {
                        case "Start":
                            panel.remove(startButton);
                            choosingImg = true;
                            break;
                        case "Restart":
                            panel.remove(restartButton);
                            playing = false;
                            startMenu = true;
                            break;
                        case "chooseImg":
                            choosePlayer.addChoosableFileFilter(new FileNameExtensionFilter("Images",
                                    "jpg",
                                    "jpeg",
                                    "png",
                                    "gif"));
                            choosePlayer.setAcceptAllFileFilterUsed(false);
                            int returnVal = choosePlayer.showOpenDialog(this);

                            if (returnVal == JFileChooser.APPROVE_OPTION) {
                                file = choosePlayer.getSelectedFile();
                                panel.remove(chImage);
                                panel.remove(deImage);
                                System.out.println("Opening: " + file.getName());
                                Start(file);
                            } else {
                                System.out.println("Open File Canceled");
                            }

                            break;
                        case "defImg":
                            panel.remove(deImage);
                            panel.remove(chImage);
                            Start(file);
                            break;
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    private void gameCode() {
        frameCounter++;
        if (frameCounter % 300 == 0) {
            int randoX = (int) (Math.random() * getWidth());
            int randoY = (int) (Math.random() * getHeight());
            double rand = Math.random();
            if (rand > 0.5)
                enemies.add(new Enemy(randoX, randoY));
            else if (rand >= 0.5 && rand < 0.9)
                enemies.add(new TrackerTurret(randoX, randoY));
            else
                enemies.add(new BurstTurret(randoX, randoY));

        }

        for (Enemy yeeee : enemies)
            yeeee.shoot(player, projectiles);
        ArrayList<Projectile> toAdd = new ArrayList<>();
        for (Projectile p : projectiles) {
            if (p instanceof Burst) {
                p.move(player, toAdd);
            } else if (p instanceof Tracker) {
                p.move(player);
            } else {
                p.move();
            }
        }
        projectiles.addAll(toAdd);
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).getAge() > 60 * 3) {  //too old?
                projectiles.remove(i);
                i--;
            } else if (projectiles.get(i).getAge() > 10) { //projectile is still alive!!!
                //check if this projectile hit any enemies.
                for (int j = 0; j < enemies.size(); j++) {

                    if (enemies.get(j).intersects(projectiles.get(i))) {
                        enemies.remove(j);
                        projectiles.remove(i);
                        player.score += 10;
                        break;
                    }

                    if (player.checkCollide(projectiles.get(i).getX(), projectiles.get(i).getY())) {
                        player.health -= 10;
                        projectiles.remove(i);
                        if (player.health <= 0) {
                            long endTime = System.nanoTime();
                            timePlayed = (endTime - startTime) / 1000000000;
                            playing = false;
                            restart = true;
                        }
                        break;
                    }

                }
            }
        }

        player.move(upPressed, downPressed, rightPressed, leftPressed);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font;

        if (playing) {
            font = new Font("TimesRoman", Font.PLAIN, 28);
            g2.setFont(font);
            g2.drawString(String.format("Health : %d", player.health), 20, 30);
            for (Enemy e : enemies)
                e.draw(g2);
            for (Projectile p : projectiles)
                p.draw(g2);
            player.draw(g2, getWidth(), getHeight());
        } else {
            if (startMenu) {
                if (!choosingImg) {
                    font = new Font("TimesRoman", Font.PLAIN, 60);
                    g2.setFont(font);
                    g2.drawString("Dodge!", getWidth() / 2 - 85, 200);
                    font = new Font("TimesRoman", Font.PLAIN, 50);
                    g2.setFont(font);
                    g2.drawString("The Game", getWidth() / 2 - 105, 300);
                    panel.add(startButton);
                } else {
                    font = new Font("TimesRoman", Font.PLAIN, 60);
                    g2.setFont(font);
                    g2.drawString("Choose a Player Image", getWidth() / 2 - 275, 200);
                    panel.add(chImage);
                    panel.add(deImage);
                }
            } else if (restart) {
                font = new Font("TimesRoman", Font.PLAIN, 60);
                g2.setFont(font);
                g2.drawString("You Died", getWidth() / 2 - 110, 200);
                font = new Font("TimesRoman", Font.PLAIN, 36);
                g2.setFont(font);
                g2.drawString(String.format("Enemies Killed : %d * 10 = %d",
                        player.score / 10, player.score),
                        100, 300);
                g2.drawString(String.format("Time Played : %d * 1 = %d",
                        (int) Math.ceil(timePlayed), (int) Math.ceil(timePlayed)),
                        100, 350);
                g2.drawString(String.format("Total Score : %d",
                        (int) Math.ceil(timePlayed + player.score)),
                        100, 400);
                panel.add(restartButton);
            }
        }
    }
}