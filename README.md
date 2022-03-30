StarPocks 是什麼？ ([English Version][EN])
============================================

~~Java + Mermaid = Starbucks => StarPocks（意義不明）~~

StarPocks 是一個將 Java class（們）自動轉換成 Mermaid.js class diagram 語法的工具。


怎麼用？
========

> 十分抱歉，目前在任何一個 Maven Repo 都找不到。
> 必須自行 clone 之後做 `mvn install` ...... Orz


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


StarPocks 的限制
================

主要是受限於 Java Reflection（以及 Don'tCare 的技術能力 :sob:）：

+ 無法處理 generic type


[EN]: README_en.md
[Mermaid.js]: https://mermaid-js.github.io/
