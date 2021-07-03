package com.samajackun.summer.core.path;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface DataPath<T extends Comparable<T>> extends Path<T>
{
	public InputStream openInputStream()
		throws IOException;

	public OutputStream openOutputStream()
		throws IOException;
}
