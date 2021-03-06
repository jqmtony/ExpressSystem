package presentation.right.accountant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import businesslogic.paymentServer.GetProfitServerImpl;
import businesslogicservice.paymentblservice.GetProfitServer;
import presentation.right.RightAll;
import presentation.right.YearMonthDay;
import presentation.watcher.*;
import vo.paymentbl.ProfitVO;

public class AccountantCost extends RightAll implements ActionListener {
	GetProfitServer blServer;
	ProfitVO result;

	int frameWidth;
	int frameHeight;
	JLabel[] jl;
	JButton back;
	private List<Watcher> list;

	JLabel time[];
	JComboBox<String>[] timeInput;
	JLabel timeover[];
	JComboBox<String>[] timeInputover;
	JButton search;

	JTextField jtf[];

	public AccountantCost(int frameWidth, int frameHeight) {
		blServer = new GetProfitServerImpl();

		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;

		list = new ArrayList<Watcher>();

		this.setLayout(null);
		this.setBounds(frameWidth / 4, 0, frameWidth * 3 / 4, frameHeight);

		jl = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			jl[i] = new JLabel();
		}
		back = new JButton("");//返回

		time = new JLabel[3];
		timeInput = new JComboBox[3];
		timeover = new JLabel[3];
		timeInputover = new JComboBox[3];
		for (int i = 0; i < 3; i++) {
			time[i] = new JLabel();
			timeover[i] = new JLabel();
		}
		YearMonthDay time1 = new YearMonthDay();
		timeInput[0] = time1.getCboYear();
		timeInput[1] = time1.getCboMonth();
		timeInput[2] = time1.getCboDay();
		YearMonthDay time2 = new YearMonthDay();
		timeInputover[0] = time2.getCboYear();
		timeInputover[1] = time2.getCboMonth();
		timeInputover[2] = time2.getCboDay();
		search = new JButton("");//查询

		jtf = new JTextField[3];
		for (int i = 0; i < 3; i++) {
			jtf[i] = new JTextField();
		}

		init();

