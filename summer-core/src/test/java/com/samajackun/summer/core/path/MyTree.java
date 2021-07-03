package com.samajackun.summer.core.path;

class MyTree extends GenericTree<String, MyBean>
{
	public MyTree(Tree<String, MyBean> parent, Path<String> path, MyBean value)
	{
		super(parent, path, value);
	}

	public static MyTree createRoot(String key)
	{
		Path<String> path=new StringPath(key);
		return new MyTree(null, path, null);
	}
}