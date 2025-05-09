
package snake_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//JPanel is like component
//JPanel is used to divide the frame into parts and assign specified work to each part of the frame
public class Board  extends JPanel implements ActionListener{
    
    private Image apple;
    private Image dot;
    private Image head;
    
    private final int all_dots=900;
    private final int dot_size=10;
    //random position for the apple
    private final int RANDOM_POSITION=29;
    
    private Timer timer;
    
    //initialiing both x and y coordinates to generate a random position
    //single variable becouse game contain single aaple
    private int apple_x;
    private int apple_y;
    
    //taking array becouse snake contaion multiple dots
   private final int x[]=new int[all_dots];  
   private final int y[]=new int[all_dots]; 
   
   private boolean leftDirection=false;
   private boolean rightDirection=true;
   private boolean UpDirection=false;
   private boolean DownDirection=false;
   
   private boolean inGame=true;
   
    
    
    
    //make dots variable univarsal becouse each time dots will increase
    private int dots;
    
    Board()
    {
        
        addKeyListener(new TAdapter());
        
        //to give black color to frame
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
        
        
       loadImages();
        //function to initialize the game
        initGame();
    }
    
    public void loadImages()
    {
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Snake_game/icons/apple.png"));
        apple=i1.getImage();
        
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("Snake_game/icons/dot.png"));
        dot=i2.getImage();
                
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("Snake_game/icons/head.png"));
        head=i3.getImage();
    }
    
    public void initGame()
    {
        dots=3;
        
        for(int i=0;i<dots;i++)
        {
            y[i]=50;
            //each time dot sie will decrese
            x[i]=50-i*dot_size;
        }
        locateApple();
        
     timer=new Timer(140,this);
     timer.start();
    }
    
    public void locateApple()
    {
       //generate random location with respect to both x and y axis 
        int r =(int)(Math.random()*RANDOM_POSITION);
        apple_x=r*dot_size;
        
        r =(int)(Math.random()*RANDOM_POSITION);
        apple_y=r*dot_size;
        
        
    }
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        
        if(inGame){
        g.drawImage(apple,apple_x,apple_y,this);
        
        for(int i=0;i<dots;i++)
        {
            if(i==0)
            {
                g.drawImage(head,x[i],y[i],this);
            }else
            {
                g.drawImage(dot,x[i],y[i],this); 
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        }else
        {
            gameOver(g);
        }
    }
    
    
    public void gameOver(Graphics g)
    {
        String msg="Game Over";
        Font font=new Font("SAR SERIF",Font.BOLD,14);
        FontMetrics metrices=getFontMetrics(font);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        
       g.drawString(msg, (300 - metrices.stringWidth(msg))/2, 300/2);

    }
    
    public void move()
    {
        for(int i=dots;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(leftDirection)
        {
            //while moving to left direction the x coordintes will decrese
            x[0]=x[0]-dot_size;
        }
        if(rightDirection)
        {
            //while moving to right direction the x coordintes will incres
            x[0]=x[0]+dot_size;
        }
         if(UpDirection)
        {
            //while moving to up direction the y coordintes will decrese
            y[0]=y[0]-dot_size;
        }
         if(DownDirection)
        {
            //while moving to down direction the y coordintes will increse
            y[0]=y[0]+dot_size;
        }
        
      
    }
    
    public void checkApple()
    {
        if((x[0]==apple_x)&&y[0]==apple_y)
        {
            //snake will eat the apple
            dots++;
            
            //after eating the apple locate another apple
            locateApple();
            
            
        }
    }
    
    
    public void checkCollision()
    {
        for(int i=dots;i>0;i--)
        {
            if((i>4)&&(x[0]==x[i])&&(y[0]==y[i]))
            {
                inGame=false;
            }
        }
        
        
        if(y[0]>=300)
        {
            inGame=false;
        }
        if(x[0]>=300)
        {
            inGame=false;
        }
        if(y[0]<0)
        {
            inGame=false;
        }
        if(x[0]<0)
        {
            inGame=false;
        }
        
        if(!inGame)
        {
            timer.stop();
        }
    }
    
    //to override the method
    public void  actionPerformed(ActionEvent ac)
    {
        if(inGame){
        checkApple();
        
        checkCollision();
        
        move();
        }
        repaint();
    }
    
    
    public class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key=e.getKeyCode();
            
            if(key==KeyEvent.VK_LEFT &&(!rightDirection))
            {
                leftDirection=true;
                UpDirection=false;
                DownDirection=false;
            }
            
            if(key==KeyEvent.VK_RIGHT &&(!leftDirection))
            {
                
                rightDirection=true;
                UpDirection=false;
                DownDirection=false;
            }
            
            if(key==KeyEvent.VK_UP &&(!DownDirection))
            {
                    UpDirection=true;
                rightDirection=false;
            leftDirection=false;
                
            }
            
             if(key==KeyEvent.VK_DOWN &&(!UpDirection))
            {
                
                 DownDirection=true;
                    
                rightDirection=false;
                leftDirection=false;
            
               
            }
        }
    }
    
}
