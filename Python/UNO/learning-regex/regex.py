# https://www.youtube.com/watch?v=K8L6KVGG-7o

import re
import csv

text_to_search = """
abcdefghijklmnopqrstuvwxyz
ABCDEFGHIJKLMNOPQRSTUVWXYZ
1234567890

Ha HaHa

MetaCharacters (Need to be escaped):
. ^ $ * + ? {  } [  ] \ | (  )

coreyms.com

321-555-4321
123.555.1234

Mr. Schafer
Mr Smith
Ms Davis
Mrs. Robinson
Mr. T
"""

sentence = "Start a sentence and then bring it to an end"

# raw string
# print(r"\ttab")


# pattern = re.compile(r"abc")
# pattern = re.compile(r"cba")


# pattern = re.compile(r".") # period is a special character
# pattern = re.compile(r"\.")  # to search literal period we need to escape it

# for url we need to escape period
# pattern = re.compile(r"coreyms\.com")

# "\d" digit(0-9)
# pattern = re.compile(r'\d')

# "\D" not a digit
# pattern = re.compile(r'\D')

# "\w" letters a-z A-Z 0-9 _  # "\W" not a word character
# pattern = re.compile(r'\w')


# "\s" whitespace (space, tabs, newline) "\S" not a whitespace
# pattern = re.compile(r"\s")

# -------------------------------------------------------------------------
#### Anchors

# "\b" word boundary "\B" not a word boundary
# pattern = re.compile(r"\bHa")
# pattern = re.compile(r"\BHa")

# matches = pattern.finditer(text_to_search)

# "^" beginning of the line
# "$" end of the line
# pattern = re.compile(r"^Start")
# pattern = re.compile(r"end$")

# matches = pattern.finditer(sentence)

# -------------------------------------------------------------------------

# pattern = re.compile(r"\d\d\d-\d\d\d-\d\d\d\d")

with open("us-500.csv", "r") as f:
    contents = f.read()


# pattern = re.compile(r"[89]\d\d-\d\d\d-\d\d\d\d") # phone number starting with 8 and 9

# matches = pattern.finditer(contents)

# for match in matches:
    # print(match)
    # span is the indexes of pattern matched in o/p


# for email
lst = re.findall("[a-zA-Z0-9+._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+", contents)
for l in lst:
    print(l)
