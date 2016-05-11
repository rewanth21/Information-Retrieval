{\rtf1\ansi\ansicpg1252\cocoartf1404\cocoasubrtf130
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red28\green28\blue28;}
\margl1440\margr1440\vieww10800\viewh10440\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Help Document:\
\
Steps to run the program with Mac/Windows Machine:\
\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\'97\
1.Copy the project in any of the drives . Open the project in Eclipse . Right click on the project , click \'93Build Path\'94 -> \'93Configure Build Path\'94 -> \'93Java Build Path\'94 on the left menu -> OK\
\
There are two project folders \
one is BM25 , which is the first part of assignment \
second is Lucene, which is the second part of the assignment\
\
\
Implementation\
The filename from main method is passed , which takes each url and adds them to a list.\
Then the total length is taken and divided by size of the documents list\
The queries are scanned and given an id with the query terms\
Then the ngram file is read where the all words are indexed\
Each document is retrieved and ranked using the MB25rank function and the singleBM25term\
and then the results are store in a list\
The list is compared and sorted to show the top 100 hits\
\
Difference for the top 5 ranks of tables\
Lucence\
Scoring is dependent on the way documents are indexed. Lucent scoring uses a combination of\
the vector space model of IR and the boolean model to determine how relevant  a given document is to a user\'92s query. In general the more times a query term appears in a document relative to the number of times the term appears in all the documents in a collection , the more relevant the document is to the query.\
\
BM25\
\
\pard\pardeftab720\partightenfactor0
\cf2 \expnd0\expndtw0\kerning0
BM25 is a bag of words retrieval function that ranks a set of documents based on the query terms appearing in each document, regardless of the inter-relationship between the query terms within a document, which is why when we compare the top 5 ranks for both BM25 and Lucene  queries BM25 has a better score.\
\
scores\
BM25  Query 1\
1--------Q0 --------Cellulosicethanol		1	3.5979813340182267	BM25\
1--------Q0 --------Energyconservation		2	1.3061601603560613	BM25\
1--------Q0 --------Seawater		3	1.3046619219894908	BM25\
1--------Q0 --------JosephRomm		4	1.2291121878567912	BM25\
1--------Q0 --------Wasteheat		5	1.1122268063086662	BM25\
\
Query 2\
2--------Q0 --------Cellulosicethanol		1	3.4583165674387364	BM25\
2--------Q0 --------Seawater		2	1.3046619219894908	BM25\
2--------Q0 --------Passivehouse		3	1.1322298461908975	BM25\
2--------Q0 --------NorthAmericancollegiatesustainabilityprograms		4	0.8455096214313073	BM25\
2--------Q0 --------NewUrbanism		5	0.5877752881564418	BM25\
\
Query 3\
3--------Q0 --------Combinedcycle		1	3.358316567438736 BM25\
3--------Q0 --------Dwelling		2	1.3046619219894908	BM25\
3--------Q0 --------France		3	1.1122268063086662	BM25\
3--------Q0 --------Metz		4	1.075398377183776	BM25\
3--------Q0 --------Continuousproduction		5	1.0221905995435 	BM25\
\
Query 4\
4--------Q0 --------Combinedcycle		1	3.71798133350182267	BM25\
4--------Q0 --------Dwelling		2	1.6061901603560912	BM25\
4--------Q0 --------France		3	1.3061602332560619	BM25\
4--------Q0 --------Metz		4	1.3046619219894908	BM25\
4--------Q0 --------Continuousproduction		5	1.128565063186662	BM25\
\
\
Lucene\
Query 1\
\pard\pardeftab720\partightenfactor0

\f1\fs22 \cf0 \kerning1\expnd0\expndtw0 1. /Users/revanthrajualluri/Desktop/IRdocs/Globalwarming.txt score=0.33656642\
2. /Users/revanthrajualluri/Desktop/IRdocs/Sustainabilityandenvironmentalmanagement.txt score=0.24888454\
3. /Users/revanthrajualluri/Desktop/IRdocs/Naturalenvironment.txt score=0.21206379\
4. /Users/revanthrajualluri/Desktop/IRdocs/Climatechangemitigation.txt score=0.20384547\
5. /Users/revanthrajualluri/Desktop/IRdocs/Greenhousegas.txt score=0.19306551\
\pard\pardeftab720\partightenfactor0

\f0\fs24 \cf2 \expnd0\expndtw0\kerning0
\
Query 2\
\pard\pardeftab720\partightenfactor0

\f1\fs22 \cf0 \kerning1\expnd0\expndtw0 1. /Users/revanthrajualluri/Desktop/IRdocs/RenewableEnergyCertificate.txt score=0.49411342\
2. /Users/revanthrajualluri/Desktop/IRdocs/Greeneconomy.txt score=0.40534168\
3. /Users/revanthrajualluri/Desktop/IRdocs/Sustainableenergy.txt score=0.3792508\
4. /Users/revanthrajualluri/Desktop/IRdocs/RenewableenergyintheUnitedKingdom.txt score=0.3781273\
5. /Users/revanthrajualluri/Desktop/IRdocs/Renewableenergycommercialization.txt score=0.36040235\
\pard\pardeftab720\partightenfactor0

\f0\fs24 \cf2 \expnd0\expndtw0\kerning0
\
\pard\pardeftab720\partightenfactor0

\f1\fs22 \cf0 \kerning1\expnd0\expndtw0 Query 3\
1. /Users/revanthrajualluri/Desktop/IRdocs/RenewableEnergyCertificate.txt score=0.49411342\
2. /Users/revanthrajualluri/Desktop/IRdocs/Greeneconomy.txt score=0.40534168\
3. /Users/revanthrajualluri/Desktop/IRdocs/Sustainableenergy.txt score=0.3792508\
4. /Users/revanthrajualluri/Desktop/IRdocs/RenewableenergyintheUnitedKingdom.txt score=0.3781273\
5. /Users/revanthrajualluri/Desktop/IRdocs/Renewableenergycommercialization.txt score=0.36040235\
6. /Users/revanthrajualluri/Desktop/IRdocs/Greencomputing.txt score=0.34967566\
\
\pard\pardeftab720\partightenfactor0

\f0\fs24 \cf2 \expnd0\expndtw0\kerning0
Query 4\
\pard\pardeftab720\partightenfactor0

\f1\fs22 \cf0 \kerning1\expnd0\expndtw0 1. /Users/revanthrajualluri/Desktop/IRdocs/Incandescentlightbulb.txt score=0.39219892\
2. /Users/revanthrajualluri/Desktop/IRdocs/Energyconservation.txt score=0.314201\
3. /Users/revanthrajualluri/Desktop/IRdocs/Compactfluorescentlamp.txt score=0.2589264\
4. /Users/revanthrajualluri/Desktop/IRdocs/Lighting.txt score=0.21850374\
5. /Users/revanthrajualluri/Desktop/IRdocs/LEDlamp.txt score=0.21261346\
\pard\pardeftab720\partightenfactor0

\f0\fs24 \cf2 \expnd0\expndtw0\kerning0
\
}