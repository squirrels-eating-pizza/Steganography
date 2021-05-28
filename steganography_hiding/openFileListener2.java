package steganography;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

class openFileListener2 extends JFrame implements ActionListener
{
	static JLabel chosenFile;
	
	JLabel second;
	window values;
	
	openFileListener2(JLabel x, window y)
	{
		second = x;
		values = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser loader = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int worked = loader.showOpenDialog(null);
		
		if(worked == JFileChooser.APPROVE_OPTION)
		{
			second.setText(loader.getSelectedFile().getAbsolutePath());
			values.setFile2(loader.getSelectedFile().getAbsolutePath());
		}
		else
		{
			second.setText("no file selected.");
			values.setFile2(null);
		}
	}
}