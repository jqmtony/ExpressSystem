package presentation.ip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import presentation.MainFrame;
import client.RMIHelper;

public class ClientIP extends JFrame {

	JPanel jp;
	JLabel jl;
	JTextField jtf[];
	JButton jb;
	JLabel jl2;
	JTextField jtf2;

	RemindIP remind;
	JPanel jp_remind;
	boolean ip_right = true;

	public ClientIP() {

		jp = new JPanel();
		jl = new JLabel("IP地址：");
		jtf = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			jtf[i] = new JTextField();
		}
		jb = new JButton("连接");
		jl2 = new JLabel("端口：");
		jtf2 = new JTextField();

		init();
		this.add(jp);
		this.setSize(300, 500);
		this.setLocation(200, 100);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		jp.setBounds(0, 0, 300, 500);
		jp.setLayout(null);

		jl.setBounds(25, 100, 50, 25);
		for (int i = 0; i < 4; i++) {
			jtf[i].setBounds(100 + 35 * i, 100, 33, 25);
			jtf[i].addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (!Character.isDigit(e.getKeyChar())) {
						e.consume();
					}
				}

			});
		}

		jtf[0].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
					jtf[1].requestFocus();
				}
			}
		});
		jtf[1].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
					jtf[2].requestFocus();
				}
			}
		});
		jtf[2].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
					jtf[3].requestFocus();
				}
			}
		});
		jtf[3].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					jtf[2].requestFocus();
				}
			}
		});
		jtf[2].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					jtf[1].requestFocus();
				}
			}
		});
		jtf[1].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					jtf[0].requestFocus();
				}
			}
		});
		jb.setBounds(110, 300, 80, 30);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jb) {

					String ip = "";
					for (int i = 0; i < 4; i++) {
						if (i != 3)
							ip += (jtf[i].getText() + ".");
						else
							ip += jtf[i].getText();
					}

					System.out.println("开启ip");
					System.out.println("IP:" + ip);

					// 根据连接情况设置是否正确
					ip_right = true;

					if (ip_right) {
						closeFrame();
						RMIHelper.init();

						try {
							org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper
									.launchBeautyEyeLNF();

						} catch (Exception e1) {
							// TODO exception
						}

						// 这个包只能在下面的线程里改变他的frame属性，不然会报错

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								MainFrame m = new MainFrame();
								m.getRootPane().setWindowDecorationStyle(
										JRootPane.NONE);
								// m.setUndecorated(true);
								// 用来消除上面的属性栏

							}
						});
					} else {
						jp_remind = new JPanel();
						remind = new RemindIP(jp_remind, "IP错误");
						jp.add(jp_remind);
						remind.start();
					}

				}
			}
		});

		jl2.setBounds(25, 200, 50, 25);
		jtf2.setBounds(100, 200, 100, 25);
		jtf2.setEditable(false);

		jp.add(jl);
		for (int i = 0; i < 4; i++) {
			jp.add(jtf[i]);
		}
		jp.add(jb);
		jp.add(jl2);
		jp.add(jtf2);

	}

	private void closeFrame() {
		this.dispose();
	}

	public static void main(String[] arg0) {

		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e1) {
			// TODO exception
		}

		ClientIP client = new ClientIP();
	 
	}

}
