jar_name = FileTransfer.jar

#Makefile que automatiza la compilación y empaquetado de los programas
all: FileTransfer.jar
	@echo "Proyecto completado, se ha compilado y empaquetado en un archivo JAR"

#Creación del archivo JAR
FileTransfer.jar: Main.class FileClient.class FileServer.class Manifest.mf
	@echo Generando archivo Jar...
	jar cmf Manifest.mf ${jar_name} Main.class FileClient.class FileServer.class

#Compilción de las clases
Main.class: Main.java
	javac Main.java

FileClient.class: FileClient.java
	javac FileClient.java

FileServer.class: FileServer.java
	javac FileServer.java

#Limpieza de archivos temporales
clean:
	@echo Haciendo limpieza del proyecto...
	rm *.class