package steganography;

import java.awt.*;
import javax.swing.*;

public class window extends JFrame {
	Container contentPane;
	JButton Activate, loadImage, loadFile;
	JLabel text1, text2,text3, firstFile, secFile, title, blank;
	String file1 = null;
	String file2 = null;
	
	public static void main(String[] args)
	{
		
		window gui;
		gui = new window();
		gui.setSize(500,250);
		gui.setVisible(true);
		gui.setResizable(false);
	}

	public window() {
		contentPane = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		title = new JLabel();
		title.setText("Steganography-hide your data in an image.");
		title.setFont(new Font("Calibri (body)", Font.BOLD, 20));
		contentPane.add(title);

		contentPane.add(Box.createHorizontalStrut(20));
		//contentPane.add(Box.createHorizontalGlue());
		//contentPane.add(Box.createRigidArea(new Dimension(200,100)));


		text2 = new JLabel("load the image file.");
		contentPane.add(text2);
		
		firstFile = new JLabel("no file selected.");
		contentPane.add(firstFile);
		
		loadImage = new JButton();
		loadImage.setText("Load");
		contentPane.add(loadImage);
		
		text3 = new JLabel("load the file you want to hide.");
		contentPane.add(text3);
		
		secFile = new JLabel("no file selected.");
		contentPane.add(secFile);
		
		loadFile = new JButton();
		loadFile.setText("Load");
		contentPane.add(loadFile);
		
		text1 = new JLabel("click here once two files have been selected.");
		contentPane.add(text1);
		
		Activate = new JButton();
		Activate.setText("Hide data!");
		contentPane.add(Activate);

		blank = new JLabel(" ");
		contentPane.add(blank);
		
		openFileListener ofl1 = new openFileListener(firstFile, this);
		openFileListener2 ofl2 = new openFileListener2(secFile, this);
		loadImage.addActionListener(ofl1);
		loadFile.addActionListener(ofl2);
		
		steganography s = new steganography();
		ActivateHidingListener ahl = new ActivateHidingListener(s, this);
		Activate.addActionListener(ahl);
	}
	
	public String getImage()
	{
		//System.out.print(file1);
		return file1;
	}
	public String getFile()
	{
		//System.out.print(file2);
		return file2;
	}
	
	public void setFile1(String x)
	{
		file1 = x;
	}
	public void setFile2(String y)
	{
		file2 = y;
	}
}
