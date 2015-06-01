package de.ar.tools.codenameone;

import com.codename1.contacts.Address;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.io.Log;

public class ContactTool {

	public static void readPhoneContacts() {
		String[] allContactIds = ContactsManager.getAllContacts();
		for (String id : allContactIds) {
			Contact contact = ContactsManager.getContactById(id);

			String firstname = contact.getFirstName();
			String lastname = contact.getFamilyName();
			String note = contact.getNote();

			String displayName = contact.getDisplayName();
			String primaryEmail = contact.getPrimaryEmail();

			String street = "";
			String houseNumber = "";
			String postalCode = "";
			String place = "";
			String state = "";
			String land = "";

			Log.p("firstname:" + firstname);
			Log.p("lastname:" + lastname);
			Log.p("note:" + note);

			Log.p("displayName:" + displayName);
			Log.p("primaryEmail:" + primaryEmail);

			int i = 1;
			for (Object obj : contact.getAddresses().values()) {
				Address address = (Address) obj;
				Log.p("Address Nr." + i);
				street = address.getStreetAddress();
				houseNumber = address.getStreetAddress();
				postalCode = address.getPostalCode();
				place = address.getLocality();
				state = address.getRegion();
				land = address.getCountry();

				Log.p("street:" + street);
				Log.p("houseNumber:" + houseNumber);
				Log.p("postalCode:" + postalCode);
				Log.p("place:" + place);
				Log.p("state:" + state);
				Log.p("land:" + land);

				i++;
			}
		}
	}
}