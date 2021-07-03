package com.samajackun.summer.core.path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.Set;

import org.junit.Test;

public class GenericPathTest
{
	private static final StringConverter converter=new StringConverter();

	private Path<String> createPath(String serial)
	{
		try
		{
			return GenericPath.<String> parse(serial, '.', converter);
		}
		catch (ConverterException e)
		{
			throw new ServiceConfigurationError("Unexpected", e);
		}
	}

	@Test
	public void zeroSteps()
	{
		Path<String> path=new GenericPath<String>();
		assertNull(path.getParent());
		assertNull(path.getTailStep());
		assertEquals(path, path.getTail());
		List<String> steps=path.getSteps();
		assertEquals(0, steps.size());
		assertTrue(path.isEmpty());
		assertEquals(".", path.serialized());
		assertEquals("/", path.serialized('/'));
	}

	@Test
	public void oneSteps()
	{
		Path<String> path=new GenericPath<String>("enero");
		assertNull(path.getParent());
		assertEquals("enero", path.getTailStep());
		assertEquals(path, path.getTail());
		List<String> steps=path.getSteps();
		assertEquals(1, steps.size());
		assertEquals("enero", steps.get(0));
		assertFalse(path.isEmpty());
		assertEquals(".enero", path.serialized());
		assertEquals("/enero", path.serialized('/'));
	}

	@Test
	public void twoSteps()
	{
		Path<String> parent=new GenericPath<String>("enero");
		Path<String> path=parent.createBranch("febrero");
		assertEquals(parent, path.getParent());
		assertEquals("febrero", path.getTailStep());
		assertEquals(createPath("febrero"), path.getTail());
		List<String> steps=path.getSteps();
		assertEquals(2, steps.size());
		assertEquals("enero", steps.get(0));
		assertEquals("febrero", steps.get(1));
		assertFalse(path.isEmpty());
		assertEquals(".enero.febrero", path.serialized());
		assertEquals("/enero/febrero", path.serialized('/'));
	}

	@Test
	public void branch()
	{
		Path<String> parent=createPath("enero.febrero");
		Path<String> child=createPath("marzo.abril.mayo");
		Path<String> path=parent.createBranch(child);

		assertEquals(".enero.febrero.marzo.abril.mayo", path.serialized());
		assertEquals("/enero/febrero/marzo/abril/mayo", path.serialized('/'));
		assertEquals(".enero.febrero.marzo.abril", path.getParent().serialized());
		assertEquals("mayo", path.getTailStep());
		assertEquals(createPath("mayo"), path.getTail());
		List<String> steps=path.getSteps();
		assertEquals(5, steps.size());
		assertEquals("enero", steps.get(0));
		assertEquals("febrero", steps.get(1));
		assertEquals("marzo", steps.get(2));
		assertEquals("abril", steps.get(3));
		assertEquals("mayo", steps.get(4));
		assertFalse(path.isEmpty());
	}

	@Test
	public void relativePathContained()
	{
		Path<String> base=createPath("enero.febrero");
		Path<String> path=createPath("enero.febrero.marzo.abril.mayo");
		Path<String> relative=path.getPathRelativeTo(base);
		Path<String> expected=createPath("marzo.abril.mayo");
		assertEquals(expected, relative);
	}

	@Test
	public void relativePathDiverging()
	{
		Path<String> base=createPath("enero.lunes");
		Path<String> path=createPath("enero.febrero.marzo.abril.mayo");
		Path<String> relative=path.getPathRelativeTo(base);
		assertNull(relative);
	}

	@Test
	public void relativePathNotContained()
	{
		Path<String> base=createPath("lunes");
		Path<String> path=createPath("enero.febrero.marzo.abril.mayo");
		Path<String> relative=path.getPathRelativeTo(base);
		assertNull(relative);
	}

	@Test
	public void relativePathGreater()
	{
		Path<String> base=createPath("enero.febrero.marzo" + "");
		Path<String> path=createPath("enero.febrero");
		Path<String> relative=path.getPathRelativeTo(base);
		assertNull(relative);
	}

	@Test
	public void splitter()
	{
		Path<String> path=createPath("enero.febrero.marzo");
		PathSplitter<String> splitter=path.createSplitter();

		assertTrue(splitter.hasNext());
		assertEquals(createPath("marzo"), splitter.getChildPart());
		assertEquals(createPath("enero.febrero"), splitter.getParentPart());
		splitter.next();

		assertTrue(splitter.hasNext());
		assertEquals(createPath("febrero.marzo"), splitter.getChildPart());
		assertEquals(createPath("enero"), splitter.getParentPart());
		splitter.next();

		assertTrue(splitter.hasNext());
		assertEquals(createPath("enero.febrero.marzo"), splitter.getChildPart());
		assertNull(splitter.getParentPart());
		splitter.next();

		assertFalse(splitter.hasNext());
	}

	@Test
	public void hash()
	{
		Path<String> path1=createPath("enero.febrero");
		Path<String> path2=createPath("enero.febrero");
		Path<String> path3=createPath("enero.febrero.marzo");
		Path<String> path4=createPath("lunes");
		Set<Path<String>> set=new HashSet<Path<String>>();
		assertEquals(0, set.size());
		set.add(path1);
		assertEquals(1, set.size());
		set.add(path2);
		assertEquals(1, set.size());
		set.add(path3);
		assertEquals(2, set.size());
		assertTrue(set.contains(path1));
		assertTrue(set.contains(path2));
		assertTrue(set.contains(path3));
		assertFalse(set.contains(path4));
	}

	private static class StringConverter implements Converter<String>
	{
		@Override
		public String parse(String s)
			throws ConverterException
		{
			return s;
		}

		@Override
		public String serialize(String token)
			throws ConverterException
		{
			return token;
		}

	}
}
