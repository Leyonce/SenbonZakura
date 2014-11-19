package org.evenos.zerlina.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eyog Yvon Léonce - grandeyl@gmail.com
 * 
 * This Class is to create a list of the different contacts read in the phone.
 * 
 */
public class ContactsDataList implements Serializable  {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ContactData> contactList;

	public ContactsDataList() {
     
		this(new ArrayList<ContactData>());
	}

	public ContactsDataList(List<ContactData> contactList) {
		this.contactList = contactList;
	}

	public void addContact(ContactData contact) {
		if (contactList != null) {
			contactList.add(contact);
		}
	}

	public List<ContactData> getContacts() {
		return contactList;
	}
	
	public void setContacts(List<ContactData> contacts) {
        this.contactList = contacts;
    }
	
	
}