import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ChatRunnable<T extends JFrame & Accessible> implements Runnable {

  final T ui;
  final Socket socket;
  final ObjectInputStream inputStream;
  final ObjectOutputStream outputStream;
  final JTextArea display;

  ChatRunnable(T ui, ConnectionWrapper connection) {
    this.ui = ui;
    this.socket = connection.getSocket();
    this.inputStream = connection.getInputStream();
    this.outputStream = connection.getOutputStream();
    this.display = ui.getDisplay();
  }

  @Override
  public void run() {
    String strin = "";
    while (true) {
      if (socket != null && !socket.isClosed()) {
        try {
          if (socket != null && !socket.isClosed() && inputStream != null)
            strin = (String) inputStream.readObject();
        }
        catch (SocketException | EOFException | SocketTimeoutException e) {
          break;
        } catch (ClassNotFoundException | IOException exception) {
          exception.printStackTrace();
          break;
        }
          LocalDateTime localDateTime = LocalDateTime.now();
          DateTimeFormatter dt_formatter = DateTimeFormatter.ofPattern("MMMM dd, HH:mm a");
          String currentTime = dt_formatter.format(localDateTime);

          if (strin.trim().equals(ChatProtocolConstants.CHAT_TERMINATOR)) {
            final String terminate = ChatProtocolConstants.DISPLACMENT + currentTime + ChatProtocolConstants.LINE_TERMINATOR + strin;
            display.append(terminate);
            break;
          } 
          else 
          {
              final String append = ChatProtocolConstants.DISPLACMENT + currentTime + ChatProtocolConstants.LINE_TERMINATOR + strin;
              display.append(append);
          }
      } 
      else
        break;
    }

    if (socket != null && outputStream != null && !socket.isClosed()) {
      try {
        outputStream.writeBytes(ChatProtocolConstants.DISPLACMENT
            + ChatProtocolConstants.CHAT_TERMINATOR 
            + ChatProtocolConstants.LINE_TERMINATOR);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    ui.closeChat();
  }
}
