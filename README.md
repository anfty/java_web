# java_web

## 本项目仅作为作者学习jsp 不适合生产环境，作者不承担使用该代码后果。  

## 环境  


- mysql  
- servlet-api.jar
- mysql-connector-java-8.0.27.jar

### 

## mysql  
```
+-----------+--------------+-----------------+------+-----+
| Field     | Type         | Collation       | Null | Key |
+-----------+--------------+-----------------+------+-----+
| runoob_id | int unsigned | NULL            | NO   | PRI |
| logname   | varchar(25)  | utf8_general_ci | NO   |     |
| password  | varchar(40)  | utf8_general_ci | NO   |     |
| sex       | varchar(5)   | utf8_general_ci | YES  |     |
| age       | int          | NULL            | YES  |     |
| phone     | varchar(16)  | utf8_general_ci | YES  |     |
| email     | char(40)     | utf8_general_ci | YES  |     |
| message   | varchar(160) | utf8_general_ci | YES  |     |
| pic       | varchar(60)  | utf8_general_ci | YES  |     |
+-----------+--------------+-----------------+------+-----+
```

## tree  

```
├── findMember.jsp
├── head.html
├── index.jsp
├── login.jsp
├── loginMessage.jsp
├── lookMember.jsp
├── register.jsp
├── registerMessage.jsp
├── showAllMember.jsp
├── showMember.jsp
├── showUploadMessage.jsp
├── updatePwd.jsp
├── updatePwdMessage.jsp
├── updateRegister.jsp
├── updateRegisterMessage.jsp
├── updateReg.jsp
├── upload
├── uploadPic.jsp
├── WEB-INF
│   ├── classes
│   │   └── tom
│   │       ├── bean
│   │       │   ├── Login.class
│   │       │   ├── Login.java
│   │       │   ├── MemberInformation.class
│   │       │   ├── MemberInformation.java
│   │       │   ├── MemberInform.class
│   │       │   ├── MemberInform.java
│   │       │   ├── Register.class
│   │       │   ├── Register.java
│   │       │   ├── ShowByPage.class
│   │       │   ├── ShowByPage.java
│   │       │   ├── ShowResult.class
│   │       │   ├── ShowResult.java
│   │       │   ├── UpdatePassword.class
│   │       │   ├── UpdatePassword.java
│   │       │   ├── UpdateRegister.class
│   │       │   ├── UpdateRegister.java
│   │       │   ├── UploadPic.class
│   │       │   └── UploadPic.java
│   │       └── servlet
│   │           ├── Exit.class
│   │           ├── Exit.java
│   │           ├── FindMember.class
│   │           ├── FindMember.java
│   │           ├── HandleDatabase.class
│   │           ├── HandleDatabase.java
│   │           ├── LoginMessage.class
│   │           ├── LoginMessage.java
│   │           ├── RegisterMessage.class
│   │           ├── RegisterMessage.java
│   │           ├── ShowMember.class
│   │           ├── ShowMember.java
│   │           ├── UpdatePwd.class
│   │           ├── UpdatePwd.java
│   │           ├── UpdateReg.class
│   │           ├── UpdateRegisterMessage.class
│   │           ├── UpdateRegisterMessage.java
│   │           ├── UpdateReg.java
│   │           ├── UploadPicture.class
│   │           └── UploadPicture.java
│   └── web.xml
└── web.xml.bat

```
**你写的代码真的很rubbish**
