package model;

import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@ApplicationScoped
public class Addresses {
	
	private ArrayList<Address> addresses = null;
	private Address currentAddress = null;
	
	public Addresses() {
		addresses = new ArrayList<Address>();
		currentAddress = new Address();	
	}

	public ArrayList<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	public String addAddress(){

		addresses.add(0, currentAddress);
		currentAddress = new Address();
		return "addresses.xhtml";
	}

}
