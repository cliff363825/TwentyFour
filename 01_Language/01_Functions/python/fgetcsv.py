# coding: utf-8

import csv

with open("test.csv") as f:
    reader = csv.reader(f, delimiter=',', quotechar='"')
    for row in reader:
        print(row)
