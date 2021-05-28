package steganography;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class steganographyExtract {
	
	steganographyExtract()
	{
		//blank constructor.
	}
	
	/*
	public static void main(String[] args)
	{
		BufferedImage picture = null;
		byte[] data = null;
		
		if(0 < args.length)
		{
			picture = loadImage(args[0]);
		}
		else
			System.out.println("error finding image file.\n");
		
		data = extractData(picture);
		parseData(data);
	}
	*/
	
	public String doWork(String imgName)
	{
		BufferedImage picture = null;
		byte[] data = null;
		String name = null;
		
		if(imgName != null)
		{
			picture = loadImage(imgName);
			
			data = extractData(picture);
			name = parseData(data);
		}
		else
			System.out.println("error finding image file.\n");
		
		
		//System.out.print("found: " + name + "\n");
		return name;
	}
	
	private static BufferedImage loadImage(String imageName)
	{
		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}
	private static byte[] extractData(BufferedImage pic)
	{
		byte[] data = new byte[pic.getWidth() * pic.getHeight()];
		
		byte temp[] = new byte[1];
		
		int counter = 0;
		for (int x = 0; x < pic.getWidth(); x++)
		{
			for (int y = 0; y < pic.getHeight(); y++)
			{
				Color pixelColor = new Color(pic.getRGB(x, y));
				temp[0] = (byte) ((pixelColor.getRed() & (byte)3) << 6);
				temp[0] = (byte) (temp[0] | (pixelColor.getGreen() & 7) << 3);
				temp[0] = (byte) (temp[0] | (pixelColor.getBlue() & 7));
				
				data[counter] = temp[0];
				counter++;
			}
		}
		return data;
	}
	
	private static String parseData(byte[] data)
	{
		OutputStream out = null;
		String name = null;
		
		byte[] temp = null;
		temp = new byte[data[0]];
		if (data != null)
		{
			int y = 0;
			for (int x = 1; x <= data[0]; x++)
			{
				temp[y] = data[x];
				y++;
			}
			try {
				name = new String(temp, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//System.out.print("found: " + name + "\n");
		}
		//System.out.print(data[0]);
		//System.out.print((2 + data[0]) + (data[data[0]+1]));
		//System.out.print(data[data[0]+1]);
		
		try {
			out = new FileOutputStream(name);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//long dataLength = ((((data[data[0]+1] << 8) | (255&data[data[0]+2]))) & 65535);
		
		long dataLength = ((((data[data[0]+1] << 16) | (data[data[0]+2] << 8)& 65535 | (255&data[data[0]+3]))) & 16777215);
		//dataLength = dataLength | data[data[0]+2];
		//System.out.print((data[data[0]+1] << 16) + "\n");
		//System.out.print((data[data[0]+2] << 8) + "\n");
		//System.out.print(data[data[0]+3] + "\n");
		//System.out.print(((data[data[0]+1] << 8) | ( 255&data[data[0]+2])) + "\n");
		
		//System.out.print(dataLength);
		try {
			//for (int x = data[0] + 2; x < (2 + data[0]) + (data[data[0]+1]); x++)
			for (int x = data[0] + 4; x < (4 + data[0]) + (dataLength); x++)
			{
				out.write(data[x]);
				//System.out.print(data[x] + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return name;
	}
}