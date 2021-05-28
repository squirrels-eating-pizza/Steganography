package steganography;

import java.awt.Container;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame
{
	Container contentPane;
	JButton Activate, loadImage;
	JLabel text1, text2, firstFile, title, blank;
	String file1 = null;
	
	Window()
	{
		contentPane = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		title = new JLabel();
		title.setText("Steganography-Extract your data from an image.");
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
		
		text1 = new JLabel("click here once to extract data. (might take some time)");
		contentPane.add(text1);
		
		Activate = new JButton();
		Activate.setText("Extract data!");
		contentPane.add(Activate);

		blank = new JLabel(" ");
		contentPane.add(blank);
		
		openFileListener ofl = new openFileListener(firstFile, this);
		loadImage.addActionListener(ofl);
		
		steganographyExtract s = new steganographyExtract();
		ActivateExtractListener ahl = new ActivateExtractListener(s, this);
		Activate.addActionListener(ahl);
	}
	
	public String getImage()
	{
		//System.out.print(file1);
		return file1;
	}
	
	public void setImage(String x)
	{
		file1 = x;
	}
}