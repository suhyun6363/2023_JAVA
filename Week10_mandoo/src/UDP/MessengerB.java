package UDP;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessengerB {
	protected JTextField textField;
	protected JTextArea textArea;
	DatagramSocket socket;
	DatagramPacket packet;
	InetAddress address = null;
	final int myPort = 6000; // 수신용 포트 번호
	final int otherPort = 5000; // 송신용 포트 번호


	public MessengerB() throws IOException {
		MyFrame f = new MyFrame();
		address = InetAddress.getByName("127.0.0.1");
		socket = new DatagramSocket(myPort);
	}

	// 패킷을 받아서 텍스트 영역에 표시한다.
	public void process() {
		while (true) {
			try {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet); // 패킷을 받는다.
				// 받은 패킷을 텍스트 영역에 표시한다.
				textArea.append("RECIEVED: " + new String(buf) + "\n");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	// 내부 클래스 정의
	class MyFrame extends JFrame implements ActionListener {

		public MyFrame() {
			super("MessengerB");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			textField = new JTextField(30);
			textField.addActionListener(this);

			textArea = new JTextArea(10, 30);
			textArea.setEditable(false);

			add(textField, BorderLayout.PAGE_END);
			add(textArea, BorderLayout.CENTER);
			pack();
			setVisible(true);
		}

		public void actionPerformed(ActionEvent evt) {
			String s = textField.getText();
			byte[] buffer = s.getBytes();
			DatagramPacket packet;

			// 패킷을 생성한다.
			packet = new DatagramPacket(buffer, buffer.length, address,
					otherPort);
			try {
				socket.send(packet); // 패킷을 보낸다.
			} catch (IOException e) {
				e.printStackTrace();
			}
			textArea.append("SENT: " + s + "\n");
			textField.selectAll();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}

	public static void main(String[] args) throws IOException {
		MessengerB m = new MessengerB();
		m.process();
	}
}
