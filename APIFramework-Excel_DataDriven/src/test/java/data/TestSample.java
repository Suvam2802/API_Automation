package data;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

	public static void main(String[] args) throws IOException {


		DataDriven dv = new DataDriven();
		ArrayList data = dv.getdata("Addprofile","testdata");
		
		System.out.println(data.get(1));
		
		System.out.println(data.get(2));
		
		System.out.println(data.get(3));

	}

}
