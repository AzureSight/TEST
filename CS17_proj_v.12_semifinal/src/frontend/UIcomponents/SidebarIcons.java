package frontend.UIcomponents;

import frontend.Login;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SidebarIcons {

	public static final Image LOGO = new ImageIcon(Login.class.getResource("resources/Logo.png"))
					.getImage()
					.getScaledInstance(200, 200, Image.SCALE_SMOOTH),
			HOME = new ImageIcon(Login.class.getResource("resources/Home.png"))
					.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH),
			SCHEDULE = new ImageIcon(Login.class.getResource("resources/Schedule.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH),					
			EVENT_PACKAGES = new ImageIcon(Login.class.getResource("resources/EandP.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH),
			INVENTORY = new ImageIcon(Login.class.getResource("resources/Inventory.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH),
			MANAGE_STOCK = new ImageIcon(Login.class.getResource("resources/ManageStock.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH),
			MANAGE_USER = new ImageIcon(Login.class.getResource("resources/ManangeUser.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH),
			TRANSACTION = new ImageIcon(Login.class.getResource("resources/Transaction.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH),
			LOGOUT = new ImageIcon(Login.class.getResource("resources/Logout.png"))
					.getImage()
					.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

}
