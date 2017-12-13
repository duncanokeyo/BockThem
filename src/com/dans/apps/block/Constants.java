package com.dans.apps.block;


public class Constants {

public static final String ErrorResponse=
"HTTP/1.1 200 OK\r\n"+
"Content-Type: text/html\r\n"+
"Content-Length: 500\r\n"+
"Connection: close\r\n"+
"\r\n"+
"<!DOCTYPE html>"+
"<html>"+
"<head>"+
	"<title>ERROR !!!!!!!!!!!!!!!!!!!!!!!!!! </title>"+
"</head>"+
"<body>"+

"<h1 style=\"color:red;\">DANGEROUS WEBSITE YOU ARE HEADING TO !!! </h1>"+
"</body>"+
"</html>";



public static String Instructions="---------------------- INSTRUCTIONS ------------------------\n" +
		"1) Open firefox web browser go to Preference > General > Network Proxy\n" +
		"2) Click on settings then select \"manual proxy configuratin\" \n" +
		"3) In HTTP Proxy set \"local host\" in first field\n" +
		"4) In second field set 3018 as port number\n" +
		"5) In SSL Proxy make sure host field is empty and port number is 0\r\n" +
		"" +
		"The jar file comes with two files which contain domain names we want to block\n"+
		"Copy these files together with the jar and place them in the same dir/folder\n" +
		"execute the jar file. You can now use Firefox to browse. An error message will" +
		"appear if the domain is a malware or porn\n" +
		"--------------------------------------------------------------------------------\n\n";
}