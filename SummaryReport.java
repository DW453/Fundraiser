/*
 * This program will create a subtotal report for donation by students
 * Deven Woudenberg 1/25/2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;

public class SummaryReport {
	
	static boolean eof = false;
	static NumberFormat nf;		//currency formatter
	static PrintWriter pw;	//used to write to data file
	static Scanner IHCCFUNDScanner;	//.dat file reader
	static String iString;	//generic input string
	static double iDonation;  //data input
	static String iGender;  ///data input
	static String iMajorCode;  //data input
	static String iStudent;  //data input
	static double cMaleRecCtr;  //men record counter
	static double cFemaleRecCtr;  //female record counter
	static double cMaleITRecCtr;  //male information technology counter
	static double cMaleMTRecCtr;  //male manufacturing technology counter
	static double cMaleTTRecCtr;  //male transportation technology counter
	static double cFemaleITRecCtr;  //female information technology counter
	static double cFemaleMTRecCtr;  //female manufacturing technology counter
	static double cFemaleTTRecCtr;  //female transportation technology counter
	static double cITRecCtr;  //information technology counter
	static double cMTRecCtr;  //manufacturing technology counter
	static double cTTRecCtr;  //transportation technology counter
	static double cTotRecCtr;  //overall counter
	static double cMaleAmt;  //male donation amount
	static double cFemaleAmt;  //female donation amount
	static double cMaleITAmt;  //male information technology amount raised
	static double cMaleMTAmt;  //male manufacturing technology amount raised
	static double cMaleTTAmt;  //male transportation technology amount raised
	static double cFemaleITAmt;  //female information technology amount raised
	static double cFemaleMTAmt;  //female manufacturing technology amount raised
	static double cFemaleTTAmt;  //female transportation technology amount raised
	static double cITAmt;  //information technology amount raised
	static double cMTAmt;  //manufacturing technology amount raised
	static double cTTAmt;  //transportation technology amount raised
	static double cTotAmt;  //overal amount raised
	static double cMaleAvg;  //male average
	static double cFemaleAvg;  //female average
	static double cMaleITAvg;
	static double cFemaleITAvg;
	static double cMaleMTAvg;
	static double cFemaleMTAvg;
	static double cMaleTTAvg;
	static double cFemaleTTAvg;
	static double cITAvg;
	static double cMTAvg;
	static double cTTAvg;
	static double cTotAvg;
	static String oMaleAmt;
	static String oMaleAvg;
	static String oMaleITAmt;
	static String oMaleMTAmt;
	static String oMaleTTAmt;
	static String oMaleITAvg;
	static String oMaleMTAvg;
	static String oMaleTTAvg;
	static String oFemaleAmt;
	static String oFemaleAvg;
	static String oFemaleITAmt;
	static String oFemaleMTAmt;
	static String oFemaleTTAmt;
	static String oFemaleITAvg;
	static String oFemaleMTAvg;
	static String oFemaleTTAvg;
	static String oITAmt;
	static String oITAvg;
	static String oMTAmt;
	static String oMTAvg;
	static String oTTAmt;
	static String oTTAvg;
	static String oTotAmt;
	static String oTotAvg;

	public static void main(String[] args) {
		//do init
		init();

		//call loop
		while (!eof) {
		if(iGender.equals("M")) {
			malecalcs();
			calcs();
			read();	
		}
		if(iGender.equals("F")) {
			femalecalcs();
			calcs();
			read();	
		}
		//call averages
		averages();
		
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
			pw = new PrintWriter(new File ("summary.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
				
		//initial read
		read();
		
		//do headings
		headings();
		
	}
	
	public static void headings() {
		//print report title headings
		pw.format("%-50s%-55s%10s%n%n","Indian Hills Community College","Summary Report","mm/dd/yyyy");
		
		//print column headings
		pw.format("%-41s%-24s%-30s%20s%n%n","Category","Record Count","Amount Raised","Average Contribution");
	}
	
	public static void malecalcs() {
		//male ctr
		cMaleRecCtr+=1;
		
		//male information technology 
		if(iMajorCode.equals("01")||iMajorCode.equals("06")||iMajorCode.equals("08")||iMajorCode.equals("09")||iMajorCode.equals("10")) {
		cMaleITRecCtr+=1; 
		cMaleITAmt+=iDonation;}
			
		//male manufacturing technology 
		if(iMajorCode.equals("04")||iMajorCode.equals("05")||iMajorCode.equals("07")||iMajorCode.equals("11")) {
		cMaleMTRecCtr+=1;
		cMaleMTAmt+=iDonation;}
		
		//male transportation technology 
		if(iMajorCode.equals("02")||iMajorCode.equals("03")||iMajorCode.equals("12")||iMajorCode.equals("13")) {
		cMaleTTRecCtr+=1;
		cMaleTTAmt+=iDonation;}
		
		//male amount raised
		cMaleAmt+=iDonation;
		
	}
	
	public static void femalecalcs() {
		//female ctr
		cFemaleRecCtr+=1;
		
		//female information technology 
		if(iMajorCode.equals("01")||iMajorCode.equals("06")||iMajorCode.equals("08")||iMajorCode.equals("09")||iMajorCode.equals("10")) {
		cFemaleITRecCtr+=1; 
		cFemaleITAmt+=iDonation;}
			
		//female manufacturing technology 
		if(iMajorCode.equals("04")||iMajorCode.equals("05")||iMajorCode.equals("07")||iMajorCode.equals("11")) {
		cFemaleMTRecCtr+=1;
		cFemaleMTAmt+=iDonation;}
		
		//female transportation technology 
		if(iMajorCode.equals("02")||iMajorCode.equals("03")||iMajorCode.equals("12")||iMajorCode.equals("13")) {
		cFemaleTTRecCtr+=1;
		cFemaleTTAmt+=iDonation;}
		
		//female amount raised
		cFemaleAmt+=iDonation;
	}
	
	public static void calcs() {
		//information technology ctr
		cITRecCtr = cMaleITRecCtr+cFemaleITRecCtr;
		
		//manufacturing technology ctr
		cMTRecCtr = cMaleMTRecCtr+cFemaleMTRecCtr;
		
		//transportation technology ctr
		cTTRecCtr = cMaleTTRecCtr+cFemaleTTRecCtr;
		
		//overall ctr
		cTotRecCtr = cITRecCtr+cMTRecCtr+cTTRecCtr;
		
		//information technology amount raised
		cITAmt=cMaleITAmt+cFemaleITAmt;
		
		//manufacturing technology amount raised
		cMTAmt=cMaleMTAmt+cFemaleMTAmt;
		
		//transportation tecnology amount raised
		cTTAmt=cMaleTTAmt+cFemaleTTAmt;
		
		//overall amount raised
		cTotAmt=cITAmt+cMTAmt+cTTAmt;
	}
	
	public static void averages() {
		//male averages
		cMaleAvg=cMaleAmt/cMaleRecCtr;
		
		//female averages
		cFemaleAvg=cFemaleAmt/cFemaleRecCtr;
		
		//male it averages
		cMaleITAvg=cMaleITAmt/cMaleITRecCtr;
		
		//female it averages
		cFemaleITAvg=cFemaleITAmt/cFemaleRecCtr;
		
		//male mt averages
		cMaleMTAvg=cMaleMTAmt/cMaleRecCtr;
		
		//female mt averages
		cFemaleMTAvg=cFemaleMTAmt/cFemaleMTRecCtr;
		
		//male tt averages
		cMaleTTAvg=cMaleTTAmt/cMaleTTRecCtr;
		
		//female tt averages
		cFemaleTTAvg=cFemaleTTAmt/cFemaleTTRecCtr;
		
		//it averages
		cITAvg=cITAmt/cITRecCtr;
		
		//mt averages
		cMTAvg=cMTAmt/cMTRecCtr;
		
		//tt averages
		cTTAvg=cTTAmt/cTTRecCtr;
		
		//total average
		cTotAvg=cTotAmt/cTotRecCtr;
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
	
	public static void closing() {
		//currency format
		oMaleAmt = nf.format(cMaleAmt);
		oMaleAvg = nf.format(cMaleAvg);
		oMaleITAmt = nf.format(cMaleITAmt);
		oMaleMTAmt = nf.format(cMaleMTAmt);
		oMaleTTAmt = nf.format(cMaleTTAmt);
		oMaleITAvg = nf.format(cMaleITAvg);
		oMaleMTAvg = nf.format(cMaleMTAvg);
		oMaleTTAvg = nf.format(cMaleTTAvg);
		oFemaleAmt = nf.format(cFemaleAmt);
		oFemaleAvg = nf.format(cFemaleAvg);
		oFemaleITAmt = nf.format(cFemaleITAmt);
		oFemaleMTAmt = nf.format(cFemaleMTAmt);
		oFemaleTTAmt = nf.format(cFemaleTTAmt);
		oFemaleITAvg = nf.format(cFemaleITAvg);
		oFemaleMTAvg = nf.format(cFemaleMTAvg);
		oFemaleTTAvg = nf.format(cFemaleTTAvg);
		oITAmt = nf.format(cITAmt);
		oITAvg = nf.format(cITAvg);
		oMTAmt = nf.format(cMTAmt);
		oMTAvg = nf.format(cMTAvg);
		oTTAmt = nf.format(cTTAmt);
		oTTAvg = nf.format(cTTAvg);
		oTotAmt = nf.format(cTotAmt);
		oTotAvg = nf.format(cTotAvg);
		
		//print male 
		pw.format("%-45s%-21s%-38s%11s%n%n","Male",cMaleRecCtr,oMaleAmt,oMaleAvg);
		
		pw.format("%-45s%-21s%-38s%11s%n%n","Female",cFemaleRecCtr,oFemaleAmt,oFemaleAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Information Technology",cITRecCtr,oITAmt,oITAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Manufacturing Technology",cMTRecCtr,oMTAmt,oMTAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Transportation Technology",cTTRecCtr,oTTAmt,oTTAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Male Information Technology",cMaleITRecCtr,oMaleITAmt,oMaleITAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Male Manufacturing Technology",cMaleMTRecCtr,oMaleMTAmt,oMaleMTAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Male Transportation Technology",cMaleTTRecCtr,oMaleTTAmt,oMaleTTAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Female Information Technology",cFemaleITRecCtr,oFemaleITAmt,oFemaleITAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Female Manufacturing Technology",cFemaleMTRecCtr,oFemaleMTAmt,oFemaleMTAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Female Transportation Technology",cFemaleTTRecCtr,oFemaleTTAmt,oFemaleTTAvg);

		pw.format("%-45s%-21s%-38s%11s%n%n","Overall",cTotRecCtr,oTotAmt,oTotAvg);
		
		pw.close();
	}

}
