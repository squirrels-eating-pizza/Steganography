package steganography;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class steganography {
	steganography()
	{
		//blank constructor.
	}
	
	/*
	public static void main(String[] args)
	{
		
		window gui;
		gui = new window();
		gui.setSize(500,250);
		gui.setVisible(true);
		gui.setResizable(false);
		
		
		BufferedImage picture = null;
		byte[] data = null;
		
		if(0 < args.length)
		{
			picture = loadImage(args[0]);
		}
		else
			System.out.println("error finding image file.\n");
		if(1 < args.length)
		{
			data = loadText(args[1]);
		}
		else
			System.out.println("error finding text file.\n");
		
		if(data.length < picture.getHeight()*picture.getWidth())
		{
			picture = hidingData(picture, data);
			writePicture(picture);
		
			System.out.print("done! data has been hidden.\n");
		}
		else
		{
			System.out.print("data you are trying to hide wont fit in the image selected.");
		}
	}
	*/
	public int doWork(String ImageName, String FileName)
	{
		int result = 3;
		//System.out.print(ImageName + "\t" + FileName + "\n");
		
		if(ImageName != null && FileName != null) {
			BufferedImage picture = null;
			byte[] data = null;
		
				picture = loadImage(ImageName);

				data = loadText(FileName);

			if(data.length < picture.getHeight()*picture.getWidth())
			{
				picture = hidingData(picture, data);
				writePicture(picture);
				
				System.out.print("done! data has been hidden.\n");
				result = 0;
			}
			else
			{
				System.out.print("data you are trying to hide wont fit in the image selected.");
				result = 1;
			}
		}
		else
		{
			result = 3;
		}
		return result;
	}
	
	
	private byte[] loadText(String fileName)
	{
		InputStream data = null;
		try {
			data = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1).trim();
		
		byte[] nameLen = {(byte)(fileName.length())};
		byte[] NameData = fileName.getBytes();
		byte[] insideData = null;
		
		//adding nameData to the nameLen.
		byte[] byteData = new byte[nameLen.length + NameData.length];
		System.arraycopy(nameLen, 0, byteData, 0, nameLen.length);
		System.arraycopy(NameData, 0, byteData, nameLen.length, NameData.length);
		
		try {
			insideData = data.readAllBytes();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//for( int x = 0; x < byteData.length; x++)
			//System.out.print(byteData[x] + " ");
		byte[] datalen = {(byte)(insideData.length >> 16), (byte)(insideData.length >> 8), (byte)insideData.length};
		//System.out.print(insideData.length + "\n");
		//System.out.print(datalen[0] + "\n");
		//System.out.print(datalen[1] + "\n");
		//System.out.print(datalen[2] + "\n");
		
		//adding the length of the data to the size and name of the file.
		byte[] addingDataLen = new byte[byteData.length + datalen.length];
		System.arraycopy(byteData, 0, addingDataLen, 0, byteData.length);
		System.arraycopy(datalen, 0, addingDataLen, byteData.length, datalen.length);
		
		//finish by adding the data to the end of the previous 3 things.
		byte[] completeData = new byte[addingDataLen.length + insideData.length];
		System.arraycopy(addingDataLen, 0, completeData, 0, addingDataLen.length);
		System.arraycopy(insideData, 0, completeData, addingDataLen.length, insideData.length);
		
		return completeData;
	}
	
	private BufferedImage loadImage(String imageName)
	{
		BufferedImage pic = null;
		//File temppic = new File("cover.png");
		try {
			//pic = ImageIO.read(temppic);
			pic = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}
	
	private BufferedImage hidingData(BufferedImage pic, byte[] data)
	{
			if(pic != null)
			{
				//loop through all of the pixels in the image file.
				
				int counter = 0;
				//for( int w = 0; w < data.length;w++)
				//{
					for (int x = 0; x < pic.getWidth(); x++)
					{
						for (int y = 0; y < pic.getHeight(); y++)
						{
							if(counter < data.length)
							{
								//get the color of each pixel.
								Color pixColor = new Color(pic.getRGB(x, y));
								//change the lowest bit of each pixel in order of the data that is being hidden.
								pixColor = new Color(
										(pixColor.getRed() & ~3 | ((data[counter] & 192) >> 6)),
										(pixColor.getGreen() & ~7 | ((data[counter] & 56) >> 3)),
										(pixColor.getBlue() & ~7) | (data[counter] & 7));
								//set the color of the pixels.
								pic.setRGB(x, y, pixColor.getRGB());
								
								counter++;
							}
							else
							{
								//get the color of each pixel.
								Color pixColor = new Color(pic.getRGB(x, y));
								//change the lowest bit of each pixel in order of the data that is being hidden.
								pixColor = new Color((pixColor.getRed() & ~3), (pixColor.getGreen() & ~7), (pixColor.getBlue() & ~7));
								//set the color of the pixels.
								pic.setRGB(x, y, pixColor.getRGB());
							}
						}
						//System.out.print("\n");
					}
			}
			return pic;
	}
	private void writePicture(BufferedImage pic)
	{
		if(pic != null) {
			try {
				Boolean temp = null;
				temp = ImageIO.write(pic, "png", new File("output.png"));
				//System.out.print(temp + "\n");
				if(temp)
				{
					//
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}