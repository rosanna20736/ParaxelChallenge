package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int counterPlacebo;
    private static int counterReal;

    public static void main(String[] args) {
        String[][] patients = readData();
        int femalesNonSmokers = countNonSmokeFemale(patients);
        System.out.println("non smoking females =  " + femalesNonSmokers);
        int malesNonSmokers = countNonSmokeMale(patients);
        System.out.println("non smoking males =  " + malesNonSmokers);
        int femalesSmokers = countFemaleSmoke(patients);
        System.out.println("smoking females =  " + femalesSmokers);
        int maleSmokers = countMaleSmoke(patients);
        System.out.println("smoking males =  " + maleSmokers);
        patients = formGroups(patients, femalesNonSmokers, malesNonSmokers, femalesSmokers, maleSmokers);
        int numberOfGroups = 25;
        patients = assignSite(patients, numberOfGroups);
        printAll(patients);
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
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public static int countNonSmokeFemale(String[][] patients) {
        int females = 0;
        for (int i = 1; i < 201; i++) {
            if (patients[i][2].equals(patients[1][2]) && !(patients[i][5].equals(patients[1][5]))) {
                females++;
            }
        }
        return females;
    }

    public static int countNonSmokeMale(String[][] patients) {
        int females = 0;
        for (int i = 1; i < 201; i++) {
            if (!(patients[i][2].equals(patients[1][2])) && !(patients[i][5].equals(patients[1][5]))) {
                females++;
            }
        }
        return females;
    }

    public static int countFemaleSmoke(String[][] patients) {
        int smokers = 0;
        for (int i = 1; i < 201; i++) {
            if (patients[i][5].equals(patients[1][5]) && patients[i][2].equals(patients[1][2])) {
                smokers++;
            }
        }
        return smokers;
    }

    public static int countMaleSmoke(String[][] patients) {
        int smokers = 0;
        for (int i = 1; i < 201; i++) {
            if (patients[i][5].equals(patients[1][5]) && !(patients[i][2].equals(patients[1][2]))) {
                smokers++;
            }
        }
        return smokers;
    }

    public static String[][] formGroups(String[][] patients, int femalesNonSmokers, int malesNonSmokers, int femalesSmokers, int malesSmokers) {
        patients = balanceFemalesNonSmokers(patients, femalesNonSmokers);
        patients = balanceFemalesSmokers(patients, femalesSmokers);
        patients = balanceMalesSmokers(patients, malesSmokers);
        patients = balanceMalesNonSmokers(patients, malesNonSmokers);
        return patients;
    }

    public static String[][] balanceFemalesNonSmokers(String[][] patients, int femalesNonSmokers) {
        int female1 = 0;
        int female2 = 0;
        int femaleRatio = (int) Math.ceil(femalesNonSmokers/2);
        for (int i = 1; i < 201; i++) {
            if (patients[i][2].equals(patients[1][2]) && !(patients[i][5].equals(patients[1][5]))) {
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
                    counterReal++;
                    female1++;
                }
            }
        }
        return patients;
    }

    public static String[][] balanceFemalesSmokers(String[][] patients, int femalesSmokers) {
        int female1 = 0;
        int female2 = 0;
        int femaleRatio = (int) Math.ceil(femalesSmokers/2);
        for (int i = 1; i < 201; i++) {
            if (patients[i][2].equals(patients[1][2]) && patients[i][5].equals(patients[1][5])) {
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
                    counterReal++;
                    female1++;
                }
            }
        }
        return patients;
    }

    public static String[][] balanceMalesSmokers(String[][] patients, int malesSmokers) {
        int male1 = 0;
        int male2 = 0;
        int maleRatio = (int) Math.ceil(malesSmokers/2);
        for (int i = 1; i < 201; i++) {
            if (!(patients[i][2].equals(patients[1][2])) && patients[i][5].equals(patients[1][5])) {
                if (Math.random() < 0.5 && male1 < maleRatio) {
                    patients[i][7] = "Real";
                    counterReal++;
                    male1++;
                } else if (male2 < maleRatio) {
                    patients[i][7] = "Placebo";
                    counterPlacebo++;
                    male2++;
                } else {
                    patients[i][7] = "Real";
                    counterReal++;
                    male1++;
                }
            }
        }
        return patients;
    }

    public static String[][] balanceMalesNonSmokers(String[][] patients, int malesNonSmokers) {
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

    public static String[][] assignSite(String[][] patients, int numberOfGroups) {
        int numberOfPeople = (int) Math.ceil(200/numberOfGroups);
        int counter = 1;
        for (int j = 1; j <= numberOfGroups; j++) {
            for (int i = 0; i < numberOfPeople; i++) {
                patients[counter][6] = Integer.toString(j);
                counter++;
            }
        }
        return patients;
    }
}