package com.mycompany.java.eclipse.myproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame implements ActionListener{
	
	Container panel;
	JTextArea area;
	JScrollPane scroll;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	JMenuBar Menu;
	JMenu FileMenu;
	JMenu EditMenu;
	JMenuItem FontType;
	JMenuItem Save;
	JMenuItem SaveAs;
	JMenuItem Open;
	JMenuItem New;
	String reqFont;
	String fontSize;
	File file;
	Image icon;
	
	
	@SuppressWarnings("unchecked")
	public void ChangeFont() 
	{	
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String[] fontsStyle = { "BOLD"  , "ITALIC" , "PLAIN" };
		
		@SuppressWarnings("rawtypes")
		JComboBox c1 = new JComboBox(fonts);
		JComboBox c2 = new JComboBox(fontsStyle);

		JOptionPane.showInputDialog(c1);
		JOptionPane.showInputDialog(c2);
		String a = JOptionPane.showInputDialog("Enter Size of font");
		int aa = Integer.parseInt(a);
		Color c = JColorChooser.showDialog(null, "Select ForeGround Color" , Color.green);
		area.setForeground(c);
		Color cc = JColorChooser.showDialog(null, "Select Background Color" , Color.black);
		area.setBackground(cc);
		
		if(c2.getSelectedItem().toString().equals("BOLD")) {
			area.setFont(new Font(c1.getSelectedItem().toString() , Font.BOLD , aa));
		}
		if(c2.getSelectedItem().toString().equals("ITALIC")) {
			area.setFont(new Font(c1.getSelectedItem().toString() , Font.ITALIC , aa));
		}
		if(c2.getSelectedItem().toString().equals("PLAIN")) {
			area.setFont(new Font(c1.getSelectedItem().toString() , Font.PLAIN , aa));
		}
		
	}
	
	public void Save() {
		File reqFile = file;
		FileWriter writer;
		try {
			writer = new FileWriter(reqFile);
			writer.write(area.getText());
			System.out.println(area.getText());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Open() {
		FileDialog fd = new FileDialog((JFrame)null , "Select File To Open");
		fd.setMode(FileDialog.LOAD);
		fd.setVisible(true);
		String path = fd.getDirectory() + fd.getFile();
		file = new File(path);
		String ss = "";
		try {
			
			Scanner s = new Scanner(file);
			while(s.hasNextLine()) {
				ss = ss + s.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		area.setText(ss);
		this.setTitle(path);
		
		
	}
	public void SaveAs() {
		FileDialog fd1 = new FileDialog((JFrame)null , "Save as");
		fd1.setVisible(true);
		String path = fd1.getDirectory() + fd1.getFile();
		file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Save();
		this.setTitle(path);
	}
	
	public Main() {
		
		panel = getContentPane();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		Menu = new JMenuBar();
		icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\eclipse-workspace\\MyEclipseProject\\src\\com\\mycompany\\java\\eclipse\\myproject\\icon.png");
		
		FileMenu = new JMenu("File");
		EditMenu = new JMenu("Edit");
		
		Save = new JMenuItem("Save");
		SaveAs = new JMenuItem("SaveAs");
		Open = new JMenuItem("Open");
		New = new JMenuItem("New");
		FontType = new JMenuItem("FontType");
		
		Save.addActionListener(this);
		Open.addActionListener(this);
		New.addActionListener(this);
		SaveAs.addActionListener(this);
		FontType.addActionListener(this);
		
		Menu.add(FileMenu);
		Menu.add(EditMenu);
		
		FileMenu.add(Save);
		FileMenu.add(Open);
		FileMenu.add(SaveAs);
		FileMenu.add(New);
		
		EditMenu.add(FontType);

		panel.add(scroll);
		
		file = new File("Untitled.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon);
		setSize(500 , 500);
		setTitle(file.getAbsolutePath());
		setJMenuBar(Menu);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(Save)) {
			System.out.println("Saved");
			Save();
		}
		if(e.getSource().equals(SaveAs)) {
			System.out.println("Saved As");
			SaveAs();
		}
		if(e.getSource().equals(Open)) {
			System.out.println("Opened");
			Open();
		}
		if(e.getSource().equals(New)) {
			System.out.println("Newed");
		}
		if(e.getSource().equals(FontType)) {
			ChangeFont();
		}
		
		
	}

}
