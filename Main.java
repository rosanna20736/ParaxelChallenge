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
    private static int counterPlacebo;
    private static int counterReal;

    public static void main(String[] args) {
        String[][] patients = readData();
        int females = countFemale(patients);
        System.out.println("females =  " + females);
        int smokers = countSmoke(patients);
        System.out.println("smokers =  " + smokers);
        patients = formGroups(patients, females, smokers);
        printAll(patients);
        System.out.println("Real =  " + counterReal);
        System.out.println("Placebo =  " + counterPlacebo);
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

    public static int countFemale(String[][] patients) {
        int females = 0;
        for (int i = 1; i < 201; i++) {
            if (patients[i][2].equals(patients[1][2])) {
                females++;
                //System.out.println("females =  " + females);
            }
        }
        return females;
    }

    public static int countSmoke(String[][] patients) {
        int smokers = 0;
        for (int i = 1; i < 201; i++) {
            if (patients[i][5].equals(patients[1][5])) {
                smokers++;
                //System.out.println("smokers =  " + smokers);
            }
        }
        return smokers;
    }

    public static String[][] formGroups(String[][] patients, int females, int smokers) {
        patients = balanceSmokers(patients, smokers);
        patients = balanceFemales(patients, females);
        patients = balanceGroups(patients);
        return patients;
    }

    public static String[][] balanceFemales(String[][] patients, int females) {
        int female1 = 0;
        int female2 = 0;
        int femaleRatio = (int) Math.ceil(females/2);
        for (int i = 1; i < 201; i++) {
            if (patients[i][2].equals(patients[1][2])) {
                if (Math.random() < 0.5 && female1 < femaleRatio) {
                    patients[i][7] = "Real";
                    counterReal++;
                    female1++;
                } else if (female2 < femaleRatio) {
                    patients[i][7] = "Placebo";
                    counterPlacebo++;
                    female2++;
                } else {
                    patients[i][7] = "Real";
                    counterPlacebo++;
                    female1++;
                }
            }
        }
        return patients;
    }

    public static String[][] balanceSmokers(String[][] patients, int smokers) {
        int smoker1 = 0;
        int smoker2 = 0;
        int smokerRatio = (int) Math.ceil(smokers/2);
        for (int i = 1; i < 201; i++) {
            if (!(patients[i][2].equals(patients[1][2])) && patients[i][5].equals(patients[1][5])) {
                if (Math.random() < 0.5 && smoker1 < smokerRatio) {
                    patients[i][7] = "Real";
                    counterReal++;
                    smoker1++;
                } else if (smoker2 < smokerRatio) {
                    patients[i][7] = "Placebo";
                    counterPlacebo++;
                    smoker2++;
                } else {
                    patients[i][7] = "Real";
                    counterReal++;
                    smoker1++;
                }
            }
        }
        return patients;
    }

    public static String[][] balanceGroups(String[][] patients) {
        for (int i = 1; i < 201; i++) {
            if (!(patients[i][2].equals(patients[1][2])) && !(patients[i][5].equals(patients[1][5]))) {
                if (Math.random() < 0.5 && counterReal < 100) {
                    patients[i][7] = "Real";
                    counterReal++;
                } else if (counterPlacebo < 100) {
                    patients[i][7] = "Placebo";
                    counterPlacebo++;
                } else {
                    patients[i][7] = "Real";
                    counterReal++;
                }
            }
        }
        return patients;
    }

    public static void printAll(String[][] patients) {
        for (int i = 1; i < 201; i++) {
            System.out.println("Patient_ID = " + patients[i][0] + ", Age = " + patients[i][1] + ", Gender = " + patients[i][2] + ", Height = " + patients[i][3] + ", Weight = " + patients[i][4] + ", Smoke = " + patients[i][5] + ", Site = " + patients[i][6] + ", Drug = " + patients[i][7]);
        }
    }

    public static String[][] assignSite(String[][] patients) {
        //////
        return patients;
    }
}