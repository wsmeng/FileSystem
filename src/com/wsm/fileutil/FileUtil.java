package com.wsm.fileutil;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

public interface FileUtil {
	public byte[] read(String path) throws Exception;
	public Path write(String path, byte[] data, OpenOption option) throws Exception;
	public boolean exist(String path);
	public InputStream newInputStream(String path, OpenOption option) throws Exception;
	public OutputStream newOutputStream(String path, OpenOption option) throws Exception;
	public BufferedWriter newBufferedWriter(String path, Charset cs, OpenOption option) throws Exception;
	public BufferedReader newBufferedReader(String path, Charset cs) throws Exception;
}
