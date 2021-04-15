package projectPart2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;    // all of the Swing objects
import java.awt.*;       // more windowing components, including Container
import java.io.*;

public class KNNPredictorDriver {

	public static void main(String[] args) {
		System.out.println("Enter a number: ");
		Scanner c = new Scanner(System.in);
		int k = 0;
		boolean run = true;
		try {
//			to break ties, K must be an odd number
			k = c.nextInt();
		
		}
		catch (InputMismatchException e){
			System.out.println("Error");
			run = false;
		}
		if (run) {
			if (k%2 == 0) {
				System.out.println("Error");
			}
			else {
				JFrame myFrame = new JFrame();
				myFrame.setTitle("Data Points");
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container contentPane = myFrame.getContentPane();
				//Instantiate a KNNPredictor object
				KNNPredictor obj = new KNNPredictor(3, 0, 0);
				// Read titanic.csv into an ArrayList of DataPoints as using readData. Make sure to split the 
				//data into train and test using the random number generator.
				ArrayList <DataPoint> data = obj.readData("titanic.csv");
				DecimalFormat decFormat = new DecimalFormat("#%");
				System.out.println("Accuracy:" + (decFormat.format(obj.getAccuracy(data))));
				System.out.println("Precision:" + (decFormat.format(obj.getPrecision(data))));
				JLabel accuracy = new JLabel("Accuracy:" + (decFormat.format(obj.getAccuracy(data))));
				contentPane.add(accuracy);
				JLabel precision = new JLabel("Precision:" + (decFormat.format(obj.getPrecision(data))));
				contentPane.add(precision);
				
				myFrame.setVisible(true);
				myFrame.setLayout(new GridLayout(2,2));
				myFrame.setSize(200,200);
				
			}
		}
			c.close();
		
	
		}


}
