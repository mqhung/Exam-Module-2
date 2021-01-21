package storage;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderWriterCSV {
    Scanner sc = new Scanner(System.in);
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    String filePath = "data/contacts.txt";
    File file = new File(filePath);
    public void writeFile(ArrayList<Contact> arrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < arrayList.size(); i++) {
            fileWriter.append(arrayList.get(i).getFullName());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(arrayList.get(i).getPhoneNumber()));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(arrayList.get(i).getGroup());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(arrayList.get(i).getGender());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(arrayList.get(i).getAddress());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(arrayList.get(i).getMail());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(arrayList.get(i).getYearOfBirth()));
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
        fileWriter.flush();
        fileWriter.close();
        System.out.println("Lưu thành công");

    }

    public ArrayList<Contact> readFile(ArrayList<Contact> contacts) {
        for (int i = 0; i < contacts.size(); i++) {
            contacts.remove(i);
        }
        try {
            FileReader fileReader = new FileReader(file);
            String line;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitData = line.split(COMMA_DELIMITER);
                String fullName = splitData[0];
                int phoneNumber = Integer.parseInt(splitData[1]);
                String group = splitData[2];
                String gender = splitData[3];
                String address = splitData[4];
                String mail = splitData[5];
                int yearOfBirth = Integer.parseInt(splitData[6]);
                contacts.add(new Contact(fullName, phoneNumber, group, gender, address, mail, yearOfBirth));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

}