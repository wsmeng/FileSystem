package com.wsm.fileutil;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class NIOFile implements FileUtil {

	@Override
	public byte[] read(String path) throws Exception {
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public boolean exist(String path) {
		return Files.exists(Paths.get(path),  LinkOption.NOFOLLOW_LINKS);
	}

	@Override
	public InputStream newInputStream(String path, OpenOption option) throws Exception {
		return Files.newInputStream(Paths.get(path), option);
	}

	@Override
	public OutputStream newOutputStream(String path, OpenOption option) throws Exception {
		return Files.newOutputStream(Paths.get(path), option);
	}

	@Override
	public BufferedWriter newBufferedWriter(String path, Charset cs, OpenOption option) throws Exception {
		return Files.newBufferedWriter(Paths.get(path), cs, option);
	}

	@Override
	public BufferedReader newBufferedReader(String path, Charset cs) throws Exception {
		return Files.newBufferedReader(Paths.get(path), cs);
	}


	@Override
	public Path write(String path, byte[] data, OpenOption option) throws IOException {
		return Files.write(Paths.get(path), data, option);
	}

}
