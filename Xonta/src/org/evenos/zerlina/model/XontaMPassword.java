package org.evenos.zerlina.model;

/**
 * @author Jan Thieleman
 * @author Eyog Yvon Léonce
 * */
public class XontaMPassword {

	public static final String TABLE_NAME = "Password";

	public static final String Columnname_ID = "Password_ID";
	public static final String Columnname_Key = "Key";
	public static final String Columnname_Tries = "Tries";
	public static final String Columnname_Salt = "Salt";
	public static final String Columnname_InitVector = "InitVector";
    public static final String Columnname_Day = "Day";
	public static final String[] COLUMNS = { 
		                                     Columnname_ID, 
		                                     Columnname_Key,
		                                     Columnname_Tries,
		                                     Columnname_Salt,
		                                     Columnname_InitVector,
		                                     Columnname_Day 
		                                    };

	private int password_id;
	private String key;
	private int tries;
	private String salt;
	private String initVector;
    private String day;
	

	public XontaMPassword(int password_id, String key, int tries, String salt, String initVector, String day) {
		this.password_id = password_id;
		this.key = key;
		this.salt = salt;
		this.initVector = initVector;
		this.day=day;
	}

	@Override
	public String toString() {
		return "MPassword[Password_ID=" + this.password_id + ", Key=" + this.key + ", Tries=" + this.tries + ", Salt=" + this.salt
				+ ", Initialization Vector=" + this.initVector + "Day="+ this.day + "]";
	}

	public int getPassword_id() {
		return password_id;
	}

	public void setPassword_id(int password_id) {
		this.password_id = password_id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getInitVector() {
		return initVector;
	}

	public void setInitVector(String initVector) {
		this.initVector = initVector;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
