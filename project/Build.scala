import sbt._
import Keys._


object JXLBuild extends Build {

  lazy val junitXmlListener = Project(
      id = "junit_xml_listener"
    , base = file(".")
    , settings = standardSettings ++ Seq(
          version := "0.3.1-SNAPSHOT"
    )
  )

  lazy val standardSettings = Defaults.defaultSettings ++ Seq(
      organization := "eu.henkelmann"
    , scalaVersion := "2.9.2"
    , sbtPlugin := true

    , publishSetting
    , credentialsSetting
    , scalacOptions  ++= Seq("-deprecation", "-unchecked")
  )

  lazy val publishSetting = publishTo <<= (version) {
    version: String =>
      if(version.trim.endsWith("SNAPSHOT"))
        Some(n4Snapshots)
      else
        Some(n4Releases)
  }

  lazy val credentialsSetting = credentials += {
    Credentials(Path.userHome / ".ivy2" / ".n4-credentials")
  }

  val nexusServer = "repository"
  val n4Snapshots = "snapshots" at "http://" + nexusServer + "/nexus/content/repositories/snapshots"
  val n4Releases = "releases" at "http://" + nexusServer + "/nexus/content/repositories/releases"
}
