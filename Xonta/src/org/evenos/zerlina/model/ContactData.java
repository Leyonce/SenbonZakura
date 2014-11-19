package org.evenos.zerlina.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author Eyog Yvon Léonce - grandeyl@gmail.com
 * 
 * This class is to create contact objects with attributes name and
 * one unique phone number
 * 
 * */

public class ContactData implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String contact_name;
	private String phone_number;
	private String contact_ID;
    private String operator_name;
    private int number_count;
	private String secondPN;
	private String thirdPN;
	private String fouthPN;
    private int phonetype;
    public ContactData() {

	}
	public int getNumber_count() {
		return number_count;
	}
	public void setNumber_count(int number_count) {
		this.number_count = number_count;
	}
	public String getContact_ID() {
		return contact_ID;
	}

	public void setContact_ID(String contact_ID) {
		this.contact_ID = contact_ID;
	}

	public ContactData(String contact_name, String phone_number) {

		this.contact_name = contact_name;
		this.phone_number = phone_number;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getSecondPN() {
		return secondPN;
	}
	public void setSecondPN(String secondPN) {
		this.secondPN = secondPN;
	}
	public String getThirdPN() {
		return thirdPN;
	}
	public void setThirdPN(String thirdPN) {
		this.thirdPN = thirdPN;
	}
	public String getFouthPN() {
		return fouthPN;
	}
	public void setFouthPN(String fouthPN) {
		this.fouthPN = fouthPN;
	}
	public int getPhonetype() {
		return phonetype;
	}
	public void setPhonetype(int phonetype) {
		this.phonetype = phonetype;
	}
	public String toString() {
		return contact_name + ", " + phone_number;
	}

	public boolean equals(Object obj) {
		if (obj instanceof ContactData) {
			ContactData contact = (ContactData) obj;
			return (contact_name.equals(contact.getContact_name()) && phone_number
					.equals(contact.getPhone_number()));
		}

		return false;
	}

	public int hashCode() {
		return (contact_name.length() + phone_number.length());
	}

	
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}


	class ContactNameComparator implements Comparator<ContactData> {
		public int compare(ContactData contact1, ContactData contact2) {
			return contact1.getContact_name().compareToIgnoreCase(
					contact2.getContact_name());
		}
	}
}