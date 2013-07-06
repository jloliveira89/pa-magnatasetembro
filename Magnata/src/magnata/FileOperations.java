package magnata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileOperations {
	public FileOperations()
	{
	
	}

	
	public void writeFile(String name, Object ob)
	{
		try{
			FileOutputStream fos = new FileOutputStream(name);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(ob);
		    oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Object readFile(String name){
		Object ob = new Object();
		
		FileInputStream fin;
		try {
			fin = new FileInputStream(name);
			ObjectInputStream ois;
			ois = new ObjectInputStream(fin);
			ob = ois.readObject();		
			ois.close();
			
		} catch (IOException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		return ob;
	}
	
	public void writeNameFileToTXT(String name)
	{
		BufferedWriter out;
		
		try {
			out = new BufferedWriter(new FileWriter("savegames.txt",true));
			out.write(name+"\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> readNameFilesFromTXT()
	{
		BufferedReader in;
		String strLine;
		ArrayList<String> s_readStrings = new ArrayList<>();
		try {
			in = new BufferedReader(new FileReader("savegames.txt"));
			 while((strLine = in.readLine())!= null)
			 {
				s_readStrings.add(strLine);
			 }
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return s_readStrings;
	}
	
}
