package com.samajackun.summer.core.path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GenericTreeTest
{
	/*
	 * tierra
	 * -america
	 * -europa
	 * --espa�a
	 * ---madrid
	 * ---castilla la mancha
	 * ----toledo
	 * ----cuenca
	 * ---galicia
	 * ----coru�a
	 * ----pontevedra
	 * --inglaterra
	 * --portugal
	 * -asia
	 */
	private Tree<String, MyBean> createTreeEarth()
	{
		int p=1;
		Tree<String, MyBean> earth=MyTree.createRoot("tierra");

		Tree<String, MyBean> america=earth.addChild("am�rica", new MyBean(p++, "continente americano"));
		Tree<String, MyBean> europe=earth.addChild("europa", new MyBean(p++, "continente europeo"));
		Tree<String, MyBean> asia=earth.addChild("asia", new MyBean(p++, "continente asi�tico"));
		Tree<String, MyBean> spain=europe.addChild("espa�a", new MyBean(p++, "pa�s ezpa�a"));
		Tree<String, MyBean> madrid=spain.addChild("madrid", new MyBean(p++, "regi�n madrid"));
		Tree<String, MyBean> castillaMancha=spain.addChild("castilla-la-mancha", new MyBean(p++, "regi�n castilla la mancha"));
		Tree<String, MyBean> toledo=castillaMancha.addChild("toledo", new MyBean(p++, "provincia toledo"));
		Tree<String, MyBean> cuenca=castillaMancha.addChild("cuenca", new MyBean(p++, "provincia cuenca"));
		Tree<String, MyBean> galicia=spain.addChild("galicia", new MyBean(p++, "regi�n galicia"));
		Tree<String, MyBean> uk=europe.addChild("reino-unido", new MyBean(p++, "pa�s reino unido"));
		Tree<String, MyBean> portugal=europe.addChild("portugal", new MyBean(p++, "pa�s portugal"));
		return earth;
	}

	private Tree<String, MyBean> createTreeMadridCapital()
	{
		int p=1;
		Tree<String, MyBean> capital=MyTree.createRoot("madrid-capital");

		Tree<String, MyBean> lavapies=capital.addChild("lavapi�s", new MyBean(p++, "barrio de lavapi�s"));
		Tree<String, MyBean> chamberi=capital.addChild("chamberi", new MyBean(p++, "barrio de chamber�"));
		Tree<String, MyBean> almagro=chamberi.addChild("almagro", new MyBean(p++, "calle almagro"));
		Tree<String, MyBean> campos=chamberi.addChild("mart�nez-campos", new MyBean(p++, "calle general mart�nez campos"));
		Tree<String, MyBean> arguelles=capital.addChild("arg�elles", new MyBean(p++, "barrio de arg�elles"));
		return capital;
	}

	@Test
	public void traverse()
	{
		Tree<String, MyBean> earth=createTreeEarth();

		final List<String> lines=new ArrayList<String>(12);
		Visitor<String, MyBean> myVisitor=new DefaultVisitor<String, MyBean>()
		{
			@Override
			public com.samajackun.summer.core.path.Visitor.Control before(Tree<String, MyBean> tree)
			{
				lines.add(tree.getPath() + ": " + tree.getValue());
				return super.before(tree);
			}
		};
		earth.traverse(myVisitor);
		int p=0;
		assertEquals(".tierra: null", lines.get(p++));
		assertEquals(".tierra.am�rica: MyBean [id=1, name=continente americano]", lines.get(p++));
		assertEquals(".tierra.asia: MyBean [id=3, name=continente asi�tico]", lines.get(p++));
		assertEquals(".tierra.europa: MyBean [id=2, name=continente europeo]", lines.get(p++));
		assertEquals(".tierra.europa.reino-unido: MyBean [id=10, name=pa�s reino unido]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a: MyBean [id=4, name=pa�s ezpa�a]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.galicia: MyBean [id=9, name=regi�n galicia]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid: MyBean [id=5, name=regi�n madrid]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.castilla-la-mancha: MyBean [id=6, name=regi�n castilla la mancha]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.castilla-la-mancha.toledo: MyBean [id=7, name=provincia toledo]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.castilla-la-mancha.cuenca: MyBean [id=8, name=provincia cuenca]", lines.get(p++));
		assertEquals(".tierra.europa.portugal: MyBean [id=11, name=pa�s portugal]", lines.get(p++));
	}

	@Test
	public void index()
	{
		Tree<String, MyBean> earth=createTreeEarth();
		Map<Path<String>, PlainTree<String, MyBean>> index=earth.index();
		Iterator<Path<String>> entryIterator=index.keySet().iterator();

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.portugal", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.espa�a.castilla-la-mancha", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".am�rica", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.espa�a.galicia", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.espa�a.madrid", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.espa�a", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.espa�a.castilla-la-mancha.cuenca", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".asia", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.espa�a.castilla-la-mancha.toledo", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.reino-unido", entryIterator.next().toString());

		assertFalse(entryIterator.hasNext());
		// System.out.println("map.entrySet()=" + index.entrySet());
		// Set<Path<String>> entries=index.keySet();
		// for (Path<String> entry : entries)
		// {
		// System.out.println("* entry=" + entry + ": " + index.get(entry));
		// }

		// StringPath rootPath=new StringPath("tierra");
		// PlainTree<String, MyBean> rootMap=map.get(rootPath);
		// assertNotNull("Available keys=" + map.keySet(), rootMap);
		// assertNull(rootMap.getValue());
		// Set<Path<String>> entries=rootMap.getEntries();
		// for (Path<String> entry : entries)
		// {
		// System.out.println("* entry=" + entry);
		// }
		// System.out.println("* entries.isEmpty()=" + entries.isEmpty());
		// assertTrue("Available keys=" + map.keySet(), rootMap.getEntries().contains(StringPath.parse("tierra.asia", '.')));
		// assertTrue(rootMap.getEntries().contains(new StringPath(rootPath, "europa")));
		// assertTrue(rootMap.getEntries().contains(new StringPath(rootPath, "am�rica")));
		// StringPath pathPortugal=new StringPath(new StringPath(rootPath, "europa"), "portugal");
		// System.out.println("pathPortugal=" + pathPortugal);
		// System.out.println("rootEntries.getEntries()=" + rootMap.getEntries());
		// System.out.println("rootEntries.getEntries().size()=" + rootMap.getEntries().size());
		// assertTrue(rootMap.getEntries().contains(pathPortugal));
		// assertTrue(rootMap.getEntries().contains(new StringPath(new StringPath(rootPath, "europa"), "espa�a")));
		// assertFalse(rootMap.getEntries().contains(new StringPath(new StringPath(rootPath, "europa"), "grecia")));
		// assertTrue(rootMap.getEntries().contains(new StringPath(new StringPath(new StringPath(rootPath, "europa"), "espa�a"), "galicia")));
	}

	@Test
	public void tree()
	{
		Tree<String, MyBean> earth=createTreeEarth();
		Path<String> subPath;
		Tree<String, MyBean> subTree;

		subPath=StringPath.parse("europa", '.');
		subTree=earth.getTree(subPath);
		assertNotNull(subTree);

		subPath=StringPath.parse("europa.portugal", '.');
		subTree=earth.getTree(subPath);
		assertNotNull(subTree);
	}

	@Test
	public void combineAndTraverse()
	{
		Tree<String, MyBean> earth=createTreeEarth();
		Tree<String, MyBean> capital=createTreeMadridCapital();
		Tree<String, MyBean> madrid=earth.getTree(StringPath.parse("europa.espa�a.madrid", '.'));
		madrid.addChild(capital);
		final List<String> lines=new ArrayList<String>(12);
		Visitor<String, MyBean> myVisitor=new DefaultVisitor<String, MyBean>()
		{
			@Override
			public com.samajackun.summer.core.path.Visitor.Control before(Tree<String, MyBean> tree)
			{
				String line=tree.getPath() + ": " + tree.getValue();
				System.out.println(line);
				lines.add(line);
				return super.before(tree);
			}
		};
		earth.traverse(myVisitor);
		int p=0;
		assertEquals(".tierra: null", lines.get(p++));
		assertEquals(".tierra.am�rica: MyBean [id=1, name=continente americano]", lines.get(p++));
		assertEquals(".tierra.asia: MyBean [id=3, name=continente asi�tico]", lines.get(p++));
		assertEquals(".tierra.europa: MyBean [id=2, name=continente europeo]", lines.get(p++));
		assertEquals(".tierra.europa.reino-unido: MyBean [id=10, name=pa�s reino unido]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a: MyBean [id=4, name=pa�s ezpa�a]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.galicia: MyBean [id=9, name=regi�n galicia]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid: MyBean [id=5, name=regi�n madrid]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid.madrid-capital: MyBean [id=12, name=madrid capital]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid.madrid-capital.lavapi�s: MyBean [id=13, name=barrio de lavapi�s]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid.madrid-capital.chamber�: MyBean [id=14, name=barrio de chamber�]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid.madrid-capital.chamber�.almagro: MyBean [id=15, name=calle almagro]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid.madrid-capital.chamber�.mart�nez-campos: MyBean [id=16, name=calle general mart�nez campos]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.madrid.madrid-capital.arg�elles: MyBean [id=17, name=barrio de arg�elles]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.castilla-la-mancha: MyBean [id=6, name=regi�n castilla la mancha]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.castilla-la-mancha.toledo: MyBean [id=7, name=provincia toledo]", lines.get(p++));
		assertEquals(".tierra.europa.espa�a.castilla-la-mancha.cuenca: MyBean [id=8, name=provincia cuenca]", lines.get(p++));
		assertEquals(".tierra.europa.portugal: MyBean [id=11, name=pa�s portugal]", lines.get(p++));
	}
}
