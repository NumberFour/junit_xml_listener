import sbt._
import Keys._


object JXLBuild extends Build {

  lazy val junitXmlListener = Project(
      id = "junit_xml_listener"
    , base = file(".")
    , settings = standardSettings ++ Seq(
          version := "0.3.2-SNAPSHOT"
    )
  )

  lazy val standardSettings = Defaults.defaultSettings ++ Seq(
      organization := "eu.henkelmann"
    , sbtPlugin := true
    , publishSetting
    , credentialsSetting
    , scalaVersion := "2.10.3"
    , scalacOptions  ++= Seq("-deprecation", "-unchecked", "-feature")
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
