package com.wsm.simulation;

import com.wsm.common.Common;
import com.wsm.fileSystem.FileSystem;
import com.wsm.fileutil.FileUtil;

public class Simulation {
	
	public static void main(String[] args) {
		FileUtil fileSystem = FileSystem.newFileSystem(Common.FILE_UTIL_NIO);
		try {
			byte[] data = fileSystem.read("F:/testResource/test.txt");
			for(int index = 0; index < data.length; index++) {
				System.out.println((char)data[index]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
