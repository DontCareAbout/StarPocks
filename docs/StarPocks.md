
class 定義
----------

```Java
class C_NAME ~G_TYPE~ {
    <<S_TEXT>>
	     （\~G_TYPE~）（visibility） F_TYPE F_NAME （$）
	     （\~G_TYPE~）（visibility） M_NAME（（\~G_TYPE~）M_ARGS） R_TYPE （$）（*）
}
```

Command Line 指令說明
---------------------
#### 1. 顯示給定的 class 定義，不用顯示該 class 的 hierarchy / relation ####

| 指令 | 說明 |
| ---- | ---- |
| `（java Path.mermaid） [C_NAME] （outputfile）` | 顯示給定 `[C_NAME]` 的 field 及 method  |
| `（java Path.mermaid） [C_NAME] （\~G_TYPE~） field （$） （outputfile） ` | 顯示給定 `[C_NAME]` 的 field |
| `（java Path.mermaid） [C_NAME] （\~G_TYPE~） method （$）（*）（outputfile） ` | 顯示給定 `[C_NAME]` 的 method |
| `（java Path.mermaid） <<stereotype>> [Class_Name] （outputfile）` | 顯示 class 的 stereotype |
| `（java Path.mermaid） [C_NAME] （\~G_TYPE~）F_TYPE field （$） （outputfile）`  | 顯示給定 `[C_NAME]`及 `F_TYPE` 的 field  |
| `（java Path.mermaid） [C_NAME] （\~G_TYPE~）R_TYPE method （$） （*） （outputfile）`  | 顯示給定 `[C_NAME]`及 `R_TYPE` 的 method  |
| `（java Path.mermaid） [C_NAME] [NAME] （outputfile）` | 顯示給定 `[C_NAME]` 的 constructor  |
| `（java Path.mermaid） [C_NAME] [NAME] （（\~G_TYPE~）M_ARGS） （outputfile）` | 顯示給定 `[C_NAME] ` 及 constructor 下有幾種 args*  |
　
#### 2. 顯示給定的 class 定義，顯示該 class 的 hierarchy / relation ####

| 指令 | 說明 |
| ---- | ---- |
| `（java Path.mermaid） [C_NAME_A] <-- （outputfile）` | 顯示關聯（Association Link）`[C_NAME_A]` 的 class |
| `（java Path.mermaid） [C_NAME_A] --> （outputfile）` | 顯示被 `[C_NAME_A]`關聯（Association Link）`[C_NAME_A]` 的 class |
| `（java Path.mermaid） [C_NAME_A] <.. （outputfile）` | 顯示依賴（Dependency Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] ..> （outputfile）` | 顯示被 `[C_NAME_A]`依賴（Dependency Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] *-- （outputfile）` | 顯示組合（Composition Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] --* （outputfile）` | 顯示被 `[C_NAME_A]`組合（Composition Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] o-- （outputfile）` | 顯示聚合（Aggregation Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] --o （outputfile）` | 顯示被 `[C_NAME_A]`聚合（Aggregation Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] <\|-- （outputfile）` | 顯示繼承（Generalization Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] --\|> （outputfile）` | 顯示被 `[C_NAME_A]`繼承（Generalization Link） 的 class  |
| `（java Path.mermaid） [C_NAME_A] <\|.. （outputfile）` | 顯示實作（Realization Link）`[C_NAME_A]` 的 class  |
| `（java Path.mermaid） [C_NAME_A] ..\|> （outputfile）` | 顯示被 `[C_NAME_A]`實作（Realization Link）`[C_NAME_A]` 的 class  |

#### 3. 顯示給定 class／visibility／type 中的 field / method ####
  
| 指令 | 說明 |
| ---- | ---- |
| `（java Path.mermaid） [C_NAME] visibility（\~G_TYPE~） F_TYPE field （$） （outputfile） ` | 顯示在給定class、visibility、F_TYPE 下的 field |
| `（java Path.mermaid） [C_NAME] visibility（\~G_TYPE~） F_TYPE F_NAME （$） （outputfile） ` | 顯示在給定class、visibility、F_TYPE 及 F_NAME 下 傳入那些 method |
| `（java Path.mermaid） [C_NAME] visibility（\~G_TYPE~） R_TYPE method （$） （*） （outputfile） ` | 顯示在給定 class、visibility 及 R_TYPE 下的 method |
| `（java Path.mermaid） [C_NAME] visibility（\~G_TYPE~） R_TYPE M_NAME （$） （outputfile） ` | 顯示在給定class、visibility、R_TYPE 及 M_NAME 下 傳入那些 method |
| `（java Path.mermaid） [C_NAME] visibility（\~G_TYPE~） R_TYPE M_NAME（（\~G_TYPE~）M_ARGS） （$） （*） （outputfile） ` | 顯示在給定 class、visibility、R_TYPE 及 M_NAME 下有哪幾種 args* |
| `（java Path.mermaid） visibility field（$） （outputfile） ` | 顯示在給定 visibility 下的 class field|
| `（java Path.mermaid） visibility（\~G_TYPE~）  F_TYPE （$） （outputfile） ` | 顯示在給定 visibility 及 type 下的 field |
| `（java Path.mermaid） visibility method （$） （*） ` | 顯示在給定 visibility 下的 method |
| `（java Path.mermaid） visibility（\~G_TYPE~）  R_TYPE method （$） （*） ` | 顯示在給定 visibility 及 R_TYPE 下的 method |
| `（java Path.mermaid） visibility （$） （*） ` | 顯示在給定 visibility 下的 method / field |


#### 4. class 間的關係 ####

```Java
CLASS_A "CM_TEXT" A_TYPE LINK A_TYPE "CM_TEXT" CLASS_B : L_TEXT
```
| 指令 | 說明 |
| ---- | ---- |
| `（java Path.mermaid） [C_NAME_A] "CLASS_A "CM_TEXT" A_TYPE -- A_TYPE "CM_TEXT" CLASS_B : L_TEXT（outputfile） ` | 顯示 classA 跟 classB 的關係，看看是否能跑出 arrow |






















