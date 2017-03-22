package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String csvFile = "/home/klekot/Desktop/Fake_Patient_Data.csv";
        String line = "";
        String cvsSplitBy = ",";

        String[] patients;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                patients = line.split(cvsSplitBy);
                System.out.println("Patient_ID = " + patients[0] + "\t, Age = " + patients[1] + "\t, Gender = " + patients[2] + "\t, Height = " + patients[3] + "\t, Weight = " + patients[4] + "\t, Smoke = " + patients[5] + "\t, Site = " + patients[6] + "\t, Drug = " + patients[7]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
