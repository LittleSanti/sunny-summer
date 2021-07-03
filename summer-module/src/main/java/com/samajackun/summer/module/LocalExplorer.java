package com.samajackun.summer.module;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.samajackun.summer.core.IdentifierPath;

class LocalExplorer implements Explorer
{
	private static final Log LOG=LogFactory.getLog(LocalExplorer.class);

	private final Set<JarFile> classpath;

	private final Map<IdentifierPath, Module> modules;

	public LocalExplorer(Set<JarFile> classpath)
	{
		super();
		this.classpath=classpath;
		this.modules=explore(classpath);
	}

	public static Explorer getInstanceFromClasspath()
					throws IOException
	{
		Enumeration<URL> enumeration=LocalExplorer.class.getClassLoader().getResources("/META-INF/summer-module");
		Set<JarFile> files=new LinkedHashSet<JarFile>();
		while (enumeration.hasMoreElements())
		{
			try
			{
				URL url=enumeration.nextElement();
				files.add(new JarFile(new File(url.getFile())));
			}
			catch (IOException e)
			{
				LOG.error(e.getMessage(), e);
			}
		}
		Explorer explorer=new LocalExplorer(files);
		return explorer;
	}

	private Map<IdentifierPath, Module> explore(Set<JarFile> jarFiles)
	{
		Map<IdentifierPath, Module> map=new HashMap<IdentifierPath, Module>();
		for (JarFile jarFile : jarFiles)
		{
			try
			{
				exploreJar(jarFile, map);
			}
			catch (ModuleParseException | IOException e)
			{
				LOG.error(e.getMessage(), e);
			}
		}
		return map;
	}

	private void exploreJar(JarFile jarFile, Map<IdentifierPath, Module> map)
					throws IOException,
					ModuleParseException
	{
		JarEntry entry=jarFile.getJarEntry("META-INF/summer-modules");
		if (entry != null)
		{
			InputStream input=jarFile.getInputStream(entry);
			Module module=parse(input);
			map.put(module.getName(), module);
		}
	}

	private Module parse(InputStream input)
					throws ModuleParseException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Set<JarFile> getClasspath()
	{
		return this.classpath;
	}

	@Override
	public Set<IdentifierPath> getModulePaths()
	{
		return this.modules.keySet();
	}

	@Override
	public Module getModule(IdentifierPath path)
					throws ModuleNotFoundException
	{
		Module module=this.modules.get(path);
		if (module == null)
		{
			throw new ModuleNotFoundException(path);
		}
		return module;
	}
}
