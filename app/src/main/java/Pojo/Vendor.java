package Pojo;

/**
 * Created by w7 on 1/29/2016.
 */
public class Vendor {

 String Vendorname;
 String VendorId;
 String VendorContact;
 String Address;
 String City;
 String Country;
 String Zip;
 String Telephone;
 String Mobile;
 String VendorInventory;
 String Active;
 String Email;



 public Vendor(String vendorname, String vendorId, String vendorContact, String address, String city, String country, String zip, String telephone, String mobile, String vendorInventory,String active,String Email) {
  Vendorname = vendorname;
  VendorId = vendorId;
  VendorContact = vendorContact;
  Address = address;
  City = city;
  Country = country;
  Zip = zip;
  Telephone = telephone;
  Mobile = mobile;
  VendorInventory = vendorInventory;
  Active = active;
  Email=Email;
 }






 public Vendor(){}

 public String getVendorId() {
  return VendorId;
 }

 public void setVendorId(String vendorId) {
  VendorId = vendorId;
 }


 public String getVendorname() {
  return Vendorname;
 }

 public void setVendorname(String vendorname) {
  Vendorname = vendorname;
 }

 public String getVendorContact() {
  return VendorContact;
 }

 public void setVendorContact(String vendorContact) {
  VendorContact = vendorContact;
 }

 public String getAddress() {
  return Address;
 }

 public void setAddress(String address) {
  Address = address;
 }

 public String getCity() {
  return City;
 }

 public void setCity(String city) {
  City = city;
 }

 public String getCountry() {
  return Country;
 }

 public void setCountry(String country) {
  Country = country;
 }

 public String getZip() {
  return Zip;
 }

 public void setZip(String zip) {
  Zip = zip;
 }

 public String getTelephone() {
  return Telephone;
 }

 public void setTelephone(String telephone) {
  Telephone = telephone;
 }

 public String getMobile() {
  return Mobile;
 }

 public void setMobile(String mobile) {
  Mobile = mobile;
 }

 public String getVendorInventory() {
  return VendorInventory;
 }

 public void setVendorInventory(String vendorInventory) {
  VendorInventory = vendorInventory;
 }
 public String getActive() {
  return Active;
 }

 public void setActive(String active) {
  Active = active;
 }


 public String getEmail() {
  return Email;
 }

 public void setEmail(String email) {
  Email = email;
 }


 @Override
 public String toString() {
  return Vendorname;
 }
}
