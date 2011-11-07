Requirements
------------

* [SBT 0.10+](https://github.com/harrah/xsbt/wiki)

Installation
------------

The plugin should be published to our nexus repository. To use it, add the following line to the plugins.sbt:

    addSbtPlugin("eu.henkelmann" % "junit_xml_listener" % "0.3.1-SNAPSHOT")

This will add the dependency to the plugin. The next step is to configure your build to output the XML. The following will output the XML in target/test-reports:

    testListeners <<= target.map(t => Seq(new eu.henkelmann.sbt.JUnitXmlTestsListener(t.getAbsolutePath)))

