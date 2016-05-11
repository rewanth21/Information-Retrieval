Initial instructions
1.Project is a zipped version of the folder named IR-Project. It contains solutions to all the tasks mentioned in the project description to be completed. Extract this folder onto your file system. 
2.In Eclipse, click on File-->Import-->Existing projects into workspace-->Browse through your file system-->Locate IR-Project-->Finish 

To run the base BM25 Search engine service
1. Open BM25BaseSystem.java. Resolve errors in the project and the class file, if any by loading the required dependency jars, in this case jsoup which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.
2. Clean and build the project and click on BM25BaseSystem.java to Run As -> "Java Application". 
3. Output is written onto the BM25-BaseSystem.txt file for all 64 queries and the top 100 documents are retrieved for each. 

To run BM25PseudoRelevanceFeedback
1. Open BM25PseudoRelevanceFeedback.java. Resolve errors if any by loading the required dependency jars, in this case jsoup which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.
2. Clean and build the project and click on BM25PseudoRelevanceFeedback.java to Run As -> "Java Application". 
3. Output is written onto the BM25-PseudoRelevanceFeedback.txt file for all 64 queries and the top 100 documents are retrieved for each query having undergone expansion through pseudo relevance feedback.

To run BM25StemmedSystem
1. Open BM25StemmedSystem.java. Resolve errors if any by loading the required dependency jars, in this case jsoup which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.
2. Clean and build the project and click on BM25StemmedSystem.java to Run As -> "Java Application". 
3. Output is written onto the BM25-Stemming.txt file for all 64 queries and the top 100 documents are retrieved for each query having undergone stemming.

To run BM25Stopping
1. Open BM25Stopping.java. Resolve errors if any by loading the required dependency jars, in this case jsoup which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.
2. Clean and build the project and click on BM25Stopping.java to Run As -> "Java Application". 
3. Output is written onto the BM25-Stopping.txt file for all 64 queries and the top 100 documents are retrieved for each query having undergone stopping.

To run BM25 with query expansion using thesaurus
1. Open BM25Thesaurus.java. Resolve errors if any by loading the required dependency jars, in this case jsoup and jaws which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.   
2. Obtain a copy of the WordNet database files, which can be accomplished by downloading and installing WordNet (you must download the full version of WordNet (obtained at http://wordnet.princeton.edu/wordnet/download/) on to your C:\ folder and not just the database files).
3. Download the Java Archive (JAR) file containing the compiled JAWS code and load it on to the project. 
4. Eclipse allows you to specify "VM Arguments" and you would need to include an entry like -Dwordnet.database.dir=C:\WordNet\2.1\dict\ to use when running your code. You can add this under Run-->Run Configurations--> Arguments Tab-->VM Arguments--> add the command, apply and run.
5. Clean and build project and click on BM25Thesaurus.java to Run As -> "Java Application". 
6. Output is written onto the BM25-Thesaurus.txt file for all 64 queries which have undergone query expansion using thesaurus post which the top 100 documents were retrieved. 

Note: Evaluation.java is called from other class file to retrieve their performance scores

To run LuceneSystem 
1. I have included Jars within the unzipped submission. Save these file onto your system so that this can be loaded into the project later.
2. You will have a couple of warnings which need to be resolved. Most important one being 'Adding external Jars'
3. Right click on the project and say Properties. Click on 'Java Build path', and then click on the 'libraries' tab and click on Add External JARs. Browse and find the lucene-analyzers-common-4.7.2.jar, lucene-core-4.7.2.jar, lucene-queryparser-4.7.2.jar files and add them.
4. Next, click on the JRE System Library and say 'Edit', and then selected JavaSE-1.7(jdk1.7.0_71) as the execution environment.
5. This will resolve all the errors and warnings. Click on Run As -> "Java Application". 
6. Run LuceneSystem.java as a java application.
7. You will be prompted to "Enter the FULL path where the index will be created:" I have included a folder called index_location within LuceneSystem. Add the path of this folder 
8. Now you will be prompted to "Enter the FULL path to add into the index:" I have included another set of raw files in a folder called "Corpus" add the path of this folder. 
9. It will run for the 64 queries in cacm.html
10. The results are compiled under 'systemResults.xlsx' under the tab named 'Lucene-System'.

Note: Doc.java is called by other class files to create objects of documents

To run tfidfSystem
1. Open tfidfSystem.java. Resolve errors if any by loading the required dependency jars, in this case jsoup which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.
2. Clean and build the project and click on tfidfSystem.java to Run As -> "Java Application". 
3. Output is written onto the TfIdf-BaseSystem.txt file.

To run ThesaurusTfIdf
1. Open ThesaurusTfIdf.java. Resolve errors if any by loading the required dependency jars, in this case jsoup which can be found in the unzipped project submission. Change the value of the global variable 'path' to any location where you would want to have your results stored.
2. Obtain a copy of the WordNet database files, which can be accomplished by downloading and installing WordNet (you must download the full version of WordNet (obtained at http://wordnet.princeton.edu/wordnet/download/) on to your C:\ folder and not just the database files).
3. Download the Java Archive (JAR) file containing the compiled JAWS code and load it on to the project. 
4. Eclipse allows you to specify "VM Arguments" and you would need to include an entry like -Dwordnet.database.dir=C:\WordNet\2.1\dict\ to use when running your code. You can add this under Run-->Run Configurations--> Arguments Tab-->VM Arguments--> add the command, apply and run.
5. Clean and build project and click on ThesaurusTfIdf.java to Run As -> "Java Application". 
6. Output is written onto the Thesaurus-TfIDf.txt file for all 64 queries which have undergone query expansion using thesaurus and stopping post which the top 100 documents were retrieved. 

Note: All results are compiled under a single spreadsheet 'systemResults.xlsx'. Which along with 'Precision&Recall-Results.xslx' are included under 'SystemResults' folder in the submission. The folder also contains many jar files, html files and raw files required by the different classes implemented, 
each of which I have elaborated in the steps above. The 'SystemResults' folder also contains all the output results saved into text files of various parts of the project that has been implemented. The project's description, citings, references, team-members and other such information are compiled under the file titled 'RajkumarMurukeshan_RevanthRajuAlluri_SangeethaSivaramakrishnan.pdf'
