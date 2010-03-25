import sbt.{Logger, BasicDependencyProject}
import xml.{XML, Node}

class SbtProjectDefinitionIdeaModuleDescriptor(val project: BasicDependencyProject, val log: Logger) extends SaveableXml with ProjectPaths {
  val path = String.format("%s/project_definition.iml", projectPath)

  def content: Node = {
    <module type="JAVA_MODULE" version="4">
      <component name="FacetManager">
        <facet type="Scala" name="Scala">
          <configuration>
            <option name="takeFromSettings" value="true" />
            <option name="myScalaCompilerJarPath" value={String.format("$MODULE_DIR$/%s", relativePath(defScalaCompilerJar))} />
            <option name="myScalaSdkJarPath" value={String.format("$MODULE_DIR$/%s", relativePath(defScalaLibraryJar))} />
          </configuration>
        </facet>
      </component>
      <component name="NewModuleRootManager" inherit-compiler-output="true">
        <exclude-output />
        <content url="file://$MODULE_DIR$">
          <sourceFolder url="file://$MODULE_DIR$/project/build" isTestSource="false" />
          <sourceFolder url="file://$MODULE_DIR$/project/build/src" isTestSource="false" />
          <excludeFolder url="file://$MODULE_DIR$/target" />
          <excludeFolder url="file://$MODULE_DIR$/project/build/target" />
        </content>
        <orderEntry type="inheritedJdk" />
        <orderEntry type="sourceFolder" forTests="false" />
        <orderEntry type="library" name="defScala" level="project" />
        {
          <orderEntry type="module-library">
            <library>
              <CLASSES>
                <root url={String.format("file://$MODULE_DIR$/project/boot/scala-%s/org.scala-tools.sbt", project.defScalaVersion.value)} />
              </CLASSES>
              <JAVADOC />
              <SOURCES />
              <jarDirectory url={String.format("file://$MODULE_DIR$/project/boot/scala-%s/org.scala-tools.sbt", project.defScalaVersion.value)} recursive="true" />
            </library>
          </orderEntry>
          <orderEntry type="module-library">
            <library>
              <CLASSES>
                <root url={String.format("file://$MODULE_DIR$/project/plugins/target/scala_%s/plugin-classes", project.defScalaVersion.value)} />
              </CLASSES>
              <JAVADOC />
              <SOURCES />
            </library>
          </orderEntry>
        }
      </component>
    </module>
  }
}