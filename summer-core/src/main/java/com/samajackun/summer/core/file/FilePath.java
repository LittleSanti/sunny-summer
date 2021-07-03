package com.samajackun.summer.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.samajackun.summer.core.path.GenericPath;
import com.samajackun.summer.core.path.Path;
import com.samajackun.summer.core.path.StreamSource;

public class FilePath extends GenericPath<FileStep> implements StreamSource
{
	private final File ioFile;

	public FilePath()
	{
		super();
		this.ioFile=createIoFile();
	}

	public FilePath(FileStep lastStep)
	{
		super(lastStep);
		this.ioFile=createIoFile();
	}

	public FilePath(Path<FileStep> parent, FileStep lastStep)
	{
		super(parent, lastStep);
		this.ioFile=createIoFile();
	}

	private File createIoFile()
	{
		return new java.io.File(serialized(java.io.File.separatorChar));
	}

	@Override
	public InputStream openInputStream()
					throws IOException
	{
		return new FileInputStream(this.ioFile);
	}

	@Override
	public OutputStream openOutputStream()
					throws IOException
	{
		return new FileOutputStream(this.ioFile);
	}

	public File getIoFile()
	{
		return this.ioFile;
	}
}
