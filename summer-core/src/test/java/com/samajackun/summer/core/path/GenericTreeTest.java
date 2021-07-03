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
	 * --españa
	 * ---madrid
	 * ---castilla la mancha
	 * ----toledo
	 * ----cuenca
	 * ---galicia
	 * ----coruña
	 * ----pontevedra
	 * --inglaterra
	 * --portugal
	 * -asia
	 */
	private Tree<String, MyBean> createTreeEarth()
	{
		int p=1;
		Tree<String, MyBean> earth=MyTree.createRoot("tierra");

		Tree<String, MyBean> america=earth.addChild("américa", new MyBean(p++, "continente americano"));
		Tree<String, MyBean> europe=earth.addChild("europa", new MyBean(p++, "continente europeo"));
		Tree<String, MyBean> asia=earth.addChild("asia", new MyBean(p++, "continente asiático"));
		Tree<String, MyBean> spain=europe.addChild("españa", new MyBean(p++, "país ezpaña"));
		Tree<String, MyBean> madrid=spain.addChild("madrid", new MyBean(p++, "región madrid"));
		Tree<String, MyBean> castillaMancha=spain.addChild("castilla-la-mancha", new MyBean(p++, "región castilla la mancha"));
		Tree<String, MyBean> toledo=castillaMancha.addChild("toledo", new MyBean(p++, "provincia toledo"));
		Tree<String, MyBean> cuenca=castillaMancha.addChild("cuenca", new MyBean(p++, "provincia cuenca"));
		Tree<String, MyBean> galicia=spain.addChild("galicia", new MyBean(p++, "región galicia"));
		Tree<String, MyBean> uk=europe.addChild("reino-unido", new MyBean(p++, "país reino unido"));
		Tree<String, MyBean> portugal=europe.addChild("portugal", new MyBean(p++, "país portugal"));
		return earth;
	}

	private Tree<String, MyBean> createTreeMadridCapital()
	{
		int p=1;
		Tree<String, MyBean> capital=MyTree.createRoot("madrid-capital");

		Tree<String, MyBean> lavapies=capital.addChild("lavapiés", new MyBean(p++, "barrio de lavapiés"));
		Tree<String, MyBean> chamberi=capital.addChild("chamberi", new MyBean(p++, "barrio de chamberí"));
		Tree<String, MyBean> almagro=chamberi.addChild("almagro", new MyBean(p++, "calle almagro"));
		Tree<String, MyBean> campos=chamberi.addChild("martínez-campos", new MyBean(p++, "calle general martínez campos"));
		Tree<String, MyBean> arguelles=capital.addChild("argüelles", new MyBean(p++, "barrio de argüelles"));
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
		assertEquals(".tierra.américa: MyBean [id=1, name=continente americano]", lines.get(p++));
		assertEquals(".tierra.asia: MyBean [id=3, name=continente asiático]", lines.get(p++));
		assertEquals(".tierra.europa: MyBean [id=2, name=continente europeo]", lines.get(p++));
		assertEquals(".tierra.europa.reino-unido: MyBean [id=10, name=país reino unido]", lines.get(p++));
		assertEquals(".tierra.europa.españa: MyBean [id=4, name=país ezpaña]", lines.get(p++));
		assertEquals(".tierra.europa.españa.galicia: MyBean [id=9, name=región galicia]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid: MyBean [id=5, name=región madrid]", lines.get(p++));
		assertEquals(".tierra.europa.españa.castilla-la-mancha: MyBean [id=6, name=región castilla la mancha]", lines.get(p++));
		assertEquals(".tierra.europa.españa.castilla-la-mancha.toledo: MyBean [id=7, name=provincia toledo]", lines.get(p++));
		assertEquals(".tierra.europa.españa.castilla-la-mancha.cuenca: MyBean [id=8, name=provincia cuenca]", lines.get(p++));
		assertEquals(".tierra.europa.portugal: MyBean [id=11, name=país portugal]", lines.get(p++));
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
		assertEquals(".europa.españa.castilla-la-mancha", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".américa", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.españa.galicia", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.españa.madrid", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.españa", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.españa.castilla-la-mancha.cuenca", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".asia", entryIterator.next().toString());

		assertTrue(entryIterator.hasNext());
		assertEquals(".europa.españa.castilla-la-mancha.toledo", entryIterator.next().toString());

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
		// assertTrue(rootMap.getEntries().contains(new StringPath(rootPath, "américa")));
		// StringPath pathPortugal=new StringPath(new StringPath(rootPath, "europa"), "portugal");
		// System.out.println("pathPortugal=" + pathPortugal);
		// System.out.println("rootEntries.getEntries()=" + rootMap.getEntries());
		// System.out.println("rootEntries.getEntries().size()=" + rootMap.getEntries().size());
		// assertTrue(rootMap.getEntries().contains(pathPortugal));
		// assertTrue(rootMap.getEntries().contains(new StringPath(new StringPath(rootPath, "europa"), "españa")));
		// assertFalse(rootMap.getEntries().contains(new StringPath(new StringPath(rootPath, "europa"), "grecia")));
		// assertTrue(rootMap.getEntries().contains(new StringPath(new StringPath(new StringPath(rootPath, "europa"), "españa"), "galicia")));
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
		Tree<String, MyBean> madrid=earth.getTree(StringPath.parse("europa.españa.madrid", '.'));
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
		assertEquals(".tierra.américa: MyBean [id=1, name=continente americano]", lines.get(p++));
		assertEquals(".tierra.asia: MyBean [id=3, name=continente asiático]", lines.get(p++));
		assertEquals(".tierra.europa: MyBean [id=2, name=continente europeo]", lines.get(p++));
		assertEquals(".tierra.europa.reino-unido: MyBean [id=10, name=país reino unido]", lines.get(p++));
		assertEquals(".tierra.europa.españa: MyBean [id=4, name=país ezpaña]", lines.get(p++));
		assertEquals(".tierra.europa.españa.galicia: MyBean [id=9, name=región galicia]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid: MyBean [id=5, name=región madrid]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid.madrid-capital: MyBean [id=12, name=madrid capital]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid.madrid-capital.lavapiés: MyBean [id=13, name=barrio de lavapiés]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid.madrid-capital.chamberí: MyBean [id=14, name=barrio de chamberí]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid.madrid-capital.chamberí.almagro: MyBean [id=15, name=calle almagro]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid.madrid-capital.chamberí.martínez-campos: MyBean [id=16, name=calle general martínez campos]", lines.get(p++));
		assertEquals(".tierra.europa.españa.madrid.madrid-capital.argüelles: MyBean [id=17, name=barrio de argüelles]", lines.get(p++));
		assertEquals(".tierra.europa.españa.castilla-la-mancha: MyBean [id=6, name=región castilla la mancha]", lines.get(p++));
		assertEquals(".tierra.europa.españa.castilla-la-mancha.toledo: MyBean [id=7, name=provincia toledo]", lines.get(p++));
		assertEquals(".tierra.europa.españa.castilla-la-mancha.cuenca: MyBean [id=8, name=provincia cuenca]", lines.get(p++));
		assertEquals(".tierra.europa.portugal: MyBean [id=11, name=país portugal]", lines.get(p++));
	}
}
