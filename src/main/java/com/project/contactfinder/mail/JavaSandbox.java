package com.project.contactfinder.mail;

import com.project.contactfinder.fct.str;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author Ryan.Oglesby
*/
public class JavaSandbox {

private static String sURL;

/**
 * @param args the command line arguments
 */
public static void main(String[] args) throws MalformedURLException, IOException {

JavaSandbox jsb = new JavaSandbox();
				
String staar = "lkjamlzkelkazmekmakzmlekmaz,em a-ek@hotmail.fr lkazmllakzkmlk karim@hot.de aamlazemlk";

List<String> AA = jsb.listMailCreatorSC(staar);

for(String st:AA){
    System.out.println("test : "+st);
}


List<String> lstUrls = jsb.urlsGetterFromQuestion("mes contacts email", 2);


List<String> lstMails = new ArrayList<String>();
List<String> lstMailA = new ArrayList<String>();

    for(String str:lstUrls){
        try {
            lstMails = jsb.urlsGetterFromUrl(str);
        } catch (Exception e) {
        
        }
        if(lstMails != null)
        for(String st:lstMails){
           // lstMailA.add(st);
            System.out.println("URL: "+st);
            lstMailA = jsb.listMailCreatorSC(st);
            
            if(lstMailA != null)
                for(String sts:lstMailA){
                    System.out.println("Mails: "+sts);
                }
            lstMailA = new ArrayList<String>();
        }
        lstMails = new ArrayList<String>();
    }
    
     

}


public List<String> listMailCreator(String url) throws IOException{
	URL oracle = new URL(url);
	
        boolean go = true;
	
         BufferedReader in = null; //= new BufferedReader(new InputStreamReader(new URL("http://google.com").openStream()));
	try {
	        in = new BufferedReader(new InputStreamReader(oracle.openStream()));
	} catch (IOException e) {
                go = false;
	}
	
  
    String inputLine;
    String page = "";
   if(go){
        while ((inputLine = in.readLine()) != null){
     	page = page + inputLine;
    }
    
       List<String>  lst = new ArrayList<String>();
        
       lst = listMail(page);
       
        in.close();	    
        return lst;
   }else{
       return null;
   }
   
}


//--------------------------------------------------

public List<String> listHttpCreator(String url) throws IOException{
		 URL oracle = new URL(url);
		 BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
	   
		 String inputLine;
	     String page = "";
	    
	     while ((inputLine = in.readLine()) != null){
	      	page = page + inputLine;
	     }
	     
	        List<String>  lst = new ArrayList<String>();
	         
	        lst = listHttp(page);
	        
	    in.close();	    
	    return lst;

}

//------------------------------------------------

List<String> listHttpCreatorSC(String page) throws IOException{
	 Matcher m = Pattern.compile("\\b(http|https|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]").matcher(page);
	    
	    List<String> lst = new ArrayList<String>();
	    
	    while (m.find()) {
	        lst.add(m.group());
	    }
	    
	    return lst;
}

public List<String> listMailCreatorSC(String page) {
        List<String> lst0 = new ArrayList<String>();
    try {
          lst0 = listMailCreator(page);
    } catch (IOException ex) {
       // Logger.getLogger(JavaSandbox.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(page);

    List<String> lst = new ArrayList<String>();
    
    while (m.find()) {
       // System.out.println(m.group());
        lst.add(m.group());
    }
    
    if(lst0!=null)
    for(String gt:lst0){
        lst.add(gt);
    }
    
    return lst;
}



 public List<String> listNumberCreatorSC(String page) {

    Matcher m = Pattern.compile("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$").matcher(page);
    
    List<String> lst = new ArrayList<String>();
    
    while (m.find()) {
       // System.out.println(m.group());
        lst.add(m.group());
    }
    
    return lst;
}       
        
//------------------------------------------------

public String codeSourcePage(String url) throws IOException{
    
    
	 String generate_URL = url;
         String result = "";
         String inputLine;
        try {
            URL data = new URL(generate_URL);
           
            HttpURLConnection con = (HttpURLConnection) data.openConnection(); 
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                result = result+"\n"+inputLine;
            }
            in.close();
            con.disconnect();
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return result;
}

//------------------------------------------------
public List<String> listHttp(String httpStr){
	List<String> lst = new ArrayList<String>();
	
	while(httpExist(httpStr)){
		int i = indexOfHttp(httpStr);
		String htp = getHttp(httpStr, i);
		if(htp != null){
			lst.add(htp);
		    httpStr = newHttpStr(httpStr, i);
		}
	}
	
	return lst;
}

public String getHttp(String httpStr, int index){
	try {
		char carectere = 0;
		String link = "";
		int i = index;
		while(carectere != '"'){
			    link = link+carectere;
			    carectere = httpStr.charAt(i);
			   i++;
		}
		// System.out.println(link);
		return link;
	} catch (Exception e) {
		return null;
	}
	
}


public int indexOfHttp (String httpStr){
	int index = httpStr.indexOf("http://");
	return index;
}


public String newHttpStr (String httpStr, int index){
	return httpStr.substring(index+7, httpStr.length());
}

public boolean httpExist(String httpStr){
	return httpStr.contains("http://");
}
//------------------------------------------------------------

public List<String> listMail(String httpStr){
	List<String> lst = new ArrayList<String>();
	
	while(mailExist(httpStr)){
		try {
			int i = indexMail(httpStr);
			String htp = getMail(httpStr, i);
			lst.add(htp);
			httpStr = newMail(httpStr, i);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	return lst;
}

public String getMail(String httpStr, int index){
	char carectere = 0;
	String mail = "";
	int i = index;
	while(carectere != '"'){
		    mail = mail+carectere;
		    carectere = httpStr.charAt(i);
		   i++;
	}
	// System.out.println(mail.substring(8, mail.length()));
	return mail.substring(8, mail.length());
}

public int indexMail (String httpStr){
	int index = httpStr.indexOf("mailto:");
	return index;
}


public boolean mailExist(String httpStr){
	return httpStr.contains("mailto:");
}


public String newMail (String httpStr, int index){
	return httpStr.substring(index+7, httpStr.length());
}


public List<String> getSimpleMailList(String url) throws IOException{
	URL oracle = new URL(url);
	 BufferedReader in = null ;
	try {
		  in = new BufferedReader(new InputStreamReader(oracle.openStream()));
	} catch (Exception e) {
		e.printStackTrace();
	}
   String inputLine;
   String page = "";
   while ((inputLine = in.readLine()) != null){
    	page = page + inputLine;
   }
    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(page);
    List<String> lst = new ArrayList<String>();
    while (m.find()) {
        lst.add(m.group());
    }
    
    return lst;
}

//---------------------------------------------

public List<String> getSimpleHttpList(String url) throws IOException{
	URL oracle = new URL(url);
	 BufferedReader in = null ;
	try {
		  in = new BufferedReader(new InputStreamReader(oracle.openStream()));
	} catch (Exception e) {
		e.printStackTrace();
	}
   String inputLine;
   String page = "";
   while ((inputLine = in.readLine()) != null){
    	page = page + inputLine;
   }
    Matcher m = Pattern.compile("\\b(http?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]").matcher(page);
    List<String> lst = new ArrayList<String>();
    while (m.find()) {
        lst.add(m.group());
    }
    
    return lst;
}


public List<String> listMot(String str, String separator){
	
	String mot = "";
	String chaine = str;
	List<String> lstMot = new ArrayList<String>();
	
	while(chaine.contains(separator)){
		mot = chaine.substring(0, chaine.indexOf(separator));
		lstMot.add(mot);
		chaine = chaine.substring(mot.length()+separator.length(), chaine.length());
	}
	lstMot.add(chaine);
	
	return lstMot;
}


private List<String> distinctResultList(List<String> lstStr){
    List<String> lst = new ArrayList<String>();
    
    for(String str : lstStr){
        if(!lst.contains(str))lst.add(str);
    }
    return lst;
}


private List<String> search(String question, int profondeur){
    // yassine+jebali&first=11&FORM=PERE 
 List<String> lstUrls = new ArrayList<String>();
 str mot = new str();
 List<String> req = mot.searchWordList(question);

 List<Integer> intlst = mot.intList(question);
 
 String ints = "";
 
 for(Integer intt:intlst){
     ints = ints+"+"+intt;
 }
 
 for(int i = 0; i<=profondeur; i++){
     String wordToInsert = "";
     for(String st:req){
             wordToInsert = wordToInsert+"+"+st;
     }
     
     // first=41&FORM=PERE3
     
     wordToInsert = wordToInsert.substring(1, wordToInsert.length());
     if(i==0){
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints);
     }else if(i>1){
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first="+i+"1&FORM=PERE"+(i-1)); 
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first="+i+"1&FORM=PORE"+(i-1)); 
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first="+i+"1&FORM=PQRE"+(i-1)); 
         
         int s =(int) Math.random()*(0-i)+1;
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first="+i+"1&FORM=PERE"+s); 
          s =(int) Math.random()*(0-i)+1;
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first="+i+"1&FORM=PORE"+s); 
          s =(int) Math.random()*(0-i)+1;
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first="+i+"1&FORM=PQRE"+s); 
     }else{
         lstUrls.add("https://www.bing.com/search?q="+wordToInsert+ints+"&first=1&FORM=PERE"); 
         
     }
     
 }
 
 
 return lstUrls;
}

//------------------------------------------------

public List<String> urlsGetterFromUrl(String url){
    String sourceCode = "";
				try {
                                    sourceCode = codeSourcePage(url);
					//System.out.println(codeSourcePage(url));
				} catch (IOException e) { }
                                
//---------------------------------------------------                                
                                
				List<String> listHttpLoc = new ArrayList<>();
				try {
					listHttpLoc = listHttpCreatorSC(sourceCode);
				} catch (IOException e) {
				}
				Vector vct = new Vector();
				for(String url1: listHttpLoc)
					if(!url1.contains("xhtml")){
					if( !url1.contains("microsoft"))
					if( !url1.contains("schemas"))
					if(!url1.contains(".pdf"))
                                        if(!url1.contains(".zip"))
                                        if(!url1.contains(".rar"))
                                        if(!url1.contains(".exe"))
                                        if(!url1.contains(".jar"))
					if(!url1.contains(".doc"))
					if(!url1.contains(".jpg"))
					if(!url1.contains(".png"))
					if(!url1.contains("google"))
					if(!url1.contains("w3.org"))
					if(!url1.contains("mailto:robert@broofa.com"))
					if(!url1.contains("webcache.googleusercontent"))
					if(!url1.contains("schema.org/SearchResultsPage")){
					 //  System.out.println(url1);
					   vct.addElement(url1);
							}
						}
                                List<String> strList = new ArrayList<String>();
				for(int i = 0; i<vct.size(); i++) 
                                    strList.add((String)vct.elementAt(i));
                                
                     List<String> lstUrls = distinctResultList(strList);         
                                
                            
                                
            return distinctResultList(strList);
}


public List<String> urlsGetterFromQuestion(String question, int profondeur){
    List<String> lstFromBing = search(question, profondeur);
    List<String> lstResults = new ArrayList<String>();
    List<String> lstRst = new ArrayList<String>();
    for(String url: lstFromBing){
        
        lstRst = urlsGetterFromUrl(url);
        for(String rst : lstRst)  lstResults.add(rst);
        lstRst = new ArrayList<String>();
    }
    return distinctResultList(lstResults);
}


}