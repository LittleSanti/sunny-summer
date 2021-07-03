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

@SupportedAnnotationTypes({ "com.samajackun.summer.conf.annotations.PropertiesSource", "com.samajackun.summer.conf.annotations.Property" })
@SupportedOptions({ "demo.apt.boot.classpath", "demo.apt.edit.classpath" })
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class PropertyProcessor extends AbstractProcessor
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
		String s1=getOption("demo.apt.boot.classpath");
		System.out.println("option demo.apt.boot.classpath=" + s1);
		String s2=getOption("demo.apt.edit.classpath");
		System.out.println("option demo.apt.edit.classpath=" + s2);
		String s=s1;
		if (s2 != null)
		{
			s=s + File.pathSeparator + s2;
		}
		System.out.println("demo.apt.(boot+edit).classpath=" + s);
		if (s != null)
		{
			for (StringTokenizer stk=new StringTokenizer(s, File.pathSeparator); stk.hasMoreTokens();)
			{
				String path=stk.nextToken();
				pool.appendClassPath(path);
			}
		}
		pool.appendClassPath(new ClassClassPath(getClass()));

		this.classesDir=getOption("demo.apt.edit.classpath");
		if (this.classesDir != null)
		{
			pool.appendClassPath(this.classesDir);
		}
		return pool;
	}

	private String getOption(String name)
	{
		System.out.println("super.processingEnv.getOptions()=" + super.processingEnv.getOptions());
		String value=super.processingEnv.getOptions().get(name);
		return value;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
	{
		// if (!roundEnv.processingOver())
		{
			this.processingEnv.getMessager().printMessage(Kind.NOTE, "PropertyProcessor by SKN LTD 2014");
			// System.out.println("classpath=" + System.getProperties());
			for (TypeElement typeElement : annotations)
			{
				Set<? extends Element> annotatedElements=roundEnv.getElementsAnnotatedWith(typeElement);
				System.out.println("MyProcessor.process: 10: typeElement=" + typeElement.getSimpleName());
				processElements(annotatedElements);
			}
			if (roundEnv.processingOver())
			{
				endProcess();
			}
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
			System.out.println("MyProcessor.processElements: 10: element=" + element.getSimpleName());
			processElement(element);
		}
	}

	private String getClassName(Element classElement)
	{
		while (!(classElement instanceof TypeElement))
		{
			classElement=classElement.getEnclosingElement();
		}
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
		System.out.println("MyProcessor.processElement: 00: element(" + element.getClass().getName() + ")=" + element.getSimpleName());
		if (element instanceof VariableElement)
		{
			processVariable((VariableElement)element);
		}
	}

	private void processVariable(VariableElement variableElement)
	{
		System.out.println("MyProcessor.processVariable: 10: variableElement=" + variableElement.getSimpleName());
		Property property=variableElement.getAnnotation(Property.class);
		Element classElement=variableElement.getEnclosingElement();
		String className=getClassName(classElement);
		CtClass ctClass;
		try
		{
			System.out.println("MyProcessor.processVariable: 19: className=" + className);
			ctClass=getPool().get(className);
			System.out.println("MyProcessor.processVariable: 20: ¡Qué bien! Hemos cargado " + className);
			CtField ctField=ctClass.getField(variableElement.getSimpleName().toString());
			if (isSupportedType(ctField.getType()))
			{
				System.out.println("MyProcessor.processVariable: 21: ctField.getFieldInfo().getConstantValue()=" + ctField);
				System.out.println("MyProcessor.processVariable: 21: ctField.getFieldInfo().toString()=" + ctField.toString());
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
				String sourceName=property.name().isEmpty()
					? getFullName(variableElement)
					: property.name();
				String fieldSerial=getMethodForType(ctField.getType()) + "(\"" + sourceName + "\"" + formatParameter + ");";
				CtField fieldNew=new CtField(ctField.getType(), ctField.getName(), ctClass);
				fieldNew.setModifiers(ctField.getModifiers());
				ctClass.addField(fieldNew, fieldSerial);
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
		System.out.println("MyProcessor.processVariable: 99: variableElement=" + variableElement.getSimpleName());
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
					if (fieldName.equals(varName.toString()))
					{
						System.out.println(index + "\tHay que reemplazar por NOPs todo el bytecode desde el último ALOAD_0: posAload0=" + posAload0 + ", index=" + index);
						// Hay que reemplazar por NOPs todo el bytecode desde el último ALOAD_0
						for (int j=posAload0; j <= index; j++)
						{
							codeIterator.writeByte(Opcode.NOP, j);
							System.out.println(index + "\t*" + j + "=NOP");
							// constructor.getMethodInfo().getCodeAttribute().get()[j]=Opcode.NOP;
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
		typeName="com.samajackun.summer.conf.utils.ParameterUtils.getSystemPropertyAs" + typeName;
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

	private static String getFullName(VariableElement variableElement)
	{
		String s="";
		Element p=variableElement;
		while (p != null)
		{
			if (!s.isEmpty())
			{
				s="." + s;
			}
			s=p.getSimpleName() + s;
			p=p.getEnclosingElement();
		}
		return s;
	}
}
