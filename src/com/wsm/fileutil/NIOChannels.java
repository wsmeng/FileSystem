package com.wsm.fileutil;

import java.io.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class NIOChannels implements FileUtil {

	@Override
	public byte[] read(String path) throws Exception {
		InputStream is = Channels.newInputStream(
				FileChannel.open(Paths.get(path), StandardOpenOption.READ));
		List<Byte> result = new ArrayList<Byte>();
		int length = 0;
		byte[] temp = new byte[1024];
		while((length = is.read(temp)) > 0) {
			for(int index = 0; index < length; index++) {
				result.add(temp[index]);
			}
		}
		is.close();
		if(result.size() != 0) {
			byte[] data = new byte[result.size()];
			int count = 0;
			for(Byte sin : result) {
				data[count++] = sin;
			}
			return data;
		}
		return null;
	}

	@Override
	public Path write(String path, byte[] data, OpenOption option)
			throws Exception {
		Path file = Paths.get(path);
		OutputStream os = Channels.newOutputStream(
				FileChannel.open(file, StandardOpenOption.APPEND));
		os.write(data);
		return file;
	}

	@Override
	public boolean exist(String path) {
		return Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS);
	}

	@Override
	public InputStream newInputStream(String path, OpenOption option)
			throws Exception {
		return Channels.newInputStream(FileChannel.open(Paths.get(path), option));
	}

	@Override
	public OutputStream newOutputStream(String path, OpenOption option)
			throws Exception {
		return Channels.newOutputStream(FileChannel.open(Paths.get(path), option));
	}

	@Override
	public BufferedWriter newBufferedWriter(String path, Charset cs,
			OpenOption option) throws Exception {
		return new BufferedWriter(
				new OutputStreamWriter(Channels.newOutputStream(
						FileChannel.open(Paths.get(path), option)), cs));
	}

	@Override
	public BufferedReader newBufferedReader(String path, Charset cs)
			throws Exception {
		return new BufferedReader(new InputStreamReader(
				Channels.newInputStream(FileChannel.open(Paths.get(path))), cs));
	}

}
