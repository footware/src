import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class Avenge extends JFrame{
	
	//定数
	public static final int F_WIDTH = 600;
	public static final int F_HEIGHT = 800;
	
	int val = 0;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Avenge mainFrame = new Avenge();
		mainFrame.setVisible(true);
		//Oracle JDBC Driverのロード
		Class.forName("com.mysql.cj.jdbc.Driver");
		//データベース接続
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.3:3306/test?autoReconnect=true&useSSL=false", "avenge", "ghost2580");
		Statement st = con.createStatement();
		int x = st.executeUpdate("insert into test(num) values(2)");
		ResultSet rs = st.executeQuery("select * from test");
		
		while (rs.next()){
			int num = rs.getInt(1);
			System.out.println(num);
		}
		rs.close();
		st.close();
		con.close();
	}
	
	Avenge(){
		//ウィンドウの初期設定
		setTitle("test");
		setSize(F_WIDTH, F_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SpringLayout layout = new SpringLayout();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(layout);
		mainPanel.setBackground(Color.BLACK);

		JButton testButton = new JButton(String.valueOf(val));
		testButton.setFont(new Font("メイリオ", Font.PLAIN, 30));
		layout.putConstraint(SpringLayout.NORTH, testButton, 350, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.WEST, testButton, 250, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, testButton, -250, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, testButton, -350, SpringLayout.SOUTH, mainPanel);
		
		testButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						val++;
						testButton.setText(String.valueOf(val));
					}
				}
		);
		
		mainPanel.add(testButton);
		
		
		add(mainPanel);
	}

}
