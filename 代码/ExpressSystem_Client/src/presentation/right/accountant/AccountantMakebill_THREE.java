package presentation.right.accountant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import businesslogic.constantbl.CityServerImpl;
import businesslogic.informationbl.Inform_CarInformServerImpl;
import businesslogic.informationbl.Inform_HallInformServerImpl;
import businesslogic.informationbl.Inform_StorageInformServerImpl;
import businesslogic.informationbl.Inform_TranStationInformServerImpl;
import businesslogic.systembl.SystemBlServerImpl;
import businesslogicservice.informationblservice.InstitutionInform.Inform_HallInformServer;
import businesslogicservice.informationblservice.InstitutionInform.Inform_StorageInformServer;
import businesslogicservice.informationblservice.InstitutionInform.Inform_TranStationInformServer;
import businesslogicservice.informationblservice.WorkerInform.Inform_CarInformServer;
import businesslogicservice.informationblservice.WorkerInform.Inform_DriverInformServer;
import businesslogicservice.systemblservice.systemServer;
import po.CityPO;
import po.SystemUserPO;
import po.Institution.HallPO;
import po.Institution.StoragePO;
import po.Institution.TranStationPO;
import po.Workers.CarPO;
import po.Workers.HallStaffPO;
import po.Workers.StorageKeeperPO;
import po.Workers.TranStaffPO;
import presentation.right.ColorRenderer;
import presentation.right.RightAll;
import presentation.watcher.*;

