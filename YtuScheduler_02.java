import java.awt.Button;
import java.awt.Frame;
import java.awt.List;

import static java.nio.file.StandardCopyOption.*;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.*;  

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
//import com.sun.java_cup.internal.runtime.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YtuScheduler extends JFrame
{
	static Thread t = new Thread(new Runnable(){
		public void run(){
			JOptionPane.showMessageDialog(null, threadjopsmd);
	    }
	});
	private static final long serialVersionUID = -7247545467576093140L;
	protected static String threadjopsmd = null;
	private static boolean success;

	public YtuScheduler() throws AuthenticationException, IOException
	{
		String GDataDKey = "AI39si7Iw2fopIkvtGuNeHbPHqQRZyWuN3m-Cv21PX6CO_3EcqrFjOfWbrS59tifYjB6-oYszwbXegEvgF1JDpMq2WB5jwySLQ";
		String appdata2 = System.getProperty("user.home") + "\\AppData\\Roaming\\";
    	File storedpassword = new File( appdata2 + "YTscheduler/" + GDataDKey);
    	//Find out if the file exists already
    	String loadedusrnme = "";
    	String loadedpsswrd = "";
    	if( storedpassword.exists() ) {
    		Scanner opnScanner = new Scanner(storedpassword);
    		while( opnScanner.hasNext() ) {
    			// Read each line and display its value
    			loadedusrnme = opnScanner.nextLine(); // username2
    			loadedpsswrd = opnScanner.nextLine(); // passwrd2
    		}
    		opnScanner.close();    		
    	}
    	//File chooser
		final JFileChooser fc = new JFileChooser();
	    int answer = fc.showOpenDialog(getComponent(0));
	    File fcstring = null;
	    String filestring = "";
        String filestringstr = null;

	    if (answer == JFileChooser.APPROVE_OPTION)  
	    {  
			fcstring = fc.getSelectedFile();
			filestring = fcstring.toString();
			//System.out.println(fcstring);
			//System.out.println(filestring);
	        String[] filestringarr=filestring.split("\\\\"); // uses SPACE as a delimiter and splits a string in to an array
	        for(int i=0; i<filestringarr.length; i++) {
    			//System.out.println("array place " + i + ":" + args[i]);
	        	filestringstr = filestringarr[i];
    			//System.out.println(filefromargslist);
    		}
			//System.out.println(filestringstr);

	    } else {
	    	System.exit(0);
	    }

	    
	    //Upload information
		Component frame = null;
		Icon icon = null;
		String[] YTcatorgories = { "Autos", "Comedy", "Education", "Entertainment", "Film", "Gaming", "Howto", "Music", "News", "Nonprofits", "People", "Tech", "Sports", "Travel" };
		String[] privacyoption = { "Public", "Private" };
		String[] savelogininformation = { "No", "Yes" };
		JComboBox<?> savelogininformation2 = new JComboBox(savelogininformation);
		JComboBox<?> YTcatorgories2 = new JComboBox<Object>(YTcatorgories);
		JComboBox<?> privacyoption2 = new JComboBox<Object>(privacyoption);
		JTextField username = new JTextField(loadedusrnme); 
		JTextField passwrd = new JPasswordField(loadedpsswrd);
		JTextField desiredtitle = new JTextField(filestringstr);
		JTextArea discription = new JTextArea(5, 30);
		//JTextField location = new JTextField();
		JTextField keywords = new JTextField(filestringstr);
		
		Object[] msg1 = {"Username:", username, "Password:", passwrd, "Save log-in information(You only need to do it once)", savelogininformation2,  "Title", desiredtitle, "Discription", discription , "Catorgory", YTcatorgories2, "Privacy", privacyoption2, "Keywords", keywords, "Upload now?"};
		//Object[] options = { "YES", "NO", "CANCEL" };
		//add(new Button("Browse"))

		//JPanel op = new JPanel();

		JOptionPane op = new JOptionPane(
			msg1,
			JOptionPane.PLAIN_MESSAGE,
			JOptionPane.YES_NO_CANCEL_OPTION,
			null, 
			null,
			null);
		
		JDialog dialog = op.createDialog("Youtube upload");
		dialog.getRootPane().setDefaultButton(null);
		dialog.setVisible(true); // program stops here
		int result = JOptionPane.YES_OPTION;
		//System.out.println(result);
		//System.exit(0);

		try
		{
		    result = ((Integer)op.getValue()).intValue();
		}
		catch(Exception uninitializedValue)
		{}
		if (result == JOptionPane.CANCEL_OPTION){
			System.exit(0);
		}
		String username2 = username.getText();
		String passwrd2 = passwrd.getText();
		String savelogininformation3 = (String) savelogininformation2.getSelectedItem();
		String desiredtitle2 = desiredtitle.getText();
		String discription2 = discription.getText();
		String YTcatorgories3 = (String) YTcatorgories2.getSelectedItem();
		String privacyoption3 = (String) privacyoption2.getSelectedItem();
		//String location2 = location.getText();
		String keywords2 = keywords.getText();
		String fcfileName = fcstring.getCanonicalPath();  
		String ext="";
		int mid= fcfileName.lastIndexOf(".");
		ext=fcfileName.substring(mid+1,fcfileName.length());
        String videomimetype = "video/" + ext;
               
        String[] arr= null;
        if (discription2.length() == 0) {
        	discription2 = filestringstr;
        }
        if (desiredtitle2.length() == 0) {
        	desiredtitle2 = filestringstr;
        }
        /*
        if (desiredtitle2.length() > 60){
            String[] desiredtitle22=desiredtitle2.split(""); // uses NOTHING as a delimiter and splits a string in to an array
            desiredtitle2 = "";
    		for(int i=0; i<desiredtitle22.length; i++) {
    			if ( i < 61 ) {
    				desiredtitle2 = desiredtitle2 + desiredtitle22[i];
    			}
    		}
        }
        */
        if (keywords2.length() < 2) {
        	keywords2 = filestringstr;
        }
       /* if (keywords2.length() > 60){
            String[] keywords22=keywords2.split(""); // splits a string in to an array
            keywords2 = "";
    		for(int i=0; i<keywords22.length; i++) {
    			if ( i < 61 ) {
    				keywords2 = keywords2 + keywords22[i];
    			}
    			
    		}
            arr = keywords2.split(" "); // uses SPACE as a delimiter and splits a string in to an array   		
        } else {
        */

        
//System.out.println(matchList);        
//System.exit(0);
        
        
        
            //arr = keywords2.split(" "); // uses SPACE as a delimiter and splits a string in to an array

       // }
  	  	String usrnme = null;
        if (username2.length() == 0) {
        	System.out.println("no username entered");
    		usrnme = (String)JOptionPane.showInputDialog(
        	//username = (JTextField)JOptionPane.showInputDialog(
                    frame,
                    "Please enter your YouTube username:\n",
                    "No username was entered",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    null,
                    "");
            if (usrnme.length() == 0) {

                JOptionPane.showMessageDialog(frame, "Still no usernamed entered. Quitting");
            	System.out.println("Quitting");
    			System.exit(0);
    		}

    		username2 = usrnme;
        }
  	  	String psswrd = null;
        if (passwrd2.length() == 0) {
        	System.out.println("no password entered");
        	psswrd = (String)JOptionPane.showInputDialog(
        	//username = (JTextField)JOptionPane.showInputDialog(
                    frame,
                    "Please enter your YouTube password:\n",
                    "No password was entered",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    null,
                    "");
    		//int USresult = JOptionPane.OK_OPTION;
            if (psswrd.length() == 0) {

                JOptionPane.showMessageDialog(frame, "Still no password entered. Quitting");
            	System.out.println("Quitting");
    			System.exit(0);
    		}
            passwrd2 = psswrd;
        }
        if (savelogininformation3 == "Yes") {
            //System.out.println("savelogininformation3 read as Yes");
        	String appdata = System.getProperty("user.home") + "\\AppData\\Roaming\\"; 
        	File logininformationfile = new File( appdata + "YTscheduler/" + GDataDKey );

        	PrintWriter pwInput = new PrintWriter(logininformationfile);
    		pwInput.println(username2);
    	    pwInput.println(passwrd2);
    	    pwInput.close();
    		System.out.println("Log-in information saved");

    		//System.exit(0);
        	
        } else {
            //System.out.println("savelogininformation3 read as No");
        }
//        System.exit(0);
        //String value2 = ((String) op.getValue());
		if(op.getValue() == null)			
		{
    		System.out.println("You hit CLOSED_OPTION");
    		System.exit(0);
		}
		if(result == JOptionPane.YES_OPTION)
		{
	        String matchList = "";
	        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
	        Matcher regexMatcher = regex.matcher(keywords2);
	        while (regexMatcher.find()) {
	            if (regexMatcher.group(1) != null) {
	                // Add double-quoted string without the quotes
	                matchList =  matchList + regexMatcher.group(1) + ";";
	            } else if (regexMatcher.group(2) != null) {
	                // Add single-quoted string without the quotes
	                matchList =  matchList + regexMatcher.group(2) + ";";
	            } else {
	                // Add unquoted word
	                matchList =  matchList + regexMatcher.group() + ";";
	            }
	        } 
	        arr = matchList.split(";");
			System.out.println("You hit YES");
	        YouTubeService myService = new YouTubeService(username2, GDataDKey);
        	myService.setUserCredentials(username2,passwrd2);        	
			VideoEntry newEntry = new VideoEntry();
			YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
			mg.setTitle(new MediaTitle());
			mg.getTitle().setPlainTextContent("Temp Title");
			mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, YTcatorgories3));

			mg.setDescription(new MediaDescription());
			mg.getDescription().setPlainTextContent("Temp Description");
			if (privacyoption3 == "Public") {
				mg.setPrivate(false);
			} else {
				mg.setPrivate(true);
			};
			threadjopsmd = "Your upload has started";
			t.start();
			//MediaFileSource ms = new MediaFileSource(new File(filestring), "video/quicktime");
	        MediaFileSource ms = new MediaFileSource(new File(filestring), videomimetype);
			newEntry.setMediaSource(ms);
			String uploadUrl =
			  "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";

			try {				
				VideoEntry createdEntry = myService.insert(new URL(uploadUrl), newEntry); //Uploads
				YouTubeMediaGroup mg2 = createdEntry.getOrCreateMediaGroup();
				createdEntry.getMediaGroup().getDescription().setPlainTextContent(discription2);
				createdEntry.getMediaGroup().getTitle().setPlainTextContent(desiredtitle2);
				createdEntry.getMediaGroup().getKeywords().clearKeywords();

		        for(int i=0;i<arr.length;i++) {
					createdEntry.getMediaGroup().getKeywords().addKeyword((String) arr[i]);
		        }
				createdEntry.update();
			} catch (IOException | ServiceException e) {
    			//threadjopsmd = "Upload has failed";
    			//t.start();
				e.printStackTrace();
				System.exit(0);
				
			}
			
			System.out.println("Uploaded");	
			System.exit(0);
		} else if (result == JOptionPane.NO_OPTION){
			String appdata = System.getProperty("user.home") + "\\AppData\\Roaming\\"; 
        	File fleExample = new File( appdata + "YTscheduler/" + desiredtitle.getText() + ".txt" );
        	//makes a file
        	
        	/*
    		username2 = username.getText();
    		passwrd2 = passwrd.getText();
    		desiredtitle2 = desiredtitle.getText();
    		fcstring = fc.getSelectedFile();
    		discription2 = discription.getText();
    		filestring = fcstring.toString();
    		*/
    		YTcatorgories3 = (String) YTcatorgories2.getSelectedItem();
    		privacyoption3 = (String) privacyoption2.getSelectedItem();

    		//String location2 = location.getText();
    		keywords2 = keywords.getText();           
			boolean privacyoption4 = false;
			if (privacyoption3 == "Public") {
				privacyoption4 = false;
			} else {
				privacyoption4 = true;
			};
    		System.out.println("Saving entered information for later");
    	    PrintWriter pwInput = new PrintWriter(fleExample);
    		//pwInput.println(username2);
    	    //pwInput.println(passwrd2);
    		pwInput.println(desiredtitle2);
    		pwInput.println(discription2);
    		pwInput.println(filestring);
    		pwInput.println(YTcatorgories3);
    		pwInput.println(privacyoption4);
    		pwInput.println(videomimetype);
    		pwInput.print(keywords2);
    	    pwInput.close();
    	    System.out.println("The file has been created.");
    	    
    		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    		DateFormat dateFormatdate = new SimpleDateFormat("MM/dd/yyyy");
    		DateFormat dateFormattime = new SimpleDateFormat("HH:mm");
    		Date date = new Date();
    		String dateFormatdate2 = dateFormatdate.format(date);
    		String dateFormattime2 = dateFormattime.format(date);
    	       		
    	    String[] AMPM = { "AM", "PM" };
    	    String[] RAUO = { "No", "Yes" };
    		JComboBox<?> AMPM2 = new JComboBox<Object>(AMPM);
    		JComboBox<?> RAU = new JComboBox<Object>(RAUO);
    		JTextField winusername = new JTextField(); 
    		JTextField winpasswrd = new JPasswordField();
    		JTextField ytsdate = new JTextField(dateFormatdate2);
    		JTextField ytstime = new JTextField(dateFormattime2);
    		//JTextField location = new JTextField();
    		//JTextField keywords = new JTextField();
    		
    		//Object[] msg5 = {"Windows Username:", winusername, "Windows Password:", winpasswrd, "Date to be ran MM/DD/YYYY", ytsdate, "Time to be ran HH:MM", ytstime, "AM/PM", AMPM2 };
    		Object[] msg5 = {"Date to be ran MM/DD/YYYY", ytsdate, "Time to be ran HH:MM (12h or 24h)", ytstime, "AM/PM", AMPM2 };
    		//add(new Button("Browse"))
   		
    		final JOptionPane op2 = new JOptionPane(
    			msg5,
    			JOptionPane.PLAIN_MESSAGE,
    			JOptionPane.OK_CANCEL_OPTION,
    			null, 
    			null,
    			null);

    		
    		final JDialog dialog2 = op2.createDialog("Schedule your upload");
    		dialog2.setVisible(true); // program stops here
    		
    		int value = ((Integer) op2.getValue()).intValue();
    		if (value == JOptionPane.OK_OPTION) {

    		String winusername2 = winusername.getText();
    		String winpasswrd2 = winpasswrd.getText();
    		String ytsdate2 = ytsdate.getText();
    		String ytstime2 = ytstime.getText();
    		String AMPM3 = (String) AMPM2.getSelectedItem();
    		
    		String[] ytstime3 = ytstime2.split(":"); // uses : as a delimiter and splits a string in to an array
    		String[] ytsdate3 = ytsdate2.split("/"); // uses / as a delimiter and splits a string in to an array
    		
    		String ytsdateMM = ytsdate3[0];
    		String ytsdateDD = ytsdate3[1];
    		String ytsdateYY = ytsdate3[2];

    		String ytstimeHH = ytstime3[0];
    		String ytstimeMM = ytstime3[1];
	        
	        String ytshourString = null;

    		if (AMPM3 == "PM") {
    			System.out.println("PM cought");
    	        switch (ytstimeHH) {
                case "1": ytshourString = "13";       break;
                case "2": ytshourString = "14";      break;
                case "3": ytshourString = "15";         break;
                case "4": ytshourString = "16";         break;
                case "5": ytshourString = "17";           break;
                case "6": ytshourString = "18";          break;
                case "7": ytshourString = "19";          break;
                case "8": ytshourString = "20";        break;
                case "9": ytshourString = "21";     break;
                case "01": ytshourString = "13";       break;
                case "02": ytshourString = "14";      break;
                case "03": ytshourString = "15";         break;
                case "04": ytshourString = "16";         break;
                case "05": ytshourString = "17";           break;
                case "06": ytshourString = "18";          break;
                case "07": ytshourString = "19";          break;
                case "08": ytshourString = "20";        break;
                case "09": ytshourString = "21";     break;
                case "10": ytshourString = "22";       break;
                case "11": ytshourString = "23";      break;
                case "12": ytshourString = "12";      break;
                default: ytshourString = ytstimeHH; break;
                
            } 

    		}else if (AMPM3 == "AM") {
    			System.out.println("AM cought");

    	        switch (ytstimeHH) {
                case "01": ytshourString = "01";       break;
                case "02": ytshourString = "02";      break;
                case "03": ytshourString = "03";         break;
                case "04": ytshourString = "04";         break;
                case "05": ytshourString = "05";           break;
                case "06": ytshourString = "06";          break;
                case "07": ytshourString = "07";          break;
                case "08": ytshourString = "08";        break;
                case "09": ytshourString = "09";     break;
                case "1": ytshourString = "01";       break;
                case "2": ytshourString = "02";      break;
                case "3": ytshourString = "03";         break;
                case "4": ytshourString = "04";         break;
                case "5": ytshourString = "05";           break;
                case "6": ytshourString = "06";          break;
                case "7": ytshourString = "07";          break;
                case "8": ytshourString = "08";        break;
                case "9": ytshourString = "09";     break;
                case "10": ytshourString = "10";       break;
                case "11": ytshourString = "11";      break;
                case "12": ytshourString = "00";       break;
                default: ytshourString = ytstimeHH; break;
    	        }
            	
            }
    		ytstimeHH = ytshourString;
    		String ytstime4 = ytstimeHH + ":" + ytstimeMM;
    		String ytsdate4 = ytsdateMM + "/" + ytsdateDD + "/" + ytsdateYY;
    		
    		

    		//String vipfn =  appdata + "YTscheduler/" + desiredtitle.getText() + ".txt";
    		//String tjaln = "This java app location and name";
    		String Ytusjarfilelocation = "\"\'java.exe\' -jar " + System.getProperty("user.home") + "\\AppData\\Roaming\\YTscheduler\\YtuScheduler.jar";
    		//String olsytu = Ytusjarfilelocation; //+ " " + desiredtitle.getText() + ".txt";
    		String olsytu = " " + desiredtitle.getText() + ".txt";
    		String taskname = desiredtitle.getText();
    		String[] taskname2 = taskname.split(" ");
        	String taskname3 = "";
        	
    		for(int i=0; i<taskname2.length; i++) {
    			//System.out.println("array place " + i + ":" + args[i]);
    			taskname3 = taskname3 + taskname2[i] + "_";
    			//System.out.println(filefromargslist);
    		}
    		
            try {
            	//String makeascheduler = "cmd Schtasks /Create /RU " + winusername2 + " /RP " + winpasswrd2 + " /TN " + taskname + " /TR \'\"" + olsytu + "\"\' /SD " + ytsdate4 +" /ST " + ytstime4 + " /DU 168:00 /Z";
                String makeascheduler = "SchTasks /Create /SC ONCE /TN " + taskname3 + dateFormat.format(date) + " /TR " + Ytusjarfilelocation + "" + olsytu + "\" /ST " + ytstime4 + " /SD "  + ytsdate4;
                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec(makeascheduler);
                System.out.println(makeascheduler);
                 BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                 String line=null;
                 String linetake2 = "";
                // System.out.println("makeascheduler1" + input);
                //     System.out.println("makeascheduler10");
                     
                     linetake2 = "Task creation has failed";
                 while((line=input.readLine()) != null) {
                    System.out.println(line);
                    linetake2 = line;
                    //JOptionPane.showMessageDialog(frame, line);
                }

                     System.out.println("makeascheduler2 null");

                 
                 System.out.println("makeascheduler2");

                 JOptionPane.showMessageDialog(frame, linetake2);

                 
                 int exitVal = pr.waitFor();
                System.out.println("Exited with error code "+exitVal);
             } catch(Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
    	    System.out.println("The file has been created. 2");
    		} else if (value == JOptionPane.CANCEL_OPTION) {
     			System.out.println("You hit \"cancel\"");
     			System.exit(0);

    		}
    		

		} else {
			System.out.println("Canceled VIA Cancel");
			System.exit(0);
		}
	}
		
		 
	public static void main(String[] args) throws AuthenticationException, IOException
	{

		//Component frame2 = null;
		//JOptionPane.showMessageDialog(frame2,testvar2);
		//System.exit(0);
		//boolean loadpreconfigured = false;
		String appdata = System.getProperty("user.home") + "\\AppData\\Roaming\\";
		success = (new File(appdata + "YTscheduler")).mkdirs();
		if (!success) {

		}
		File targetcopyfrom =  new File(YtuScheduler.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String copytopath = System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "YTscheduler" + File.separator + "YtuScheduler.jar";
		String copyfrompath3 = targetcopyfrom.getPath();
		
		Path p1 = Paths.get(copytopath);
		Path p2 = Paths.get(copyfrompath3);

		Files.copy(p2, p1, REPLACE_EXISTING);
		
        if(args.length > 0) {
        	String filefromargslist = "";
    		for(int i=0; i<args.length; i++) {
    			//System.out.println("array place " + i + ":" + args[i]);
    			filefromargslist = filefromargslist + args[i] + " ";
    			//System.out.println(filefromargslist);
    		}

        	File fleExample = new File( appdata + "YTscheduler/" + filefromargslist);
// Find out if the file exists already
        	if( fleExample.exists() ) {
        		String GDataDKey = "AI39si7Iw2fopIkvtGuNeHbPHqQRZyWuN3m-Cv21PX6CO_3EcqrFjOfWbrS59tifYjB6-oYszwbXegEvgF1JDpMq2WB5jwySLQ";
        		String appdata2 = System.getProperty("user.home") + "\\AppData\\Roaming\\";
            	File storedpassword = new File( appdata2 + "YTscheduler/" + GDataDKey);
            	//Find out if the file exists already
            	String loadedusrnme = "";
            	String loadedpsswrd = "";
            	if( storedpassword.exists() ) {
            		Scanner opnScanner = new Scanner(storedpassword);
            		while( opnScanner.hasNext() ) {
            			// Read each line and display its value
            			loadedusrnme = opnScanner.nextLine(); // username2
            			loadedpsswrd = opnScanner.nextLine(); // passwrd2
            		}
            		opnScanner.close();    		
            	}
        		// Prepare a Scanner that will "scan" the document
        		Scanner opnScanner = new Scanner(fleExample);
        		// Read each line in the file
        		// TODO remove the need to include username and password in the video config file
        		String username2 = loadedusrnme;
        		String passwrd2 = loadedpsswrd;
        		String desiredtitle2 = null;
        		String discription2 = null;
        		String filestring = null;
        		String YTcatorgories3 = null;
        		String privacyoption3 = null;
        		String keywords2 = null;
        		String videomimetype = null;
        		String[] arr = null;


        		while( opnScanner.hasNext() ) {
        			// Read each line and display its value
        			//username2 = opnScanner.nextLine(); // username2
        			//passwrd2 = opnScanner.nextLine(); // passwrd2
        			desiredtitle2 = opnScanner.nextLine(); // desiredtitle2
        			discription2 = opnScanner.nextLine(); // discription2
        			filestring = opnScanner.nextLine(); // filestring
        			YTcatorgories3 = opnScanner.nextLine(); // YTcatorgories3
        			privacyoption3 = (String) opnScanner.nextLine(); // privacyoption3
        			videomimetype = opnScanner.nextLine(); // privacyoption3
        			keywords2 = opnScanner.nextLine(); // keywords2
        			
        	        String matchList = "";
        	        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        	        Matcher regexMatcher = regex.matcher(keywords2);
        	        while (regexMatcher.find()) {
        	            if (regexMatcher.group(1) != null) {
        	                // Add double-quoted string without the quotes
        	                matchList =  matchList + regexMatcher.group(1) + ";";
        	            } else if (regexMatcher.group(2) != null) {
        	                // Add single-quoted string without the quotes
        	                matchList =  matchList + regexMatcher.group(2) + ";";
        	            } else {
        	                // Add unquoted word
        	                matchList =  matchList + regexMatcher.group() + ";";
        	            }
        	        } 
        	        arr = matchList.split(";");
        		}
        		
    			boolean privacyoption4 = Boolean.parseBoolean(privacyoption3) ;
    			/* 
    			System.out.println(username2);
    			System.out.println(passwrd2);
    			System.out.println(desiredtitle2);
    			System.out.println(discription2);
    			System.out.println(filestring);
    			System.out.println(YTcatorgories3);
    			System.out.println(privacyoption4);
    			System.out.println(videomimetype);
    			System.out.println(keywords2);
    			*/
    			//String[] arr=keywords2.split(";");
    			//System.exit(0);
    			//System.out.println(arr[1]);
        		opnScanner.close();
    	        YouTubeService myService = new YouTubeService(username2, GDataDKey);
            	myService.setUserCredentials(username2,passwrd2);        	
    			VideoEntry newEntry = new VideoEntry();
    			YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
    			mg.setTitle(new MediaTitle());
    			mg.getTitle().setPlainTextContent("Temp title");
    			mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, YTcatorgories3));
    			//mg.setKeywords(new MediaKeywords());
    	        //for(int i=0;i<arr.length;i++) {mg.getKeywords().addKeyword(arr[i]);
    	        //}
    			mg.setDescription(new MediaDescription());
    			mg.getDescription().setPlainTextContent("Temp description");
    			//if (privacyoption3 == "Public\n") {
        		//	System.out.println("= = = Public = = =");
    			mg.setPrivate(privacyoption4);
    			//} else {
        		//	System.out.println("= = = Private = = = ");
    			//	mg.setPrivate(true);
    			//};
    			//String fcfileName = fcstring.getCanonicalPath();  
    			
    			threadjopsmd = "Your scheduled upload \"" + desiredtitle2 + "\" has started \n Please dont close the \"Taskeng.exe window\"";
    			t.start();
    			
    	        MediaFileSource ms = new MediaFileSource(new File(filestring), videomimetype);
    			newEntry.setMediaSource(ms);
    			String uploadUrl =
    			  "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";

    			try {
    				//Component frame = null;
    				//VideoEntry createdEntry = myService.insert(new URL(uploadUrl), newEntry);
    				VideoEntry createdEntry = myService.insert(new URL(uploadUrl), newEntry); //Uploads
    				//YouTubeMediaGroup mg2 = createdEntry.getOrCreateMediaGroup();
    				createdEntry.getMediaGroup().getDescription().setPlainTextContent(discription2);
    				createdEntry.getMediaGroup().getTitle().setPlainTextContent(desiredtitle2);
    				createdEntry.getMediaGroup().getKeywords().clearKeywords();
    		        for(int i=0;i<arr.length;i++) {
    					createdEntry.getMediaGroup().getKeywords().addKeyword((String) arr[i]);
    		        }
    				createdEntry.update();

    			} catch (IOException | ServiceException e) {
        			threadjopsmd = "Upload has failed";
        			t.start();
    				e.printStackTrace();
    				
    			}
    			
    			
    			System.out.println("Uploaded");
        		System.exit(0);
        		
        		
        	} else { // if( !fleExample.exists() )
        		
        		Component frame = null;
				// makes a file
                JOptionPane.showMessageDialog(frame, "No file exists with that name");
        		//System.out.println("No file exists with that name");
        		System.exit(0);
        		/*
        	    // Create that file and prepare to write some values to it
        	    PrintWriter pwInput = new PrintWriter(fleExample);
        		// Write a string to the file
        		pwInput.println("Francine");
        		// Write a string to the file
        	    pwInput.println("Mukoko");
        		// Write a double-precision number to the file
        		pwInput.println(22.85);
        		// Write a Boolean value to the file
        		pwInput.print(true);
        		// After using the PrintWriter object, de-allocated its memory
        	    pwInput.close();
        	    // For convenience, let the user know that the file has been created
        	    //System.out.println("The file has been created.");
        	    
        		//String fleExample2 = fleExample.toString();
        	    //System.out.println("File path:" + fleExample2);
        	     */
        	    System.exit(0);
        	}
        } else {     	
        	JFrame frame = new YtuScheduler();
			frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
			frame.setAlwaysOnTop(true);
			frame.pack();
			frame.setVisible(true);
			System.exit(0);
        }
	}



}
//mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "mydevtag"));
//mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "anotherdevtag"));
//newEntry.setGeoCoordinates(new GeoRssWhere(37.0,-122.0));
// alternatively, one could specify just a descriptive string

//String keywords2="Converting java String into Array";
//1. First method with split() 
// split(String Delimiter)
//System.out.println("Array :"+arr.length);
//for(int i=0;i<arr.length;i++)
//{
//    System.out.println("array"+i+"  :"+arr[i]);
//}
//System.out.println("Username:" + username2);
//System.out.println("Password:" + passwrd2);
//System.out.println("Title:" + desiredtitle2);
//System.out.println("Discription:" + discription2);
//System.out.println("File path:" + fcstring);
//System.out.println("File title:" + fc.getName());

/*
if(fcfileName.endsWith(".txt")) 
{  
System.out.print("Text file"); 
System.exit(0);
}  
else if(fcfileName.endsWith(".mp4"))  
{  
System.out.print("quicktime");
System.exit(0);

}  
else  
{  
System.out.print("Other file");  
System.exit(0);

}  

                    JOptionPane.showMessageDialog(frame, line);


*/
