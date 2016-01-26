# exposable

Exposable is a an annotation processor designed to generate at compile time the metadata - field names - for classes annotated with <b>@Exposable</b>.
It's something similar to Hibernate metamodel generator, but much lighter. 

## using exposable
If you use maven, please add the dependency in your project pom.xml.

```xml
<dependency>
	<groupId>org.grozandrei.exposable</groupId>
	<artifactId>exposable-annotation</artifactId>
	<version>0.0.2</version>
</dependency>
```

Then, you just annotate the classes you want to have the metamodel generated at compile time with <b>@Exposable</b> annotation.

For maven, the following plugins must be used:

```xml
<build>
		<plugins>
		...
			<plugin>
				<groupId>org.bsc.maven</groupId>
				<artifactId>maven-processor-plugin</artifactId>
				<version>2.2.4</version>
				<executions>
					<execution>
						<id>process</id>
						<phase>generate-sources</phase>
						<goals>
							<goal>process</goal>
						</goals>
						<configuration>
							<outputDirectory>target/generated-sources/exposable</outputDirectory>
							<processors>
								<processor>org.grozandrei.exposable.processor.ExposableProcessor</processor>
							</processors>
						</configuration>
					</execution>
				</executions>
				<dependencies>
					<dependency>
						<groupId>org.grozandrei.exposable</groupId>
						<artifactId>exposable-processor</artifactId>
						<version>0.0.1</version>
					</dependency>
				</dependencies>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>build-helper-maven-plugin</artifactId>
				<version>1.10</version>
				<executions>
					<execution>
						<phase>process-sources</phase>
						<goals>
							<goal>add-source</goal>
						</goals>
						<configuration>
							<sources>
								<source>target/generated-sources/exposable</source>
							</sources>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```

Then run the usual <code>mvn install</code> build command on your project and the processor will generate metadata information for exposable classes.
