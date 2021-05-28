package steganography;

import java.awt.Container;

import javax.swing.JFrame;

public class Start extends JFrame
{
	Container pane;
	
	public static void main(String args[])
	{
		Window gui;
		gui = new Window();
		gui.setSize(500,200);
		gui.setVisible(true);
		gui.setResizable(false);
	}
}