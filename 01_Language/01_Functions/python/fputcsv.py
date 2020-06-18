# coding: utf-8

import csv

rows = [
    ["aaa", "bbb", "ccc", "dd,dd"],
    ["123", "456", "789"],
    ["\"aaa\"", "\"bbb\""],
]

with open("test.csv", mode="w") as f:
    writer = csv.writer(f, delimiter=",", quotechar='"')
    for row in rows:
        writer.writerow(row)
