import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Action;
import javax.swing.JList;
import org.jasypt.util.text.BasicTextEncryptor;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.util.ServiceException;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;


public class YtuSchedulerGUI2 {
	
	public static JFileChooser fc = new JFileChooser();
	
    public final static String uploadUrl = "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";
	public static String usershome = System.getProperty("user.home"); //%20
    public static String GDataDKey = "AI39si7Iw2fopIkvtGuNeHbPHqQRZyWuN3m-Cv21PX6CO_3EcqrFjOfWbrS59tifYjB6-oYszwbXegEvgF1JDpMq2WB5jwySLQ";	
	public static String appdata = usershome + "\\AppData\\Roaming\\";
	public static String textencryptorpass = "f9c6127646e881a939b23d6c9704679e42acdd3c1aa18bf143d211925615f761" + usershome;
    public static String filelocation2 = "";
	public static String videomimetype = "";
	public static String username2 = "";
    public static String passwrd2 = "";
    public static String savelogininformation3 = "No";
    public static String desiredtitle2 = "";
    public static String discription2 = "";
    public static String YTcatorgories3 = "";
    public static String privacyoption3 = "";
	public static String keywords2 = null;
	public static String filestringstr = null;
    public static String threadjopsmd = null;
    public static String filestring = "";
    public static String filelocationtest =  "";
    public static String systemoutvar = "";
    public static String[] YTcatorgories = { "Autos", "Comedy", "Education", "Entertainment", "Film", "Gaming", "Howto", "Music", "News", "Nonprofits", "People", "Tech", "Sports", "Travel" };
	public static String[] privacyoption = { "Public", "Private" };
	public static String[] savelogininformation = { "No", "Yes" };
    public static String[] AMPM = { "AM", "PM" };
    public static String[] arr = {};

    //public static String[] SchTaskarrlist=linearr[0].split("_");


    public static List savedloginU = new ArrayList();
    public static List savedloginP = new ArrayList();
    public static List savedloginUE = new ArrayList();
    public static List savedloginPE = new ArrayList();
	public static List SchTaskarrlist = new ArrayList();
	public static List SchTaskarralreadylisted = new ArrayList();
	public static List SchTaskarrlistraw = new ArrayList();
	public static List SchTaskarralreadylistedraw = new ArrayList();

    
	public static DefaultListModel listmodel = new DefaultListModel();
	
	public static JPasswordField passwrd;	
    public static JTextArea discription = new JTextArea();
    public static JTextField keywords;
	public static JTextField filelocation;
	public static JTextField ytstime = new JTextField();
	public static JTextField ytsdate = new JTextField();
	public static JComboBox AMPM2 = new JComboBox(AMPM);
    public static JTextField desiredtitle = new JTextField(); //threadjopsmd   
    public static JComboBox<?> YTcatorgories2 = new JComboBox<Object>(YTcatorgories); 
    public static JComboBox<?> privacyoption2 = new JComboBox<Object>(privacyoption); 
	public static JComboBox username;
	public static JList list = new JList(listmodel);
	public static JLabel statusbar = new JLabel("");
	
	private JFrame frame;
	
    public static boolean privacyoption4;
    public static boolean addusertolist = true;
	public static BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	public static File fcstring = null;
	
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
    private final Action action_3 = new SwingAction_3();
    private final Action action_4 = new SwingAction_4();
    private final Action action_5 = new SwingAction_5();
    private final Action action_6 = new SwingAction_6();
    private final Action action_7 = new SwingAction_7();
    private final Action action_8 = new SwingAction_8();
    private final Action action_9 = new SwingAction_9();
    private final Action action_10 = new SwingAction_10();

    
    public static MediaFileSource ms = null;
    public static VideoEntry newEntry = null;
    
