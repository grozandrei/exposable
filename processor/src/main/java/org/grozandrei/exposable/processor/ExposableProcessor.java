/**
 *
 */
package org.grozandrei.exposable.processor;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import org.grozandrei.exposable.annotation.Exposable;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Andrei Groza
 *
 */
@SupportedAnnotationTypes("org.grozandrei.exposable.annotation.Exposable")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
// @SupportedOptions(value = { "" })
public class ExposableProcessor extends AbstractProcessor {

	@Override
	public boolean process(
			final Set<? extends TypeElement> annotations,
			final RoundEnvironment roundEnv) {
		System.out.println("DONEEEEEEEEEEEEEEEEEEE");
		for (final Element elem : roundEnv.getElementsAnnotatedWith(Exposable.class)) {

			System.out.println("package=" + elem.getEnclosingElement().toString());
			final TypeSpec.Builder classBuilder = TypeSpec.classBuilder(elem.getSimpleName() + "_");
			for (final Element subElement : elem.getEnclosedElements()) {
				System.out.println(subElement);
				if (subElement.getKind() == ElementKind.FIELD) {
					final FieldSpec fieldSpec = FieldSpec.builder(String.class, "FIELD_" + subElement.getSimpleName().toString().toUpperCase(), Modifier.PUBLIC,
							Modifier.STATIC, Modifier.FINAL).initializer("$S", subElement.getSimpleName().toString()).build();
					classBuilder.addField(fieldSpec);
				}
			}
			final TypeSpec typespec = classBuilder.addModifiers(Modifier.PUBLIC, Modifier.FINAL).build();
			final JavaFile javaFile = JavaFile.builder(elem.getEnclosingElement().toString(), typespec).build();

			try {
				javaFile.writeTo(new File("target/generated-sources/annotations"));
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// if (elem instanceof TypeElement) {
		// final TypeElement typeElement = TypeElement.class.cast(elem);
		//
		// System.out.println(typeElement.getQualifiedName());
		//
		// final Exposable complexity = elem.getAnnotation(Exposable.class);
		// final String message = "annotation found in " + elem.getSimpleName();
		// System.out.println(elem.getSimpleName());
		// processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
		// //
		// // final TypeElement typeElement = (TypeElement) elem;
		// // final String className = pluralize(typeElement.getSimpleName().toString());
		// // final String classQName = pluralize(typeElement.getQualifiedName().toString());
		// // final String packageName = ((PackageElement) typeElement.getEnclosingElement()).getQualifiedName()
		// // .toString();
		// //
		// // final PrintWriter w = new PrintWriter(
		// // processingEnv.getFiler().createSourceFile(classQName, element).openWriter());
		// //
		// // w.print("package " + packageName + ";");
		// // w.print("public class " + name + " { }");
		// // w.close();
		//
		// // final Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(elem.getSimpleName().toString());
		// try {
		// final Class<?> clazz = Class.forName(typeElement.getQualifiedName().toString());
		//
		// final TypeSpec.Builder classBuilder = TypeSpec.classBuilder(clazz.getSimpleName() + "_");
		// for (final Field field : clazz.getClass().getDeclaredFields()) {
		// final FieldSpec fieldSpec = FieldSpec
		// .builder(String.class, "FIELD_" + field.getName().toUpperCase(), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
		// .initializer("$S", field.getName()).build();
		// classBuilder.addField(fieldSpec);
		// }
		// final TypeSpec typespec = classBuilder.build();
		// final JavaFile javaFile = JavaFile.builder(elem.getClass().getPackage().getName(), typespec).build();
		//
		// try {
		// javaFile.writeTo(System.out);
		// } catch (final IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// } catch (final ClassNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }

		// }

		return true; // no further processing of this annotation type

	}
}
