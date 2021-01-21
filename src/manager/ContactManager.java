package manager;

import model.CheckInfo;
import model.Contact;
import view.DisplayMenu;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    Scanner sc = new Scanner(System.in);

    public void displayAll(ArrayList<Contact> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("--------------------------------------");
            System.out.println("Full name: " + arrayList.get(i).getFullName());
            System.out.println("Phone number: " + arrayList.get(i).getPhoneNumber());
            System.out.println("Gender: " + arrayList.get(i).getGender());
            System.out.println("Group: " + arrayList.get(i).getGroup());
            System.out.println("Address: " + arrayList.get(i).getAddress());
            System.out.println("--------------------------------------");
            String line = sc.nextLine();
        }
        DisplayMenu.displayMenu();
    }

    public void addContact(ArrayList<Contact> arrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Full name: ");
        String fullName = sc.nextLine();
        System.out.println("Phone Number: ");
        int phoneNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Group: ");
        String group = sc.nextLine();
        System.out.println("Gender: ");
        String gender = sc.nextLine();
        System.out.println("Address: ");
        String address = sc.nextLine();
        System.out.println("mail: ");
        String mail = sc.nextLine();
        System.out.println("Year Of Birth: ");
        int yearOfBirth = Integer.parseInt(sc.nextLine());
        System.out.println("--------------------------------------");
        String result = this.checkInformation(fullName, phoneNumber, group, gender, address, mail, yearOfBirth);
        if (result.equals("Lưu thành công")) {
            System.out.println(result);
            Contact contact = new Contact(fullName, phoneNumber, group, gender, address, mail, yearOfBirth);
            arrayList.add(contact);
        } else {
            System.out.println(result);
            this.addContact(arrayList);
        }
        DisplayMenu.displayMenu();

    }

    public String checkInformation(String fullName, int phoneNumber, String group, String gender, String address, String mail, int yearOfBirth) {
        String result = "";
        if (fullName.equals("") || group.equals("") || gender.equals("") || address.equals("") || yearOfBirth == 0) {
            result = "Không bỏ trống thông tin";
        } else if (!CheckInfo.checkPhone(String.valueOf(phoneNumber))) {
            result = "Nhập sai định dạng điện thoai";
        } else if (!CheckInfo.checkMail(mail)) {
            result = "Nhập sai định dạng mail";
        } else {
            result = "Lưu thành công";
        }
        return result;

    }

    public void editContact(int phone, ArrayList<Contact> arrayList) {
        if (this.checkPhone(phone, arrayList) != -1) {
            System.out.println("Tìm thấy số điện thoại, mời bạn nhập thông tin");
            int index = this.checkPhone(phone, arrayList);
            System.out.println("Full name: ");
            String fullName = sc.nextLine();
            System.out.println("Phone Number: ");
            int phoneNumber = Integer.parseInt(sc.nextLine());
            System.out.println("Group: ");
            String group = sc.nextLine();
            System.out.println("Gender: ");
            String gender = sc.nextLine();
            System.out.println("Address: ");
            String address = sc.nextLine();
            System.out.println("mail: ");
            String mail = sc.nextLine();
            System.out.println("Year Of Birth: ");
            int yearOfBirth = Integer.parseInt(sc.nextLine());
            String result = this.checkInformation(fullName, phoneNumber, group, gender, address, mail, yearOfBirth);
            if (result.equals("Lưu thành công")) {
                System.out.println("Cập nhật thành công");
                Contact contact = new Contact(fullName, phoneNumber, group, gender, address, mail, yearOfBirth);
                arrayList.set(index, contact);
            } else {
                System.out.println(result);
            }
            DisplayMenu.displayMenu();


        } else {
            System.out.println("Không tìm được danh bạ với số điện thoại trên");
            System.out.println("Nhập lại số điện thoại");
            int reCheckPhone = sc.nextInt();
            if (reCheckPhone != 0) {
                this.editContact(reCheckPhone, arrayList);
            } else {
                DisplayMenu.displayMenu();
            }

        }
    }

    public void delete(int phone, ArrayList<Contact> arrayList) {
        if (checkPhone(phone, arrayList) != -1) {
            System.out.println("Bạn xác nhận muốn xóa");
            String accept = sc.nextLine();
            if (accept.equals("Y")) {
                int index = checkPhone(phone, arrayList);
                System.out.println("Xóa thành công");
                arrayList.remove(index);
            } else DisplayMenu.displayMenu();

        } else {
            System.out.println("Không tìm được danh bạ với số điện thoại trên");
            System.out.println("Nhập lại số điện thoại");
            int rePhone = sc.nextInt();
            if (rePhone != 0) {
                delete(rePhone, arrayList);
            } else DisplayMenu.displayMenu();

        }

    }

    public int checkName(String name, ArrayList<Contact> arrayList) {
        int indexName = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getFullName().equals(name)) {
                indexName = i;
                break;
            }
        }
        return indexName;
    }

    public void search(ArrayList<Contact> arrayList) {
        System.out.println("Mời bạn nhập chức năng tìm kiếm: ");
        System.out.println("1.Tìm theo số điện thoại");
        System.out.println("2.Tìm theo tên");
        int choice = Integer.parseInt(sc.nextLine());
        if (choice == 1) {
            System.out.println("Mời bạn nhập vào số điện thoại");
            int phoneSearch = Integer.parseInt(sc.nextLine());
            if (checkPhone(phoneSearch, arrayList) != -1) {
                int indexPhone = checkPhone(phoneSearch, arrayList);
                displayContact(indexPhone, arrayList);
            } else System.out.println("Không tìm thấy");
        } else if (choice == 2) {
            System.out.println("Mời bạn nhập vào số điện thoại");
            String nameSearch = sc.nextLine();
            if (checkName(nameSearch, arrayList) != -1) {
                int indexName = checkName(nameSearch, arrayList);
                displayContact(indexName, arrayList);
            } else System.out.println("Không tìm thấy");
        }
    }

    public int checkPhone(int phoneNumber, ArrayList<Contact> arrayList) {
        int indexPhone = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getPhoneNumber() == phoneNumber) {
                indexPhone = i;
                break;
            }
        }
        return indexPhone;
    }

    public void displayContact(int index, ArrayList<Contact> arrayList) {
        System.out.println("--------------------------------------");
        System.out.println("Full name: " + arrayList.get(index).getFullName());
        System.out.println("Phone number: " + arrayList.get(index).getPhoneNumber());
        System.out.println("Gender: " + arrayList.get(index).getGender());
        System.out.println("Group: " + arrayList.get(index).getGroup());
        System.out.println("Address: " + arrayList.get(index).getAddress());
        System.out.println("--------------------------------------");
        DisplayMenu.displayMenu();
    }

}
