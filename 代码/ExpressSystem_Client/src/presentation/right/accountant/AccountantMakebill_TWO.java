package presentation.right.accountant;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import businesslogic.accountSetbl.BankSetterImpl;
import businesslogicservice.accountSetblservice.bankSetter;
import presentation.right.ColorRenderer;
import presentation.right.RightAll;
import presentation.watcher.*;
import vo.BankVO;

public class AccountantMakebill_TWO extends RightAll implements ActionListener {
	bankSetter blServer;
	
	int frameWidth;
	int frameHeight;
	private List<Watcher> list;
	
	DefaultTableCellRenderer dtc;

	JButton jb[];
	JButton allover;

	DefaultTableModel model;
	JTable table;
	JScrollPane js;

	JPanel addpanel;
	JTextField addjtf[];
	JLabel addlable[];
	JButton addover;

	JPanel changepanel;
	JTextField changejtf[];
	JLabel changelable[];
	JButton changeover;

	public AccountantMakebill_TWO(int frameWidth, int frameHeight) {
		blServer=new BankSetterImpl();
		
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		list = new ArrayList<Watcher>();
		this.setLayout(null);
		this.setBounds(frameWidth / 4, 0, frameWidth * 3 / 4, frameHeight);

		model = new DefaultTableModel();
		table = new JTable(model){ public boolean isCellEditable(int row, int column) { return false; }}; 
		js = new JScrollPane(table);

		jb = new JButton[3];
		for (int i = 0; i < 3; i++) {
			jb[i] = new JButton();
		}
		allover = new JButton("");//建账完成
		
		dtc=new ColorRenderer();

		init();

		this.add(js);
		for (int i = 0; i < 3; i++) {
			this.add(jb[i]);
		}
		this.add(allover);
	}
	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("pictures\\系统管理startRight.png");
		Image bg =background.getImage();
		g.drawImage(bg, 0, 0,frameWidth*3/4,frameHeight,null);
	}
	

	private void init() {
		js.setBounds(0, frameHeight / 10, frameWidth / 4 * 3,
				frameHeight / 5 * 3);
		initTable();
		jb[0].setText("");//增加
		jb[1].setText("");//删除
		jb[2].setText("");//修改
		for (int i = 0; i < 3; i++) {
			jb[i].setBounds(frameWidth / 8 + frameWidth / 5 * i,
					frameHeight / 40, frameWidth / 10, frameHeight / 20);
			jb[i].addActionListener(this);
		}
		
		ImageIcon icon2 = new ImageIcon("pictures//增加黄.png");
		Image temp2 = icon2.getImage().getScaledInstance(icon2.getIconWidth(),
				icon2.getIconHeight(), icon2.getImage().SCALE_DEFAULT);
		icon2 = new ImageIcon(temp2);
		jb[0].setIcon(icon2);
		
		ImageIcon icon3 = new ImageIcon("pictures//删除.png");
		Image temp3 = icon3.getImage().getScaledInstance(icon3.getIconWidth(),
				icon3.getIconHeight(), icon3.getImage().SCALE_DEFAULT);
		icon3= new ImageIcon(temp3);
		jb[1].setIcon(icon3);
		
		ImageIcon icon4 = new ImageIcon("pictures//修改浅.png");
		Image temp4 = icon4.getImage().getScaledInstance(icon4.getIconWidth(),
				icon4.getIconHeight(), icon4.getImage().SCALE_DEFAULT);
		icon4 = new ImageIcon(temp4);
		jb[2].setIcon(icon4);
		
		allover.setBounds(frameWidth / 40 * 13, frameHeight / 10 * 9,
				frameWidth / 10, frameHeight / 20);
		
		ImageIcon icon1 = new ImageIcon("pictures//完成.png");
		Image temp1 = icon1.getImage().getScaledInstance(icon1.getIconWidth(),
				icon1.getIconHeight(), icon1.getImage().SCALE_DEFAULT);
		icon1 = new ImageIcon(temp1);
		allover.setIcon(icon1);
		allover.addActionListener(this);
		
	}

	private void initTable() {
		model.addColumn("名称");
		model.addColumn("金额");
		 table.setFont(new Font("宋体",Font.PLAIN,13));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.getColumnModel().getColumn(0).setCellRenderer(dtc);
		table.getColumnModel().getColumn(1).setCellRenderer(dtc);
		
		initTableModel();

	}
	
	private void initTableModel(){
		Iterator<BankVO> volist=blServer.getInform();
	 
		while(volist.hasNext()){
			BankVO vo=volist.next();
			String name=vo.getName();
		    String money=vo.getMoney();
			
			Vector<String> vec=new Vector<>();
			vec.add(name);
			vec.add(money);
			model.addRow(vec);
		}
		
		
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

	private void addChangePanel(Vector<String> vec) {
		changepanel = new JPanel();
		changepanel.setLayout(null);
		changepanel.setBounds(0, frameHeight / 10 * 7, frameWidth / 4 * 3,
				frameHeight / 10);
		changelable = new JLabel[2];
		changejtf = new JTextField[2];
		for (int i = 0; i < 2; i++) {
			changelable[i] = new JLabel();
			changejtf[i] = new JTextField();
		}
		changejtf[1].setEditable(false);
		changeover = new JButton("");//√
		changelable[0].setText("名称");
		changelable[1].setText("金额 ");

		for (int i = 0; i < 2; i++) {
			changelable[i].setBounds(frameWidth / 10 * i, 0,
					frameWidth / 28 * 3, frameHeight / 20);
			changejtf[i].setBounds(frameWidth / 10 * i, frameHeight / 20,
					frameWidth / 28 * 3, frameHeight / 20);

			changejtf[i].setText(vec.get(i));

			changepanel.add(changelable[i]);
			changepanel.add(changejtf[i]);
		}
		changeover.setBounds(frameWidth / 10 * 7+frameWidth/65, frameHeight / 20,
				frameHeight/ 20, frameHeight / 20);
		changeover.addActionListener(this);

		ImageIcon icon4 = new ImageIcon("pictures//勾.png");
		Image temp4 = icon4.getImage().getScaledInstance(changeover.getWidth(),
				changeover.getHeight(), icon4.getImage().SCALE_DEFAULT);
		icon4 = new ImageIcon(temp4);
		changeover.setIcon(icon4);

		changepanel.add(changeover);
		this.add(changepanel);
		this.repaint();
	}

	private void addAddPanel() {
		addpanel = new JPanel();
		addpanel.setLayout(null);
		addpanel.setBounds(0, frameHeight / 10 * 7, frameWidth / 4 * 3,
				frameHeight / 10);
		addlable = new JLabel[2];
		addjtf = new JTextField[2];
		for (int i = 0; i < 2; i++) {
			addlable[i] = new JLabel();
			addjtf[i] = new JTextField();
		}
		addover = new JButton("");//√
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
		addover.setBounds(frameWidth / 10 * 7+frameWidth/65, frameHeight / 20,
				frameHeight / 20, frameHeight / 20);
		
		ImageIcon icon4 = new ImageIcon("pictures//勾.png");
		Image temp4 = icon4.getImage().getScaledInstance(addover.getWidth(),
				addover.getHeight(), icon4.getImage().SCALE_DEFAULT);
		icon4 = new ImageIcon(temp4);
		addover.setIcon(icon4);
		
		addover.addActionListener(this);

		addpanel.add(addover);
		this.add(addpanel);
		this.repaint();

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

				vec.add((String) (table.getValueAt(row, 0)));
				vec.add((String) (table.getValueAt(row, 1)));

				addChangePanel(vec);
				model.removeRow(row);
			}

		}

		if (e.getSource() == addover) {
			this.remove(addpanel);
			this.repaint();

			Vector<String> vec = new Vector<>();
			for (int i = 0; i < 2; i++) {
				vec.add(addjtf[i].getText());
			}

			model.addRow(vec);
		}

		if (e.getSource() == changeover) {
			this.remove(changepanel);
			this.repaint();

			Vector<String> vec = new Vector<>();
			for (int i = 0; i < 2; i++) {
				vec.add(changejtf[i].getText());
			}
			
			model.addRow(vec);
		}

		
		if (e.getSource() == allover) {
		 
		   System.out.println("All over");
		   int row=table.getRowCount();
		   
		   for(int i=0;i<row;i++){
			   String name=table.getValueAt(i, 0).toString();
			   String money=table.getValueAt(i, 1).toString();
			   
			   
			   BankVO result=blServer.addInform(new BankVO(name, money));
		   }
		   
		}
	}

}
