#Makefile que automatiza la compilación y empaquetado de los programas
all: FileClient.jar FileServer.jar
	@echo "Vamos bien"

FileClient.jar:	FileClient.class ManifestClient.mf
	jar cmf ManifestClient.mf FileClient.jar FileClient.class

FileServer.jar: FileServer.class ManifestServer.mf
	jar cmf ManifestServer.mf FileServer.jar FileServer.class

FileClient.class: FileClient.java
	javac FileClient.java

FileServer.class: FileServer.java
	javac FileServer.java

#Bajo observacion
ManifestClient.mf:
	touch ManifestClient.mf
	echo Manifest-Version: 1.0 Main-Class: FileClient > ManifestClient.mf

ManifestServer.mf:
	touch ManifestServer.mf
	echo Manifest-Version: 1.0 Main-Class: FileServer > ManifestServer.mf

#Limpieza de archivos temporales
clean:
	rm *.class