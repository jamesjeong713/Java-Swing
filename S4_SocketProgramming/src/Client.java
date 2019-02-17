import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Client 
{
  public static void main(String[] args)
  {
    ClientChatUI chatUI = new ClientChatUI("John and Seongyeop's ClientChatUI");
    
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        chatUI.setMinimumSize(new Dimension(588, 500));
        chatUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatUI.setLocationByPlatform(true);
        chatUI.setLocationRelativeTo(null);
        chatUI.setResizable(false);

        chatUI.setVisible(true);
      }
    });
  }
}
