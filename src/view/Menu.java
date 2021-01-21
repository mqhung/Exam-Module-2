package view;

import manager.ContactManager;
import model.Contact;
import storage.ReaderWriterCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static view.DisplayMenu.displayMenu;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Tao arrayList luu thong tin
        ArrayList<Contact> contacts = new ArrayList<>();
        ContactManager contactManager = new ContactManager();
        ReaderWriterCSV readerWriterCSV = new ReaderWriterCSV();
        displayMenu();
        int choice = -1;
        do {
            System.out.println("Chọn chức năng: ");
            try{
                choice = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    contactManager.displayAll(contacts);
                    break;
                case 2:
                    contactManager.addContact(contacts);
                    break;
                case 3:
                    System.out.println("Mời bạn nhập vào số điện thoại: ");
                    int phone = Integer.parseInt(sc.nextLine());
                    contactManager.editContact(phone, contacts);
                    break;
                case 4:
                    System.out.println("Mời bạn nhập vào số điện thoại: ");
                    int phoneDelete = Integer.parseInt(sc.nextLine());
                    contactManager.delete(phoneDelete, contacts);
                    break;
                case 5:
                    contactManager.search(contacts);
                    break;
                case 6:
                    try {
                        readerWriterCSV.writeFile(contacts);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    readerWriterCSV.readFile(contacts);
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        } while (choice != 0);
    }


}
