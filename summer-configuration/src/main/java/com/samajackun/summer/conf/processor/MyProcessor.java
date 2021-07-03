package com.samajackun.summer.conf.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.StringTokenizer;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Opcode;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic.Kind;

import com.samajackun.summer.conf.annotations.Property;

@SupportedAnnotationTypes("demo.annotations.Property")
@SupportedOptions({ "demo.apt.boot.classpath", "demo.apt.edit.classpath" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class MyProcessor extends AbstractProcessor
{
	private ClassPool pool;

	private final Collection<CtClass> modifiedClasses=new ArrayList<CtClass>();

	private String classesDir;

	private ClassPool getPool()
		throws NotFoundException
	{
		if (this.pool == null)
		{
			this.pool=createPool();
		}
		return this.pool;
	}

	private ClassPool createPool()
		throws NotFoundException
	{
		ClassPool.doPruning=false;
		ClassPool pool=ClassPool.getDefault();
		String s=getOption("demo.apt.boot.classpath");
		if (s != null)
		{
			for (StringTokenizer stk=new StringTokenizer(s, File.pathSeparator); stk.hasMoreTokens();)
			{
				String path=stk.nextToken();
				pool.appendClassPath(path);
			}
		}
		pool.appendClassPath(new ClassClassPath(getClass()));
		//
		// this.classesDir=getOption("demo.apt.edit.classpath");
		// if (this.classesDir != null)
		// {
		// pool.appendClassPath(this.classesDir);
		// }
		return pool;
	}

	private String getOption(String name)
	{
		String value=super.processingEnv.getOptions().get(name);
		return value;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
	{
		this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor by SKN LTD 2014");
		// this.processingEnv.getMessager().printMessage(Kind.NOTE, "classpath=" + System.getProperties());
		for (TypeElement typeElement : annotations)
		{
			Set<? extends Element> annotatedElements=roundEnv.getElementsAnnotatedWith(typeElement);
			this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor.process: 10: typeElement=" + typeElement.getSimpleName());
			processElements(annotatedElements);
		}
		if (roundEnv.processingOver())
		{
			endProcess();
		}
		return false;
		// ClassPool pool=new ClassPool(true);
		// TypeDeclaration typeDeclaration=(TypeDeclaration)declaration;
		// String declarationName=typeDeclaration.getQualifiedName();
		// CtClass cc=pool.get(declarationName);
	}

	private void endProcess()
	{
		for (CtClass ctClass : this.modifiedClasses)
		{
			try
			{
				ctClass.writeFile(this.classesDir); // update the class file
			}
			catch (CannotCompileException e)
			{
				e.printStackTrace(System.err);
				super.processingEnv.getMessager().printMessage(Kind.ERROR, e.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace(System.err);
				super.processingEnv.getMessager().printMessage(Kind.ERROR, e.toString());
			}
		}
	}

	private void processElements(Set<? extends Element> annotatedElements)
	{
		for (Element element : annotatedElements)
		{
			this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor.processElements: 10: element=" + element.getSimpleName());
			processElement(element);
		}
	}

	private String getClassName(Element classElement)
	{
		return ((TypeElement)classElement).getQualifiedName().toString();
		// String s=classElement.getSimpleName().toString();
		// if (classElement.getEnclosingElement() != null)
		// {
		// s=getClassName(classElement.getEnclosingElement()) + "." + s;
		// }
		// return s;
	}

	private void processElement(Element element)
	{
		this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor.processElement: 00: element(" + element.getClass().getName() + ")=" + element.getSimpleName());
		if (element instanceof VariableElement)
		{
			VariableElement variableElement=(VariableElement)element;
			this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor.processElement: 10: variableElement=" + variableElement.getSimpleName());
			Property property=variableElement.getAnnotation(Property.class);
			Element classElement=element.getEnclosingElement();
			String className=getClassName(classElement);
			CtClass ctClass;
			try
			{
				this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor.processElement: 19: className=" + className);
				ctClass=getPool().get(className);
				this.processingEnv.getMessager().printMessage(Kind.NOTE, "MyProcessor.processElement: 20: ¡Qué bien! Hemos cargado " + className);
				CtField ctField=ctClass.getField(variableElement.getSimpleName().toString());
				if (isSupportedType(ctField.getType()))
				{
					ctClass.removeField(ctField);
					for (CtConstructor constructor : ctClass.getConstructors())
					{
						removeVariableInitialization(variableElement.getSimpleName(), constructor);
					}
					// if (ctClass.getConstructors().length > 0)
					// {
					// System.out.println("******** Constructor from class " + ctClass.getName());
					// System.out.println("******** hasEmptyConstructor=" + hasEmptyConstructor(ctClass));
					//
					// CtConstructor constructor=ctClass.getConstructors()[0];
					// // CodeAttribute ca=constructor.getMethodInfo().getCodeAttribute();
					// // byte[] codeAttribute=ca.getCode();
					// // for (int i=0; i < ca.getCodeLength(); i++)
					// // {
					// // System.out.println("codeAttribute[" + i + "]=" + codeAttribute[i] + " (" + ByteCodeHelper.CODES.get(codeAttribute[i]) + ")");
					// // }
					// CodeIterator codeIterator=constructor.getMethodInfo().getCodeAttribute().iterator();
					// try
					// {
					// while (codeIterator.hasNext())
					// {
					// int index=codeIterator.next();
					// int code=codeIterator.byteAt(index);
					// String s=ByteCodeHelper.CODES.get(code);
					// if (code == Opcode.PUTFIELD || code == Opcode.PUTSTATIC)
					// {
					// s+=" " + codeIterator.byteAt(index + 1);
					// s+=" " + codeIterator.byteAt(index + 2);
					// }
					// else
					// {
					// s+=" ...";
					// }
					// System.out.println("codeIterator.next: " + s);
					// }
					// }
					// catch (BadBytecode e)
					// {
					// e.printStackTrace();
					// }
					// }
					//
					// byte[] constantValue=ctField.getAttribute("ConstantValue");
					// System.out.println("***" + ctField.getName() + ".getConstantValue()=" + ctField.getFieldInfo2().getConstantValue() + ", constantValue=" + (constantValue == null
					// ? null
					// : new String(constantValue)));
					// if (ctField.getFieldInfo2().getAttributes().size() > 0)
					// {
					// AttributeInfo attr=(AttributeInfo)ctField.getFieldInfo2().getAttributes().get(0);
					// System.out.print("***" + ctField.getName() + ": attr=" + attr.getName() + ", length=" + attr.length() + ", ");
					// for (byte b : attr.get())
					// {
					// System.out.print((int)b + ",");
					// }
					// System.out.println();
					// }
					// System.out.println("***" + ctField.getName() + ".getFieldInfo2().getAttributes()=" + ctField.getFieldInfo2().getAttributes());
					// System.out.println("***" + ctField.getName() + ".getFieldInfo2().getDescriptor()=" + ctField.getFieldInfo2().getDescriptor());
					String formatParameter=isFormateableType(ctField.getType()) && !property.format().isEmpty()
						? ",\"" + property.format() + "\""
						: "";
					String fieldSerial=getMethodForType(ctField.getType()) + "(\"" + property.name() + "\"" + formatParameter + ");";
					ctClass.addField(ctField, fieldSerial);
				}
				this.modifiedClasses.add(ctClass);
			}
			catch (NotFoundException e)
			{
				e.printStackTrace();
				super.processingEnv.getMessager().printMessage(Kind.ERROR, e.toString(), classElement);
			}
			catch (CannotCompileException e)
			{
				e.printStackTrace();
				super.processingEnv.getMessager().printMessage(Kind.ERROR, e.toString(), classElement);
			}
		}
	}

	private void removeVariableInitialization(Name varName, CtConstructor constructor)
	{
		ConstPool constPool=constructor.getDeclaringClass().getClassFile().getConstPool();
		CodeIterator codeIterator=constructor.getMethodInfo().getCodeAttribute().iterator();
		try
		{
			int posAload0=-1;
			while (codeIterator.hasNext())
			{
				int index=codeIterator.next();
				int code=codeIterator.byteAt(index);
				String s=ByteCodeHelper.CODES.get(code);
				if (code == Opcode.ALOAD_0)
				{
					posAload0=index;
				}
				else if (code == Opcode.PUTFIELD /* || code == Opcode.PUTSTATIC */)
				{
					int b1=codeIterator.byteAt(index + 1);
					int b2=codeIterator.byteAt(index + 2);
					s+=" " + b1;
					s+=" " + b2;
					int b=b2 + 256 * b1;
					String fieldName=constPool.getFieldrefName(b);
					s+=" b=" + b + ", (field=" + fieldName + ")";
					if (fieldName.equals(varName))
					{
						// Hay que reemplazar por NOPs todo el bytecode desde el último ALOAD_0
						for (int j=posAload0; j <= index; j++)
						{
							constructor.getMethodInfo().getCodeAttribute().get()[j]=Opcode.NOP;
						}
					}
				}
				else if (code == Opcode.BIPUSH)
				{
					s+=" " + codeIterator.byteAt(index + 1);
				}
				else if (code == Opcode.LDC)
				{
					s+=" " + codeIterator.byteAt(index + 1);
					int n=codeIterator.byteAt(index + 1);
					Object ref=constPool.getLdcValue(n);
					s+=" (ref=" + ref + ")";
				}
				else
				{
					s+=" ...";
				}
				System.out.println(index + "\tcodeIterator.next: " + s);
			}
		}
		catch (BadBytecode e)
		{
			e.printStackTrace();
		}
	}

	// private static boolean hasEmptyConstructor(CtClass cl)
	// {
	// boolean x=false;
	// CtConstructor[] constructors=cl.getConstructors();
	// for (int i=0; !x && i < constructors.length; i++)
	// {
	// CtConstructor constructor=constructors[i];
	// try
	// {
	// x=(constructor.getParameterTypes().length == 0 && (constructor.getModifiers() & Modifier.PUBLIC) != 0);
	// }
	// catch (NotFoundException e)
	// {
	// e.printStackTrace();
	// }
	// }
	// return x;
	// }

	private String getMethodForType(CtClass type)
	{
		String typeName=type.getSimpleName();
		typeName=Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
		typeName="demo.utils.ParameterUtils.getSystemPropertyAs" + typeName;
		return typeName;
	}

	private boolean isSupportedType(CtClass type)
	{
		String typeName=type.getName();
		boolean x=type.isPrimitive() || "java.lang.String".equals(typeName) || "java.util.Date".equals(typeName) || "java.io.File".equals(typeName);
		return x;
	}

	private boolean isFormateableType(CtClass type)
	{
		String typeName=type.getName();
		boolean x="java.util.Date".equals(typeName);
		return x;
	}
}
