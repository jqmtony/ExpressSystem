package presentation.right.accountant;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import po.Workers.CarPO;
import presentation.right.RightAll;
import presentation.watcher.*;

public class AccountantManage extends RightAll implements ActionListener {
	int frameWidth;
	int frameHeight;
	private List<Watcher> list;

	DefaultTableModel model;
	JTable table;
	JScrollPane js;
	JButton jb[];

	JPanel addpanel;
	JTextField addjtf[];
	JLabel addlable[];
	JButton addover;

	JPanel changepanel;
	JTextField changejtf[];
	JLabel changelable[];
	JButton changeover;
	
	//修改界面待完成
	JPanel searchpanel;
	JTextField searchjtf[];
	JLabel searchlable[];
	JButton searchover;

	public AccountantManage(int frameWidth, int frameHeight) {

		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		list = new ArrayList<Watcher>();
		this.setLayout(null);
		this.setBounds(frameWidth / 4, 0, frameWidth * 3 / 4, frameHeight);

		model = new DefaultTableModel();
		table = new JTable(model);
		js = new JScrollPane(table);
		jb = new JButton[4];
		for (int i = 0; i < 4; i++) {
			jb[i] = new JButton();
		}

		init();

		this.add(js);
		for (int i = 0; i < 4; i++) {
			this.add(jb[i]);
		}
	}

	private void addAddPanel() {
		addpanel = new JPanel();
		addpanel.setLayout(null);
		addpanel.setBounds(frameWidth / 16, frameHeight / 5 * 3,
				frameWidth / 5 * 3, frameHeight / 10);
		addlable = new JLabel[2];
		addjtf = new JTextField[2];
		for (int i = 0; i < 2; i++) {
			addlable[i] = new JLabel();
			addjtf[i] = new JTextField();
		}
		addover = new JButton("√");
		addlable[0].setText("名称");
		addlable[1].setText("金额");

		for (int i = 0; i < 2; i++) {
			addlable[i].setBounds(frameWidth / 10 * i, 0, frameWidth / 28 * 3,
					frameHeight / 20);
			addjtf[i].setBounds(frameWidth / 10 * i, frameHeight / 20,
					frameWidth / 28 * 3, frameHeight / 20);

			addpanel.add(addlable[i]);
			addpanel.add(addjtf[i]);
		}
		addover.setBounds(frameWidth / 2, frameHeight / 20, frameWidth / 20,
				frameHeight / 20);
		addover.addActionListener(this);

		addpanel.add(addover);
		this.add(addpanel);
		this.repaint();

	}

	private void addChangePanel(Vector<String> vec) {
		changepanel = new JPanel();
		changepanel.setLayout(null);
		changepanel.setBounds(frameWidth / 16, frameHeight / 5 * 3,
				frameWidth / 5 * 3, frameHeight / 10);
		changelable = new JLabel[2];
		changejtf = new JTextField[2];
		for (int i = 0; i < 2; i++) {
			changelable[i] = new JLabel();
			changejtf[i] = new JTextField();
		}
		changeover = new JButton("√");
		changelable[0].setText("名称");
		changelable[1].setText("金额");

		for (int i = 0; i < 2; i++) {
			changelable[i].setBounds(frameWidth / 10 * i, 0,
					frameWidth / 28 * 3, frameHeight / 20);
			changejtf[i].setBounds(frameWidth / 10 * i, frameHeight / 20,
					frameWidth / 28 * 3, frameHeight / 20);

			changejtf[i].setText(vec.get(i));

			changepanel.add(changelable[i]);
			changepanel.add(changejtf[i]);
		}
		changeover.setBounds(frameWidth / 2, frameHeight / 20, frameWidth / 20,
				frameHeight / 20);
		changeover.addActionListener(this);

		changepanel.add(changeover);
		this.add(changepanel);
		this.repaint();
	}

	private void init() {
		js.setBounds(frameWidth / 16, frameHeight / 10, frameWidth / 8 * 5,
				frameHeight / 2);
		initTable();
		jb[0].setText("增加");
		jb[1].setText("删除");
		jb[2].setText("修改");
		jb[3].setText("查询");
		for (int i = 0; i < 4; i++) {
			jb[i].setBounds(frameWidth / 10 + frameWidth / 20 * 3 * i,
					frameHeight / 10 * 9, frameWidth / 10, frameHeight / 20);
			jb[i].addActionListener(this);
		}
	}

	private void initTable() {

		model.addColumn("名称");
		model.addColumn("金额");
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		initTableModel();
	}

	private void initTableModel() {

		Vector<String> vec = new Vector<>();
		vec.add("人民银行");
		vec.add("100,000");

		model.addRow(vec);
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

		if (e.getSource() == jb[0]) {
			if (changepanel != null) {
				this.remove(changepanel);
				this.repaint();
			}
			addAddPanel();
		} else if (e.getSource() == jb[1]) {
			if (addpanel != null) {
				this.remove(addpanel);
				this.repaint();
			}
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				model.removeRow(selectedRow);
			}
		} else if (e.getSource() == jb[2]) {
			if (addpanel != null) {
				this.remove(addpanel);
				this.repaint();
			}
			if (changepanel != null) {
				this.remove(changepanel);
				this.repaint();
			}
			// 改
			int row = table.getSelectedRow();
			if (row >= 0) {
				Vector<String> vec = new Vector<String>();
				vec.add(table.getValueAt(row, 0).toString());
				vec.add(table.getValueAt(row, 1).toString());
				addChangePanel(vec);
				model.removeRow(row);
			}

		}

		// 增加成功
		if (e.getSource() == addover) {		 
			Vector<String> vec = new Vector<>();
			vec.add(addjtf[0].getText());
			vec.add(addjtf[1].getText());

			model.addRow(vec);
			
			this.remove(addpanel);
			this.repaint();
		}

		if (e.getSource() == changeover) {
			Vector<String> vec = new Vector<>();
			vec.add(changejtf[0].getText());
			vec.add(changejtf[1].getText());

			model.addRow(vec);
			
			this.remove(changepanel);
			this.repaint();

		}

	}
}
