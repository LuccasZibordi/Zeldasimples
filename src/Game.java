import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;  //port de partes importantes
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {


    public static int width= 480, height= 480; //definindo o tamanho da janela do jogo
    public Player player;
    public World wolrd;



    public Game() {
            this.addKeyListener(this);
            this.setPreferredSize(new Dimension(width,height));

            player = new Player(32,32);
            wolrd = new World();

    }




    public void tick(){
        player.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs ==null){
            this.createBufferStrategy(3);
            return;
        }
        /*verifica se pode renderizar, e tenta otimizar o código*/

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0,0,width,height);


    player.render(g);
    wolrd.render(g);

    bs.show();


    }



    public static void main(String[] args){ /*por fim o começo do código executável*/
        Game game= new Game();
        JFrame frame  = new JFrame(); /*criando um objeto "game" da classe "Game" e um objeto
        "frame" da classe "JFrame", sendo o JFrame uma propriedade do java referente
        a uma janela extra*/



        frame.add(game);
        frame.setTitle("The Legend of the Forgotten King");
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }


    @Override
    public void run() {

        while(true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            /*System.out.println("Chamando game looping!"); /*Setando uma referencia para o funcionamento
             de um game loop*/
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            player.right=true;
        }
        else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            player.left=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            player.up=true;
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN){
            player.down=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            player.right=false;
        }
        else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            player.left=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            player.up=false;
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN){
            player.down=false;
        }
    }
}