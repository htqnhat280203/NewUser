package Model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class Manager {
Validate vd = new Validate();
    public  void createNewAccount() {
        //check file data exist or not
        if (!vd.checkFileExist()) {
            return;
        }
        String username = vd.checkInputUsername();
        String password = vd.checkInputPassword();
        //check username exist or not
        if (!vd.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

    //login system
    public void loginSystem() {
        //check file data exist or not
        if (!vd.checkFileExist()) {
            return;
        }
        String username = vd.checkInputUsername();
        String password = vd.checkInputPassword();
        //check username exist or not
        if (!vd.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Password incorrect.");
            }
            System.err.println("Login success.");
        }
    }

    //write new account to data
    public void addAccountData(String username, String password) {
        File file = new File("D:/NewUser.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //get password by username
    public  String passwordByUsername(String username) {
        File file = new File("NewUser.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}