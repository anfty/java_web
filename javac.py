import os

os.system("rm -rf *.class")
for i in os.listdir("./"):
    if i == "javac.py":
        continue
    os.system("javac "+i)
