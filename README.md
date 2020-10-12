# E-ASSET_MANAGEMENT

## RUNTIME TERROR

**Instructions to configure and execute the project in STS (Spring Tool Suite v4.0+)**:-

1. Go to File menu.
2. Select Import option.
3. In General folder, select Existing Projects into Workspace and click Next.
4. Browse the project file and select the project folder and click Finish.
5. After the project is loaded, in Project Explorer on the left, right click on the Project,then after selecting Build Path click on Configure Build Path.
6. In Java Build Path, select Libraries then click on Add External JAR on right side.
7. Select the following JAR files present inside JARS folder:-
   - apache-commons.jar
   - com.fasterxml.jackson.core.jar
   - com.fasterxml.jackson.databind.jar
   - commons-csv-1.6.jar
   - commons-fileupload-1.3.jar
   - commons-io-2.6.jar
   - derby.jar
   - jackson-annotations-2.1.2.jar
   - jackson-dataformat-xml-2.1.1.jar
   - java-json.jar
   - json-simple-1.1.jar
   - org.json.jar
8. Click on Apply and Close.
9. From Servers View, Click on create new server.
10. Then from Apache select Tomcat v9.0 Server, click Next then click Finish.
11. From Data Source Explorer, right click on Database Connections click on New.
12. Select Derby from Connection Profile and click Next.
13. Aside Drivers click on New Driver Defination -> Derby Embedded JDBC Driver 10.2.
14. Then Select JAR List select derby.jar and then click Remove JAR.
15. Now click on Add JAR and select file derby.jar from JARS folder.
16. Now Select Properties and Add Password as "admin" and User Id as "admin" and click OK.
17. Now Browse the Database File from Project Folder.
18. Click on Save password checkbox and Test Connection.
19. The screen shows Ping Succeeded! then click OK.
20. Copy the URL and click Finish.
21. Now in Project Explorer, in Project file -> Java Resources -> src  -> com.hsbc.easset.resources -> db.properties.
22. Paste the URL after: url="URL" and save the file.
23. Now the Project is ready to Run, right click on Project name -> Run As -> Run on Server (Tomcat server to be added explicitly).
24. The Index Page will load on Web Browser.

### ADMIN LOGIN CREDENTIALS :-

**USER NAME**  -> varungujarathi9  
**PASSWORD** -> AAAAa@2
