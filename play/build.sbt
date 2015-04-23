import com.typesafe.sbt.SbtNativePackager.NativePackagerKeys._
import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.archetypes.ServerLoader

maintainer := "Andreas Teichrib <ping@antex.io>"
packageDescription in Debian := "Andreas Teichrib <ping@antex.io>"
serverLoading in Debian := ServerLoader.SystemV

val deployTask = TaskKey[Unit]("deploy", "Installs debian package on remote location ")
deployTask <<= (packageBin in Debian) map { (asm) =>
  println(s"deploying with ansible...")
  "ansible-playbook ansible/deploy.yml -i ansible/inventory".!
}

name := """play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)


