package steganography;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

class openFileListener extends JFrame implements ActionListener
{
	static JLabel chosenFile;
	
	JLabel first;
	window values;
	
	openFileListener(JLabel x, window y)
	{
		first = x;
		values = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser loader = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int worked = loader.showOpenDialog(null);
		
		if(worked == JFileChooser.APPROVE_OPTION)
		{
			first.setText(loader.getSelectedFile().getAbsolutePath());
			values.setFile1(loader.getSelectedFile().getAbsolutePath());
		}
		else
		{
			first.setText("no file selected.");
			values.setFile1(null);
		}
	}
}