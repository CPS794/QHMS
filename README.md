# QHMS
A Practice and Test System for teacher-student interactive classes

## About
This is a Practice and Test System that provides teacher and students a platform to do some practice or homework and have some tests. Students can be divided into several groups and each group can prepare some questions for their classmates. The teacher(as the administrator of this system) can also manage the questions and assign homework for students.
The problem is that though this document is in English, the system itself is mainly in Chinese.
+ Web user-interface
	* Mainly based on [Bootstrap 3](http://getbootstrap.com/) framework, together with some plugins like [Summernote](http://summernote.org/) and [DataTables](https://www.datatables.net/)
+ Web server
	* Java Web with Struts

## Installation
### Basic Requirements
+ Java 7
+ Tomcat 7
+ MySQL Server 5.6

### Configrations
+ Database informations located in [DatabaseConnection.java](https://github.com/CPS794/QHMS/blob/master/src/cn/edu/ustb/sem/datastructure/util/DatabaseConnection.java) and more details can be find in [createDB.sql](https://github.com/CPS794/QHMS/blob/master/WebContent/WebRoot/res/sql/createDB.sql)
+ There will be an administrator with user ID 000 and password 000, and also a student with the same user ID and password. 

## Usage
Deployed it on a server with Java, Tomcat and MySQL running on it. Then login as administrator and you'll know what to do if you can understand Chinese.

