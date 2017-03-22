package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        String[][] patients = readData();
        int females = countFemale(patients);
        System.out.println("females =  " + females);
        int smokers = countSmoke(patients);
        System.out.println("smokers =  " + smokers);
    }

    public static String[][] readData() {
        String csvFile = "/home/klekot/Desktop/Fake_Patient_Data.csv";
        String line = "";
        String cvsSplitBy = ",";

        String[] Data;
        String[][] patients = new String[201][8];
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Data = line.split(cvsSplitBy);
                patients[counter][0] = Data[0];
                patients[counter][1] = Data[1];
                patients[counter][2] = Data[2];
                patients[counter][3] = Data[3];
                patients[counter][4] = Data[4];
                patients[counter][5] = Data[5];
                patients[counter][6] = "unknown";
                patients[counter][7] = "unknown";
                //System.out.println("Patient_ID = " + patients[counter][0] + ", Age = " + patients[counter][1] + ", Gender = " + patients[counter][2] + ", Height = " + patients[counter][3] + ", Weight = " + patients[counter][4] + ", Smoke = " + patients[counter][5] + ", Site = " + patients[counter][6] + ", Drug = " + patients[counter][7]);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public static int countFemale(String[][] patient) {
        int females = 0;
        for (int i = 0; i < 201; i++) {
            if (patient[i][2].equals(patient[1][2])) {
                females = females + 1;
                //System.out.println("females =  " + females);
            }
        }
        return females;
    }

    public static int countSmoke(String[][] patient) {
        int smokers = 0;
        for (int i = 0; i < 201; i++) {
            if (patient[i][5].equals(patient[1][5])) {
                smokers = smokers + 1;
                //System.out.println("smokers =  " + smokers);
            }
        }
        return smokers;
    }

    public static int formGroups(String[][] patient) {

    }
}