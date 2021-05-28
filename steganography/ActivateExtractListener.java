package steganography;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActivateExtractListener extends JFrame implements ActionListener {

	Container resultPane;
	JLabel title;
	JLabel blank;
	steganographyExtract mainProg;
	Window values;


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String result = null;
		
		result = mainProg.doWork(values.getImage());
		//System.out.print(values.getImage() + "\t" + values.getFile() + "\n");
		
		title = new JLabel();
		title.setText("temp");
		
		if (result == null)
		{
			title.setText("an Error occured.");
		}
		else 
		{
			//String temp = (new StringBuilder()).append("File found: ").append(result).toString();
			//String temp = "File found: " + result;
			title.setText("File found: " + result);
		}
		
		resultWindow gui;
		gui = new resultWindow();
		gui.setSize(200,100);
		gui.setVisible(true);
		//gui.setResizable(false);
		
		resultPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultPane.setLayout(new BoxLayout(resultPane, BoxLayout.Y_AXIS));
		resultPane.add(Box.createHorizontalStrut(20));
		
		resultPane.add(title);
		
		blank = new JLabel();
		blank.setText(" ");
		resultPane.add(blank);
		gui.add(resultPane);
	}
	
	ActivateExtractListener(steganographyExtract s, Window val)
	{		
		mainProg = s;
		values = val;
	}
}
