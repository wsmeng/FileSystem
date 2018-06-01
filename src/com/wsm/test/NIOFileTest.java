package com.wsm.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.wsm.fileutil.*;
public class NIOFileTest {
	
	private FileUtil fileUtil;
	
	@Before
	public void setUp() {
		fileUtil = new NIOFile();
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
		try {
			System.out.println(fileUtil.write("F:/testResource/test.txt", data, StandardOpenOption.APPEND));
		} catch (Exception e) {
			e.printStackTrace();
		}
		testRead();
	}

	@Test
	public void testExist() {
		assertEquals(fileUtil.exist("F:/testResource/test"), false);
		assertEquals(fileUtil.exist("F:/testResource/test.txt"), true);
	}

	@Ignore
	@Test
	public void testNewInputStream() {
		try {
			InputStream is = fileUtil.
					newInputStream("F:/testResource/test.txt", StandardOpenOption.READ);
			byte[] data = new byte[1024];
			int length = 0;
			while((length = is.read(data)) > 0) {
				for(int index = 0; index < length; index++) {
					System.out.println((char)data[index]);
				}
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testNewOutputStream() {
		try{
			OutputStream os = fileUtil.
					newOutputStream("F:/testResource/test.txt", StandardOpenOption.APPEND);
			byte[] data = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
			os.write(data);
			os.flush();
			os.close();
			testRead();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testNewBufferedWriter() {
		try {
			BufferedWriter bw = fileUtil.
					newBufferedWriter("F:/testResource/test.txt", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			bw.newLine();
			bw.write("ÎÒ°®Äã");
			bw.flush();
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNewBufferedReader() {
		try{
			BufferedReader br = fileUtil.newBufferedReader("F:/testResource/test.txt", StandardCharsets.UTF_8);
			String result = "";
			while((result = br.readLine()) != null) {
				System.out.println(result);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
