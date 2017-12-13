package com.dans.apps.block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WebSiteChecker {
	// temporary cache....
	static ArrayList<String> hosts = new ArrayList<String>();

	/**
	 * Reads host names from malware.txt and porn.txt and populates our
	 * temporary cache
	 * 
	 * should be called from a different thread.....
	 * 
	 * @throws IOException
	 */
	public static void populate() throws IOException {
		String cwd = System.getProperty("user.dir");
		System.out.println("Current working directory " + cwd);
		String pathToMalwareFile = cwd + File.separator + "malware_domain.txt";
		String pathToPornFile = cwd + File.separator + "porn_domain.txt";

		// read from the malware domain list and add to the host cache
		FileInputStream in = new FileInputStream(pathToMalwareFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		System.out.println("Reading malware domains from file " + pathToPornFile);
		while ((line = br.readLine()) != null) {
			hosts.add(line.trim());
		}

		in.close();
		br.close();
		in = null;
		br = null;
		line = null;

		// read from the porn domain list and add to the host cache
		// read from the malware domain list and add to the host cache
		in = new FileInputStream(pathToPornFile);
		br = new BufferedReader(new InputStreamReader(in));
		System.out.println("Reading porn domains from file " + pathToPornFile);
		while ((line = br.readLine()) != null) {
			hosts.add(line.trim());
		}
		
		in.close();
		br.close();
		in = null;
		br = null;
		line = null;
		
		System.out.println();
	}
	
	public static boolean checkIfBadHost(String host){
		if(hosts.size()>0 && host!=null){
			for(String s:hosts){
				if(s.equals(host)){
					return true;
				}
			}
		}
		return false;
	}

}
