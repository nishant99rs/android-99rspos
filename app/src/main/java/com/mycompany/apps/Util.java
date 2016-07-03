/*******************************************************************************
 *  
 *            Util
 *
 *            Copyright 2008 GoDB Tech Private Limited
 *
 * Proprietary and Confidential
 * Unauthorized distribution or copying is prohibited. All rights reserved.
 *
 * This source code is property of GoDB Tech and MUST not be copied, stored,
 * reproduced, printed, emailed, photocopied, camera captured in any form or
 * by any electronic, mechanical or other means now known or invented in future,
 * without written permission from GoDB Tech.
 *
 * Permission to use this software for any purpose is expressly denied.
 *
 ********************************************************************************/

package com.mycompany.apps;

public class Util 
{
	public static boolean doesFileExist(String str)
	{
		if(str==null) return false;
		java.io.File f = new java.io.File(str);
		if(f.isDirectory()) return false;
		return f.exists();
	}
	
	public static final byte[] SQLITE_HEADER = {0x53,0x51,0x4C,0x69,0x74,0x65,0x20,0x66,0x6F,0x72,0x6D,0x61,0x74,0x20,0x33,0x00};
	
	public static boolean isSqliteEncrypted(String dbName)
	{
		if(!doesFileExist(dbName)) 
			return false;
		try
		{
			java.io.File f = new java.io.File(dbName);
			byte[] header = new byte[16];
			java.io.FileInputStream fis = new java.io.FileInputStream(f);
			fis.read(header, 0, header.length);
			fis.close();
			for(int i=0;i<header.length;i++)
			{
				if(header[i]!=SQLITE_HEADER[i])
					return true;
			}
		}catch(Exception e){}
		return false;
	}
}