		for (int i = 0; i < 2; i++) {
			this.add(jl[i]);
		}
		this.add(back);
		for (int i = 0; i < 3; i++) {
			this.add(time[i]);
			this.add(timeInput[i]);
			this.add(timeover[i]);
			this.add(timeInputover[i]);
		}
		this.add(search);
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("pictures\\成本管理right.png");
		Image bg = background.getImage();
		g.drawImage(bg, 0, 0, frameWidth * 3 / 4, frameHeight, null);
	}

	private void init() {

		jl[0].setText("开始时间");
		jl[1].setText("结束时间");
		jl[2].setText("总收入");
		jl[3].setText("总支出");
		jl[4].setText("总利润");

		for (int i = 0; i < 5; i++) {
			jl[i].setBounds(frameWidth / 10, frameHeight / 15 + frameHeight / 8
					* i, frameWidth / 9, frameHeight / 20);
			jl[i].setFont(new Font("宋体", Font.BOLD, 16));
		}

		back.setBounds(frameWidth / 40 * 13, frameHeight / 10 * 9,
				frameWidth / 11, frameHeight / 20);
		back.addActionListener(this);
		


		time[0].setText("年");
		time[1].setText("月");
		time[2].setText("日");
		timeover[0].setText("年");
		timeover[1].setText("月");
		timeover[2].setText("日");
		for (int i = 0; i < 3; i++) {
			timeInput[i].setBounds(frameWidth / 4 + frameWidth / 10 * i,
					frameHeight / 15, frameWidth / 12, frameHeight / 20);
			time[i].setBounds(frameWidth / 3 + frameWidth / 10 * i,
					frameHeight / 15, frameWidth / 12, frameHeight / 20);
			time[i].setFont(new Font("宋体", Font.PLAIN, 14));

			timeInputover[i].setBounds(frameWidth / 4 + frameWidth / 10 * i,
					frameHeight / 15 + frameHeight / 8, frameWidth / 12,
					frameHeight / 20);
			timeover[i].setBounds(frameWidth / 3 + frameWidth / 10 * i,
					frameHeight / 15 + frameHeight / 8, frameWidth / 12,
					frameHeight / 20);
			timeover[i].setFont(new Font("宋体", Font.PLAIN, 14));
		}

		search.setBounds(frameWidth / 8 * 5-frameWidth/26,
				frameHeight / 15 + frameHeight / 8, frameWidth / 11,
				frameHeight / 20);
		search.addActionListener(this);
		
		ImageIcon icon1 = new ImageIcon("pictures//返回小.png");
		Image temp1 = icon1.getImage().getScaledInstance(icon1.getIconWidth(),
				icon1.getIconHeight(), icon1.getImage().SCALE_DEFAULT);
		icon1 = new ImageIcon(temp1);
		back.setIcon(icon1);
		
		ImageIcon icon2 = new ImageIcon("pictures//查询.png");
		Image temp2 = icon2.getImage().getScaledInstance(icon2.getIconWidth(),
				icon2.getIconHeight(), icon2.getImage().SCALE_DEFAULT);
		icon2 = new ImageIcon(temp2);
		search.setIcon(icon2);

		for (int i = 0; i < 3; i++) {
			jtf[i].setBounds(frameWidth / 4, frameHeight / 15 + frameHeight / 8
					* (i + 2), frameWidth / 10, frameHeight / 20);
			jtf[i].setFont(new Font("宋体",Font.PLAIN,14));
		}

		jtf[0].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					jtf[1].requestFocus();
				}
			}
		});
		jtf[1].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					jtf[2].requestFocus();
				}
			}
		});
		jtf[1].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jtf[0].requestFocus();
				}
			}
		});
		jtf[2].addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jtf[1].requestFocus();
				}
			}
		});
	}

	public void addWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		list.add(watcher);
	}

	public void removeWatcehr(Watcher watcher) {
		// TODO Auto-generated method stub
		list.remove(watcher);
	}

	public void notifyWatchers(State state) {
		for (Watcher watcher : list) {
			watcher.update(state);
		}
	}

	private void wrongShow() {
		// 错误处理
		final JLabel remindWrong = new JLabel();
		remindWrong.setBounds(frameWidth * 2 / 8, frameHeight *7 / 20,
				frameWidth / 2, frameHeight / 20);
		remindWrong.setFont(new Font("宋体", Font.BOLD, 20));
		remindWrong.setForeground(Color.red);
		this.add(remindWrong);
		this.repaint();

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// 以下根据错误类型设置文字
				remindWrong.setText(result.getWrongMessage());
				try {
					Thread.sleep(2000);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				remindWrong.setText("");
			}
		});
		t.start();
		// 错误处理结束
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.notifyWatchers(State.ACCOUNTANTCOST);
		}

		if (e.getSource() == search) {
			// 设置总支出，收入，利润，将jtf设为不可编辑
			String year = timeInput[0].getSelectedItem().toString();
			String month = timeInput[1].getSelectedItem().toString();
			String day = timeInput[2].getSelectedItem().toString();

			String start = year + "-" + month + "-" + day;

			String year2 = timeInputover[0].getSelectedItem().toString();
			String month2 = timeInputover[1].getSelectedItem().toString();
			String day2 = timeInputover[2].getSelectedItem().toString();

			String end = year2 + "-" + month2 + "-" + day2;

			result = blServer.getProfit(start, end);

			if (result.isWrong()) {
				wrongShow();
			} else {
				jtf[0].setText(String.valueOf(result.getInput()) + " 元");
				jtf[1].setText(String.valueOf(result.getOutput()) + " 元");
				jtf[2].setText(String.valueOf(result.getProfit()) + " 元");

				this.remove(search);
				for (int i = 0; i < 3; i++) {
					this.add(jtf[i]);
					this.add(jl[i + 2]);
					jtf[i].setEditable(false);
				}

				this.repaint();
			}

		}
	}
}
