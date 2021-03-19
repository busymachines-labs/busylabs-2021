/*
 * Copyright 2021 BusyMachines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

ThisBuild / organization     := "com.busymachines"
ThisBuild / organizationName := "BusyMachines"
ThisBuild / homepage         := Option(url("https://www.busymachines.com/"))
ThisBuild / startYear        := Some(2021)
ThisBuild / licenses         := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

//=============================================================================
//=============================================================================

lazy val root =
  Project(id = "busylabs-2021", base = file("."))
    .settings(commonSettings)
    .settings(
      Compile / mainClass := Option("busylabs2021.Main")
    )
    .dependsOn(
       `teaching-plan`
    )

lazy val `teaching-plan` = project
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      pureharmCore,
      catsCore,
      catsEffect,
      fs2Core,
      fs2IO
    )
  )


def commonSettings: Seq[Setting[_]] = Seq(
  //https://github.com/scala/scala/releases
  scalaVersion     := "2.13.5",
  /*
   * Eliminates useless, unintuitive, and sometimes broken additions of `withFilter`
   * when using generator arrows in for comprehensions. e.g.
   *
   * Vanilla scala:
   * {{{
   *   for {
   *      x: Int <- readIntIO
   *      //
   *   } yield ()
   *   // instead of being `readIntIO.flatMap(x: Int => ...)`, it's something like .withFilter {case x: Int}, which is tantamount to
   *   // a runtime instanceof check. Absolutely horrible, and ridiculous, and unintuitive, and contrary to the often-
   *   // parroted mantra of "a for is just sugar for flatMap and map
   * }}}
   *
   * https://github.com/oleg-py/better-monadic-for
   */
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  /*
   * Kind projector brings a much needed feature to Scala, namely:
   * partially applied higher kinded types. For instancommonSettingsce, if we
   * want to partially apply an Either[L, R], to fix the type
   * for L, we can't do that in vanilla scala (easily).
   *
   * But with kind projector we can simply do:
   * Either[*, R], which create an anonymous higher kinded
   * type which then takes the remaining type parameter
   * for the right hand side.
   *
   * https://github.com/typelevel/kind-projector
   */
  addCompilerPlugin(("org.typelevel" %% "kind-projector" % "0.11.3").cross(CrossVersion.full)),
  scalacOptions ++= scala_2_13_Flags ++ betterForPluginCompilerFlags,
)

  def scala_2_13_Flags: Seq[String] = betterForPluginCompilerFlags ++ Seq(
    //"-Xfatal-warnings",            // Fail the compilation if there are any warnings.
    "-deprecation",                  // Emit warning and location for usages of deprecated APIs.
    "-encoding",                     // yeah, it's part of the "utf-8" thing, two flags
    "utf-8",                         // Specify character encoding used by source files.
    "-explaintypes",                 // Explain type errors in more detail.
    "-feature",                      // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials",        // Existential types (besides wildcard types) can be written and inferred
    "-language:higherKinds",         // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit",                   // Wrap field accessors to throw an exception on uninitialized access.
    "-Xlint:adapted-args",           // Warn if an argument list is modified to match the receiver.
    "-Xlint:constant",               // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select",     // Selecting member of DelayedInit.
    "-Xlint:doc-detached",           // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible",           // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any",              // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",   // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-unit",           // Warn when nullary methods return Unit.
    "-Xlint:option-implicit",        // Option.apply used implicit view.
    "-Xlint:package-object-classes", // Class or object defined in package object.
    "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow",         // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align",            // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow",  // A local type parameter shadows a type already in scope.
    "-Wdead-code",                   // Warn when we have dead code
    "-Ywarn-extra-implicit",         // Warn when more than one implicit parameter section is defined.
    "-Ywarn-numeric-widen",          // Warn when numerics are widened.
    "-Ywarn-unused:implicits",       // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports",         // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals",          // Warn if a local definition is unused.
    "-Wunused:params",               // Warn if a value parameter is unused.
    "-Wunused:synthetics",           // Warn if context boud is not used
    "-Ywarn-unused:patvars",         // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates",        // Warn if a private member is unused.
    "-Ywarn-value-discard",          // Warn when non-Unit expression results are unused.
    "-Wconf:any:warning-verbose",    // Gives extra information about warning,
    "-Ytasty-reader",                // Allows depending on libraries written in Scala 3
  )

/**
  * These are flags specific to the "better-monadic-for" plugin:
  * https://github.com/oleg-py/better-monadic-for
  */
def betterForPluginCompilerFlags: Seq[String] = Seq(
  "-P:bm4:no-filtering:y",     // see https://github.com/oleg-py/better-monadic-for#desugaring-for-patterns-without-withfilters--pbm4no-filteringy
  "-P:bm4:no-map-id:y",        // see https://github.com/oleg-py/better-monadic-for#final-map-optimization--pbm4no-map-idy
  "-P:bm4:no-tupling:y",       // see https://github.com/oleg-py/better-monadic-for#desugar-bindings-as-vals-instead-of-tuples--pbm4no-tuplingy
  "-P:bm4:implicit-patterns:y",// see https://github.com/oleg-py/better-monadic-for#define-implicits-in-for-comprehensions-or-matches
)

//============================================================================================
//============================================================================================
//======================================= DEPENDENCIES =======================================
//============================================================================================
//============================================================================================

val pureharmCoreV = "0.1.0"     //https://github.com/busymachines/pureharm-core/releases
val catsV         = "2.4.2"     //https://github.com/typelevel/cats/releases
val catsEffectV   = "3.0.0-RC2" //https://github.com/typelevel/cats-effect/releases
val fs2V          = "3.0.0-M9"  //https://github.com/functional-streams-for-scala/fs2/releases

val pureharmCore: ModuleID = "com.busymachines" %% "pureharm-core" % pureharmCoreV withSources()
val catsCore:     ModuleID = "org.typelevel"    %% "cats-core"     % catsV         withSources()
val catsEffect:   ModuleID = "org.typelevel"    %% "cats-effect"   % catsEffectV   withSources()
val fs2Core:      ModuleID = "co.fs2"           %% "fs2-core"      % fs2V          withSources()
val fs2IO:        ModuleID = "co.fs2"           %% "fs2-io"        % fs2V          withSources()
