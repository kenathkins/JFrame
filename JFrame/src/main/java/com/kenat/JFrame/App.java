package com.kenat.JFrame;

import java.awt.Font;

import javax.swing.UIManager;

import com.kenat.JFrame.windows.AppUI;

/**
 * Main Process
 *
 */
public class App {
	
	public static void main(String[] args) {

		AppUI.InitGlobalLook(new UIManager());
		AppUI.InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
		AppUI app = new AppUI();
		app.setVisible(true);
	}
}
