import numpy as np
import csv


#patient_data = np.genfromtxt('Fake_Patient_Data.csv', delimiter=',')

#patient_data = csv.reader('Fake_Patient_Data.csv', dialect='excel')

patient_data = np.recfromcsv('Fake_Patient_Data.csv', delimiter=',')

print(patient_data[11])
