package com.wsm.fileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import com.wsm.fileutil.*;

import com.wsm.common.Common;
import com.wsm.fileutil.FileUtil;

public class FileSystem implements FileUtil {
	
	private static FileUtil fileSystem = null;
	
	private FileSystem(Common common) {
		switch(common) {
		case FILE_UTIL_IO:
			fileSystem = new IOFile();
			break;
		case FILE_UTIL_NIO:
			fileSystem = new NIOFile();
			break;
		case FILE_UTIL_CHANNEL:
			fileSystem = new NIOChannels();
			break;
		}
	}
	
	public static FileUtil newFileSystem(Common common) {
		if(fileSystem == null) {
			synchronized(FileSystem.class) {
				if(fileSystem == null) {
					new FileSystem(common);
				}
			}
		}
		return fileSystem;
	}

	@Override
	public byte[] read(String path) throws Exception {
		return fileSystem.read(path);
	}

	@Override
	public Path write(String path, byte[] data, OpenOption option)
			throws Exception {
		return fileSystem.write(path, data, option);
	}

	@Override
	public boolean exist(String path) {
		return fileSystem.exist(path);
	}

	@Override
	public InputStream newInputStream(String path, OpenOption option)
			throws Exception {
		return fileSystem.newInputStream(path, option);
	}

	@Override
	public OutputStream newOutputStream(String path, OpenOption option)
			throws Exception {
		return fileSystem.newOutputStream(path, option);
	}

	@Override
	public BufferedWriter newBufferedWriter(String path, Charset cs,
			OpenOption option) throws Exception {
		return fileSystem.newBufferedWriter(path, cs, option);
	}

	@Override
	public BufferedReader newBufferedReader(String path, Charset cs)
			throws Exception {
		return fileSystem.newBufferedReader(path, cs);
	}

	
}
