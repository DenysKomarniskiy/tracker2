Maven Eclipse plugin installation step by step:
Open Eclipse IDE
Click Help -> Install New Software...
Click Add button at top right corner
At pop up: fill up Name as "M2Eclipse" and Location as "http://download.eclipse.org/technology/m2e/releases" or http://download.eclipse.org/technology/m2e/milestones/1.0
Now click OK
After that installation would be started.


maven: settings.xml
proxy
	<proxy>
      <id>proxy</id>
      <active>true</active>
      <protocol>https</protocol>
      <username></username>
      <password></password>
      <host>proxy-lv.isd.dp.ua</host>
      <port>8080</port>
      <nonProxyHosts></nonProxyHosts>
    </proxy>




https://wiki.base22.com/display/btg/How+to+create+a+Maven+web+app+and+deploy+to+Tomcat+-+fast



pom.xml
<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
			<scope>provided</scope>
		</dependency>


"C:\Program Files\apache-maven-3.3.9\bin\mvn"






"C:\Program Files\apache-maven-3.3.9\bin\mvn" install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar


© ISD 