    public static Thread startupload = new Thread(new Runnable(){
		public void run(){
			String videomimetype1 = videomimetype;
			String filestring1 = filestring;
			String username21 = username2;
			String passwrd21 = passwrd2;
			String desiredtitle21 = desiredtitle2;
			String discription21 = discription2;
			String YTcatorgories31 = YTcatorgories3;
			boolean privacyoption41 = privacyoption4;
			//String[] arr1 = arr;
			
			YouTubeService myService = new YouTubeService(username21, GDataDKey);
			VideoEntry newEntry = new VideoEntry();
			YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
			mg.setTitle(new MediaTitle());
			mg.getTitle().setPlainTextContent("Temp title");
			mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, YTcatorgories31));
			mg.setDescription(new MediaDescription());
			mg.getDescription().setPlainTextContent("Temp description");
			mg.setPrivate(privacyoption41);
			MediaFileSource ms = new MediaFileSource(new File(filestring1), videomimetype1);
			String uploadUrl =
			  "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";
			YtuSchedulerGUI2.desiredtitle.setText("");
			YtuSchedulerGUI2.discription.setText("");
			YtuSchedulerGUI2.YTcatorgories2.setSelectedItem("Autos");	
			YtuSchedulerGUI2.privacyoption2.getSelectedItem();
			YtuSchedulerGUI2.keywords.setText("");
			YtuSchedulerGUI2.filelocation.setText("");
			try {
				newEntry.setMediaSource(ms);
				systemoutvar = "Your upload \"" + desiredtitle21 + "\" has started. Dont close this window.";
				new Thread(systemout).start();
            	myService.setUserCredentials(username21,passwrd21);        	
				VideoEntry createdEntry = myService.insert(new URL(uploadUrl), newEntry); //Uploads
				createdEntry.getMediaGroup().getDescription().setPlainTextContent(discription21);
				createdEntry.update();
				createdEntry.getMediaGroup().getTitle().setPlainTextContent(desiredtitle21);
				createdEntry.update();
				//createdEntry.getMediaGroup().getKeywords().clearKeywords();
				/*
		        for(int i=0;i<arr1.length;i++) {
					createdEntry.getMediaGroup().getKeywords().addKeyword((String) arr1[i]); 
		        }
		        */
		        for(int i=0;i<arr.length;i++) {
		        	if (arr[i].length() > 1) {
		        		if (arr[i].length() < 31) {
		        			System.out.println("Adding keyword:" + arr[i]);
		        			createdEntry.getMediaGroup().getKeywords().addKeyword((String) arr[i]); 
		        		}
		        	}		        	
		        	/*
		        	if (arr[i].length() > 1) {
		        		System.out.println("arr[i]:" + arr[i]);
		        		createdEntry.getMediaGroup().getKeywords().addKeyword((String) arr[i]); 
		        	}
		        	createdEntry.update();
		        	*/
		        }
		        
			} catch (IOException | ServiceException e1) {
				systemoutvar = "Username authentication error. Is your stored username and password correct?";
				new Thread(systemout).start();
				e1.printStackTrace();
				return;
			}
			systemoutvar = "Uploaded";
			new Thread(systemout).start();


		}
	});
    
    /*
     
	Start the following thread like:
	systemoutvar = "what ever message you want";
	new Thread(systemout).start();
	
	new Thread(removeselecteduser).start();


     */
    public static Thread systemout = new Thread(new Runnable(){
		public void run(){
			System.out.println(systemoutvar);
			//listmodel.addElement(systemoutvar);
			YtuSchedulerGUI2.statusbar.setText(systemoutvar);
			Thread.currentThread();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			YtuSchedulerGUI2.statusbar.setText("");
			
		}
	});
    public static Thread removeselecteduser = new Thread(new Runnable(){
		public void run(){
			
			for(int i=0; i<savedloginU.size(); i++) {
				if (username.getSelectedItem() == savedloginU.get(i)) {
					System.out.println("removing selected user:" + username.getSelectedItem());
					username.removeItem(username.getSelectedItem());    		
					savedloginU.remove(i);
					savedloginP.remove(i); 						
					
				}
			}
        	File logininformationfile = new File( appdata + "YTscheduler/" + GDataDKey );
        	PrintWriter pwInput = null;
			try {
				pwInput = new PrintWriter(logininformationfile);
			} catch (FileNotFoundException e) {
				systemoutvar = "File not found";
				new Thread(systemout).start();
				e.printStackTrace();
			}

			for(int i=0; i<savedloginU.size(); i++) {
				//savedloginU.get(i)
        		String myEncryptedText = textEncryptor.encrypt((String) savedloginU.get(i));
        		String myEncryptedText2 = textEncryptor.encrypt((String) savedloginP.get(i));
    			pwInput.println(myEncryptedText);
    			pwInput.println(myEncryptedText2);
				}
	    	pwInput.close();
		}
	});
    public static Thread deletetask = new Thread(new Runnable(){
		public void run(){
			Object Selectedtask = null;
		if (list.isSelectionEmpty()) {
			systemoutvar = "Nothing selected";
			new Thread(systemout).start();
		} else {
			int selected[] = list.getSelectedIndices();
			System.out.println("selected:" + selected[0]);
			for (int i=0; i < selected.length; i++) {
				Selectedtask = list.getModel().getElementAt(selected[i]);
				System.out.println("Selectedtask:" + Selectedtask);
				}
			for (int i=0; i<SchTaskarralreadylisted.size(); i++) {
				if (SchTaskarralreadylisted.get(i).equals(Selectedtask)) {
					System.out.println(SchTaskarralreadylistedraw.get(i));
					List cutdownSchTaskname = new ArrayList();
        	        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        	        Matcher regexMatcher = regex.matcher((CharSequence) SchTaskarralreadylistedraw.get(i));
        	        while (regexMatcher.find()) {
        	            if (regexMatcher.group(1) != null) {
        	                // Add double-quoted string without the quotes
        	            	cutdownSchTaskname.add(regexMatcher.group(1));
        	            }
        	        }
        	        System.out.println("cutdownSchTaskname:" + cutdownSchTaskname.get(0));
		            try {
		            	String makeascheduler2 = "c:\\windows\\system32\\schtasks.exe /Delete /TN " + cutdownSchTaskname.get(0) + " /F";
		                Runtime rt = Runtime.getRuntime();
		                Process pr = rt.exec(makeascheduler2);
		                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		                int exitVal = pr.waitFor();
		             } catch(Exception e1) {
		                System.out.println("e1.toString():" + e1.toString());
		                e1.printStackTrace();
		            }
					for (int i1=0; i1<SchTaskarrlistraw.size(); i1++) {	
						if (SchTaskarrlistraw.get(i1).equals(SchTaskarralreadylistedraw.get(i))) {
							SchTaskarrlistraw.remove(i1);			
						}
		            }
					for (int i1=0; i1<SchTaskarrlist.size(); i1++) {	
						if (SchTaskarrlist.get(i1).equals(SchTaskarralreadylisted.get(i))) {
							SchTaskarrlist.remove(i1);			
						}
		            }
		            list.clearSelection();
					listmodel.remove(selected[0]);
					SchTaskarralreadylistedraw.remove(i);
					SchTaskarralreadylisted.remove(i);
					systemoutvar = "Task deleted";
					new Thread(systemout).start();
					
				}

			}
			
		}
		new Thread(loadtasks).start();
		//System.out.println(listmodel.getSelectedValue());
		}
	});
    public static Thread loadtasks = new Thread(new Runnable(){
		public void run(){ 

	        
	        System.out.println(SchTaskarrlist.size());
	        System.out.println(SchTaskarrlistraw.size());
	        System.out.println(SchTaskarralreadylisted.size());
	        System.out.println(SchTaskarralreadylistedraw.size());
	        
		}
	});
    public static Thread runtask = new Thread(new Runnable(){
		public void run(){ //TODO finish this
			Object Selectedtask = null;
		if (list.isSelectionEmpty()) {
			systemoutvar = "Nothing selected";
			new Thread(systemout).start();
		} else {
			int selected[] = list.getSelectedIndices();
			System.out.println("selected:" + selected[0]);
			for (int i=0; i < selected.length; i++) {
				Selectedtask = list.getModel().getElementAt(selected[i]);
				System.out.println("Selectedtask:" + Selectedtask);
				}
			for (int i=0; i<SchTaskarralreadylisted.size(); i++) {
				if (SchTaskarralreadylisted.get(i).equals(Selectedtask)) {
					System.out.println(SchTaskarralreadylistedraw.get(i));
					List cutdownSchTaskname = new ArrayList();
        	        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        	        Matcher regexMatcher = regex.matcher((CharSequence) SchTaskarralreadylistedraw.get(i));
        	        while (regexMatcher.find()) {
        	            if (regexMatcher.group(1) != null) {
        	                // Add double-quoted string without the quotes
        	            	cutdownSchTaskname.add(regexMatcher.group(1));
        	            }
        	        }
        	        System.out.println("cutdownSchTaskname:" + cutdownSchTaskname.get(0));
		            try {
		            	String makeascheduler2 = "c:\\windows\\system32\\schtasks.exe /Run /TN " + cutdownSchTaskname.get(0) + " /I";
		                Runtime rt = Runtime.getRuntime();
		                Process pr = rt.exec(makeascheduler2);
		                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		                int exitVal = pr.waitFor();
		             } catch(Exception e1) {
		                System.out.println("e1.toString():" + e1.toString());
		                e1.printStackTrace();
		            }				
				}
			}			
		}	        
		}
	});
    public static Thread rescheduletask = new Thread(new Runnable(){
		public void run(){
			// TODO ================================================================================================================================
    		String ytstime4 = "";
    		String ytsdate4 = "";
    		DateFormat dateFormat3 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    		DateFormat dateFormatdate3 = new SimpleDateFormat("MM/dd/yyyy");
    		DateFormat dateFormattime3 = new SimpleDateFormat("HH:mm");
    		Date date = new Date();
    		String dateFormatdate4 = dateFormatdate3.format(date);
    		String dateFormattime4 = dateFormattime3.format(date);
    	       		
    	    String[] AMPM = { "AM", "PM" };
    	    String[] RAUO = { "No", "Yes" };
    		JComboBox<?> AMPM2 = new JComboBox<Object>(AMPM);
    		JComboBox<?> RAU = new JComboBox<Object>(RAUO);
    		//JTextField winusername = new JTextField(); 
    		JTextField winpasswrd = new JPasswordField();
    		JTextField ytsdate = new JTextField(dateFormatdate4);
    		JTextField ytstime = new JTextField(dateFormattime4);
    		//JTextField location = new JTextField();
    		//JTextField keywords = new JTextField();
    		
    		Object[] msg5 = {"Windows Password", winpasswrd, "Date to be ran MM/DD/YYYY", ytsdate, "Time to be ran HH:MM (12h or 24h)", ytstime, "AM/PM", AMPM2 };
   		
    		final JOptionPane op2 = new JOptionPane(
    			msg5,
    			JOptionPane.PLAIN_MESSAGE,
    			JOptionPane.OK_CANCEL_OPTION,
    			null, 
    			null,
    			null);
    		
    		final JDialog dialog2 = op2.createDialog("Reschedule");
    		dialog2.setVisible(true); // program stops here
    		
    		int value = ((Integer) op2.getValue()).intValue();
    		if (value == JOptionPane.OK_OPTION) {

    		String ytsdate2 = ytsdate.getText();
    		String ytstime2 = ytstime.getText();
    		String winpasswrd2 = winpasswrd.getText();
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
    		ytstime4 = ytstimeHH + ":" + ytstimeMM;
    		//ytsdate4 = ytsdateMM + "/" + ytsdateDD + "/" + ytsdateYY;
    		ytsdate4 = ytsdateDD + "/" + ytsdateMM + "/" + ytsdateYY;
			Object Selectedtask = null;
		if (list.isSelectionEmpty()) {
			systemoutvar = "Nothing selected";
			new Thread(systemout).start();
		} else {
			int selected[] = list.getSelectedIndices();
			System.out.println("selected:" + selected[0]);
			for (int i=0; i < selected.length; i++) {
				Selectedtask = list.getModel().getElementAt(selected[i]);
				System.out.println("Selectedtask:" + Selectedtask);
				}
			for (int i=0; i<SchTaskarralreadylisted.size(); i++) {
				if (SchTaskarralreadylisted.get(i).equals(Selectedtask)) {
					System.out.println(SchTaskarralreadylistedraw.get(i));
					List cutdownSchTaskname = new ArrayList();
        	        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        	        Matcher regexMatcher = regex.matcher((CharSequence) SchTaskarralreadylistedraw.get(i));
        	        while (regexMatcher.find()) {
        	            if (regexMatcher.group(1) != null) {
        	                // Add double-quoted string without the quotes
        	            	cutdownSchTaskname.add(regexMatcher.group(1));
        	            }
        	        }
        	        System.out.println("cutdownSchTaskname:" + cutdownSchTaskname.get(0));
		            try {
		            	String makeascheduler2 = "c:\\windows\\system32\\schtasks.exe /Change /TN " + cutdownSchTaskname.get(0) + " /SD " + ytsdate4 + " /ST " + ytstime4 + " /RP " + winpasswrd2;
		                Runtime rt = Runtime.getRuntime();
		                Process pr = rt.exec(makeascheduler2);
		                System.out.println("makeascheduler2:" + makeascheduler2);
		                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		                int exitVal = pr.waitFor();
		             } catch(Exception e1) {
		                System.out.println("e1.toString():" + e1.toString());
		                e1.printStackTrace();
		            }				
				}
			}			
		}

 
    		} else if (value == JOptionPane.CANCEL_OPTION) {
     			System.out.println("You hit \"cancel\"");
     			return;

    		}
			//TODO =================================================================================================================================
		}
	});
    FocusListener listener = new FocusListener() {
        public void focusGained(FocusEvent event) {
          System.out.println("Gained focus");
        }
        public void focusLost(FocusEvent event) {
            System.out.println("lost focus");
        	new Thread(setmimetype).start();
        }
      };
    public static Thread setmimetype = new Thread(new Runnable(){
		public void run(){
			filelocation2 = (String) YtuSchedulerGUI2.filelocation.getText();

	        String[] filestringarr=filelocation2.split("\\\\"); // uses \\ as a delimiter and splits a string in to an array
	        for(int i=0; i<filestringarr.length; i++) {
	        	filestringstr = filestringarr[i];
    		}
			System.out.println("Setting mime type");
			String ext="";
			int mid= filelocation2.lastIndexOf(".");
			ext=filelocation2.substring(mid+1,filelocation2.length());
			videomimetype = "video/" + ext;  
			System.out.println("File ext:" + ext);
		
		}
	});
    public static Thread settime = new Thread(new Runnable(){
		public void run(){
			//DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    		DateFormat dateFormatdate = new SimpleDateFormat("MM/dd/yyyy");
    		DateFormat dateFormattime = new SimpleDateFormat("HH:mm");
    		Date date = new Date();
    		String dateFormatdate2 = dateFormatdate.format(date);
    		String dateFormattime2 = dateFormattime.format(date);
    		ytsdate.setText(dateFormatdate2);
    		ytstime.setText(dateFormattime2);
    		
		}
	});

    public static Thread makevideoconfig = new Thread(new Runnable(){
		public void run(){
			username2 = (String) YtuSchedulerGUI2.username.getSelectedItem();
			passwrd2 = (String) YtuSchedulerGUI2.passwrd.getText();
			desiredtitle2 = YtuSchedulerGUI2.desiredtitle.getText();
			discription2 = YtuSchedulerGUI2.discription.getText();
			YTcatorgories3 = (String) YtuSchedulerGUI2.YTcatorgories2.getSelectedItem();
			privacyoption3 = (String) YtuSchedulerGUI2.privacyoption2.getSelectedItem();
			keywords2 = YtuSchedulerGUI2.keywords.getText();
			filelocationtest = (String) YtuSchedulerGUI2.filelocation.getText();

			if (privacyoption3 == "Private") {
				privacyoption4 = true;
			} else if (privacyoption3 == "Public") {
				privacyoption4 = false;
			}
			//filelocation
			if (filestringstr == null) {
				if (filelocationtest.length() == 0) {
					systemoutvar = "Please choose a file";
					new Thread(systemout).start();
					JOptionPane.showMessageDialog(null, systemoutvar);
					return;
					
				} else {
					systemoutvar = "setting filestringstr to filelocationtest";
					new Thread(systemout).start();
			        String[] filestringarr = filelocationtest.split("\\\\"); // uses \\ as a delimiter and splits a string in to an array
			        for(int i=0; i<filestringarr.length; i++) {
		    			//System.out.println("array place " + i + ":" + args[i]);
			        	filestringstr = filestringarr[i];
		    		}
					//filestringstr = filelocationtest;
					
				}
				systemoutvar = filestringstr;
				new Thread(systemout).start();

				/*
				System.out.println("Please choose a file");
				threadjopsmd = "Please choose a file";
				JOptionPane.showMessageDialog(null, threadjopsmd);
				return;
				*/

				
			}
			if (username2 == null) {
				systemoutvar = "Please enter a username";
				new Thread(systemout).start();
				JOptionPane.showMessageDialog(null, systemoutvar);
				return;
				
			}
			if (passwrd2.length() == 0) {
				systemoutvar = "Please enter a password";
				new Thread(systemout).start();
				JOptionPane.showMessageDialog(null, systemoutvar);
				return;
				
			}
			
		    if (discription2.length() < 1) {
		    	discription2 = filestringstr;
		    }
		    if (desiredtitle2.length() < 1) {
		    	desiredtitle2 = filestringstr;
		    }
		    if (keywords2.length() < 2) {
		    	keywords2 = filestringstr;
		    }
			String videomimetype1 = videomimetype;
			//String filestring1 = filestring;
			String username21 = username2;
			//String passwrd21 = passwrd2;
			String desiredtitle21 = desiredtitle2;
			String discription21 = discription2;
			String YTcatorgories31 = YTcatorgories3;
			//boolean privacyoption41 = privacyoption4;
			//String[] arr1 = arr;
			filestringstr = filestringstr.replace( "\'",""); //TODO 
			filestringstr = filestringstr.replace( "\"","");  
    		System.out.println("Saving entered information for later");
        	File mainconfig = new File( appdata + "YTscheduler\\" + username2 + "_" + filestringstr + ".txt" );
    	    PrintWriter MCpwInput = null;
			try {
				MCpwInput = new PrintWriter(mainconfig);
			} catch (FileNotFoundException e) {
				//Auto-generated catch block
				e.printStackTrace();
			} // + .description
			MCpwInput.println(username21);
			MCpwInput.println(desiredtitle21);
			MCpwInput.println(filelocationtest);
			MCpwInput.println(YTcatorgories31);
			MCpwInput.println(privacyoption4);
			MCpwInput.println(videomimetype1);
			MCpwInput.print(keywords2);
			MCpwInput.close();
    	    
        	File DESconfig = new File( appdata + "YTscheduler\\" + "description." + username2 + "_" + filestringstr + ".txt" );
    	    PrintWriter pwInputDES = null;
			try {
				pwInputDES = new PrintWriter(DESconfig);
			} catch (FileNotFoundException e) {
				//Auto-generated catch block
				e.printStackTrace();
			} // "description." + 
    	    pwInputDES.print(discription21);
    	    pwInputDES.close();

    	    System.out.println("The file has been created at:" + appdata + "YTscheduler\\"  + username2 + "_" + filestringstr + ".txt");		
		}
	});   
    public static Thread saveloginstuff = new Thread(new Runnable(){
		public void run(){   	
		//savelogininformation3 = ;
        if (savelogininformation3 == "Yes") {
        	//textEncryptor.setPassword(textencryptorpass);
			username2 = (String) YtuSchedulerGUI2.username.getSelectedItem();
			passwrd2 = (String) YtuSchedulerGUI2.passwrd.getText();
        	//String myEncryptedText = textEncryptor.encrypt(passwrd2);
        	//String myEncryptedText2 = textEncryptor.encrypt(username2);
			boolean username2found = false;
    		for(int i=0; i<savedloginU.size(); i++) {
    			if (username2 == savedloginU.get(i)) {
    				username2found = true;
    			}
    		}
    		
    		System.out.println("username2:" + username2);
    		//savedloginU.remove(n)
    		if (username2found == true) {
    			System.out.println("Username already stored:" + username2);
    			for(int i=0; i<savedloginU.size(); i++) {
    				if (username2 == savedloginU.get(i)) {
    		    		//System.out.println("savedloginU.get(i)" + savedloginU.get(i));
    		    		String passwrdloginP = (String) savedloginP.get(i);
    		    		//System.out.println(passwrdloginP);
    		    		//System.out.println("passwrd2:" + passwrd2);
    					if (passwrd2.equals(passwrdloginP)) {
    						//System.out.println("Username already stored with that password")
    						systemoutvar = "Username already stored with that password";
    						new Thread(systemout).start();
    					} else {
    						username2found = false;
    						username.removeItem(username2);    		
    						System.out.println("Password not the same, removing old username/pass and saving new information");
    						//System.out.println("savedloginU.get(i)" + savedloginU.get(i));
    						//System.out.println("savedloginP.get(i)" + savedloginP.get(i));
    						savedloginU.remove(i);
    						savedloginP.remove(i);
    					}
    				}
    			}
    		}

    		
    		if (username2found == false) {
            	File logininformationfile = new File( appdata + "YTscheduler/" + GDataDKey );
    			savedloginU.add(username2);
    	    	savedloginP.add(passwrd2);
    			username.addItem(username2);
    			username.setSelectedItem(username2);
    			
            	PrintWriter pwInput = null;
    			try {
    				pwInput = new PrintWriter(logininformationfile);
    			} catch (FileNotFoundException e) {
    				systemoutvar = "File not found";
    				new Thread(systemout).start();
    				e.printStackTrace();
    			}

    			for(int i=0; i<savedloginU.size(); i++) {
    				//savedloginU.get(i)
            		String myEncryptedText = textEncryptor.encrypt((String) savedloginU.get(i));
            		String myEncryptedText2 = textEncryptor.encrypt((String) savedloginP.get(i));
        			pwInput.println(myEncryptedText);
        			pwInput.println(myEncryptedText2);
    				}
    	    	pwInput.close();
    			//System.out.println(savedloginU);
    	    	systemoutvar = "Log-in information saved";
				new Thread(systemout).start();
    		} else { //if username2found == false
    			System.out.println("User information already saved");
    			
    		}
    		savelogininformation3 ="No";
       	   }
		}
	}); 
    
    public static Thread loadusersandpass = new Thread(new Runnable(){
		public void run(){
			File Storeduserpass = new File(  appdata + "YTscheduler/" + GDataDKey );
			if( Storeduserpass.exists() ) {
        		Scanner opnScanner2 = null;
				try {
					opnScanner2 = new Scanner(Storeduserpass);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String decryptedU = "";
        		while( opnScanner2.hasNext() ) {
        			//String loadedusrnme2 = opnScanner2.nextLine(); // filestring
        			//String loadedpsswrd2 = opnScanner2.nextLine(); // YTcatorgories3
                	//String loadedusrnme = textEncryptor.decrypt(opnScanner2.nextLine());
                	//String loadedpsswrd = textEncryptor.decrypt(opnScanner2.nextLine());
        			decryptedU = textEncryptor.decrypt(opnScanner2.nextLine());
            	    savedloginP.add(textEncryptor.decrypt(opnScanner2.nextLine()));
            	    savedloginU.add(decryptedU);
            	    if (addusertolist == true) {
            	    	username.addItem(decryptedU);
            	    }
        		}
				
			}
			addusertolist = true;
		}
	});
    public static Thread makecopy = new Thread(new Runnable(){
		public void run(){ 
			File targetcopyfrom =  new File(YtuSchedulerGUI2.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			String targetcopyfromstring = targetcopyfrom.toString();
			String targetcopyfromreplace2 = targetcopyfromstring.replace( "%20"," ");
			String copytopath = appdata + "YTscheduler\\YtuScheduler.jar";
			//File isfilethere =  new File(copytopath);
        	//if(!isfilethere.exists()) {
        		System.out.println("File not there. Making a new one");
    			Path p1 = Paths.get(copytopath);
    			Path p2 = Paths.get(targetcopyfromreplace2);
    			System.out.println("copytopath: " + copytopath);
    			System.out.println("targetcopyfromreplace2: " + targetcopyfromreplace2);
    			try {
    				Files.copy(p2, p1, REPLACE_EXISTING);
    			} catch (IOException e) {
    				e.printStackTrace();
    				return;
    			}
    			System.out.println("File copied");
        	//}

		}
	});
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
		    	textEncryptor.setPassword(textencryptorpass);
				if (args.length == 0) {
					//new Thread(loadtasks).start();
					new Thread(makecopy).start();
			    	new Thread(loadusersandpass).start();
			    	new Thread(settime).start();
					boolean success = (new File(appdata + "YTscheduler")).mkdirs();
					if (!success) {}
					try {
						YtuSchedulerGUI2 window = new YtuSchedulerGUI2();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else { //if (args.length > 0)
			       	String filefromargslist = "";
		    		for(int i=0; i<args.length; i++) {
		    			//System.out.println("array place " + i + ":" + args[i]);
		    			filefromargslist = filefromargslist + args[i] + " ";
		    			//System.out.println(filefromargslist);
		    		}
		        	//String loadedusrnme = "";
		        	//String loadedpsswrd = "";
		        	//String plainTextpassword = "";
		        	//String plainTextuser = "";
		    		//String appdata2 = usershome + "\\AppData\\Roaming\\";
		        	//File storedpassword = new File( appdata2 + "YTscheduler/" + GDataDKey);		        	
		        	addusertolist = false;
		        	new Thread(loadusersandpass).start();
		        	int counter1 = 0;
	        		System.out.print("Loading users");
		        	while (addusertolist == false) {
		        		//do nothing
		        		counter1 = counter1 + 1;
		        		System.out.print(".");
		        		try {
							Thread.currentThread();
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        		
		        	}
	        		System.out.println("");
		        	File fleExample = new File( appdata + "YTscheduler/" + filefromargslist);
		        	File fleExampleDES = new File( appdata + "YTscheduler/" + "description." + filefromargslist);
		        	if( fleExample.exists() ) {
		        		String selecteduser = null;
		        		String username2 = "";
		        		String passwrd2 = "";
		        		String desiredtitle2 = null;
		        		String discription2 = "";
		        		String filestring = null;
		        		String YTcatorgories3 = null;
		        		String privacyoption3 = null;
		        		String keywords2 = null;
		        		String videomimetype = null;
		        		String[] arr = null;
		        		Scanner opnScanner2 = null;
						try {
							opnScanner2 = new Scanner(fleExample);
						} catch (FileNotFoundException e2) {
							e2.printStackTrace();
						}
		        		while( opnScanner2.hasNext() ) {
		        			// Read each line and display its value
		        			selecteduser = (String) opnScanner2.nextLine(); // discription2 
		        			desiredtitle2 = opnScanner2.nextLine(); // desiredtitle2        			
		        			filestring = opnScanner2.nextLine(); // filestring
		        			YTcatorgories3 = opnScanner2.nextLine(); // YTcatorgories3
		        			privacyoption3 = (String) opnScanner2.nextLine(); // privacyoption3
		        			videomimetype = opnScanner2.nextLine(); // privacyoption3
		        			keywords2 = opnScanner2.nextLine(); // keywords2	
		        			
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
		        		opnScanner2.close();	        		
		        		String username21 = selecteduser;
		        		List testarray = new ArrayList();
		        		testarray.add(username21);
		        		for(int i=0; i<savedloginU.size(); i++) {
		        			if (savedloginU.get(i).equals(testarray.get(0))) {
		        				System.out.println("username " + savedloginU.get(i) + " found");
		        				passwrd2 = (String) savedloginP.get(i);
		        				username2 = (String) savedloginU.get(i);
		        			}
		        		}
		        		if (passwrd2 == "") {
		        			System.out.println("Password for stored username not found");
		        			return;
		        		}
		        		Scanner opnScannerDES = null;		        		
						try {
							opnScannerDES = new Scanner(fleExampleDES);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
		        		while( opnScannerDES.hasNext() ) {
		        			discription2 =  discription2 + opnScannerDES.nextLine() + "\n"; // discription2 
		        		}
		        		opnScannerDES.close();				        		
		    			boolean privacyoption4 = Boolean.parseBoolean(privacyoption3) ;		    	        
		    			YouTubeService myService = new YouTubeService(username2, GDataDKey);
		    			VideoEntry newEntry = new VideoEntry();
		    			YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
		    			mg.setTitle(new MediaTitle());
		    			mg.getTitle().setPlainTextContent("Temp title");
		    			mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, YTcatorgories3));
		    			mg.setDescription(new MediaDescription());
		    			mg.getDescription().setPlainTextContent("Temp description");
		    			mg.setPrivate(privacyoption4);    			
		    			threadjopsmd = "Your scheduled upload \"" + desiredtitle2 + "\" has started \n Please dont close the \"Taskeng.exe\" window";
		    			System.out.println("Your scheduled upload \"" + desiredtitle2 + "\" has started \n Please dont close the \"Taskeng.exe window\"");    			
		    	        MediaFileSource ms = new MediaFileSource(new File(filestring), videomimetype);
		    			newEntry.setMediaSource(ms);
		    			String uploadUrl =
		    			  "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";
		    			try {
			            	myService.setUserCredentials(username2,passwrd2);        	
		    				VideoEntry createdEntry = myService.insert(new URL(uploadUrl), newEntry); //Uploads
		    				createdEntry.getMediaGroup().getDescription().setPlainTextContent(discription2);
	    		        	createdEntry.update();
		    				createdEntry.getMediaGroup().getTitle().setPlainTextContent(desiredtitle2);
	    		        	createdEntry.update();
		    		        for(int i=0;i<arr.length;i++) {
		    		        	if (arr[i].length() > 1) {
		    		        		if (arr[i].length() < 31) {
		    		        			System.out.println("Adding keyword:" + arr[i]);
		    		        			createdEntry.getMediaGroup().getKeywords().addKeyword((String) arr[i]); //TODO bad request on keywords
		    		        		}
		    		        	}
		    		        	createdEntry.update();
		    		        }
		    			} catch (IOException | ServiceException e) {
		    				e.printStackTrace();
			    			System.out.println("Username authentication error. Is your stored username and password correct?");
		    				return;
		    			}   			
		    			System.out.println("Video uploaded");
		        		System.exit(0);		        				        		
		        	} else { // if( !fleExample.exists() )		        		
		        		System.out.println(fleExample + ": No file exists with that name");
		        		return;
		        	}
					return;			
				}
			}
		});
	}
	public YtuSchedulerGUI2() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame("YtuScheduler" + discription.getText());
		frame.setBounds(100, 100, 669, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(377, 26, 59, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(518, 26, 59, 20);
		frame.getContentPane().add(lblPassword);
		
		passwrd = new JPasswordField();
		passwrd.setBounds(518, 44, 127, 20);
		frame.getContentPane().add(passwrd);
		
		desiredtitle.setBounds(10, 44, 359, 20);
		frame.getContentPane().add(desiredtitle);
		desiredtitle.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 29, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 90, 98, 14);
		frame.getContentPane().add(lblDescription);
		discription.setLineWrap(true);
		
		
		discription.setBounds(1, 125, 633, 77);
		frame.getContentPane().add(discription); 
		
		JScrollPane discriptionJSP = new JScrollPane(discription); 
		discriptionJSP.setBounds(10, 106, 635, 203);
		frame.getContentPane().add(discriptionJSP);
		
		JLabel lblNewLabel = new JLabel("Catorgory");
		lblNewLabel.setBounds(237, 64, 59, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Privacy");
		lblNewLabel_1.setBounds(138, 64, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblKeywords = new JLabel("Keywords");
		lblKeywords.setBounds(10, 311, 68, 14);
		frame.getContentPane().add(lblKeywords);
		
		YTcatorgories2.setBounds(236, 79, 133, 20);
		frame.getContentPane().add(YTcatorgories2);
		
		privacyoption2.setBounds(137, 79, 97, 20);
		frame.getContentPane().add(privacyoption2);
		
		keywords = new JTextField();
		keywords.setBounds(9, 327, 636, 20);
		frame.getContentPane().add(keywords);
		keywords.setColumns(10);
		
		JButton btnUploadNow = new JButton();
		btnUploadNow.setAction(action);
		btnUploadNow.setBounds(10, 355, 309, 30);
		frame.getContentPane().add(btnUploadNow);
		
		JButton btnScheduleIt = new JButton();
		btnScheduleIt.setAction(action_1);
		btnScheduleIt.setBounds(540, 355, 105, 30);
		frame.getContentPane().add(btnScheduleIt);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.setBounds(10, 509, 629, 70);
		frame.getContentPane().add(list);
		
		JScrollPane scrollPane = new JScrollPane(list); 
		scrollPane.setBounds(10, 393, 529, 105);
		frame.getContentPane().add(scrollPane);
		
		
		filelocation = new JTextField("");
		filelocation.setBounds(105, 5, 537, 20);
		frame.getContentPane().add(filelocation);
		filelocation.setColumns(10);
		filelocation.addFocusListener(listener);
		filelocation.setAction(action_7);

		
		JButton btnNewButton = new JButton();
		btnNewButton.setAction(action_2);
		btnNewButton.setBounds(8, 5, 89, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReschedule = new JButton("Reschedule");
		btnReschedule.setAction(action_3);
		btnReschedule.setBounds(540, 392, 105, 23);
		frame.getContentPane().add(btnReschedule);
		
		JButton btnRunNow = new JButton("Run now");
		btnRunNow.setAction(action_4);
		btnRunNow.setBounds(540, 447, 105, 23);
		frame.getContentPane().add(btnRunNow);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setAction(action_5);
		btnDelete.setBounds(540, 474, 105, 23);
		frame.getContentPane().add(btnDelete);
		
		username = new JComboBox();
		username.setEditable(true);
		username.setBounds(377, 44, 136, 20);
		frame.getContentPane().add(username);
		//username.addActionListener 
		username.setAction(action_6);
		
		statusbar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		statusbar.setBounds(10, 500, 633, 21);
		frame.getContentPane().add(statusbar);
		
		ytstime.setBounds(422, 364, 61, 20);
		frame.getContentPane().add(ytstime);
		ytstime.setColumns(10);
		
		ytsdate.setBounds(329, 364, 86, 20);
		frame.getContentPane().add(ytsdate);
		ytsdate.setColumns(10);
		
		JLabel lblDate = new JLabel("Date mm/dd/yyyy");
		lblDate.setBounds(329, 352, 111, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblTimeHhmm = new JLabel("  Time HH:MM");
		lblTimeHhmm.setBounds(422, 352, 86, 14);
		frame.getContentPane().add(lblTimeHhmm);
		
		AMPM2.setBounds(490, 364, 47, 19);
		frame.getContentPane().add(AMPM2);
		
		JButton button = new JButton("New button");
		button.setAction(action_9);
		button.setBounds(504, 79, 141, 20);
		frame.getContentPane().add(button);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAction(action_8);
		btnNewButton_1.setBounds(376, 79, 128, 20);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnReschedule_1 = new JButton("Reschedule");
		btnReschedule_1.setAction(action_10);
		btnReschedule_1.setBounds(540, 419, 105, 23);
		frame.getContentPane().add(btnReschedule_1);
	}
	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 5440391449648952784L;
		public SwingAction() {
			putValue(NAME, "Upload now");
			putValue(SHORT_DESCRIPTION, "Click here to upload your video now");
		}
		public void actionPerformed(ActionEvent e) {
			username2 = (String) YtuSchedulerGUI2.username.getSelectedItem();
			passwrd2 = (String) YtuSchedulerGUI2.passwrd.getText();
			desiredtitle2 = YtuSchedulerGUI2.desiredtitle.getText();
			discription2 = YtuSchedulerGUI2.discription.getText();
			YTcatorgories3 = (String) YtuSchedulerGUI2.YTcatorgories2.getSelectedItem();
			privacyoption3 = (String) YtuSchedulerGUI2.privacyoption2.getSelectedItem();
			keywords2 = YtuSchedulerGUI2.keywords.getText();
			filelocationtest = (String) YtuSchedulerGUI2.filelocation.getText();
			if (filestringstr == null) {
				if (filelocationtest.length() == 0) {
					systemoutvar = "Please choose a file";
					new Thread(systemout).start();
					JOptionPane.showMessageDialog(null, systemoutvar);
					return;
					
				} else {
					systemoutvar = "setting filestringstr to filelocationtest";
					new Thread(systemout).start();
			        String[] filestringarr = filelocationtest.split("\\\\"); // uses \\ as a delimiter and splits a string in to an array
			        for(int i=0; i<filestringarr.length; i++) {
		    			//System.out.println("array place " + i + ":" + args[i]);
			        	filestringstr = filestringarr[i];
		    		}		
				}
				systemoutvar = filestringstr;
				new Thread(systemout).start();
			}
			if (username2 == null) {
				systemoutvar = "Please enter a username";
				new Thread(systemout).start();
				JOptionPane.showMessageDialog(null, systemoutvar);
				return;
				
			}
			if (passwrd2.length() == 0) {
				systemoutvar = "Please enter a password";
				new Thread(systemout).start();
				JOptionPane.showMessageDialog(null, systemoutvar);
				return;
				
			}
			
		    if (discription2.length() == 0) {
		    	discription2 = filestringstr;
		    }
		    if (desiredtitle2.length() == 0) {
		    	desiredtitle2 = filestringstr;
		    }
		    if (keywords2.length() < 2) {
		    	keywords2 = filestringstr;
		    }
			System.out.println("File location: " + filelocationtest);
			System.out.println("File name: " + filestringstr);
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
    		if (privacyoption3 == "Public") {
    			privacyoption4 = false;
    		} else {
    			privacyoption4 = true;
    		};
			new Thread(makevideoconfig).start();
    		try {
				Thread.currentThread();
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			new Thread(saveloginstuff).start();
    		new Thread(startupload).start();
			return;
		}
	}
	private class SwingAction_1 extends AbstractAction {
		private static final long serialVersionUID = -7249609492621075126L;
		public SwingAction_1() {
			putValue(NAME, "Schedule it");
			putValue(SHORT_DESCRIPTION, "Click here to upload the video later");
		}
		public void actionPerformed(ActionEvent e) {
			new Thread(makevideoconfig).start();
			username2 = (String) YtuSchedulerGUI2.username.getSelectedItem();
			passwrd2 = (String) YtuSchedulerGUI2.passwrd.getText();
			desiredtitle2 = YtuSchedulerGUI2.desiredtitle.getText();
			discription2 = YtuSchedulerGUI2.discription.getText();
			YTcatorgories3 = (String) YtuSchedulerGUI2.YTcatorgories2.getSelectedItem();
			privacyoption3 = (String) YtuSchedulerGUI2.privacyoption2.getSelectedItem();
			keywords2 = YtuSchedulerGUI2.keywords.getText();
			filelocationtest = (String) YtuSchedulerGUI2.filelocation.getText();
			if (filestringstr == null) {
				if (filelocationtest.length() == 0) {
					systemoutvar = "Please choose a file";
					new Thread(systemout).start();
					//JOptionPane.showMessageDialog(null, systemoutvar);
					return;
					
				} else {
					systemoutvar = "setting filestringstr to filelocationtest";
					new Thread(systemout).start();
			        String[] filestringarr = filelocationtest.split("\\\\"); // uses \\ as a delimiter and splits a string in to an array
			        for(int i=0; i<filestringarr.length; i++) {
			        	filestringstr = filestringarr[i];
		    		}					
				}
				systemoutvar = filestringstr;
				new Thread(systemout).start();			
			}
			if (username2 == null) {
				systemoutvar = "Please enter a username";
				new Thread(systemout).start();
				JOptionPane.showMessageDialog(null, systemoutvar);
				return;
				
			}
			if (passwrd2.length() == 0) {
				systemoutvar = "Please enter a password";
				new Thread(systemout).start();
				JOptionPane.showMessageDialog(null, systemoutvar);
				return;
				
			}
			
		    if (discription2.length() == 0) {
		    	discription2 = filestringstr;
		    }
		    if (desiredtitle2.length() == 0) {
		    	desiredtitle2 = filestringstr;
		    }
		    if (keywords2.length() < 2) {
		    	keywords2 = filestringstr;
		    }
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
    		String ytsdate5 = ytsdateYY + "." + ytsdateMM + "." + ytsdateDD;
    		String ytstime5 = ytstimeHH + "." + ytstimeMM;
    		String Ytusjarfilelocation = "\"java.exe -jar \'" + usershome + "\\AppData\\Roaming\\YTscheduler\\YtuScheduler.jar\'";
    		String olsytu = " " + username2 + "_" + filestringstr + ".txt";
    		String taskname = desiredtitle2;
    		String[] taskname2 = taskname.split(" ");
        	String taskname3 = "";
        	olsytu = olsytu.replace( "\'",""); //TODO 
        	olsytu = olsytu.replace( "\"","");  
    		for(int i=0; i<taskname2.length; i++) {
    			taskname3 = taskname3 + taskname2[i] + "_";
    		}
    		String taskname4 = "YtuScheduler_"  + username2 + "_" + taskname3 + ytsdate5 + "_" + ytstime5;    	
    		taskname4 = taskname4.replace( "\'","");  
    		taskname4 = taskname4.replace( "\"","");
            try {
            	System.out.println("Ytusjarfilelocation:" + Ytusjarfilelocation);
                // FIXED: scheduled task fails when there is a single or double quote in the title (maybe filename as well?) just remove them from text file name. 
            	String makeascheduler = "c:\\windows\\system32\\schtasks.exe /Create /SC ONCE /TN " + taskname4 + " /TR " + Ytusjarfilelocation + olsytu + "\" /ST " + ytstime4 + " /SD "  + ytsdate4;
                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec(makeascheduler);
                System.out.println(makeascheduler);
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line=null;
                String linetake2 = "";   
                linetake2 = "Task creation has failed";
                while((line=input.readLine()) != null) {
                    //System.out.println(line);
                    linetake2 = line;
                }
                //JOptionPane.showMessageDialog(frame, linetake2);    
				systemoutvar = linetake2;
				new Thread(systemout).start();
                int exitVal = pr.waitFor();
                System.out.println("code:"+exitVal);
             } catch(Exception e1) {
                System.out.println(e1.toString());
                e1.printStackTrace();
            }
			YtuSchedulerGUI2.desiredtitle.setText("");
			YtuSchedulerGUI2.discription.setText("");
			YtuSchedulerGUI2.YTcatorgories2.setSelectedItem("Autos");	
			YtuSchedulerGUI2.privacyoption2.getSelectedItem();
			YtuSchedulerGUI2.keywords.setText("");
			YtuSchedulerGUI2.filelocation.setText("");
    	    System.out.println("Task has been scheduled");
		}
	}
	private class SwingAction_2 extends AbstractAction {
		private static final long serialVersionUID = -4109162319778233662L;
		public SwingAction_2() {
			putValue(NAME, "Browse");
			putValue(SHORT_DESCRIPTION, "Borwse for a video file");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("File selection dialog opened");
			int answer = fc.showOpenDialog(fc);
			if (answer == JFileChooser.APPROVE_OPTION)  
    	    {  			
    			fcstring = fc.getSelectedFile();
    			filestring = fcstring.toString();
    			System.out.println("File choosen");
    	    } else {
    			System.out.println("No file choosen");
    	    	return;
    	    }
			filelocation.setText(filestring);
			new Thread(setmimetype).start();
		}
	}
	private class SwingAction_3 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6962752476160718560L;
		public SwingAction_3() {
			putValue(NAME, "Load tasks");
			putValue(SHORT_DESCRIPTION, "Load currently scheduled tasks ... Use this to confirm that your task has been scheduled"/*"Reschedule selected task"*/);
			
		}
		public void actionPerformed(ActionEvent e) {
            try {
            	String makeascheduler1 = "c:\\windows\\system32\\schtasks.exe /Query /FO CSV";// > SchTasksCSVoutput.txt";
                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec(makeascheduler1);
                //System.out.println(makeascheduler);
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line=null;
                String linetake2 = "";   
                while((line=input.readLine()) != null) {
                	String[] linearr=line.replace( "\"","").split(",");
                	String[] linearr2=linearr[0].split("_");
                	if (linearr2[0].equals("\\YtuScheduler")) {
                		//System.out.println("linearr[0]:" + linearr[0]);
                		SchTaskarrlist.add(line.replace( "\"","").replace( "\\YtuScheduler_",""));
                		SchTaskarrlistraw.add(line);
                		System.out.println(line);
                	}
                }
                System.out.println(linetake2);
                int exitVal = pr.waitFor();
             } catch(Exception e1) {
                System.out.println(e1.toString());
                e1.printStackTrace();
            }
            boolean yesadd = true;
	        for(int i=0; i<SchTaskarrlist.size(); i++) {
	        	yesadd = true;
		        for(int i1=0; i1<SchTaskarralreadylisted.size(); i1++) {
		        	if (SchTaskarralreadylisted.get(i1).equals(SchTaskarrlist.get(i))) {
		        		yesadd = false;
		        	}		        	
		        }
		        if (yesadd == true) {
		        	System.out.println("adding:" + SchTaskarrlist.get(i));
		        	listmodel.addElement(SchTaskarrlist.get(i));
		        	SchTaskarralreadylisted.add(SchTaskarrlist.get(i));
		        	SchTaskarralreadylistedraw.add(SchTaskarrlistraw.get(i));
	        		//System.out.println("SchTaskarrlist.get(i):" + SchTaskarrlist.get(i));
		        }
    		}
			new Thread(loadtasks).start();
		}
	}
	private class SwingAction_4 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1541079814375640616L;
		public SwingAction_4() {
			putValue(NAME, "Run now");
			putValue(SHORT_DESCRIPTION, "Run the selected task now (the task may still run as originally scheduled"/*Run the selected task now (will delete task after upload)*/);
		}
		public void actionPerformed(ActionEvent e) {
			new Thread(runtask).start();
		}
	}    
	private class SwingAction_5 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -83110152283876448L;
		public SwingAction_5() {
			putValue(NAME, "Delete");
			putValue(SHORT_DESCRIPTION, "Deletes selected task");
		}
		public void actionPerformed(ActionEvent e) {
			new Thread(deletetask).start();

		}
	}
	private class SwingAction_6 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6786548599216332991L;
		public SwingAction_6() {
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("user selection change detected");
			//new Thread(systemout).start();
			//System.out.println(systemoutvar);
    		for(int i=0; i<savedloginU.size(); i++) {
    			if ((String) savedloginU.get(i) == username.getSelectedItem()) {
        			//System.out.println("savedloginU currently selected:" + (String) savedloginU.get(i));
        			System.out.println("Retrieving password for:" + (String) savedloginU.get(i));
        			YtuSchedulerGUI2.passwrd.setText((String) savedloginP.get(i));
    			}
    		}			
		}
	}
	private class SwingAction_7 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1982263906556842811L;
		public SwingAction_7() {
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change detected in file path area");
			new Thread(setmimetype).start();
		}
	}
	private class SwingAction_8 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2388188843950976676L;
		public SwingAction_8() {
			putValue(NAME, "Save username");
			putValue(SHORT_DESCRIPTION, "Save the currently typed in username and password (will ignore \'save login\' selection \n You can use this to update the password aswell");
		}
		public void actionPerformed(ActionEvent e) {
			savelogininformation3 ="Yes";
			new Thread(saveloginstuff).start();		}
	}
	private class SwingAction_9 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1753372711425999802L;
		public SwingAction_9() {
			putValue(NAME, "Remove username");
			putValue(SHORT_DESCRIPTION, "Deletes the saved username and password");
		}
		public void actionPerformed(ActionEvent e) {
			new Thread(removeselecteduser).start();
		}
	}
	private class SwingAction_10 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1541173214575640616L;
		public SwingAction_10() {
			putValue(NAME, "Reschedule");
			putValue(SHORT_DESCRIPTION, "Reschedule selected task"/*Run the selected task now (will delete task after upload)*/);
		}
		public void actionPerformed(ActionEvent e) {
			new Thread(rescheduletask).start();
		}
	}
}

