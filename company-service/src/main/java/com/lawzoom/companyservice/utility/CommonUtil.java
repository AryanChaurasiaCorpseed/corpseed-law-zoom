package com.lawzoom.companyservice.utility;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@UtilityClass
public class CommonUtil {

	public String getCurrentTimeStamp() {
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf1.format(timestamp).replace(".", "");
	}
	
	public Date getDate() {
		return new Date();
	}
		
	public String getPassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for(int i = 4; i< length ; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		return password.toString().replaceAll("\\[","");
	}

	public String getCredentialMessage(String companyName, String companyEmail, String password) {
//		System.out.println("pass =="+password);
		String message="<p>Hi "+companyName+",</p><p>Your Login credential is : "
				+ "</p><p>Username : "+companyEmail+"<br>Password : "+password+"</p>";
				
		return message;
	}

	public Date getExpiryDate(int hour) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance(); 
        c.setTime(getDate()); 
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public long getUniqueCode() {
		return (long) Math.floor(Math.random() * 9000000000L) ;
	}

	 public boolean isValidPassword(String password)
	    {
	  
	        // Regex to check valid password.
	        String regex = "^(?=.*[0-9])"
	                       + "(?=.*[a-z])(?=.*[A-Z])"
	                       + "(?=.*[@#$%^&+=])"
	                       + "(?=\\S+$).{6,20}$";
	  
	        // Compile the ReGex
	        Pattern p = Pattern.compile(regex);
	  
	        // If the password is empty
	        // return false
	        if (password == null) {
	            return false;
	        }
	  
	        // Pattern class contains matcher() method
	        // to find matching between given password
	        // and regular expression.
	        Matcher m = p.matcher(password);
	  
	        // Return if the password
	        // matched the ReGex
	        return m.matches();
	    }

	public String encodePassword(String password) {
		 return new BCryptPasswordEncoder().encode(password);
	}

	public Date parseToDate(Object object) {
		if(object==null)return null;
		String date=object.toString();
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(date);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean isPasswordMatched(String password, String password1) {
		 return password.equals(password1);
	}
	public Date dateTimeAfterMinutes(int m) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDate());
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+m);
		c.set(Calendar.SECOND, c.get(Calendar.SECOND));
		return c.getTime();
	}

	public boolean isTimeExpired(Date expireDateTime) {
		if(new Date().before(expireDateTime)){
			return false;
		}else
			return true;
	}
}
