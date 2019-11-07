import sbt.Keys._
import sbt._

// *****************************************************************************
// Projects
// *****************************************************************************

lazy val scala212 = "2.12.10"
lazy val scala213 = "2.13.1"
lazy val supportedScalaVersions = List(scala212, scala213)

lazy val `scala-iso` =
  project
    .in(file(".")).withId("scala-iso")
    //    .enablePlugins(AutomateHeaderPlugin, GitVersioning)
    .settings(settings)
    //    .settings(crossScalaVersions := List(scala213, scala212))
    .settings(
      libraryDependencies ++= Seq(
        library.scalaCheck % Test,
        library.scalaTest % Test
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {

    object Version {
      val scalaCheck = "1.14.2"
      val scalaTest = "3.0.8"
    }

    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck
    val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************        |

lazy val settings =
  commonSettings
//++
//  gitSettings ++
//  headerSettings

lazy val commonSettings =
  Seq(
    organization := "com.robkler",
    organizationName := "robkler",
    startYear := Some(2017),
    licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
    version := "0.2.0",
    scalaVersion := scala213,
    crossScalaVersions := Seq(scala212, scala213),
    crossVersion := CrossVersion.binary,
//    publishMavenStyle := true,
//    pomIncludeRepository := { _ => false },
//    releaseCrossBuild := true,
//    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
//    publishMavenStyle := true,
//    publishArtifact in Test := false,
//    pomExtra := {
//      <url>https://github.com/vitorsvieira/scala-iso</url>
//        <licenses>
//          <license>
//            <name>Apache 2</name>
//            <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
//          </license>
//        </licenses>
//        <scm>
//          <url>git@github.com:robkler/scala-iso.git</url>
//          <connection>scm:git:git@github.com:vitorsvieira/scala-iso.git</connection>
//        </scm>
//        <developers>
//          <developer>
//            <id>robkler</id>
//            <name>Rob Kler</name>
//            <url>https://github.com/robkler</url>
//          </developer>
//        </developers>
//    }

    //    mappings.in(Compile, packageBin) +=
    //      baseDirectory.in(ThisBuild).value / "LICENSE" -> "LICENSE",
    //    scalacOptions ++= Seq(
    //      "-unchecked",
    //      "-deprecation",
    //      "-encoding", "utf8",
    //      "-feature",
    //      "-explaintypes",
    //      "-target:jvm-1.8",
    //      "-language:_",
    //      "-Ydelambdafy:method",
    //      "-Xcheckinit",
    ////      "-Xfuture",
    //      "-Xlint",
    //      "-Xlint:-nullary-unit",
    //      "-Ywarn-unused",
    ////      "-Ywarn-unused-import",
    //      "-Ywarn-dead-code",
    //      "-Ywarn-value-discard",
    //      "-Xfatal-warnings"
    //    )
    //    javacOptions ++= Seq(
    //      "-source", "1.8",
    //      "-target", "1.8"
    //    ),
    //    unmanagedSourceDirectories.in(Compile) :=
    //      Seq(scalaSource.in(Compile).value),
    //    unmanagedSourceDirectories.in(Test) :=
    //      Seq(scalaSource.in(Test).value),
    //    SbtScalariform.autoImport.scalariformPreferences := SbtScalariform.autoImport.scalariformPreferences.value
    //      .setPreference(AlignSingleLineCaseStatements, true)
    //      .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
    //      .setPreference(DoubleIndentClassDeclaration, true)
    //      .setPreference(RewriteArrowSymbols, true)
    //      .setPreference(AlignParameters, true)
    //      .setPreference(AlignArguments, true)
    //      .setPreference(DoubleIndentClassDeclaration, true)
    //      .setPreference(DanglingCloseParenthesis, Preserve),
    //    wartremoverWarnings ++= Warts.unsafe
  )

//lazy val gitSettings =
//  Seq(
//    git.useGitDescribe := true
//  )
//
//
//lazy val dontPublishSettings = Seq(
//  //publishSigned := (),
//  publish := (),
//  publishLocal := (),
//  publishArtifact := false
//)
//
//lazy val publishSettings = Seq(
//  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
//  publishTo <<= version.apply {
//    v =>
//    val nexus = "https://oss.sonatype.org/"
//    if (v.trim.endsWith("SNAPSHOT")) {
//      Some("snapshots" at nexus + "content/repositories/snapshots")
//    } else {
//      Some("releases" at nexus + "service/local/staging/deploy/maven2")
//    }
//  },
//
//  externalResolvers <<= resolvers map { rs =>
//  Resolver.withDefaultResolvers(rs, mavenCentral = true)
//  }
////  ,
//
//  // Release settings

//  ,
//
//  releaseProcess := Seq[ReleaseStep](
//    checkSnapshotDependencies,
//    inquireVersions,
//    runClean,
//    runTest,
//    setReleaseVersion,
//    commitReleaseVersion,
//    tagRelease,
//    publishArtifacts,
//    setNextVersion,
//    commitNextVersion,
//    pushChanges
//  )
//)
//
////get the tools.jar JDK dependency
//val JavaTools = List[Option[String]] (
//  // manual
//  sys.env.get("JDK_HOME"),
//  sys.env.get("JAVA_HOME"),
//  // osx
//  try Some("/usr/libexec/java_home".!!.trim)
//  catch {
//    case _: Throwable => None
//  },
//  // fallback
//  sys.props.get("java.home").map(new File(_).getParent),
//  sys.props.get("java.home")
//).flatten.map { n =>
//new File(n + "/lib/tools.jar")
//}.find(_.exists).getOrElse (
//  throw new FileNotFoundException (
//    """Could not automatically find the JDK/lib/tools.jar.
//      |You must explicitly set JDK_HOME or JAVA_HOME.""".stripMargin
//  )
//)
parallelExecution in Test := false
//fork in Test := true
