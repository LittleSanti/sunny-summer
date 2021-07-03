package com.samajackun.summer.module;

import java.io.IOException;
import java.util.ServiceConfigurationError;
import java.util.Set;

import com.samajackun.summer.core.Identifier;
import com.samajackun.summer.core.IdentifierPath;

public class ModuleManager
{
	private static final ModuleManager INSTANCE=new ModuleManager();

	private final Explorer localExplorer;

	private final Explorer remoteExplorer;

	public static ModuleManager getInstance()
	{
		return INSTANCE;
	}

	private ModuleManager()
	{
		try
		{
			this.localExplorer=LocalExplorer.getInstanceFromClasspath();
			this.remoteExplorer=RemoteExplorer.getInstanceFromDefaultFile();
		}
		catch (IOException e)
		{
			throw new ServiceConfigurationError(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public <P> P getProxy(IdentifierPath moduleName, Identifier serviceName)
					throws ModuleNotFoundException,
					ServiceNotFoundException
	{
		Module module=this.localExplorer.getModule(moduleName);
		if (module == null)
		{
			module=this.remoteExplorer.getModule(moduleName);
			if (module == null)
			{
				throw new ModuleNotFoundException(moduleName);
			}
		}
		return (P)module.getService(serviceName);
	}

	public Set<IdentifierPath> getLocalModules()
	{
		return this.localExplorer.getModulePaths();
	}
}
