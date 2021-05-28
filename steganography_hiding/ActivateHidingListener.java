package steganography;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActivateHidingListener extends JFrame implements ActionListener
{
	Container resultPane;
	JLabel title;
	JLabel blank;
	steganography mainProg;
	window values;


	@Override
	public void actionPerformed(ActionEvent e) {
		
		resultWindow gui;
		gui = new resultWindow();
		gui.setSize(200,100);
		gui.setVisible(true);
		gui.setResizable(false);
		
		
		resultPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultPane.setLayout(new BoxLayout(resultPane, BoxLayout.Y_AXIS));
		resultPane.add(Box.createHorizontalStrut(20));
		
		int result = 3;
		
		result = mainProg.doWork(values.getImage(), values.getFile());
		//System.out.print(values.getImage() + "\t" + values.getFile() + "\n");
		
		title = new JLabel();
		title.setText("temp");
		
		if (result == 0)
		{
			title.setText("done! data has been hidden.");
		}
		else if (result == 1)
		{
			title.setText("data you are trying to hide wont fit in the image selected.");
		}
		else if (result == 3)
		{
			title.setText("missing image or other file.");
		}	
		//title.setFont(new Font("Calibri (body)", Font.BOLD, 20));
		resultPane.add(title);
		
		blank = new JLabel();
		blank.setText(" ");
		resultPane.add(blank);
		gui.add(resultPane);
	}
	
	ActivateHidingListener(steganography s, window val)
	{		
		mainProg = s;
		values = val;
	}
}