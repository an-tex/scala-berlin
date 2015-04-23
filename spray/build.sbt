import com.typesafe.sbt.SbtNativePackager.autoImport._
import com.typesafe.sbt.packager.archetypes.ServerLoader

// Mandatory native Packaging settings
enablePlugins(JavaServerAppPackaging)

maintainer := "Andreas Teichrib"

serverLoading in Debian := ServerLoader.SystemV

// optional
//debianPackageDependencies ++= Seq("openjdk-7-jre-headless")
//daemonUser in Linux := "andreas"
//daemonGroup in Linux := "andreas"
//javaOptions in Universal ++= Seq( "-J-Xmx64m", "-J-Xms64m", "-Dproperty=true")
//bashScriptExtraDefines += """addJava "-Dconfig.file=${app_home}/../conf/production.conf""""

mainClass in Compile := Some("scala.berlin.spray.Main")
val deployTask = TaskKey[Unit]("deploy", "Installs debian package on remote location ")
deployTask <<= (packageBin in Debian) map { (asm) =>
  println(s"deploying with ansible...")
  "ansible-playbook ansible/deploy.yml -i ansible/inventory".!
}



// project settings

name := "spray"
version := "1.1-SNAPSHOT"

scalaVersion := "2.11.6"
val sprayVersion = "1.3.3"
libraryDependencies ++= Seq(
  "io.spray" %% "spray-routing" % sprayVersion,
  "io.spray" %% "spray-io" % sprayVersion,
  "io.spray" %% "spray-can" % sprayVersion,
  "com.typesafe.akka" %% "akka-actor" % "2.3.9"

)
resolvers += "spray repo" at "http://repo.spray.io"

