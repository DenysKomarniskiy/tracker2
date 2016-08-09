1 git config --global http.proxy http://proxy-lv.isd.dp.ua:8080
  https://github.com/asdman384/tracker2.git


2 Maven Eclipse plugin installation step by step:
	Open Eclipse IDE
	Click Help -> Install New Software...
	Click Add button at top right corner
	At pop up: fill up Name as "M2Eclipse" and Location as "http://download.eclipse.org/technology/m2e/releases" or 	http://download.eclipse.org/technology/m2e/milestones/1.0
	Now click OK
	After that installation would be started.

3 download maven
	maven: settings.xml
	proxy:
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

4. add ms sql
"C:\Program Files\apache-maven-3.3.9\bin\mvn" install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

5. install the m2e-wtp connector.
	http://download.eclipse.org/m2e-wtp/snapshots/neon/
	OR
	You can install this from: Window, Preferences, Maven, Discovery, click Open Catalog and search for wtp. Check the box next to m2e-wtp, and 		click Finish.
	Once the installation process has completed, allow it to restart Eclipse. After that, you may need to right click on the project, choose Maven 		and Update project.
	http://stackoverflow.com/questions/15748434/how-to-run-an-eclipse-m2e-webapp-project-on-a-local-server

6 tracker2\.settings org.eclipse.wst.common.project.facet.core.xml
  <installed facet="jst.web" version="3.0"/>
© ISD 

