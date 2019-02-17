import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class ClientChatUI extends JFrame implements Accessible {

  /**
   * 
   */
  private static final long serialVersionUID = 4825180218508220458L;

  private JTextField messageField;
  private JTextField hostField;
  private JButton sendButton;
  private JButton connectButton;
  private JComboBox<String> ports;
  private JTextArea displayArea;
  private ObjectOutputStream outputStream;
  private Socket socket;
  private ConnectionWrapper connection;

  public ClientChatUI(String title) {
    this.setTitle(title);
    this.runClient();
  }

  public JPanel createClientUI() {
    Controller handler = new Controller();
    ///////// Create Panels////////////////
    JPanel mainPanel = new JPanel();
    JPanel connectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel messagePanel = new JPanel();
    JPanel chatPanel = new JPanel(new BorderLayout());

    //////// Set Dimensions////////////////
    connectionPanel.setPreferredSize(new Dimension(588, 110));
    messagePanel.setPreferredSize(new Dimension(588, 70));
    chatPanel.setPreferredSize(new Dimension(588, 280));

    ///////// Set Borders//////////////////
    int borderThickness = 10;
    connectionPanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(borderThickness,
            borderThickness, borderThickness, borderThickness, Color.RED), "CONNECTION"));
    messagePanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(borderThickness,
            borderThickness, borderThickness, borderThickness, Color.BLACK), "MESSAGE"));
    chatPanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(borderThickness,
            borderThickness, borderThickness, borderThickness, Color.BLUE), "CHAT DISPLAY"));
    TitledBorder b = (TitledBorder) chatPanel.getBorder();
    b.setTitleJustification(TitledBorder.CENTER);

    //////// Create Connection UI//////////
    JLabel hostLabel = new JLabel("Host:");
    hostLabel.setPreferredSize(new Dimension(35, 30));
    hostLabel.setDisplayedMnemonic(KeyEvent.VK_H);
    hostLabel.setToolTipText("Host (Alt H)");

    JLabel portLabel = new JLabel("Port:");
    portLabel.setPreferredSize(new Dimension(35, 30));
    portLabel.setDisplayedMnemonic(KeyEvent.VK_P);
    portLabel.setToolTipText("Host (Alt P)");

    hostField = new JTextField();
    hostField.setBackground(Color.WHITE);
    hostField.setEditable(true);
    hostField.setHorizontalAlignment(SwingUtilities.LEFT);
    hostField.requestFocus();
    hostField.setText("localhost");
    hostField.setMargin(new Insets(0, 5, 0, 0));
    hostField.setCaretPosition(0); // |localhost
    hostField.setPreferredSize(new Dimension(515, hostField.getPreferredSize().height));
    hostLabel.setLabelFor(hostField);
    hostLabel.setToolTipText("Host (Alt H)");

    ports = new JComboBox<>();
    ports.setBackground(Color.WHITE);
    ports.setEditable(true);
    ports.setPreferredSize(new Dimension(100, hostField.getPreferredSize().height));
    portLabel.setLabelFor(ports);
    ports.setToolTipText("Host (Alt P)");
    ports.addActionListener(handler);
    ports.setMaximumSize(ports.getPreferredSize());

    ports.addItem("");
    ports.addItem("8089");
    ports.addItem("65000");
    ports.addItem("65535");

    connectButton = new JButton("Connect");
    connectButton.setMnemonic('C');
    connectButton.setToolTipText("Connect (Alt C)");
    connectButton.setPreferredSize(
        new Dimension(ports.getPreferredSize().width, ports.getPreferredSize().height));
    connectButton.setBackground(Color.RED);
    connectButton.addActionListener(handler);

    //////// Create Message UI/////////////
    messageField = new JTextField();
    messageField.setBackground(Color.WHITE);
    messageField.setEditable(true);
    messageField.setHorizontalAlignment(SwingUtilities.LEFT);
    messageField.setPreferredSize(new Dimension(460, messageField.getPreferredSize().height));
    messageField.setText("Type message");

    sendButton = new JButton("Send");
    sendButton.setPreferredSize(new Dimension(90, messageField.getPreferredSize().height));
    sendButton.setMnemonic('S');
    sendButton.setToolTipText("Send (Alt S)");
    sendButton.setEnabled(false);
    sendButton.addActionListener(handler);

    //////// Create Display UI/////////////
    displayArea = new JTextArea(30, 45);
    displayArea.setEditable(false);
    JScrollPane scrollablePane = new JScrollPane(displayArea);
    scrollablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    ////// Add components to panels////////
    // Connection panel
    connectionPanel.add(hostLabel);
    connectionPanel.add(hostField);
    connectionPanel.add(portLabel);
    connectionPanel.add(ports);
    connectionPanel.add(connectButton);
    // Message panel
    messagePanel.add(messageField);
    messagePanel.add(sendButton);
    // Chat panel
    chatPanel.add(scrollablePane);
    ////// Add panels to main panel////////
    mainPanel.add(connectionPanel);
    mainPanel.add(messagePanel);
    mainPanel.add(chatPanel);

    return mainPanel;
  }

  private void runClient() {
    this.addWindowListener(new WindowController());
    setContentPane(createClientUI());
  }

  private ClientChatUI getInstance() {
    return this;
  }

  private class WindowController extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      super.windowClosing(e);
      try {
        if(socket != null && !socket.isClosed())
        outputStream.writeObject(ChatProtocolConstants.CHAT_TERMINATOR);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      finally
      {
        System.exit(0);
      }
    }
  }
  
  private void send()
  {
    String sendMessage = messageField.getText() + ChatProtocolConstants.LINE_TERMINATOR;
    getDisplay().append(sendMessage);
    try {
      if(socket != null && socket.isClosed())
      outputStream.writeObject(ChatProtocolConstants.DISPLACMENT + sendMessage +ChatProtocolConstants.LINE_TERMINATOR);
      outputStream.flush();
    } catch (IOException e) {
      enableConnectButton();
      getDisplay().append(e.getMessage());
    }
  }

  private class Controller implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      boolean connected = false;
      int port = 65535;
      if (e.getSource() == connectButton) {
        String host = hostField.getText();
        try
        {
        port = Integer.parseInt(ports.getSelectedItem().toString());
        }
        catch (Exception e1) {
          getDisplay().append(e1.getMessage());
        }
        connected = connect(host, port);
        if (connected) {
          connectButton.setEnabled(false);
          connectButton.setBackground(Color.BLUE);
          sendButton.setEnabled(true);
          messageField.requestFocus();

          Runnable runnable = new ChatRunnable<ClientChatUI>(getInstance(), connection);
          Thread thread = new Thread(runnable);
          thread.start();
        } else
          return;
      }
      if (e.getSource() == sendButton) {
        send();
      }
    }

    boolean connect(String host, int port) {
      try {
        socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getByName(host), port), 60000);
        socket.setSoTimeout(60000);
        if (socket.getSoLinger() != -1)
          socket.setSoLinger(true, 5);
        if (!socket.getTcpNoDelay())
          socket.setTcpNoDelay(true);

        getDisplay().append("Connected to Socket[addr=" + socket.getInetAddress() + ", port="
            + socket.getPort() + ", localport=" + socket.getLocalPort() + "]\n");
        
        connection = new ConnectionWrapper(socket);
        connection.createStreams();
        outputStream = connection.getOutputStream();
        return true;
        
      } catch (UnknownHostException e) {
        getDisplay().append("ERROR: Unkown host.\n");
        return false;
      } catch (IllegalArgumentException e) {
        getDisplay().append(
            "ERROR: Connection refused: server is not avaliable. Check port or restart server.\n");
        return false;
      } catch (IOException e) {
        getDisplay().append(
            "ERROR: Connection refused: server is not avaliable. Check port or restart server.\n");
        return false;
      }
    }

  }

  @Override
  public JTextArea getDisplay() {
    return displayArea;
  }

  @Override
  public void closeChat() {
    if (!socket.isClosed()) {
      try {
        connection.closeConnection();
      } catch (IOException e) {
        e.printStackTrace();
      }
      enableConnectButton();
    }
  }

  private void enableConnectButton() {
    connectButton.setEnabled(true);
    connectButton.setBackground(Color.RED);
    sendButton.setEnabled(false);
    hostField.requestFocus();
  }
}