public class AccountantMakebill_THREE extends RightAll implements
		ActionListener {
	Inform_HallInformServer hallServer;
	Inform_StorageInformServer storageServer;
	Inform_TranStationInformServer stationServer;

	Inform_CarInformServer carServer;

	int frameWidth;
	int frameHeight;
	private List<Watcher> list;
	int chooseRow;

	DefaultTableCellRenderer dtc;

	JPanel jp1;
	JPanel jp2;
	JPanel jp3;
	JPanel jp4;

	DefaultTableModel model1;
	JTable table1;
	JScrollPane js1;

	// 机构
	DefaultTableModel model2;
	JTable table2;
	JScrollPane js2;
	JButton orgAdd;
	JButton orgDel;
	JTextField jtf;
	JLabel addLabel;
	JButton overButton;

	// 沿用总经理里的命名习惯，注意：以下代表人员
	DefaultTableModel con_tableModel;
	JTable con_table;
	JScrollPane con_js;
	JTextField jtf_people;
	JLabel addLabel_people;
	JButton overButton_people;
	JButton con_orgAdd;
	JButton con_orgDel;

	// 车辆的命名习惯与人员类似
	DefaultTableModel car_tableModel;
	JTable car_table;
	JScrollPane car_js;
	// JTextField jtf_car;
	JTextField jtf_car[];
	JLabel addLabel_car[];
	JButton overButton_car;
	JButton car_orgAdd;
	JButton car_orgDel;

	String chooseCity;
	String chooseOrg;

	public AccountantMakebill_THREE(int frameWidth, int frameHeight) {
		
		hallServer = new Inform_HallInformServerImpl();
		storageServer = new Inform_StorageInformServerImpl();
		stationServer = new Inform_TranStationInformServerImpl();

		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		list = new ArrayList<Watcher>();
		this.setLayout(null);
		this.setBounds(frameWidth / 4, 0, frameWidth * 3 / 4, frameHeight);

		jp1 = new JPanel();
		jp1.setBackground(new Color(174,205,207));
		model1 = new DefaultTableModel();
		table1 = new JTable(model1) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		js1 = new JScrollPane(table1);
		dtc = new ColorRenderer();

		initJp1();

		jp1.add(js1);
		this.add(jp1);
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("pictures\\系统管理startRight.png");
		Image bg = background.getImage();
		g.drawImage(bg, 0, 0, frameWidth * 3 / 4, frameHeight, null);
	}

	private void initJp1() {
		jp1.setBounds(0, 0, frameWidth / 4, frameHeight);
		jp1.setLayout(null);
		initTable1();
		js1.setBounds(0, 0, frameWidth / 4, frameHeight / 2);
	}

	private void initTableModel1() {
		// 读取城市列表
		CityServerImpl city = new CityServerImpl();
		Iterator<CityPO> list = city.getAll();

		while (list.hasNext()) {
			CityPO po = list.next();
			Vector<String> vec = new Vector<>();
			vec.add(po.getName());
			model1.addRow(vec);
		}

	}

	private void initJp2() {
		if (jp2 != null) {
			this.remove(jp2);
		}

		jp2 = new JPanel();
		jp2.setBounds(frameWidth / 4, 0, frameWidth / 4, frameHeight);
		jp2.setLayout(null);
jp2.setBackground(new Color(174,205,207));
		model2 = new DefaultTableModel();
		table2 = new JTable(model2) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		js2 = new JScrollPane(table2);

		initTable2();

		js2.setBounds(0, 0, frameWidth / 4, frameHeight / 2);

		orgAdd = new JButton("");// 增加
		orgAdd.setBounds(frameWidth / 16, frameHeight / 2 + frameHeight / 8,
				frameWidth / 10, frameHeight / 20);
		orgAdd.addActionListener(this);
		orgDel = new JButton("");// 删除
		orgDel.setBounds(frameWidth / 16, frameHeight / 10 * 9,
				frameWidth / 10, frameHeight / 20);
		orgDel.addActionListener(this);

		ImageIcon icon2 = new ImageIcon("pictures//增加黄.png");
		Image temp2 = icon2.getImage().getScaledInstance(icon2.getIconWidth(),
				icon2.getIconHeight(), icon2.getImage().SCALE_DEFAULT);
		icon2 = new ImageIcon(temp2);
		orgAdd.setIcon(icon2);

		ImageIcon icon3 = new ImageIcon("pictures//删除.png");
		Image temp3 = icon3.getImage().getScaledInstance(icon3.getIconWidth(),
				icon3.getIconHeight(), icon3.getImage().SCALE_DEFAULT);
		icon3 = new ImageIcon(temp3);
		orgDel.setIcon(icon3);

		jp2.add(js2);
		jp2.add(orgAdd);
		jp2.add(orgDel);
		this.add(jp2);
		this.repaint();
	}

	private void initJp3() {
		this.remove(jp1);
		this.remove(jp2);
		
		jp3 = new JPanel();
		jp3.setBounds(0, 0, frameWidth / 4, frameHeight);
		jp3.setLayout(null);
jp3.setBackground(new Color(174,205,207));
		con_tableModel = new DefaultTableModel();
		con_table = new JTable(con_tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		con_tableModel.addColumn("姓名");
		con_tableModel.addColumn("编号");
		con_table.getTableHeader().setReorderingAllowed(false);
		con_table.getTableHeader().setResizingAllowed(false);
		con_table.getColumnModel().getColumn(0).setCellRenderer(dtc);
		con_table.getColumnModel().getColumn(1).setCellRenderer(dtc);
		con_table.setFont(new Font("宋体", Font.PLAIN, 14));
		initTableModel();
		con_js = new JScrollPane(con_table);
		con_js.setBounds(0, 0, frameWidth / 4, frameHeight / 2);

		con_orgAdd = new JButton("");// 增加
		con_orgAdd.setBounds(frameWidth / 16,
				frameHeight / 2 + frameHeight / 8, frameWidth / 10,
				frameHeight / 20);
		con_orgAdd.addActionListener(this);
		con_orgDel = new JButton("");// 删除
		con_orgDel.setBounds(frameWidth / 16, frameHeight / 10 * 9,
				frameWidth / 10, frameHeight / 20);
		con_orgDel.addActionListener(this);

		ImageIcon icon2 = new ImageIcon("pictures//增加黄.png");
		Image temp2 = icon2.getImage().getScaledInstance(icon2.getIconWidth(),
				icon2.getIconHeight(), icon2.getImage().SCALE_DEFAULT);
		icon2 = new ImageIcon(temp2);
		con_orgAdd.setIcon(icon2);

		ImageIcon icon3 = new ImageIcon("pictures//删除.png");
		Image temp3 = icon3.getImage().getScaledInstance(icon3.getIconWidth(),
				icon3.getIconHeight(), icon3.getImage().SCALE_DEFAULT);
		icon3 = new ImageIcon(temp3);
		con_orgDel.setIcon(icon3);

		jp3.add(con_js);
		jp3.add(con_orgAdd);
		jp3.add(con_orgDel);

		this.add(jp3);
		this.repaint();
	}

	private void initJp4() {
		jp4 = new JPanel();
		jp4.setBounds(frameWidth / 4, 0, frameWidth / 2, frameHeight);
		jp4.setLayout(null);
jp4.setBackground(new Color(174,205,207));
		car_tableModel = new DefaultTableModel();
		car_table = new JTable(car_tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		car_js = new JScrollPane(car_table);

		initCarModel();
		car_js.setBounds(0, 0, frameWidth / 2, frameHeight / 2);

		car_orgAdd = new JButton("");// 增加
		car_orgAdd.setBounds(frameWidth * 3 / 16, frameHeight / 2 + frameHeight
				/ 8, frameWidth / 10, frameHeight / 20);
		car_orgAdd.addActionListener(this);
		car_orgDel = new JButton("");// 删除
		car_orgDel.setBounds(frameWidth * 3 / 16, frameHeight / 10 * 9,
				frameWidth / 10, frameHeight / 20);
		car_orgDel.addActionListener(this);

		ImageIcon icon2 = new ImageIcon("pictures//增加黄.png");
		Image temp2 = icon2.getImage().getScaledInstance(icon2.getIconWidth(),
				icon2.getIconHeight(), icon2.getImage().SCALE_DEFAULT);
		icon2 = new ImageIcon(temp2);
		car_orgAdd.setIcon(icon2);

		ImageIcon icon3 = new ImageIcon("pictures//删除.png");
		Image temp3 = icon3.getImage().getScaledInstance(icon3.getIconWidth(),
				icon3.getIconHeight(), icon3.getImage().SCALE_DEFAULT);
		icon3 = new ImageIcon(temp3);
		car_orgDel.setIcon(icon3);

		jp4.add(car_js);
		jp4.add(car_orgAdd);
		jp4.add(car_orgDel);
		this.add(jp4);
		this.repaint();
	}

	private void initTable1() {
		model1.addColumn("城市");
		table1.getTableHeader().setReorderingAllowed(false);
		table1.getTableHeader().setResizingAllowed(false);
		table1.getColumnModel().getColumn(0).setCellRenderer(dtc);
		table1.setFont(new Font("宋体", Font.PLAIN, 14));
		table1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				chooseRow = table1.getSelectedRow();
				chooseCity = model1.getValueAt(chooseRow, 0).toString();
				initJp2();

			}
		});
		initTableModel1();
	}

	private void initTable2() {
		model2.addColumn("机构");
		model2.addColumn("编号");
		table2.getTableHeader().setReorderingAllowed(false);
		table2.getTableHeader().setResizingAllowed(false);
		table2.getColumnModel().getColumn(0).setCellRenderer(dtc);
		table2.getColumnModel().getColumn(1).setCellRenderer(dtc);
		table2.getColumnModel().getColumn(0).setPreferredWidth(frameWidth/6);
		table2.setFont(new Font("宋体", Font.PLAIN, 14));
		table2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				chooseRow = table2.getSelectedRow();
				chooseOrg = table2.getValueAt(chooseRow, 1).toString();

				initJp3();
				
				// 如果是营业厅，使用下面的方法
				if(chooseRow>=2){
					initJp4();
				}
				//use if...
				
			}
		});
		initTableModel2();
	}
	
	private void initCarModell(){
		System.out.println(chooseOrg);
		carServer=new Inform_CarInformServerImpl(chooseOrg);
	    Iterator<CarPO> list=carServer.getAllCar(chooseOrg);
	    while(list.hasNext()){
	    	CarPO car = list.next();

			Vector<String> vec = new Vector<>();
			vec.add(car.getId());
			vec.add("未保存");
			vec.add(car.getChePai());
			vec.add("未保存");
			vec.add(car.getUsingTime());
			vec.add(car.getUsingTime());
			vec.add("未保存");

			car_tableModel.addRow(vec);
	    }
	}

	private void initCarModel() {
		
		car_tableModel.addColumn("车辆代号");
		car_tableModel.addColumn("发动机号");
		car_tableModel.addColumn("车辆号");
		car_tableModel.addColumn("地盘号");
		car_tableModel.addColumn("购买时间");
		car_tableModel.addColumn("服役时间");
		car_tableModel.addColumn("车辆描述");
		car_table.getTableHeader().setReorderingAllowed(false);
		car_table.getTableHeader().setResizingAllowed(false);
		car_table.getColumnModel().getColumn(0).setCellRenderer(dtc);
		car_table.getColumnModel().getColumn(1).setCellRenderer(dtc);
		car_table.getColumnModel().getColumn(2).setCellRenderer(dtc);
		car_table.getColumnModel().getColumn(3).setCellRenderer(dtc);
		car_table.getColumnModel().getColumn(4).setCellRenderer(dtc);
		car_table.getColumnModel().getColumn(5).setCellRenderer(dtc);
		car_table.getColumnModel().getColumn(6).setCellRenderer(dtc);
		car_table.setFont(new Font("宋体", Font.PLAIN, 14));
		car_table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				addPanel_Car();
			}
		});
		
		initCarModell();
	}

	private void initTableModel() {
		Iterator it;
		if(chooseRow==0){
			//中转中心
			TranStationPO station=stationServer.getByLocation(chooseCity).next();
			it=station.getAllStaff().iterator();
			while(it.hasNext()){
				Vector<String> vec = new Vector<>();
				
				TranStaffPO po=(TranStaffPO) it.next();

				vec.add(po.getName());
				vec.add(po.getId());

				con_tableModel.addRow(vec);
			}
		}else if(chooseRow==1){
			//中转中心仓库
			StoragePO storage=storageServer.getByLocation(chooseCity).next();
			it=storage.getAllKeeper().iterator();
			while(it.hasNext()){
				Vector<String> vec = new Vector<>();
				
				StorageKeeperPO po=(StorageKeeperPO) it.next();

				vec.add(po.getName());
				vec.add(po.getID());

				con_tableModel.addRow(vec);
			}
		}else{
			System.out.println(chooseRow);
		    HallPO hall=hallServer.getHall(chooseOrg);
		    it=hall.getAllStaff().iterator();
		    while(it.hasNext()){
				Vector<String> vec = new Vector<>();
				
				HallStaffPO po=(HallStaffPO) it.next();

				vec.add(po.getName());
				vec.add(po.getId());

				con_tableModel.addRow(vec);
			}
		}
		
		
	

	}

	private void initTableModel2() {
		Iterator<TranStationPO> stationlist = stationServer
				.getByLocation(chooseCity);
		while (stationlist.hasNext()) {

			TranStationPO po = stationlist.next();
			Vector<String> vec = new Vector<>();
			vec.add(po.getName());
			vec.add(po.getID());
			model2.addRow(vec);
		}

		Iterator<StoragePO> storageList = storageServer
				.getByLocation(chooseCity);
		while (storageList.hasNext()) {

			StoragePO po = storageList.next();
			Vector<String> vec = new Vector<>();
			vec.add(po.getName());
			vec.add(po.getID());
			model2.addRow(vec);
		}

		Iterator<HallPO> list = hallServer.getByLocation(chooseCity);
		System.out.println(chooseCity);
		while (list.hasNext()) {

			HallPO po = list.next();
			Vector<String> vec = new Vector<>();
			vec.add(po.getName());
			vec.add(po.getID());
			model2.addRow(vec);
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

	public void actionPerformed(ActionEvent e) {

		// 机构
		if (e.getSource() == orgAdd) {
			
			addPanel();
		} else if (e.getSource() == orgDel) {
			int row = table2.getSelectedRow();
			if (row >= 0) {
				model2.removeRow(row);
			}
		}

		// 人员
		if (e.getSource() == con_orgAdd) {
			addPanel_People();
		} else if (e.getSource() == con_orgDel) {
			int row = con_table.getSelectedRow();
			if (row >= 0) {
				con_tableModel.removeRow(row);
			}
		}

		// 车辆
		if (e.getSource() == car_orgAdd) {
			addPanel_Car();
		} else if (e.getSource() == car_orgDel) {
			int row = con_table.getSelectedRow();
			if (row >= 0) {
				car_tableModel.removeRow(row);
			}
		}
	}

	private void addPanel_Car() {
		if (jtf_car == null) {
			this.repaint();
			this.jtf_car = new JTextField[7];
			for (int i = 0; i < 7; i++) {
				jtf_car[i] = new JTextField();
			}
			this.addLabel_car = new JLabel[7];
			for (int i = 0; i < 7; i++) {
				addLabel_car[i] = new JLabel();
			}
			addLabel_car[0].setText("车辆代号");
			addLabel_car[1].setText("发动机号");
			addLabel_car[2].setText("车辆号");
			addLabel_car[3].setText("底盘号");
			addLabel_car[4].setText("购买时间");
			addLabel_car[5].setText("服役时间");
			addLabel_car[6].setText("车辆描述");
			this.overButton_car = new JButton("");// 完成
			for (int i = 0; i < 7; i++) {
				jtf_car[i].setBounds(frameWidth / 14 * i, frameHeight / 2
						+ frameHeight / 4, frameWidth / 14, frameHeight / 20);
			}
			for (int i = 0; i < 7; i++) {
				addLabel_car[i].setBounds(frameWidth / 14 * i, frameHeight / 2
						+ frameHeight / 5, frameWidth / 14, frameHeight / 20);
			}
			overButton_car.setBounds(frameWidth / 16 * 3, frameHeight / 20 * 17
					- frameWidth / 40, frameWidth / 10, frameHeight / 20);

			ImageIcon icon2 = new ImageIcon("pictures//完成.png");
			Image temp2 = icon2.getImage().getScaledInstance(
					icon2.getIconWidth(), icon2.getIconHeight(),
					icon2.getImage().SCALE_DEFAULT);
			icon2 = new ImageIcon(temp2);
			overButton_car.setIcon(icon2);

			overButton_car.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Vector<String> vec = new Vector<>();
					for (int i = 0; i < 7; i++) {
						vec.add(jtf_car[i].getText());
					}
					
					car_tableModel.addRow(vec);
					carServer=new Inform_CarInformServerImpl(chooseOrg);
					System.out.println(jtf_car==null);
					System.out.println(jtf_car[4].getText());
					carServer.addCar(jtf_car[2].getText(), jtf_car[4].getText());
					removeAddPanel_Car();
				
				}
			});

			for (int i = 0; i < 7; i++) {
				jp4.add(addLabel_car[i]);
				jp4.add(jtf_car[i]);
			}
			jp4.add(overButton_car);
			this.repaint();
		}
	}

	private void removeAddPanel_Car() {
		for (int i = 0; i < 7; i++) {
			jp4.remove(jtf_car[i]);
		}
		for (int i = 0; i < 7; i++) {
			jp4.remove(addLabel_car[i]);
		}
		jp4.remove(overButton_car);

		jtf_car = null;
		addLabel_car = null;
		overButton_car = null;
		this.repaint();
	}

	private void addPanel_People() {
		if (jtf_people == null) {
			this.repaint();
			this.jtf_people = new JTextField();
			this.addLabel_people = new JLabel("请输入员工编号:");
			this.overButton_people = new JButton("");// 完成
			jtf_people.setBounds(frameWidth / 24, frameHeight / 2 + frameHeight
					/ 4, frameWidth / 6, frameHeight / 20);
			addLabel_people.setBounds(frameWidth / 24, frameHeight / 2
					+ frameHeight / 5, frameWidth / 6, frameHeight / 20);
			overButton_people.setBounds(frameWidth / 16, frameHeight / 20 * 17
					- frameWidth / 40, frameWidth / 10, frameHeight / 20);

			ImageIcon icon2 = new ImageIcon("pictures//完成.png");
			Image temp2 = icon2.getImage().getScaledInstance(
					icon2.getIconWidth(), icon2.getIconHeight(),
					icon2.getImage().SCALE_DEFAULT);
			icon2 = new ImageIcon(temp2);
			overButton_people.setIcon(icon2);

			overButton_people.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					
					String input = jtf_people.getText();
					if(chooseRow==0){
						//增加中转中心业务员
						stationServer.addStaff(chooseOrg, input);
						systemServer server=new SystemBlServerImpl();
						SystemUserPO ss=server.inquire(input); 
						
						Vector<String> vec = new Vector<>();
						vec.add(ss.getUserName());
						vec.add(ss.getID());
						con_tableModel.addRow(vec);
						removeAddPanel_People();
					}else if(chooseRow==1){
						//增加中转中心仓库
						storageServer.addKeeper(chooseOrg, input);
						systemServer server=new SystemBlServerImpl();
						SystemUserPO ss=server.inquire(input); 
						
						Vector<String> vec = new Vector<>();
						vec.add(ss.getUserName());
						vec.add(ss.getID());
						con_tableModel.addRow(vec);
						removeAddPanel_People();
					}else{
						//增加营业厅
						hallServer.addStaff(chooseOrg, input);
						systemServer server=new SystemBlServerImpl();
						SystemUserPO ss=server.inquire(input); 
						
						Vector<String> vec = new Vector<>();
						vec.add(ss.getUserName());
						vec.add(ss.getID());
						con_tableModel.addRow(vec);
						removeAddPanel_People();
					}
					

				}
			});

			jp3.add(jtf_people);
			jp3.add(addLabel_people);
			jp3.add(overButton_people);
			this.repaint();
		}
	}

	private void removeAddPanel_People() {
		jp3.remove(jtf_people);
		jp3.remove(addLabel_people);
		jp3.remove(overButton_people);

		jtf_people = null;
		addLabel_people = null;
		overButton_people = null;
		this.repaint();
	}

	private void addPanel() {
		if (jtf == null) {
			this.repaint();
			this.jtf = new JTextField();
			this.addLabel = new JLabel("请输入机构名称:");
			this.overButton = new JButton("");// 完成
			jtf.setBounds(frameWidth / 24, frameHeight / 2 + frameHeight / 4,
					frameWidth / 6, frameHeight / 20);
			addLabel.setBounds(frameWidth / 24, frameHeight / 2 + frameHeight
					/ 5, frameWidth / 6, frameHeight / 20);
			overButton.setBounds(frameWidth / 16, frameHeight / 20 * 17
					- frameWidth / 40, frameWidth / 10, frameHeight / 20);

			ImageIcon icon2 = new ImageIcon("pictures//完成.png");
			Image temp2 = icon2.getImage().getScaledInstance(
					icon2.getIconWidth(), icon2.getIconHeight(),
					icon2.getImage().SCALE_DEFAULT);
			icon2 = new ImageIcon(temp2);
			overButton.setIcon(icon2);

			overButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String input = jtf.getText();
					HallPO po=hallServer.addHall(chooseCity, input);
					if (!input.equals("")) {

						Vector<String> vec = new Vector<>();
						vec.add(input);
						vec.add(po.getID());
						model2.addRow(vec);

						removeAddPanel();
					}
				}
			});

			jp2.add(jtf);
			jp2.add(addLabel);
			jp2.add(overButton);
			this.repaint();
		}
	}

	private void removeAddPanel() {
		jp2.remove(jtf);
		jp2.remove(addLabel);
		jp2.remove(overButton);

		jtf = null;
		addLabel = null;
		overButton = null;
		jp2.repaint();
	}

}