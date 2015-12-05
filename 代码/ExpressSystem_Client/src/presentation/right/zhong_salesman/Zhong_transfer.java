package presentation.right.zhong_salesman;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import presentation.right.RightAll;
import presentation.watcher.State;
import presentation.watcher.Watched;
import presentation.watcher.Watcher;

public class Zhong_transfer extends RightAll implements ActionListener {

	int frameWidth;
	int frameHeight;
	JLabel jl[];
	JTextField jtf[];
	JButton add;
	JButton confirm;
	JButton cancel;
	DefaultTableModel tableModel;
	JTable jtable;
	JScrollPane js;
	JLabel time[];
	JComboBox<String>[] timeInput;
	private List<Watcher> list;

	public Zhong_transfer(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;

		list = new ArrayList<Watcher>();

		this.setLayout(null);
		this.setBounds(frameWidth / 4, 0, frameWidth * 3 / 4, frameHeight);

		jl = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			jl[i] = new JLabel();
		}
		add = new JButton("添加");
		confirm = new JButton("确认");
		cancel = new JButton("取消");
		jtf = new JTextField[5];
		for (int i = 0; i < 5; i++) {
			jtf[i] = new JTextField();
		}
		tableModel = new DefaultTableModel();
		jtable = new JTable(tableModel);
		js = new JScrollPane(jtable);
		time = new JLabel[3];
		timeInput = new JComboBox[3];
		for (int i = 0; i < 3; i++) {
			time[i] = new JLabel();
		}
		String[] year = { "2015", "2016", "2017", "2018", "2019", "2020" };
		String[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12" };
		String[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31" };
		timeInput[0] = new JComboBox<String>(year);
		timeInput[1] = new JComboBox<String>(month);
		timeInput[2] = new JComboBox<String>(day);

		init();

		for (int i = 0; i < 6; i++) {
			this.add(jl[i]);
		}
		this.add(add);
		this.add(confirm);
		this.add(cancel);
		for (int i = 0; i < 5; i++) {
			this.add(jtf[i]);
		}
		this.add(js);
		for (int i = 0; i < 3; i++) {
			this.add(timeInput[i]);
			this.add(time[i]);
		}
	}

	private void init() {
		jl[0].setText("中转单编号");
		jl[1].setText("装车日期");
		jl[2].setText("航班号");
		jl[3].setText("到达地");
		jl[4].setText("监运员");
		jl[5].setText("托运单号");
		for (int i = 0; i < 6; i++) {
			jl[i].setBounds(frameWidth / 10,  frameHeight
					/ 10 * (i+1), frameWidth / 10, frameHeight / 20);
		}

		add.setBounds(frameWidth / 2, frameHeight / 5 * 3, frameWidth / 10,
				frameHeight / 20);
		add.addActionListener(this);
		confirm.setBounds(frameWidth / 6, frameHeight * 9 / 10,
				frameWidth / 10, frameHeight / 20);
		cancel.setBounds(frameWidth * 2 / 5, frameHeight * 9 / 10,
				frameWidth / 10, frameHeight / 20);
		cancel.addActionListener(this);

		jtf[0].setBounds(frameWidth / 4, frameHeight / 10,frameWidth / 10,
				frameHeight / 20);
		jtf[1].setBounds(frameWidth / 4, frameHeight / 10 + frameHeight / 10
				* 2, frameWidth / 10, frameHeight / 20);
		jtf[2].setBounds(frameWidth / 4, frameHeight / 10 + frameHeight / 10
				* 3, frameWidth / 10, frameHeight / 20);
		jtf[3].setBounds(frameWidth / 4, frameHeight / 10 + frameHeight / 10
				* 4, frameWidth / 10, frameHeight / 20);
		jtf[4].setBounds(frameWidth / 4, frameHeight / 10 + frameHeight / 10
				* 5, frameWidth / 10, frameHeight / 20);
		time[0].setText("年");
		time[1].setText("月");
		time[2].setText("日");
		for (int i = 0; i < 3; i++) {
			timeInput[i].setBounds(frameWidth / 4 + frameWidth / 10 * i,
					frameHeight / 5, frameWidth / 12, frameHeight / 20);
			time[i].setBounds(frameWidth / 3+ frameWidth / 10 * i,
					frameHeight / 5, frameWidth / 12, frameHeight / 20);
		}

		initTable();

		js.setBounds(frameWidth / 4, frameHeight / 10 * 7, frameWidth / 4,
				frameHeight / 5);
	}

	private void initTable() {
		tableModel.addColumn("已有单号列表");
		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.getTableHeader().setResizingAllowed(false);
		initTableModel();
	}

	private void initTableModel() {
		Vector<String> vec = new Vector<>();
		vec.add("12345666");

		// 初始化已有单号列表
		tableModel.addRow(vec);
	}

	public void addWatcher(Watcher watcher) {
		list.add(watcher);
	}

	public void removeWatcehr(Watcher watcher) {
		list.remove(watcher);
	}

	public void notifyWatchers(State state) {
		for (Watcher watcher : list) {
			watcher.update(state);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			this.notifyWatchers(State.ZHONG_START);
		}

		if (e.getSource() == add) {
			// 添加单号列表
			String input = jtf[4].getText();
			if (!input.equals("")) {
				Vector<String> vec = new Vector<>();
				vec.add(input);
				tableModel.addRow(vec);
				jtf[4].setText("");
			}
		}
	}
}
