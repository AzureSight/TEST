package frontend;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.User;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.UnlockFrame;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

public class Login extends JFrame {

	private Image img_Logo = new ImageIcon(Login.class.getResource("resources/Logo.png")).getImage()
			.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	private Image img_LoginBG = new ImageIcon(Login.class.getResource("resources/LoginBG.png")).getImage()
			.getScaledInstance(1284, 740, Image.SCALE_SMOOTH);
	private Image img_Username = new ImageIcon(Login.class.getResource("resources/User.png")).getImage()
			.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_Password = new ImageIcon(Login.class.getResource("resources/Password.png")).getImage()
			.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

	private JPanel pnlContent;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblMessage;
	private JLabel lblLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login(User user) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1284, 740);

		pnlContent = new JPanel();
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		RoundedClearpnl containerRegister = new RoundedClearpnl(20, 3, new Color(238, 237, 237),
				new Color(238, 237, 237));
		containerRegister.setBackground(new Color(238, 237, 237));
		containerRegister.setBounds(247, 195, 790, 350);
		pnlContent.add(containerRegister);
		containerRegister.setLayout(null);

		lblUsername = new JLabel();
		lblUsername.setText("Username");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblUsername.setBounds(33, 75, 79, 20);

		containerRegister.add(lblUsername);

		RoundedSearchPnl pnlUsername = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlUsername.setLayout(null);
		pnlUsername.setBounds(33, 95, 307, 40);
		containerRegister.add(pnlUsername);

		tfUsername = new JTextField();
		tfUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				{
					tfUsername.selectAll();
				}
			}

		});
		tfUsername.setBorder(null);

		tfUsername.setBackground(Color.white);
		tfUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfUsername.setColumns(10);
		tfUsername.setBounds(10, 5, 255, 30);
		pnlUsername.add(tfUsername);

		JLabel iconUser = new JLabel();
		iconUser.setBounds(265, 0, 40, 40);
		iconUser.setHorizontalAlignment(SwingConstants.CENTER);
		iconUser.setIcon(new ImageIcon(img_Username));
		pnlUsername.add(iconUser);

		lblPassword = new JLabel();
		lblPassword.setText("Password");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPassword.setBounds(33, 150, 73, 20);
		containerRegister.add(lblPassword);

		RoundedSearchPnl pnlPassword = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlPassword.setLayout(null);
		pnlPassword.setBounds(33, 170, 307, 40);
		containerRegister.add(pnlPassword);

		tfPassword = new JPasswordField();
		tfPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				tfPassword.setEchoChar('●');

				tfPassword.setFont(new Font("SansSerif", Font.PLAIN, 10));

				tfPassword.selectAll();
			}

		});

		tfPassword.setBorder(null);
		tfPassword.setEchoChar((char) 0);
		tfPassword.setBackground(Color.white);
		tfPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPassword.setColumns(10);
		tfPassword.setBounds(10, 5, 255, 30);
		pnlPassword.add(tfPassword);

		JLabel iconPassword = new JLabel();
		iconPassword.setBounds(265, 0, 40, 40);
		iconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		iconPassword.setIcon(new ImageIcon(img_Password));
		pnlPassword.add(iconPassword);

		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(255, 51, 51));
		lblMessage.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(33, 220, 307, 35);
		containerRegister.add(lblMessage);

		RoundedSubmit btnLogin = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(new Color(240, 240, 240));
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String username = tfUsername.getText(), pass = String.valueOf(tfPassword.getPassword());

				if (username.equals("Username") || username.equals("") || pass.equals("Password")
						|| pass.equals(""))
					lblMessage.setText("Kindly fill the empty fields");
				else {
					if (lck == false) {
						Date date = new Date();
						java.sql.Date curDate = new java.sql.Date(date.getTime());
						User u = new User(username, pass, curDate);
						try {
							boolean log_result = u.login();
							if (log_result == true) {
								if (u.getType().getDesc().equals("Employee")) {
									User.Employee emp = u.new Employee(u.getId(), u.getFname(), u.getLname(),
											u.getType());
									new Home_Frame(emp).setVisible(true);
								} else if (u.getType().getDesc().equals("Manager")) {
									User.Manager mngr = u.new Manager(u.getId(), u.getFname(), u.getLname(),
											u.getType());
									new Home_Frame(mngr).setVisible(true);
								}
								Login.this.dispose();
							} else {
								attempt++;
								if (attempt >= 3) {
									lck = true;
									lblMessage.setText("System locked!");
									if (attempt == 4)
										delay = 120000;
									else if (attempt == 5)
										delay = 180000;
								}
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Something went wrong.\n" + e1.getMessage(),
									"System Login", 1);
							e1.printStackTrace();
						}
					} else if (lck == true && attempt != 6 && !running) {
						running = true;
						new Thread(new Runnable() {

							@Override
							public void run() {
								while (delay > 0) {
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										System.err.println(e);
									}
									lblMessage.setText("System locked for " + (delay / 1000) + "s");
									delay -= 1000;
								}
								running = false;
								lck = false;
								lblMessage.setText("");
							}

						}).start();
					} else if (attempt == 6) {
						new UnlockFrame(Login.this);
					}
				}
			}
		});
		btnLogin.setBounds(43, 265, 287, 40);
		containerRegister.add(btnLogin);
		btnLogin.setLayout(null);

		lblLogin = new JLabel("Sign in");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBackground((new Color(71, 180, 206)));
		lblLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(10, 0, 267, 40);
		btnLogin.add(lblLogin);

		JLabel iconLogo = new JLabel("");
		iconLogo.setBounds(423, 25, 347, 220);
		containerRegister.add(iconLogo);
		iconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		iconLogo.setIcon(new ImageIcon(img_Logo));

		JLabel lblNewLabel = new JLabel("Sign in");
		lblNewLabel.setBackground(new Color(71, 102, 102));
		lblNewLabel.setBounds(33, 15, 163, 45);
		containerRegister.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 35));

		JLabel lblNewLabel_1 = new JLabel("Welcome!");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(423, 245, 347, 30);
		containerRegister.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Event Scheduling\r\n and Party Store \r\nSystem\r\n");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(423, 280, 347, 30);
		containerRegister.add(lblNewLabel_1_1);

		JLabel LoginBG = new JLabel();
		LoginBG.setBounds(0, 30, 1284, 710);
		LoginBG.setIcon(new ImageIcon(img_LoginBG));
		pnlContent.add(LoginBG);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1284, 30);
		panel.setBackground(new Color(238, 237, 237));
		pnlContent.add(panel);
		panel.setLayout(null);

		Design_Close lbl_Close = new Design_Close("Close", 0, this, new Color(238, 237, 237));
		lbl_Close.setBounds(1254, 0, 30, 30);
		panel.add(lbl_Close);
		lbl_Close.setText("");

		Minimize lbl_Minimize = new Minimize("Minimize", 0, this, new Color(238, 237, 237), new Color(227, 244, 244),
				Color.white);
		lbl_Minimize.setText("");
		lbl_Minimize.setBounds(1224, 0, 30, 30);
		pnlContent.setLayout(null);
		panel.add(lbl_Minimize);

		// ------------------------------------------------------------------------------------------------------------------------\\
		setUndecorated(true);
		setLocationRelativeTo(null);

	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setLck(boolean lck) {
		this.lck = lck;
	}

	private int attempt = 0;
	private long delay = 60000;
	private boolean lck = false, running = false;
}