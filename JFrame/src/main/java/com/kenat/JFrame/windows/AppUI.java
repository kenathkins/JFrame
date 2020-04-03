package com.kenat.JFrame.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import com.kenat.JFrame.utils.CsvUtil;
import com.kenat.JFrame.utils.PropertiesUtil;

public class AppUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFileChooser jfc = new JFileChooser();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InitGlobalLook(new UIManager());
		InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
		AppUI app = new AppUI();
		app.setVisible(true);
	}
	
	public AppUI() {
		setTitle("Masking Utility 1.0");
		setLocation(200,200);
		setLocationRelativeTo(null); 
		getContentPane().setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		final JLabel label_1 = new JLabel("Configuration");
		label_1.setFont(new Font("",0,20));
		label_1.setForeground(Color.blue);
		final GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.gridx = 0;
		gbc_1.gridy = 0;
		gbc_1.insets = new Insets(10, 10, 10, 10);
		gbc_1.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(label_1, gbc_1);
		
		final JLabel label_2 = new JLabel("File Path");
		label_2.setFont(new Font("", 0, 18));
		final GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.gridx = 0;
		gbc_2.gridy = 1;
		gbc_2.insets = new Insets(0, 10, 10, 10);
		gbc_2.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(label_2, gbc_2);
		
		final JTextField filePath = new JTextField(60);
		filePath.setPreferredSize(new Dimension(30, 30));
		filePath.setEditable(false);
		final GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.gridx = 0;
		gbc_3.gridy = 2;
		gbc_3.gridwidth = 5;
		gbc_3.gridheight = 1;
		gbc_3.insets = new Insets(0, 10, 10, 5);
		gbc_3.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(filePath, gbc_3);
		
		final JButton fileSelect = new JButton("...");
		fileSelect.setPreferredSize(new Dimension(30, 30));
		fileSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PropertiesUtil.init();
				
				jfc.setMultiSelectionEnabled(true); 
				
				if (!"".equals(PropertiesUtil.get("lastPath"))) {
					jfc.setCurrentDirectory(new File(PropertiesUtil.get("lastPath")));
				}else {
					//temporary save the file location
					jfc.setCurrentDirectory(jfc.getSelectedFile());
				}
				System.out.println(jfc.getCurrentDirectory());
				
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (jfc.showDialog(new JLabel(), "Choose") != JFileChooser.CANCEL_OPTION) {
//					File file = jfc.getSelectedFile();
					File[] files = jfc.getSelectedFiles();
					String multi = "";
					for (int i = 0; i < files.length; i++) {
						File file = files[i];
						if (file != null) {
							if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("csv") ) {
								multi += file.getAbsolutePath() + ";" ;
							}else {
								JOptionPane.showMessageDialog(null, "Selected file [" + file.getName() + "] is not a csv!");
							}
						}
					}
					filePath.setText(multi);
					System.out.println(filePath.getText());
					PropertiesUtil.update("lastPath", jfc.getCurrentDirectory().toString());
				}
			}
		});
		final GridBagConstraints gbc_4 = new GridBagConstraints();
		gbc_4.gridx = GridBagConstraints.RELATIVE;
		gbc_4.gridy = 2;
		gbc_4.gridwidth = 1;
		gbc_4.gridheight = 1;
		gbc_4.insets = new Insets(0, 0, 10, 10);
		gbc_4.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(fileSelect, gbc_4);
		
		final JLabel label_3 = new JLabel("Masking Column");
		label_3.setFont(new Font("", 0, 18));
		final GridBagConstraints gbc_5 = new GridBagConstraints();
		gbc_5.gridx = 0;
		gbc_5.gridy = 3;
		gbc_5.gridwidth = 1;
		gbc_5.gridheight = 1;
		gbc_5.insets = new Insets(0, 10, 10, 10);
		gbc_5.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(label_3, gbc_5);
		
		final JTextField maskFiled = new JTextField(5);
		maskFiled.setPreferredSize(new Dimension(30, 30));
		maskFiled.setEditable(false);
		final GridBagConstraints gbc_6 = new GridBagConstraints();
		gbc_6.gridx = GridBagConstraints.RELATIVE;
		gbc_6.gridy = 3;
		gbc_6.gridwidth = 1;
		gbc_6.gridheight = 1;
		gbc_6.anchor = GridBagConstraints.EAST;
		gbc_6.insets = new Insets(0, 0, 10, 5);
		gbc_6.fill = GridBagConstraints.BOTH;
		getContentPane().add(maskFiled, gbc_6);
		
		final JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Select");
		comboBox.addItem("CPTY");
//		comboBox.addItem("B  ");
//		comboBox.addItem("C  ");
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!"Select".equals(comboBox.getSelectedItem().toString().trim())) {
					maskFiled.setText(comboBox.getSelectedItem().toString().trim());
				}else {
					maskFiled.setText("");
				}
			}
		});
		final GridBagConstraints gbc_7 = new GridBagConstraints();
		gbc_7.gridx = GridBagConstraints.RELATIVE;
		gbc_7.gridy = 3;
		gbc_7.gridwidth = 1;
		gbc_7.gridheight = 1;
		gbc_7.anchor = GridBagConstraints.EAST;
		gbc_7.insets = new Insets(0, 0, 10, 10);
		gbc_7.fill = GridBagConstraints.BOTH;
		getContentPane().add(comboBox, gbc_7);
		
		final JButton process = new JButton("Process");
		process.setPreferredSize(new Dimension(90, 30));
		process.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ("".equals(filePath.getText())) {
					JOptionPane.showMessageDialog(null, "Please select the file 1st!");
				}else if ("".equals(maskFiled.getText())) {
					JOptionPane.showMessageDialog(null, "Please select the masking filed 1st!");
				}else {
					CsvUtil csvUtil = new CsvUtil();
					try {
//						csvUtil.updateByColumn(filePath.getText(), maskFiled.getText());
						csvUtil.updateFilesByPath(filePath.getText(), maskFiled.getText());
						
						JOptionPane.showMessageDialog(null,"Masked Successful!");
//						Runtime.getRuntime().exec("cmd /c start " + filePath.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});
		final GridBagConstraints gbc_8 = new GridBagConstraints();
		gbc_8.gridx = 0;
		gbc_8.gridy = 4;
		gbc_8.gridwidth = 6;
		gbc_8.gridheight = 1;
		gbc_8.anchor = GridBagConstraints.EAST;
		gbc_8.insets = new Insets(0, 0, 10, 10);
		gbc_8.fill = GridBagConstraints.EAST;
		getContentPane().add(process, gbc_8);
		
		pack();
	}
	
	public static void InitGlobalLook(UIManager ui) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void InitGlobalFont(Font font) {
		FontUIResource fontUIResource = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof UIManager) {
				UIManager.put(key, fontUIResource);
			}
		}
	}
}
	
