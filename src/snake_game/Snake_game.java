
package snake_game;

import javax.swing.*;
public class Snake_game  extends JFrame{
    
    Snake_game()
    {
        super("Snake Game");
        //add function will add the whatever content wrote in the board class
      add( new Board());
        //pack() will refresh the frame each time .to reflect the changes which we made.
        pack();
        
        
        
        
          //set height and width
      //  setSize(300,300);
        
        //set the frame at the center
        setLocationRelativeTo(null);
       setResizable(false);
     //set x and y axis
        //setLocation(100,100);
      


        //display the frame
        
    }
    public static void main(String[] args) {
        new Snake_game().setVisible(true);
        
    }
    
}
