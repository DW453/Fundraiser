/*
 * This program will create a subtotal report for donation by students
 * Deven Woudenberg 1/25/2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;

public class SubtotalReport {
	
	static boolean eof = false;
	static NumberFormat nf;		//currency formatter
	static PrintWriter pw;	//used to write to data file
	static String hMajorCode;  //control break
	static String iMajorCode;  //data input
	static String oMajor;  //major ouput
	static String iStudent;  //data input
	static String iGender;  //data input
	static String oGender;  //gender output
	static double iDonation;  //data input
	static String oDonation;  //donation output
	static Scanner IHCCFUNDScanner;	//.dat file reader
	static String iString;	//generic input string
	static double cGtDonateAmt;  //grand total donation amount
	static String oGtDonateAmt;  //grand total donation amount output
	static double cGtStudentCtr;  //grand total student counter
	static double cSubDonateAmt;  //subtotal donation amount accumulator
	static String oSubDonateAmt;  //subtotal donation amount output
	static double cSubRecCtr;  //subtotal record counter

	

	public static void main(String[] args) {
		//call initialization
		init();
		
		//call loop
		while (!eof) {
		if (!hMajorCode.equals(iMajorCode)) {
		subtotals();
		}
		calcs();
		output();
		read();
		
		}
		//call closing
		closing();
	}

	public static void init() {
		//set formatter to use U.S. currency format
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
		
		//set scanner to the input file
		//File payrollFile = new File("IHCCFUND.DAT");
		try {
			IHCCFUNDScanner = new Scanner(new File("IHCCFUND.DAT"));
			IHCCFUNDScanner.useDelimiter(System.getProperty("line.separator"));
		} catch (FileNotFoundException e1) {
			System.out.println("File error");
			System.exit(1);
		}
		
		//initialize the PrintWriter object
		try {
			pw = new PrintWriter(new File ("subtotal.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
				
		//initial read
		read();
		
		//do headings
		headings();
		
		hMajorCode=iMajorCode;
		
	}
	
	public static void calcs() {
		//grand total donation amount
			cGtDonateAmt+=iDonation;
		
		//grand total student counter
		cGtStudentCtr+=1;
		
		//subtotal donation amount per major
		cSubDonateAmt+=iDonation;
		
		//subtotal record counter per major
		cSubRecCtr+=1;
		
	}
	
	public static void output() {
		//set major output
		if (iMajorCode.equals("01")) {
			oMajor="Diesel Power Systems Technology";
		}
		if (iMajorCode.equals("02")) {
			oMajor="Computer Software Development";
		}
		if (iMajorCode.equals("03")) {
			oMajor="Automotive Technology";
		}
		if (iMajorCode.equals("04")) {
			oMajor="Laser/Electro-optics Technology";
		}
		if (iMajorCode.equals("05")) {
			oMajor="Robotics/Automation Technology";
		}
		if (iMajorCode.equals("06")) {
			oMajor="Digital Forensics";
		}
		if (iMajorCode.equals("07")) {
			oMajor="Machine Technology";
		}
		if (iMajorCode.equals("08")) {
			oMajor="Geospatial Technology";
		}
		if (iMajorCode.equals("09")) {
			oMajor="Administrative Assistant";
		}
		if (iMajorCode.equals("10")) {
			oMajor="Accounting Assistant";
		}
		if (iMajorCode.equals("11")) {
			oMajor="Welding Technology";
		}
		if (iMajorCode.equals("12")) {
			oMajor="Automotive Collision Technology";
		}
		if (iMajorCode.equals("13")) {
			oMajor="Aviation Pilot Training";
		}
		//set gender output
		if (iGender.equals("M")) {
			oGender="Male";
		}
		if (iGender.equals("F")) {
			oGender="Female";
		}
		
		//currency format donation
		oDonation = nf.format(iDonation);
		
		//print detail line
		pw.format("%-32s%-23s%-31s%29s%n%n",iStudent,oGender,oMajor,oDonation);
	}
	
	public static void subtotals() {
		//currency format sub donation
		oSubDonateAmt = nf.format(cSubDonateAmt);
		
		//print subtotal line
		pw.format("%-53s%-19s%-13s%-17s%9s%n%n",oMajor,"Number of Records:",cSubRecCtr,"Donation Amount:",oSubDonateAmt);
		
		//reset control break
		hMajorCode=iMajorCode;
		
		//reset subtotal donation amount per major
		cSubDonateAmt=0;
		
		//reset subtotal record counter per major
		cSubRecCtr=0;
	}
	
	public static void read() {
		String record;	//string used to hold entire record being read
		//read until there are no more records
		if (IHCCFUNDScanner.hasNext()) {
			record = IHCCFUNDScanner.next();
			iStudent = record.substring(0,7);	//file position 1 - 7
			iGender = record.substring(7,8);	//file position 8 - 8
			iMajorCode = record.substring(8,10);  //file position 9 - 10
			iString = record.substring(10,16);		//file position 11 - 16
			iDonation = Double.parseDouble(iString);
		}
		else {
			eof=true;	//no more records, eof set to true
		}
		
	}
	
	public static void headings() {
		
		//print Report title headings
		pw.format("%-50s%15s%50s%n%n","Indian Hills Community College","Subtotal Report","mm/dd/yyyy");
		
		//print column headings
		pw.format("%-32s%-34s%-34s%15s%n","Student ID","Gender","Major","Donation Amount");
	}
	
	public static void closing() {		
		//currency format grand total donations collected
		oGtDonateAmt = nf.format(cGtDonateAmt);
		
		
		//print grand totals
		pw.format("%-26s%-27s%-27s%-35s","Total Number of Students:",cGtStudentCtr,"Total Donations Collected:",oGtDonateAmt);
		
		pw.close();
	}
}
