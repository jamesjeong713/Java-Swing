import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import javax.management.openmbean.OpenDataException;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * File name: ChatRunnable.java
 * Author: John dobie(040659609), Seongyeop Jeong(040885882) 
 * Course: CST8221 JAP, Lab Section: 302
 * Assignment: 2
 * Date: December 7th, 2018
 * Professor: Daniel Cormier
 * Purpose: This is to create a runnable thread so that those will work together through threads 
 *          
 * Class list: ChatRunnable
 */

/**
 * This class is to run the chat with stream and socket which is from the conection wrapper 
 * @author John dobie(040659609), Seongyeop Jeong(040885882) 
 * @version 1.0
 * @see ChatRunnable
 * @since 1.8.0_181
 */
public class ChatRunnable<T extends JFrame & Accessible> implements Runnable {

  final T ui;
  final Socket socket;
  final ObjectInputStream inputStream;
  final ObjectOutputStream outputStream;
  final JTextArea display;

  /**
   * it is for default constructor of the ChatRunnable
   * final members have to initialize.
   * @param ui is to initialize generic T which is inherited from JFrame and Accessible
   * @param connection is to get the object from ConnectionWrapper with getter methods 
   *                   to initialize socket, inputStream, outputStream
   */
  ChatRunnable(T ui, ConnectionWrapper connection) {
    this.ui = ui;
    this.socket = connection.getSocket();
    this.inputStream = connection.getInputStream();
    this.outputStream = connection.getOutputStream();
    this.display = ui.getDisplay();
  }

  @Override
  public void run() {
    String strin = ""; // it is to hold the input streams
    while (true) {
      if (!socket.isClosed()) {
        try {
          if (outputStream != null && inputStream != null && socket != null && !socket.isClosed())
            strin = (String) inputStream.readObject();


          LocalDateTime localDateTime = LocalDateTime.now();
          DateTimeFormatter dt_formatter = DateTimeFormatter.ofPattern("MMMM dd, HH:mm a");
          String currentTime = dt_formatter.format(localDateTime);

          if (strin.trim().equals(ChatProtocolConstants.CHAT_TERMINATOR)) {
            final String terminate = ChatProtocolConstants.DISPLACMENT + currentTime
                + ChatProtocolConstants.LINE_TERMINATOR + strin;
            display.append(terminate);
            break;
          } else {
            {
              final String append = ChatProtocolConstants.DISPLACMENT + currentTime
                  + ChatProtocolConstants.LINE_TERMINATOR + strin;
              display.append(append);
            }
          }
        } catch (EOFException | SocketTimeoutException e) {
          break;
        } catch (ClassNotFoundException | IOException exception) {
          exception.printStackTrace();
          break;
        }
      } else
        break;
    } // end of the while loop 
    
    // when the loop is finished, write the output stream
    if (!socket.isClosed()) {
      try {
        outputStream.writeBytes(ChatProtocolConstants.DISPLACMENT
            + ChatProtocolConstants.CHAT_TERMINATOR + ChatProtocolConstants.LINE_TERMINATOR);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    ui.closeChat();
  }

}
