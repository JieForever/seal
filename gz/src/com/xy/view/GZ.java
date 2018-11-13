/*
 * GZ.java
 *
 * Created on __DATE__, __TIME__
 */

package com.xy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.xy.service.LsjyService;
import com.xy.service.QgdService;
import com.xy.service.RzlsService;
import com.xy.service.some.ConfigService;
import com.xy.utils.CompressedFileFilter;
import com.xy.utils.ExcleFileFilter;
import com.xy.utils.JTextAreaOutputStream;

/**
 * 
 * @author __USER__
 */
public class GZ extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	/** Creates new form GZ */
	public GZ() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		infoJF = new javax.swing.JFrame();
		panel1 = new java.awt.Panel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTree1 = new javax.swing.JTree(createHelpTree());
		jScrollPane3 = new javax.swing.JScrollPane();
		sm_textArea = new javax.swing.JTextArea();
		setupJF = new javax.swing.JFrame();
		jPanel10 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTree2 = new javax.swing.JTree(createConfigTree());
		jPanel8 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		currentModelField = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		R_RGB_jTextField1 = new javax.swing.JTextField();
		jLabel17 = new javax.swing.JLabel();
		B_RGB_jTextField3 = new javax.swing.JTextField();
		jLabel16 = new javax.swing.JLabel();
		G_RGB_jTextField2 = new javax.swing.JTextField();
		colorChooserjButton1 = new javax.swing.JButton();
		hiddenData_jTextField1 = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		apply_jButton1 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		common_msg_area = new javax.swing.JTextArea();
		cls_msg_btn = new javax.swing.JButton();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		qgd_page = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		qgd_ysb_field = new javax.swing.JTextField();
		qgd_exl_field = new javax.swing.JTextField();
		qgd_result_field = new javax.swing.JTextField();
		qgd_ysb_btn = new javax.swing.JButton();
		qgd_exl_btn = new javax.swing.JButton();
		qgd_result_btn = new javax.swing.JButton();
		qgd_reset_btn = new javax.swing.JButton();
		qgd_justDoIt_btn = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		lsjy_ysb_field = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		lsjy_result_field = new javax.swing.JTextField();
		lsjy_ysb_btn = new javax.swing.JButton();
		lsjy_result_btn = new javax.swing.JButton();
		lsjy_justDoIt_btn = new javax.swing.JButton();
		lsjy_reset_btn = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		lsjy_qzexl_field = new javax.swing.JTextField();
		lsjy_qzexl_btn = new javax.swing.JButton();
		jPanel6 = new javax.swing.JPanel();
		jPanel9 = new javax.swing.JPanel();
		jLabel13 = new javax.swing.JLabel();
		rzls_ysb_field3 = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		rzls_result_field3 = new javax.swing.JTextField();
		rzls_ysb_btn3 = new javax.swing.JButton();
		rzls_result_btn3 = new javax.swing.JButton();
		rzls_justDoIt_btn3 = new javax.swing.JButton();
		rzls_reset_btn3 = new javax.swing.JButton();
		jLabel15 = new javax.swing.JLabel();
		rzls_qzexl_field3 = new javax.swing.JTextField();
		rzls_qzexl_btn3 = new javax.swing.JButton();
		jRadioButton1 = new javax.swing.JRadioButton();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem2 = new javax.swing.JMenuItem();
		
		outRedi(common_msg_area);
		infoJF.setTitle("\u76d6\u7ae0\u5c0f\u8f6f\u4ef6-\u5e2e\u52a9");
		infoJF.setIconImage(new ImageIcon(getClass().getResource("/icon.png"))
				.getImage());
		infoJF.setMinimumSize(new java.awt.Dimension(800, 500));

		jScrollPane2.setViewportView(jTree1);

		sm_textArea.setColumns(20);
		sm_textArea.setRows(5);
		jScrollPane3.setViewportView(sm_textArea);

		javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(
				panel1);
		panel1.setLayout(panel1Layout);
		panel1Layout
				.setHorizontalGroup(panel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panel1Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												182,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												617, Short.MAX_VALUE)));
		panel1Layout.setVerticalGroup(panel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane2,
						javax.swing.GroupLayout.DEFAULT_SIZE, 382,
						Short.MAX_VALUE)
				.addComponent(jScrollPane3,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 382,
						Short.MAX_VALUE));

		javax.swing.GroupLayout infoJFLayout = new javax.swing.GroupLayout(
				infoJF.getContentPane());
		infoJF.getContentPane().setLayout(infoJFLayout);
		infoJFLayout.setHorizontalGroup(infoJFLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(panel1,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		infoJFLayout.setVerticalGroup(infoJFLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(panel1,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		setupJF.setTitle("\u76d6\u7ae0\u5c0f\u8f6f\u4ef6-\u8bbe\u7f6e");
		setupJF.setIconImage(new ImageIcon(getClass().getResource("/icon.png"))
				.getImage());
		setupJF.setMinimumSize(new java.awt.Dimension(710, 400));

		jPanel10.setBackground(new java.awt.Color(255, 255, 255));

		jScrollPane4.setViewportView(jTree2);

		jPanel8.setBackground(new java.awt.Color(255, 255, 255));
		jPanel8.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));

		jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		jLabel9.setText("\u5f53\u524d\u6a21\u677f\uff1a");

		

		jLabel8.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		jLabel8.setText("\u6a21\u677f\u9009\u62e9\uff1a");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(getComboV()));
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});

		jLabel11.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		jLabel11.setText("\u5b57\u4f53\u989c\u8272\uff1a");

		jLabel12.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		jLabel12.setText("R:");

		

		jLabel17.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		jLabel17.setText("B:");

		jLabel16.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		jLabel16.setText("G:");

		

		colorChooserjButton1.setFont(new java.awt.Font("Microsoft YaHei UI", 1,
				12));
		colorChooserjButton1.setForeground(new java.awt.Color(255, 255, 255));
		colorChooserjButton1.setText("\u989c\u8272\u9009\u62e9\u5668");
		colorChooserjButton1
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						colorChooserjButton1ActionPerformed(evt);
					}
				});

		jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1,
				1));
		jLabel7.setMaximumSize(new java.awt.Dimension(100, 100));
		jLabel7.setMinimumSize(new java.awt.Dimension(100, 100));

		jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1,
				1));
		jLabel10.setMaximumSize(new java.awt.Dimension(100, 100));
		jLabel10.setMinimumSize(new java.awt.Dimension(100, 100));

		jLabel18.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14));

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel18,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																132,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																false)
																														.addGroup(
																																jPanel8Layout
																																		.createSequentialGroup()
																																		.addComponent(
																																				jLabel11)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				jLabel12,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				11,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																jPanel8Layout
																																		.createSequentialGroup()
																																		.addComponent(
																																				hiddenData_jTextField1,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				51,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				jLabel17)))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																B_RGB_jTextField3)
																														.addComponent(
																																R_RGB_jTextField1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																130,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel9)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel10,
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																175,
																																Short.MAX_VALUE)
																														.addComponent(
																																currentModelField,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																175,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addGap(22,
																				22,
																				22)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addGap(7,
																												7,
																												7)
																										.addComponent(
																												jLabel8)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																jLabel7,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																169,
																																Short.MAX_VALUE)
																														.addComponent(
																																jComboBox1,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																169,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addGap(22,
																												22,
																												22)
																										.addComponent(
																												jLabel16,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												12,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																colorChooserjButton1)
																														.addComponent(
																																G_RGB_jTextField2,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																135,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))))
										.addContainerGap(20, Short.MAX_VALUE)));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addComponent(
												jLabel18,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												26,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGap(14,
																				14,
																				14)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								currentModelField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								25,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel8,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								23,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBox1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel10,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								150,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel7,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								151,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								jPanel8Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(
																												jLabel12)
																										.addComponent(
																												jLabel11,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												28,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel8Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(
																												R_RGB_jTextField1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												G_RGB_jTextField2,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												jLabel16,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel9,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				27,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel17)
																		.addComponent(
																				B_RGB_jTextField3,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				hiddenData_jTextField1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																colorChooserjButton1))
										.addContainerGap()));

		apply_jButton1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		apply_jButton1.setForeground(new java.awt.Color(255, 255, 255));
		apply_jButton1.setText("\u5e94\u7528\u8bbe\u7f6e");
		apply_jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				apply_jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(
				jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout
				.setHorizontalGroup(jPanel10Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel10Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												147,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel10Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel8,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																apply_jButton1))
										.addContainerGap()));
		jPanel10Layout
				.setVerticalGroup(jPanel10Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel10Layout
										.createSequentialGroup()
										.addComponent(
												jPanel8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(apply_jButton1)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addComponent(jScrollPane4,
								javax.swing.GroupLayout.DEFAULT_SIZE, 356,
								Short.MAX_VALUE));

		javax.swing.GroupLayout setupJFLayout = new javax.swing.GroupLayout(
				setupJF.getContentPane());
		setupJF.getContentPane().setLayout(setupJFLayout);
		setupJFLayout.setHorizontalGroup(setupJFLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		setupJFLayout.setVerticalGroup(setupJFLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u76d6\u7ae0\u5c0f\u8f6f\u4ef6");
		setMinimumSize(new java.awt.Dimension(800, 600));

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));

		jPanel5.setBackground(new java.awt.Color(255, 255, 255));
		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						204, 204, 204)), "\u516c\u5171\u6d88\u606f",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Microsoft YaHei UI", 1, 14)));

		common_msg_area.setColumns(20);
		common_msg_area.setFont(new java.awt.Font("Monospaced", 0, 14));
		common_msg_area.setRows(5);
		jScrollPane1.setViewportView(common_msg_area);

		cls_msg_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		cls_msg_btn.setForeground(new java.awt.Color(255, 255, 255));
		cls_msg_btn.setText("\u6e05\u7a7a\u6d88\u606f");
		cls_msg_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cls_msg_btnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 934,
						Short.MAX_VALUE)
				.addGroup(
						jPanel5Layout.createSequentialGroup()
								.addGap(423, 423, 423)
								.addComponent(cls_msg_btn)
								.addContainerGap(430, Short.MAX_VALUE)));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel5Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												277, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cls_msg_btn)));

		jTabbedPane1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));

		qgd_page.setBackground(new java.awt.Color(255, 255, 255));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						204, 204, 204)), "\u64cd\u4f5c",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Microsoft YaHei UI", 1, 14)));

		jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel1.setText("\u7b7e\u8d2d\u5355\u56fe\u7247\u538b\u7f29\u5305\uff1a");

		jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel2.setText("\u7b7e\u7ae0\u4fe1\u606fExcel\u8868\uff1a");

		jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel3.setText("\u5b58\u653e\u7ed3\u679c\u7684\u76ee\u5f55\uff1a");

		

		qgd_ysb_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		qgd_ysb_btn.setText("\u9009 \u62e9");
		qgd_ysb_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				qgd_ysb_btnActionPerformed(evt);
			}
		});

		qgd_exl_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		qgd_exl_btn.setText("\u9009 \u62e9");
		qgd_exl_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				qgd_exl_btnActionPerformed(evt);
			}
		});

		qgd_result_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		qgd_result_btn.setText("\u9009 \u62e9");
		qgd_result_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				qgd_result_btnActionPerformed(evt);
			}
		});

		qgd_reset_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		qgd_reset_btn.setForeground(new java.awt.Color(255, 255, 255));
		qgd_reset_btn.setText("\u91cd \u7f6e");
		qgd_reset_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				qgd_reset_btnActionPerformed(evt);
			}
		});

		qgd_justDoIt_btn
				.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		qgd_justDoIt_btn.setForeground(new java.awt.Color(255, 255, 255));
		qgd_justDoIt_btn.setText("\u6267 \u884c");
		qgd_justDoIt_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						qgd_justDoIt_btnActionPerformed(null);
					}
				}).start();
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																126,
																Short.MAX_VALUE)
														.addComponent(jLabel1)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																qgd_result_field)
														.addComponent(
																qgd_exl_field)
														.addComponent(
																qgd_ysb_field,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																579,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																qgd_ysb_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																71,
																Short.MAX_VALUE)
														.addComponent(
																qgd_exl_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																qgd_result_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(29, 29, 29)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																qgd_reset_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																qgd_justDoIt_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																88,
																Short.MAX_VALUE))
										.addContainerGap(17, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								32,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								qgd_ysb_btn)
																						.addComponent(
																								qgd_ysb_field,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								34,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								37,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								qgd_exl_field,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								36,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								qgd_exl_btn))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								31,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								qgd_result_field,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								39,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								qgd_result_btn)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(28,
																				28,
																				28)
																		.addComponent(
																				qgd_justDoIt_btn)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				qgd_reset_btn)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout qgd_pageLayout = new javax.swing.GroupLayout(
				qgd_page);
		qgd_page.setLayout(qgd_pageLayout);
		qgd_pageLayout.setHorizontalGroup(qgd_pageLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		qgd_pageLayout.setVerticalGroup(qgd_pageLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				qgd_pageLayout
						.createSequentialGroup()
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		jTabbedPane1.addTab("\u7b7e\u8d2d\u5355\u76d6\u7ae0", qgd_page);

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						204, 204, 204)), "\u64cd\u4f5c",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Microsoft YaHei UI", 1, 14)));

		jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel4.setText("\u6d41\u6c34Excel\u538b\u7f29\u5305\uff1a");

		

		jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel5.setText("\u5b58\u653e\u7ed3\u679c\u7684\u76ee\u5f55\uff1a");

		lsjy_ysb_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		lsjy_ysb_btn.setText("\u9009 \u62e9");
		lsjy_ysb_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lsjy_ysb_btnActionPerformed(evt);
			}
		});

		lsjy_result_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		lsjy_result_btn.setText("\u9009 \u62e9");
		lsjy_result_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lsjy_result_btnActionPerformed(evt);
			}
		});

		lsjy_justDoIt_btn
				.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		lsjy_justDoIt_btn.setForeground(new java.awt.Color(255, 255, 255));
		lsjy_justDoIt_btn.setText("\u6267 \u884c");
		lsjy_justDoIt_btn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								lsjy_justDoIt_btnActionPerformed(null);
							}
						}).start();
					}
				});

		lsjy_reset_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		lsjy_reset_btn.setForeground(new java.awt.Color(255, 255, 255));
		lsjy_reset_btn.setText("\u91cd \u7f6e");
		lsjy_reset_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lsjy_reset_btnActionPerformed(evt);
			}
		});

		jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel6.setText("\u7b7e\u7ae0\u4fe1\u606fExcel\u8868\uff1a");

		lsjy_qzexl_btn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		lsjy_qzexl_btn.setText("\u9009 \u62e9");
		lsjy_qzexl_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lsjy_qzexl_btnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																lsjy_result_field)
														.addComponent(
																lsjy_qzexl_field)
														.addComponent(
																lsjy_ysb_field,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																582,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(7, 7, 7)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																lsjy_result_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lsjy_qzexl_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lsjy_ysb_btn,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																70,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(28, 28, 28)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																lsjy_reset_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lsjy_justDoIt_btn,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																91,
																Short.MAX_VALUE))
										.addContainerGap(15, Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGap(26,
																				26,
																				26)
																		.addComponent(
																				lsjy_justDoIt_btn)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				lsjy_reset_btn))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								27,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lsjy_ysb_btn)
																						.addComponent(
																								lsjy_ysb_field,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								34,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel6,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								30,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lsjy_qzexl_field,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								34,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lsjy_qzexl_btn))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel5,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								32,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lsjy_result_field,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								35,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lsjy_result_btn))))
										.addContainerGap(17, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel3, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addComponent(jPanel3,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));

		jTabbedPane1.addTab("\u6d41\u6c34\u4ea4\u6613\u76d6\u7ae0", jPanel2);

		jPanel6.setBackground(new java.awt.Color(255, 255, 255));

		jPanel9.setBackground(new java.awt.Color(255, 255, 255));
		jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						204, 204, 204)), "\u64cd\u4f5c",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Microsoft YaHei UI", 1, 14)));

		jLabel13.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel13.setText("\u5165\u8d26Excel\u538b\u7f29\u5305\uff1a");

		

		jLabel14.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel14.setText("\u5b58\u653e\u7ed3\u679c\u7684\u76ee\u5f55\uff1a");

		rzls_ysb_btn3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		rzls_ysb_btn3.setText("\u9009 \u62e9");
		rzls_ysb_btn3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rzls_ysb_btn3ActionPerformed(evt);
			}
		});

		rzls_result_btn3
				.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		rzls_result_btn3.setText("\u9009 \u62e9");
		rzls_result_btn3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rzls_result_btn3ActionPerformed(evt);
			}
		});

		rzls_justDoIt_btn3.setFont(new java.awt.Font("Microsoft YaHei UI", 1,
				12));
		rzls_justDoIt_btn3.setForeground(new java.awt.Color(255, 255, 255));
		rzls_justDoIt_btn3.setText("\u6267 \u884c");
		rzls_justDoIt_btn3
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								rzls_justDoIt_btn3ActionPerformed(null);
							}
						}).start();
					}
				});

		rzls_reset_btn3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		rzls_reset_btn3.setForeground(new java.awt.Color(255, 255, 255));
		rzls_reset_btn3.setText("\u91cd \u7f6e");
		rzls_reset_btn3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rzls_reset_btn3ActionPerformed(evt);
			}
		});

		jLabel15.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
		jLabel15.setText("\u7b7e\u7ae0\u4fe1\u606fExcel\u8868\uff1a");

		rzls_qzexl_btn3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12));
		rzls_qzexl_btn3.setText("\u9009 \u62e9");
		rzls_qzexl_btn3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rzls_qzexl_btn3ActionPerformed(evt);
			}
		});

		jRadioButton1.setText("\u6a2a\u7248");

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(
				jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel15,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel13,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel14,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																rzls_result_field3)
														.addComponent(
																rzls_qzexl_field3)
														.addComponent(
																rzls_ysb_field3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																582,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(7, 7, 7)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																rzls_result_btn3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																rzls_qzexl_btn3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																rzls_ysb_btn3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																70,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(28, 28, 28)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jRadioButton1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																rzls_justDoIt_btn3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																82,
																Short.MAX_VALUE)
														.addComponent(
																rzls_reset_btn3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(24, Short.MAX_VALUE)));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel13,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								27,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								rzls_ysb_btn3)
																						.addComponent(
																								rzls_ysb_field3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								34,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel15,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								30,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								rzls_qzexl_field3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								34,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								rzls_qzexl_btn3)))
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addGap(6,
																				6,
																				6)
																		.addComponent(
																				jRadioButton1)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				rzls_justDoIt_btn3)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel14,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																32,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																rzls_result_field3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																35,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																rzls_result_btn3)
														.addComponent(
																rzls_reset_btn3))
										.addContainerGap(17, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout
						.createSequentialGroup()
						.addComponent(jPanel9,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));

		jTabbedPane1.addTab("\u5165\u8d26\u6d41\u6c34\u76d6\u7ae0", jPanel6);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTabbedPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 944,
						Short.MAX_VALUE)
				.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(
												jTabbedPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												212,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel5,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jMenu1.setText("\u5e2e\u52a9");

		jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_H,
				java.awt.event.InputEvent.ALT_MASK));
		jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/javax/swing/plaf/metal/icons/ocean/expanded.gif"))); // NOI18N
		jMenuItem1.setText("使用说明及注意事项");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("\u76f8\u5173");

		jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.ALT_MASK));
		jMenuItem2
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/org/jb2011/lnf/beautyeye/ch1_titlepane/imgs/frame_setup_normal.png"))); // NOI18N
		jMenuItem2.setText("\u8bbe\u7f6e");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem2);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		someInit();
		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void apply_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		String functionType = hiddenData_jTextField1.getText();//功能类型
		String model = jComboBox1.getSelectedItem().toString();//模板 
		String R = R_RGB_jTextField1.getText();
		String G = G_RGB_jTextField2.getText();
		String B = B_RGB_jTextField3.getText();

		new ConfigService(ConfigService.MODEL_CONFIG).saveModelConfig(
				functionType, model);
		new ConfigService(ConfigService.FONTCOLOR_CONFIG).saveRGBConfig(
				functionType, R, G, B);

		JOptionPane.showMessageDialog(null, "修改成功");
	}

	private void colorChooserjButton1ActionPerformed(
			java.awt.event.ActionEvent evt) {
		Color color = JColorChooser.showDialog(this, "颜色选择器", null);
		R_RGB_jTextField1.setText(String.valueOf(color.getRed()));
		G_RGB_jTextField2.setText(String.valueOf(color.getGreen()));
		B_RGB_jTextField3.setText(String.valueOf(color.getBlue()));
	}

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		String s = ((JComboBox) evt.getSource()).getSelectedItem().toString();
		//Icon i = new ImageIcon();
		Icon i = new ImageIcon(getClass().getResource(
				"/ml/"
						+ new ConfigService(ConfigService.QZ_LIST_MODEL)
								.getValue(s)));
		jLabel7.setIcon(i);
	}

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		setupJF = new javax.swing.JFrame();

		setupJF.setTitle("\u76d6\u7ae0\u5c0f\u8f6f\u4ef6-\u8bbe\u7f6e");
		setupJF.setIconImage(new ImageIcon(getClass().getResource("/icon.png"))
				.getImage());
		setupJF.setMinimumSize(new java.awt.Dimension(710, 400));

		javax.swing.GroupLayout setupJFLayout = new javax.swing.GroupLayout(
				setupJF.getContentPane());
		setupJF.getContentPane().setLayout(setupJFLayout);
		setupJFLayout.setHorizontalGroup(setupJFLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		setupJFLayout.setVerticalGroup(setupJFLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		setupJF.setResizable(false);
		setupJF.setVisible(true);

	}

	private void rzls_qzexl_btn3ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		// 关闭多选
		chooser.setMultiSelectionEnabled(false);
		// 设置文件过滤
		chooser.setFileFilter(new ExcleFileFilter());
		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();
		rzls_qzexl_field3.setText(f.getAbsolutePath());
	}

	private void rzls_reset_btn3ActionPerformed(java.awt.event.ActionEvent evt) {
		rzls_ysb_field3.setText("");
		rzls_qzexl_field3.setText("");
		rzls_result_field3.setText("");
	}

	private void rzls_justDoIt_btn3ActionPerformed(
			java.awt.event.ActionEvent evt) {
		
		String path = rzls_ysb_field3.getText().trim();
		String path2 = rzls_result_field3.getText().trim();
		String path3 = rzls_qzexl_field3.getText().trim();
		if ("".equals(path)) {
			System.out.println("请选择入账流水信息Excel压缩包");
			return;
		}
		if ("".equals(path3)) {
			System.out.println("请选择签章信息Excel文件");
			return;
		}
		if ("".equals(path2)) {
			System.out.println("请选择存放结果路径");
			return;
		} else {
			rzls_reset_btn3.setEnabled(false);
			rzls_result_btn3.setEnabled(false);
			rzls_ysb_btn3.setEnabled(false);
			rzls_qzexl_btn3.setEnabled(false);
			common_msg_area.setEnabled(false);
			rzls_justDoIt_btn3.setEnabled(false);
			int r = JOptionPane.showConfirmDialog(null, "确定执行？", "",
					JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_OPTION) 
			{
				int type = -1;
				if(jRadioButton1.isSelected())
				{
					type = 1;
				}
				boolean isDone = new RzlsService(path, path3, path2, type).doIt();
				if (isDone) {
					rzls_reset_btn3.setEnabled(true);
					rzls_result_btn3.setEnabled(true);
					rzls_ysb_btn3.setEnabled(true);
					common_msg_area.setEnabled(true);
					rzls_justDoIt_btn3.setEnabled(true);
					rzls_qzexl_btn3.setEnabled(true);
				}
			} else {
				rzls_reset_btn3.setEnabled(true);
				rzls_result_btn3.setEnabled(true);
				rzls_ysb_btn3.setEnabled(true);
				common_msg_area.setEnabled(true);
				rzls_justDoIt_btn3.setEnabled(true);
				rzls_qzexl_btn3.setEnabled(true);
			}
		}
	}

	private void rzls_result_btn3ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(1);
		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();
		rzls_result_field3.setText(f.getAbsolutePath());
	}

	private void rzls_ysb_btn3ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		// 关闭多选
		chooser.setMultiSelectionEnabled(false);
		// 设置文件过滤
		chooser.setFileFilter(new CompressedFileFilter());
		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();
		rzls_ysb_field3.setText(f.getAbsolutePath());
	}

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		infoJF.setVisible(true);
	}

	private void lsjy_qzexl_btnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		// 关闭多选
		chooser.setMultiSelectionEnabled(false);
		// 设置文件过滤
		chooser.setFileFilter(new ExcleFileFilter());
		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();
		lsjy_qzexl_field.setText(f.getAbsolutePath());
	}

	private void lsjy_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {
		lsjy_result_field.setText("");
		lsjy_ysb_field.setText("");
		lsjy_qzexl_field.setText("");
	}

	private void lsjy_justDoIt_btnActionPerformed(java.awt.event.ActionEvent evt) {
		String path = lsjy_ysb_field.getText().trim();
		String path2 = lsjy_result_field.getText().trim();
		String path3 = lsjy_qzexl_field.getText().trim();
		if ("".equals(path)) {
			System.out.println("请选择流水信息Excel压缩包");
			return;
		}
		if ("".equals(path3)) {
			System.out.println("请选择签章信息Excel文件");
			return;
		}
		if ("".equals(path2)) {
			System.out.println("请选择存放结果路径");
			return;
		} else {
			lsjy_reset_btn.setEnabled(false);
			lsjy_result_btn.setEnabled(false);
			lsjy_ysb_btn.setEnabled(false);
			lsjy_qzexl_btn.setEnabled(false);
			common_msg_area.setEnabled(false);
			lsjy_justDoIt_btn.setEnabled(false);
			int r = JOptionPane.showConfirmDialog(null, "确定执行？", "",
					JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_OPTION) {
				boolean isDone = new LsjyService(path, path3, path2).doIt();
				if (isDone) {
					lsjy_reset_btn.setEnabled(true);
					lsjy_result_btn.setEnabled(true);
					lsjy_ysb_btn.setEnabled(true);
					common_msg_area.setEnabled(true);
					lsjy_justDoIt_btn.setEnabled(true);
					lsjy_qzexl_btn.setEnabled(true);
				}
			} else {
				lsjy_reset_btn.setEnabled(true);
				lsjy_result_btn.setEnabled(true);
				lsjy_ysb_btn.setEnabled(true);
				common_msg_area.setEnabled(true);
				lsjy_justDoIt_btn.setEnabled(true);
				lsjy_qzexl_btn.setEnabled(true);
			}
		}
	}

	private void lsjy_result_btnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(1);
		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();
		lsjy_result_field.setText(f.getAbsolutePath());
	}

	private void lsjy_ysb_btnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		// 关闭多选
		chooser.setMultiSelectionEnabled(false);
		// 设置过滤器
		chooser.setFileFilter(new CompressedFileFilter());

		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();

		lsjy_ysb_field.setText(f.getAbsolutePath());
	}

	private void cls_msg_btnActionPerformed(java.awt.event.ActionEvent evt) {
		common_msg_area.setText("");
	}

	private void qgd_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {
		qgd_exl_field.setText("");
		qgd_ysb_field.setText("");
		qgd_result_field.setText("");
	}

	private void qgd_justDoIt_btnActionPerformed(java.awt.event.ActionEvent evt) {
		String img = qgd_ysb_field.getText().trim();
		String exl = qgd_exl_field.getText().trim();
		String rest = qgd_result_field.getText().trim();
		if ("".equals(img)) {
			System.out.println("请选择签购单图片压缩包");
			return;
		}
		if ("".equals(exl)) {
			System.out.println("请选择签章信息excel文件");
			return;
		}
		if ("".equals(rest)) {
			System.out.println("请选择存放结果路径");
			return;
		} else {
			qgd_exl_btn.setEnabled(false);
			qgd_reset_btn.setEnabled(false);
			qgd_ysb_btn.setEnabled(false);
			qgd_result_btn.setEnabled(false);
			common_msg_area.setEnabled(false);
			qgd_justDoIt_btn.setEnabled(false);
			int r = JOptionPane.showConfirmDialog(null, "确定执行？", "",
					JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.YES_OPTION) {
				boolean isDone = new QgdService(img, exl, rest).doIt();
				if (isDone) {
					qgd_exl_btn.setEnabled(true);
					qgd_reset_btn.setEnabled(true);
					qgd_ysb_btn.setEnabled(true);
					qgd_result_btn.setEnabled(true);
					common_msg_area.setEnabled(true);
					qgd_justDoIt_btn.setEnabled(true);
				}
			} else {
				qgd_exl_btn.setEnabled(true);
				qgd_reset_btn.setEnabled(true);
				qgd_ysb_btn.setEnabled(true);
				qgd_result_btn.setEnabled(true);
				common_msg_area.setEnabled(true);
				qgd_justDoIt_btn.setEnabled(true);
			}
		}
	}

	private void qgd_result_btnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(1);
		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();
		qgd_result_field.setText(f.getAbsolutePath());
	}

	private void qgd_exl_btnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		// 关闭多选
		chooser.setMultiSelectionEnabled(false);
		// 设置文件过滤
		chooser.setFileFilter(new ExcleFileFilter());

		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();

		qgd_exl_field.setText(f.getAbsolutePath());
	}

	private void qgd_ysb_btnActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		// 关闭多选
		chooser.setMultiSelectionEnabled(false);
		// 设置过滤器
		chooser.setFileFilter(new CompressedFileFilter());

		chooser.showDialog(new JLabel(), "选择");
		File f = chooser.getSelectedFile();

		qgd_ysb_field.setText(f.getAbsolutePath());
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper
							.launchBeautyEyeLNF();
					// 设置本属性将改变窗口边框样式定义
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper
							.launchBeautyEyeLNF();

					UIManager.put("RootPane.setupButtonVisible", false);
					UIManager.put("TabbedPane.tabAreaInsets",
							new javax.swing.plaf.InsetsUIResource(3, 0, 2, 0));
				} catch (Exception e) {

				}

				GZ gz = new GZ();
				int[] posi = getWindowPositon(gz);
				gz.setLocation(posi[0], posi[1]);
				gz.setVisible(true);
				ImageIcon ii = new ImageIcon(getClass()
						.getResource("/icon.png"));
				gz.setIconImage(ii.getImage());

			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JTextField B_RGB_jTextField3;
	private javax.swing.JTextField G_RGB_jTextField2;
	private javax.swing.JTextField R_RGB_jTextField1;
	private javax.swing.JButton apply_jButton1;
	private javax.swing.JButton cls_msg_btn;
	private javax.swing.JButton colorChooserjButton1;
	private javax.swing.JTextArea common_msg_area;
	private javax.swing.JTextField currentModelField;
	private javax.swing.JTextField hiddenData_jTextField1;
	private javax.swing.JFrame infoJF;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTree jTree1;
	private javax.swing.JTree jTree2;
	private javax.swing.JButton lsjy_justDoIt_btn;
	private javax.swing.JButton lsjy_qzexl_btn;
	private javax.swing.JTextField lsjy_qzexl_field;
	private javax.swing.JButton lsjy_reset_btn;
	private javax.swing.JButton lsjy_result_btn;
	private javax.swing.JTextField lsjy_result_field;
	private javax.swing.JButton lsjy_ysb_btn;
	private javax.swing.JTextField lsjy_ysb_field;
	private java.awt.Panel panel1;
	private javax.swing.JButton qgd_exl_btn;
	private javax.swing.JTextField qgd_exl_field;
	private javax.swing.JButton qgd_justDoIt_btn;
	private javax.swing.JPanel qgd_page;
	private javax.swing.JButton qgd_reset_btn;
	private javax.swing.JButton qgd_result_btn;
	private javax.swing.JTextField qgd_result_field;
	private javax.swing.JButton qgd_ysb_btn;
	private javax.swing.JTextField qgd_ysb_field;
	private javax.swing.JButton rzls_justDoIt_btn3;
	private javax.swing.JButton rzls_qzexl_btn3;
	private javax.swing.JTextField rzls_qzexl_field3;
	private javax.swing.JButton rzls_reset_btn3;
	private javax.swing.JButton rzls_result_btn3;
	private javax.swing.JTextField rzls_result_field3;
	private javax.swing.JButton rzls_ysb_btn3;
	private javax.swing.JTextField rzls_ysb_field3;
	private javax.swing.JFrame setupJF;
	private javax.swing.JTextArea sm_textArea;

	// End of variables declaration//GEN-END:variables

	/*
	 * 计算窗口的位置坐标
	 */
	public static int[] getWindowPositon(Window window) {
		// 整个显示器宽高
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screensize.width;
		int h = screensize.height;
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
				window.getGraphicsConfiguration());
		h = h - (screenInsets.top + screenInsets.bottom);
		// 当前窗口宽高
		int currWindowH = window.getHeight();
		int currWindowW = window.getWidth();
		int[] xy = new int[2];
		// 计算与top,left距离
		xy[0] = (w - currWindowW) / 2;
		xy[1] = (h - currWindowH) / 2;
		return xy;
	}

	// 重定向输出信息
	public void outRedi(JTextArea myArea) {
		JTextAreaOutputStream out = new JTextAreaOutputStream(myArea);
		System.setOut(new PrintStream(out));// 设置输出重定向
		// System.setErr(new PrintStream(out));//将错误输出也重定向,用于e.pritnStackTrace
		@SuppressWarnings("unused")
		JScrollPane jsp = new JScrollPane(myArea);// 设置滚动条
	}

	/*
	 * 帮助
	 */
	public DefaultMutableTreeNode createHelpTree() {
		DefaultMutableTreeNode group1 = new DefaultMutableTreeNode("签购单");
		group1.add(new DefaultMutableTreeNode("图片压缩包"));
		group1.add(new DefaultMutableTreeNode("图片签章Excel"));
		DefaultMutableTreeNode group2 = new DefaultMutableTreeNode("流水交易");
		group2.add(new DefaultMutableTreeNode("流水压缩包"));
		group2.add(new DefaultMutableTreeNode("流水签章Excel"));
		DefaultMutableTreeNode group4 = new DefaultMutableTreeNode("入账流水");
		group4.add(new DefaultMutableTreeNode("入账压缩包"));
		group4.add(new DefaultMutableTreeNode("入账签章Excel"));
		DefaultMutableTreeNode group3 = new DefaultMutableTreeNode("其他");
		group3.add(new DefaultMutableTreeNode("其他"));
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("使用说明及注意事项");
		root.add(group1);
		root.add(group2);
		root.add(group4);
		root.add(group3);
		return root;
	}

	public Map<String, Object> desc() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("图片压缩包", MyDesc.qgdysb());
		map.put("图片签章Excel", MyDesc.qgdexl());
		map.put("流水压缩包", MyDesc.lsysb());
		map.put("流水签章Excel", MyDesc.lsexl());
		map.put("入账压缩包", MyDesc.rzlsysb());
		map.put("入账签章Excel", MyDesc.rzlsexl());
		map.put("其他", MyDesc.oterhdesc());
		return map;
	}

	/*
	 * 设置
	 */
	public DefaultMutableTreeNode createConfigTree() {
		DefaultMutableTreeNode group1 = new DefaultMutableTreeNode("签章模板");
		group1.add(new DefaultMutableTreeNode("签购单"));
		group1.add(new DefaultMutableTreeNode("流水交易"));
		group1.add(new DefaultMutableTreeNode("入账流水"));
		//DefaultMutableTreeNode group3 = new DefaultMutableTreeNode("颜色");
		//group3.add(new DefaultMutableTreeNode("颜色"));
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("设置");
		root.add(group1);
		//root.add(group3);
		return root;
	}

	public String[] getComboV() {
		return new ConfigService(ConfigService.QZ_LIST_MODEL).getKeys();
	}

	public void mytree() {
		jTree1.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1
						.getLastSelectedPathComponent();
				if (node == null) {
					return;
				}//if
				Object object = node.getUserObject();
				sm_textArea.setText(desc().get(object.toString()).toString());
			}
		});

		jTree2.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree2
						.getLastSelectedPathComponent();
				if (node == null) {
					return;
				}//if
				Object obj = node.getUserObject();
				String temp_s = obj.toString().trim();
				hiddenData_jTextField1.setText(temp_s);
				jLabel18.setText(temp_s);

				Icon i = null;
				String[] rgb = null;
				String suffix = ".jpg";
				String prefix = "/ml/";
				String v = "";
				if ("签购单".equals(temp_s)) {
					v = new ConfigService(ConfigService.MODEL_CONFIG)
							.getValue("qgd");
					i = new ImageIcon(getClass().getResource(
							prefix + v + suffix));
					rgb = new ConfigService(ConfigService.FONTCOLOR_CONFIG)
							.getValue("qgd_RGB").split(",");
					apply_jButton1.setEnabled(true);
				} else if ("流水交易".equals(temp_s)) {
					v = new ConfigService(ConfigService.MODEL_CONFIG)
							.getValue("lsjy");
					i = new ImageIcon(getClass().getResource(
							prefix + v + suffix));
					rgb = new ConfigService(ConfigService.FONTCOLOR_CONFIG)
							.getValue("lsjy_RGB").split(",");
					apply_jButton1.setEnabled(true);
				} else if ("入账流水".equals(temp_s)) {
					v = new ConfigService(ConfigService.MODEL_CONFIG)
							.getValue("rzls");
					i = new ImageIcon(getClass().getResource(
							prefix + v + suffix));
					rgb = new ConfigService(ConfigService.FONTCOLOR_CONFIG)
							.getValue("rzls_RGB").split(",");
					apply_jButton1.setEnabled(true);
				}
				jLabel10.setIcon(i);
				currentModelField.setText(v);
				if (rgb != null && rgb.length >= 3) {
					R_RGB_jTextField1.setText(rgb[0]);
					G_RGB_jTextField2.setText(rgb[1]);
					B_RGB_jTextField3.setText(rgb[2]);
				}

			}
		});
	}

	private void someInit() {
		currentModelField.setEnabled(false);
		hiddenData_jTextField1.setVisible(false);

		cls_msg_btn.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));
		//签购单
		qgd_reset_btn.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		qgd_justDoIt_btn.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.green));

		//流水交易
		lsjy_justDoIt_btn.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.green));
		lsjy_reset_btn.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		//入账流水
		rzls_justDoIt_btn3.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.green));
		rzls_reset_btn3.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		//设置
		colorChooserjButton1.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		apply_jButton1.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.green));

		setResizable(false);
		mytree();
		apply_jButton1.setEnabled(false);

	}
}