package frontend.UIcomponents;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import backend.User;
import backend.User.Manager;
import frontend.Login;

public class UnlockFrame extends JFrame {

	JButton btnVerify = new JButton("Unlock");

	public UnlockFrame(Login login) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		JPanel container = new JPanel(), fldContainer = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		fldContainer.setLayout(new GridLayout(4, 2, 15, 10));

		JLabel[] lbl = new JLabel[4];
		JTextField[] fld = new JTextField[3];
		lbl[0] = new JLabel("Manager's First Name");
		lbl[1] = new JLabel("Manager's Last Name");
		lbl[2] = new JLabel("Manager's Username");
		lbl[3] = new JLabel("Manager's Password");
		for (int i = 0; i < lbl.length; i++) {
			lbl[i].setPreferredSize(new Dimension(170, 40));
			lbl[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}
		for (int i = 0; i < fld.length; i++) {
			fld[i] = new JTextField();
			fld[i].setPreferredSize(new Dimension(120, 40));
			fld[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}
		fldContainer.add(lbl[0]);
		fldContainer.add(fld[0]);

		fldContainer.add(lbl[1]);
		fldContainer.add(fld[1]);

		fldContainer.add(lbl[2]);
		fldContainer.add(fld[2]);

		fldContainer.add(lbl[3]);
		JPasswordField pfld = new JPasswordField();
		pfld.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		fldContainer.add(pfld);

		btnVerify.setPreferredSize(new Dimension(120, 30));
		btnVerify.setFocusable(false);
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mfname = fld[0].getText(), mlname = fld[1].getText(), muname = fld[2].getText(),
						mpass = String.valueOf(pfld.getPassword());

				if (!mfname.equals("") && !mlname.equals("") && !muname.equals("") && !mpass.equals("")) {
					Manager mngr = new User().new Manager(mfname, mlname, muname, mpass);
					try {
						boolean ver_result = mngr.login();
						if (ver_result == true) {
							login.setDelay(60000);
							login.setAttempt(0);
							login.setLck(false);
							JOptionPane.showMessageDialog(null, "System unlocked!",
									"System lock", 1);
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Access denied!",
									"System lock", 2);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Something went wrong.\n" + e1.getMessage(),
								"System Login", 1);
						e1.printStackTrace();
					}
				}
			}
		});

		container.add(Box.createRigidArea(new Dimension(0, 15)));
		container.add(fldContainer);
		container.add(Box.createRigidArea(new Dimension(0, 15)));
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(btnVerify);
		container.add(btnPanel);

		add(container);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
