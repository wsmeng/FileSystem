package com.wsm.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.wsm.fileutil.FileUtil;
import com.wsm.fileutil.IOFile;

public class IOFileTest {

	private FileUtil fileUtil;
	
	@Before
	public void setUp() {
		fileUtil = new IOFile();
	}
	
	@Ignore
	@Test
	public void testRead() {
		try {
			byte[] data = fileUtil.read("F:/testResource/test.txt");
			for(int index = 0; index < data.length; index++) {
				System.out.println((char)data[index]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testWrite() {
		byte[] data = {'b', 'c', 'd', 'e', 'f'};
		try{
			System.out.println(fileUtil.write("F:/testResource/test.txt", data, StandardOpenOption.APPEND));
			testRead();
			System.out.println(fileUtil.write("F:/testResource/test.txt", data, StandardOpenOption.CREATE_NEW));
			testRead();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExist() {
		assertEquals(fileUtil.exist("F:/testResource/test.txt"), true);
		assertEquals(fileUtil.exist("F:/testResource/test"), false);
	}
	
	@Ignore
	@Test
	public void testBufferedWriter() {
		BufferedWriter bw;
		try {
//			bw = fileUtil.
//					newBufferedWriter("F:/testResource/test.txt", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
//			bw.write("i love you");
//			bw.append("i love you");
//			bw.flush();
//			bw.close();
//			testRead();
			bw = fileUtil.
					newBufferedWriter("F:/testResource/test.txt", StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
			bw.write("i love you");
			bw.append("i love you");
			bw.flush();
			bw.close();
			testRead();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testBufferedReader() {
		BufferedReader br;
		try{
			br = fileUtil.newBufferedReader("F:/testResource/test.txt", StandardCharsets.UTF_8);
			String str = "";
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
