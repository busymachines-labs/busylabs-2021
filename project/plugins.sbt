/** The best thing since sliced bread.
  *
  * https://github.com/scalameta/scalafmt
  */
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2") //https://github.com/scalameta/sbt-scalafmt/releases

/** adds the sbt task headerCreateAll that adds license headers to files
  * https://github.com/sbt/sbt-header/releases
  */
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.6.0")

/** https://github.com/sbt/sbt-native-packager/releases
  */
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.8.0")
