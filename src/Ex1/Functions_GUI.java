package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Functions_GUI implements functions {
	
	ArrayList<function> func=new ArrayList<function>();
	@Override
	public boolean add(function arg0) {
		// TODO Auto-generated method stub
		func.add(arg0);
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		// TODO Auto-generated method stub
	return	func.addAll(arg0);
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		func.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof function )
		{
			return func.contains(arg0);
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		
		return func.containsAll(arg0);
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return func.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		
		return this.func.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof function )
			 return func.remove(arg0);
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		
		return func.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return func.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return func.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return func.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return func.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub

		try {

			FileReader file_reader=new FileReader(file);
			BufferedReader br = new BufferedReader(file_reader);
			String st; 
			ComplexFunction cf = new ComplexFunction();
			  while ((st = br.readLine()) != null) {
			    System.out.println(st); 
			  func.add(cf.initFromString(st));
			  }
		}catch(IOException e) {
			e.printStackTrace();
		}
		}
	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		StringBuffer bf = new StringBuffer();
		String s=new String();
		File file1 = new File (file);
        BufferedWriter out = new BufferedWriter(new FileWriter(file1));
		for(int i=0;i<func.size();i++) {
			bf=new StringBuffer (func.get(i).toString());
			out.write(new String(bf));
			out.newLine();
		}
		out.close();
	}
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		int n = resolution;
		StdDraw.setCanvasSize(width, height);
		int size = func.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		setGrid(rx, ry);
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {
				yy[a][i] = func.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(0,ry.get_min(), 0, ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);
			System.out.println(a+") "+Colors[a]+"  f(x)= "+func.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}
	}
	private class GUI_params {
		int Width;
		int Height;
		int Resolution;
		int[] Range_X;
		int[] Range_Y;
	}
	private void setGrid(Range rx, Range ry) {
		for(double i = ry.get_max(); i>=ry.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(-0.3, i, (int)i+"");
		}
		for(double i = rx.get_max(); i>=rx.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(i, ry.get_min(), i , ry.get_max());
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(i, -0.35, (int)i+"");
		}

	}
	@Override
	public void drawFunctions(String json_file)
	{
		// TODO Auto-generated method stub	
		
       try 
        {
        	 FileReader file_read=new FileReader(json_file);
        	 JSONObject o = (JSONObject)new JSONParser().parse(new FileReader(json_file));
                  JSONArray X, Y;
                  X= (JSONArray) o.get("Range_X");
                  Y= (JSONArray) o.get("Range_Y");
            long Width = (long) o.get("Width");
            int wid=(int) Width;
            long Height = (long) o.get("Height");
            int hei= (int) Height;
            long Resolution=(long) o.get("Resolution");
            int res=(int) Resolution;
            long dx[] =new long[2];
            long dy[] =new long[2];
            dx[0] = (long) X.get(0);
            dx[1] = (long) X.get(1);
            dy[0] = (long) Y.get(0);
            dy[1] = (long) Y.get(1);

            int dxx[]=new int[2];
            int dyy[]=new int[2];
            dxx[0]  = (int) dx[0];
            dyy[0]= (int) dy[0];
            dxx[1]  = (int) dx[1];
            dyy[1]= (int) dy[1];
            Range _x=new Range(dxx[0],dxx[1]);
            Range _y=new Range(dyy[0],dyy[1]);
            
            this.drawFunctions(wid, hei, _x, _y,res);
        
        
        	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public function get(int i)
	{
		// TODO Auto-generated method stub
		return this.func.get(i);
	}
	
	public String toString() {
		String ans="";
		for(int i=0;i<func.size();i++) {
			ans+=func.get(i) +"\n";
		}
		return ans;
	}
	public static Color[] Colors = {Color.blue, Color.cyan,
			Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};

}