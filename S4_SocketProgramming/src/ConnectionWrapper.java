import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionWrapper {
  private ObjectOutputStream outputStream ;
  private ObjectInputStream inputStream ;
  private Socket socket;
  
  ConnectionWrapper(Socket socket)
  {
    this.socket = socket;
  }
  
  Socket getSocket() 
  {
    return this.socket;
  }
  
  ObjectOutputStream getOutputStream() 
  {
    return outputStream;
  }
  
  ObjectInputStream getInputStream()
  {
    return inputStream;
  }
  
  ObjectInputStream createObjectIStreams() throws IOException 
  {
    return inputStream = new ObjectInputStream(socket.getInputStream());
  }
  
  ObjectOutputStream createObjectOStreams() throws IOException 
  {
    outputStream = new ObjectOutputStream(socket.getOutputStream());
    outputStream.flush();
    return outputStream;
  }
  
  void createStreams() throws IOException
  {
    createObjectOStreams();
    createObjectIStreams();
  }
  
  public void closeConnection()throws IOException 
  {
    if(inputStream != null && !socket.isClosed())
    inputStream.close();
    
    if(socket != null && !socket.isClosed() && outputStream != null)
      outputStream.close();
    
    if(socket != null && !socket.isClosed())
    socket.close();
    
    outputStream = null;
    inputStream = null;
    socket = null;
  }
}
