{\rtf1\ansi\ansicpg1252\cocoartf1404\cocoasubrtf130
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fnil\fcharset0 HelveticaNeue;}
{\colortbl;\red255\green255\blue255;\red52\green52\blue52;\red12\green97\blue120;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Help Document:\
\
Steps to run the program with Mac/Windows Machine:\
\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\
1.Copy the project in any of the drives . Open the project in Eclipse . Right click on the project , click \'93Build Path\'94 -> \'93Configure Build Path\'94 -> \'93Java Build Path\'94 on the left menu -> \'93Libraries\'94 tab in Center menu -> \'93Add External JARs \'94 -> Select the path of \'93jsoup-1.8.3.jar\'94 provided in the project zip folder -> \'93OK\'94 .\
\
2.Run the program as below. \
	1. Right click on bfsUnfocused.java and \'93run as java application\'94 .\
	2. Enter the seed document link when prompted . \
            Ex : 
\f1\fs26 \cf2 \expnd0\expndtw0\kerning0
\'a0{\field{\*\fldinst{HYPERLINK "http://en.wikipedia.org/wiki/Sustainable_energy"}}{\fldrslt \cf3 \ul \ulc3 http://en.wikipedia.org/wiki/Sustainable_energy}}
\f0\fs24 \cf0 \kerning1\expnd0\expndtw0  without quotes\
\
3. You can change the integer variable \'91int Ng\'92 at the top of the file to be between 1 and 3 representing unigram, bigram and trigram. Also if you want to print to different files..make sure to change the file name in the argument \'91file2\'92 .\
\
All six tables have been provided as texted files in sorted orders \
ngram1, ngram2 and ngram3 text files are sorted according to values.they contain the word and the term frequency\
\
ngram1df, ngram2df and ngram3df are text files where the term, df , tf are located in lexographic order.\
\
Total files for tables: 6\
ngram1 \
ngram2\
ngram3\
ngram1df\
ngram2df \
ngram3df\
\
\
The design choice I made for this assignment -\
I used a TreeMap data structure because it automatically sorts the terms lexicographically.\
The tree map contains a string and a hash map\
This string is the term and the hashmap contains the document id as a string and the term frequency as an Integer\
TreeMap<String, HashMap<String, Integer>\
I felt this design choice is better since there is no need to sort when using a TreeMap .Also to hold \
three data variables we need to use a structure within a structure hence I decided to use a HashMap inside the Treemap.\
\
Please check task3.4.txt for  3.4 explanation.\
\
All 3 Ziphian plots available as png files\
Stop list text file available\
\
\
Added the 1000 raw data files now.\
\
}