package projectPart2;

import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileNotFoundException;
import projectPart2.DataPoint;
import java.util.Random;

public class KNNPredictor extends Predictor {
	private int K = 3;
	private int passengerSurvived = 0;
	private int passengernotSurvived=0;
	private ArrayList<DataPoint> dataSet = new ArrayList<DataPoint>(); // create a new Arraylist
	public KNNPredictor(int kParam, int Survived, int notSurvived) {
		this.K = kParam;
		this.passengerSurvived = Survived;
		this.passengernotSurvived = notSurvived;
	}
	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
	}
		
		public ArrayList<DataPoint> readData(String filename) {
			try {
				Scanner scanner = new Scanner(new File(filename));
				scanner.useLocale(Locale.US);
				scanner.nextLine();
				while (scanner.hasNextLine()) {
					List<String> records = getRecordFromLine(scanner.nextLine());

//					boolean label = false;
//					Random rand = new Random();
//					double randNum = rand.nextDouble();
//					0 = notSurvived and 1=survived
//					DataPoint points;
//					boolean trainer = false;
					if (!records.get(5).isEmpty() && records.size() == 7) {
						Random rand = new Random();
						double randNum = rand.nextDouble();
						boolean survived = false;
//						the data reserved for the training is 90%
						if (randNum < 0.9 && records.get(1).equals("1")) {
							survived = true;
							passengernotSurvived +=1;
						}
						else if (records.get(1).equals("1")) {
							passengernotSurvived+=1;
						}
						else {
							passengerSurvived++;
							survived=true;
						}
						
						DataPoint data = new DataPoint(records.get(1), survived, Double.parseDouble(records.get(5)), Double.parseDouble(records.get(6)));
						dataSet.add(data);
//						if (trainer) {
//							points = new DataPoint("0", label, Double.parseDouble(records.get(5)), Double.parseDouble(records.get(6)));
//
//						} else {
//							points = new DataPoint(records.get(1), label, Double.parseDouble(records.get(5)), Double.parseDouble(records.get(6)));
//
//						}
						
						
					}
					
					
		}

	
			}
			catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
			System.out.println("Number of Survived:" + passengerSurvived);
			System.out.println("Number of Training DataPoint:" + passengernotSurvived);
//			Create new local ArrayList copy over elements from dataSet to local ArrayList then return local ArrayList
			ArrayList<DataPoint> datas = new ArrayList<DataPoint>();
			Collections.copy(dataSet, datas);
//			
			return dataSet;
	}
		
		 private double getDistance(DataPoint p1, DataPoint p2) {
			 double x1 = p1.getF1();
			 double x2 = p2.getF1();
			 double y2 =p2.getF2();
			 double y1 = p1.getF2();
			 return Math.sqrt((y2 - y1) * (y2-y1) + (x2-x1) * (x2-x1));
		 }
		 
		 
		 public String test(DataPoint data) {
			 Double[][] Array2D = new Double[passengerSurvived + passengernotSurvived][2];
			 int survived = 0;
			 int notSurvived = 0;
			 
			 if(!data.getIsTest()) {
				 return ("Data is not a test DataPoint");
			 }
			 else {
				 // Compute the distance between the input point versus every train point in the 
				 //data set and store the distance and the label into a 2D Array, {distance, label}.
				 for(int i = 0; i<(passengerSurvived + passengernotSurvived); i++) {
					 double distance = getDistance(data, dataSet.get(i));
					 Array2D[i][0] = distance;
					 Array2D[i][1] = Double.parseDouble(dataSet.get(i).getLabel());
				 }
				 
				 java.util.Arrays.sort(Array2D, new java.util.Comparator<Double[]>() {
					 public int compare(Double[]a,Double[]b){
						 return a[0].compareTo(b[0]);
						 
					 }
				
				 });
				 
				 for (int i = 0; i < K; i++) {
					 if (Array2D[i][1] == 1.0) {
						 survived++;
					 } else {
						 notSurvived++;
					 }
				 }
				 
			 }
			 if (survived > notSurvived) {
				 return "1";
			 }
			return "0";
			 
		 }
		
		 public double getAccuracy(ArrayList<DataPoint> data) {
			 double truePositive = 0.0;
			 double falsePositive = 0.0;
			 double trueNegative = 0.0;
			 double falseNegative = 0.0;
			 
			 for (int i = 0; i < data.size(); i++) {
				 String label = test(data.get(i));
//				 System.out.println("Label" + label);
				 if (label.equals("1") && data.get(i).getLabel().equals("1")) {
					 truePositive+= 1.0;
				 }
				 else if (label.equals("1") && data.get(i).getLabel().equals("0")) {
					 falsePositive+=1.0;
				 }
				 else if (label.equals("0") && data.get(i).getLabel().equals("1")) {
					 falseNegative+=1.0;	 
				 }
				 else if (label.equals("0") && data.get(i).getLabel().equals("0")) {
					 trueNegative+=1.0;
				 }
				 
				 
				 
		}	
			 double result = (truePositive+trueNegative) / (truePositive + trueNegative + falsePositive + falseNegative);
			 return (result);
			 
		 }
		 
		 public double getPrecision(ArrayList<DataPoint> data) {
			 double truePositive = 0;
			 double falsePositive = 0;
			 double trueNegative = 0;
			 double falseNegative = 0;
			 
			 for (int i = 0; i < data.size(); i++) {
				 String label = test(data.get(i));
				 if (label.equals("1") && data.get(i).getLabel().equals("1")) {
					 truePositive+= 1;
				 }
				 else if (label.equals("1") && data.get(i).getLabel().equals("0")) {
					 falsePositive+=1;
				 }
				 else if (label.equals("0") && data.get(i).getLabel().equals("1")) {
					 falseNegative+=1;	 
				 }
				 else if (label.equals("0") && data.get(i).getLabel().equals("0")) {
					 trueNegative+=1;
				 }		
		 }
			 double result2 = (truePositive) / (truePositive + falseNegative);
			 return (result2);
		 }

}

	
	


