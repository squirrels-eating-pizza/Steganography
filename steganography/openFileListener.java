package steganography;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

public class openFileListener implements ActionListener {

static JLabel chosenFile;
	
	JLabel first;
	Window values;
	
	openFileListener(JLabel x, Window y)
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
			values.setImage(loader.getSelectedFile().getAbsolutePath());
		}
		else
		{
			first.setText("no file selected.");
			values.setImage(null);
		}
	}
}
