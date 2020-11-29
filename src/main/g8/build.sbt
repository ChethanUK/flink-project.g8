ThisBuild / resolvers ++= Seq(
    "Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/snapshots/",
    Resolver.mavenLocal,
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Typesafe" at "https://repo.typesafe.com/typesafe/releases/",
)

name := "$name$"

version := "$version$"

val org = "$organization$"
organization := org

ThisBuild / scalaVersion := "$scala_version$"

val flinkVersion = "$flink_version$"
val statefunVersion = "2.2.1" 

dependencyUpdatesFilter -= moduleFilter(organization = org)

// In order to run your job in a distinct JVM
// fork in run := true

val flinkDependencies = Seq(
  // Base
  "org.apache.flink" %% "flink-scala" % flinkVersion % "provided",
  // "org.apache.flink" % "flink-core" % flinkVersion,

  // Stream
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided",
  // Table
  ("org.apache.flink" % "flink-table" % flinkVersion % "provided").pomOnly(),
  "org.apache.flink" %% "flink-table-planner" % flinkVersion % "provided",
  "org.apache.flink" % "flink-table-common" % flinkVersion % "provided",
  // "org.apache.flink" %% "flink-table-api-scala-bridge" % flinkVersion,
  // "org.apache.flink" % "flink-sql-parser" % flinkVersion,

  // Metrics
  "org.apache.flink" % "flink-metrics-core" % flinkVersion % "provided",
  // Clients or Connectors
  "org.apache.flink" %% "flink-clients" % flinkVersion % "provided",
  // "org.apache.flink" %% "flink-connector-kafka" % flinkVersion,
  // "org.apache.flink" % "flink-connector-files" % flinkVersion,
  // "org.apache.flink" %% "flink-connector-elasticsearch7" % flinkVersion,
  // "org.apache.flink" %% "flink-connector-jdbc" % flinkVersion,
  // "org.apache.flink" %% "flink-connector-hive" % flinkVersion % "provided",

  // SQL
  // "org.apache.flink" % "flink-sql-parser-hive" % flinkVersion,
  // "org.apache.flink" % "flink-sql-avro" % flinkVersion,
  // "org.apache.flink" % "flink-sql-avro-confluent-registry" % flinkVersion,
  // "org.apache.flink" %% "flink-sql-connector-elasticsearch7" % flinkVersion,

  // Format
  // "org.apache.flink" % "flink-avro" % flinkVersion,
  // "org.apache.flink" % "flink-compress" % flinkVersion,

  // Stateful
  // "org.apache.flink" % "statefun-flink-datastream" % "2.2.1",
  // "org.apache.flink" % "statefun-testutil" % "2.2.1".

  // Spillable State Backend -
  // "org.apache.flink" %% "flink-statebackend-heap-spillable" % flinkVersion,

  // External
  "org.apache.flink" %% "flink-kubernetes" % flinkVersion,
  // "org.apache.flink" % "flink-external-resources" % flinkVersion pomOnly(),
  // "org.apache.flink" % "flink-external-resource-gpu" % flinkVersion,

  // Logging
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % Test,

  // Testing
  "org.scalatest" %% "scalatest" % "3.2.3" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.3" % Test,
  "org.apache.flink" % "flink-tests" % flinkVersion % Test,
  "org.apache.flink" %% "flink-test-utils" % flinkVersion % Test,
  // "org.apache.flink" %% "flink-statebackend-rocksdb" % flinkVersion % Test,
  // "org.apache.flink" %% "flink-runtime" % flinkVersion % Test,
  // "org.apache.flink" % "flink-connector-test-utils" % flinkVersion,
  // "org.apache.flink" % "flink-test-utils-junit" % flinkVersion % Test,
)

// jacocoReportSettings := JacocoReportSettings()
//   .withThresholds(
//     JacocoThresholds(
//       instruction = 80,
//       method = 100,
//       branch = 100,
//       complexity = 100,
//       line = 90,
//       clazz = 100)
//   )  
// enablePlugins(JacocoItPlugin)  

lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= flinkDependencies
  )

assembly / mainClass := Some("$organization$.Job")

// make run command include the provided dependencies
Compile / run  := Defaults.runTask(Compile / fullClasspath,
                                   Compile / run / mainClass,
                                   Compile / run / runner
                                  ).evaluated

// stays inside the sbt console when we press "ctrl-c" while a Flink programme executes with "run" or "runMain"
Compile / run / fork := true
Global / cancelable := true

// exclude Scala library from assembly
assembly / assemblyOption  := (assembly / assemblyOption).value.copy(includeScala = false)
