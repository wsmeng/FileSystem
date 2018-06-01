package com.wsm.fileutil;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class IOFile implements FileUtil {

	@Override
	public byte[] read(String path) throws Exception {
		FileInputStream fis = new FileInputStream(new File(path));
		List<Byte> result = new ArrayList<Byte>();
		byte[] temp = new byte[1024];
		int length = 0;
		while((length = fis.read(temp)) > 0) {
			for(int index = 0; index < length; index++) {
				result.add(temp[index]);
			}
		}
		fis.close();
		byte[] data = new byte[result.size()];
		int count = 0;
		for(Byte sin : result) {
			data[count++] = sin;
		}
		return data;
	}

	@Override
	public Path write(String path, byte[] data, OpenOption option)
			throws Exception {
		FileOutputStream fos;
		if(option == StandardOpenOption.APPEND) {
			fos = new FileOutputStream(new File(path), true);
		} else {
			fos = new FileOutputStream(new File(path), false);
		}
		fos.write(data);
		fos.flush();
		fos.close();
		return Paths.get(path);
	}

	@Override
	public boolean exist(String path) {
		return new File(path).exists();
	}

	@Override
	public InputStream newInputStream(String path, OpenOption option)
			throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public OutputStream newOutputStream(String path, OpenOption option)
			throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public BufferedWriter newBufferedWriter(String path, Charset cs,
			OpenOption option) throws Exception {
		BufferedWriter bw;
		if(option == StandardOpenOption.APPEND) {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path), true), cs));
		} else {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path), false), cs));
		}
		return bw;
	}

	@Override
	public BufferedReader newBufferedReader(String path, Charset cs)
			throws Exception {
		return new BufferedReader(new InputStreamReader(new FileInputStream(path), cs));
	}

}
