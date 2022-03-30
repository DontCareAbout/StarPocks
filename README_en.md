What is StarPocks? ([中文版][ZH])
=================================

~~Java + Mermaid = Starbucks => StarPocks~~

StarPocks is a tool that can convert Java classes into [Mermaid.js] class diagram syntax.


How to Use?
===========

> Sorry, StarPocks does not exist in any Maven Repo.
> Please clone this project and execute `mvn install` by yourself.


As a library 
------------

Maven：

```XML
<dependencies>
	<dependency>
		<groupId>us.dontcareabout</groupId>
		<artifactId>StarPocks</artifactId>
		<version>0.0.1</version>
	</dependency>
</dependencies>
```

Java：

```Java
ClassDiagram cd = new ClassDiagram();
System.out.println(
	cd.write(
		java.nio.channels.AlreadyBoundException.class,
		java.rmi.AlreadyBoundException.class
	)
);
```


Limitations of StarPocks :sob:
=============================

+ Generic type is ignored.


[ZH]: README.md
[Mermaid.js]: https://mermaid-js.github.io/
