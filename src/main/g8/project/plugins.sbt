resolvers += Resolver.bintrayIvyRepo("rtimush", "sbt-plugin-snapshots")

// Native packager
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.7.5")

addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.23")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")
// sbtfmt
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")
// Updates - check Maven and Ivy repositories for dependency updates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.1")

// Generating model classes For Presto
addSbtPlugin("org.xerial.sbt" % "sbt-sql-presto" % "0.13")
addSbtPlugin("org.xerial.sbt" % "sbt-sql" % "0.13")

// Code Coverage - https://www.scala-sbt.org/sbt-jacoco/
addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.3.0")

// Codacy Coverage
//addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "3.0.3")
// Enabling a super-fast development turnaround
// "io.spray" % "sbt-revolver" % "0.9.1"
// Scalaprops - Test
// addSbtPlugin("com.github.scalaprops" % "sbt-scalaprops" % "0.3.2")
// dependency resolver
// addSbtPlugin("io.get-coursier" % "sbt-coursier" % "2.0.0-RC6-8")
// SBT dependency resolver and publisher for Google Cloud Storage
// addSbtPlugin("com.lightbend" % "sbt-google-cloud-storage" % "0.0.10")
// Find classes or resources that could create conflicts
// addSbtPlugin("com.github.sbt" % "sbt-duplicates-finder" % "1.1.0")