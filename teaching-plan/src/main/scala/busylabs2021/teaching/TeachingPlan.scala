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

package busylabs2021.teaching

import busylabs2021._
import cats.implicits._
import cats.effect.IO

object TeachingPlan {

  def SettingThingsUp: Topic =
    Topic(
      title       = "Settings things up".sprout[Title],
      description = """|Follow the instructions in the README and set everything up.
                       |
                       |We will be giving context as to why we need each and every one
                       |of those technologies.
                       |
                       |But in short see the various sub topics. For a quick description,
                       |and reminder in case you forget.
                       |""".stripMargin.sprout[Description],
      subTopics   = List(
        Topic(
          title       = "Java JDK".sprout[Title],
          description = """|
                           |we need a JDK because Scala compiles to Java bytecode and it's the runtime system
                           |  we use will be running our programs on.
                           |""".stripMargin.sprout[Description],
          subTopics   = List.empty,
          examples    = List(
            Example(
              description = """|
                               |
                               |""".stripMargin.sprout[Description],
              run         = IO.unit,
            )
          ),
        ),
        Topic(
          title       = """`sbt` - Scala build tool""".sprout[Title],
          description = """|We need sbt because it's the tool we use to "build" applications.
                           |Any real application becomes a complex amalgam of external and internal
                           |libraries, a system of modules, and telling the compiler in which order
                           |to, and how to compile all these is a complex task that would b
                           |herculean to deal with without such a build system.
                           |
                           |The sbt build is defined in the file build.sbt in the root of this
                           |repository. The build itself is written in Scala, as a library, not
                           |unlike what we're writing here! It's all data structure creation,
                           |and method invocations.
                           |
                           |It's all very meta.
                           |
                           |However, we won't be getting very deep into `sbt`, mostly on a need
                           |to know basis to get an real world app up an running with a reliable, 
                           |and maintainable build. 
                           |""".stripMargin.sprout[Description],
          subTopics   = List.empty,
          examples    = List(
            Example(
              description =
                """|sbt has a REPL - Read Evaluate Print Loop
                   |
                   |Basically, sbt defines it's own language, like any familiar linux
                   |commands, like `ls`, or `grep`.
                   |
                   |Therefore we tell it what to do via such so called "tasks":
                   |  - `sbt compile`
                   |  - `sbt clean`
                   |  - `sbt update`
                   |  - the list goes on. 
                   | 
                   |But if we keep invoking sbt in such a one off manner we incur
                   |each startup cost. If need to use sbt often then it is recommended
                   |to just run: `sbt`
                   |
                   |Which will open an interactive terminal UI, a REPL.
                   |If you start it up in this project you should eventually see
                   |something like this:
                   |`sbt:busylabs-2021> `
                   |
                   |Where you can start typing commands and seeing their output,
                   |rinse + loop, hence the "L" in REPL.
                   |```
                   |sbt:busylabs-2021> clean
                   |[success] Total time: 0 s, completed Mar 19, 2021, 8:07:35 PM
                   |sbt:busylabs-2021> reload
                   |[info] welcome to sbt 1.5.0-RC1 (AdoptOpenJDK Java 11.0.10)
                   |[info] loading global plugins from /home/lorand/.sbt/1.0/plugins
                   |[info] loading settings for project busylabs-2021-build-build-build from metals.sbt ...
                   |[info] loading project definition from /home/lorand/workspace/bm/ligaac/busylabs-2021/project/project/project
                   |[info] loading settings for project busylabs-2021-build-build from metals.sbt ...
                   |[info] loading project definition from /home/lorand/workspace/bm/ligaac/busylabs-2021/project/project
                   |[success] Generated .bloop/busylabs-2021-build-build.json
                   |[success] Total time: 0 s, completed Mar 19, 2021, 8:07:38 PM
                   |[info] loading settings for project busylabs-2021-build from metals.sbt,plugins.sbt ...
                   |[info] loading project definition from /home/lorand/workspace/bm/ligaac/busylabs-2021/project
                   |[success] Generated .bloop/busylabs-2021-build.json
                   |[success] Total time: 0 s, completed Mar 19, 2021, 8:07:38 PM
                   |[info] loading settings for project busylabs-2021 from build.sbt ...
                   |[info] set current project to busylabs-2021 (in build file:/home/lorand/workspace/bm/ligaac/busylabs-2021/)
                   |```
                   |
                   |""".stripMargin.sprout[Description],
              run         = IO.unit,
            ),
            Example(
              description = """|`sbt compile`
                               |
                               |This compiles our code if it wasn't compiled before. It uses an
                               |incremental compiler. Fairly intuitive, really.
                               |""".stripMargin.sprout[Description],
              run         = IO.unit,
            ),
            Example(
              description = """|`sbt run`
                               |
                               |This will first run the `compile` task
                               |and run the configured/detected "Main" class. In our case
                               |it's `busylabs2021.BusyLabs2021Main`
                               |
                               |""".stripMargin.sprout[Description],
              run         = IO.unit,
            ),
            Example(
              description = """|`sbt stage`
                               |
                               |Once we get to the build part of the project you'll see that using
                               |`sbt stage` the build tool will generate the following two things
                               |at the path (relative to the project root).
                               |  - A bash script to run on Linux-like systems:
                               |     - target/universal/stage/bin/busylabs-2021
                               |  - A bat script to run our application on windows:
                               |    - target/universal/stage/bin/busylabs-2021.bat
                               |
                               |Simply invoke the scripts from your terminal, and if you look
                               |into your system's Application process monitor you'll see a
                               |new "Java" process starting.
                               |""".stripMargin.sprout[Description],
              run         = IO.unit,
            ),
          ),
        ),
        Topic(
          title       = """Docker""".sprout[Title],
          description = """|docker we use to run ourselves external services in a fairly hassle free way.
                           |Primarily, we'll be needing it to run a Postgresql database server.
                           |And ultimately, our own servers will be packaged into a docker image.
                           |
                           |Other external services that we might stumble upon during the course of the lab:
                           |- min.io
                           |  - an object file store. You put structured data in Postgresql, while files (images, various
                           |    data exports, etc.)  you just dump into such an object store, and serve them from there.
                           |    min.io is a self-hosted alternative for Amazon S3.
                           |
                           |- Prometheus
                           |  - a monitoring system that can gather, aggregate, display various metrics from your
                           |    system. Both standard (like memory consumption, uptime checks, etc) or domain
                           |    specific ones (e.g. number of logins in a time window)
                           |
                           |- nginx
                           |  - a reverse proxy server that we can use to serve a pretty front-end for our server.
                           |
                           |The point is that real world systems have all sorts moving parts, and docker might help
                           |with this.
                           |""".stripMargin.sprout[Description],
          subTopics   = List.empty,
          examples    = List(
            Example(
              description = """|It's this easy to start up a postgresql database server in docker,
                               |without further installation.
                               |
                               |https://github.com/busymachines/pure-movie-server/blob/main/docker-postgresql.sh
                               |
                               |That script is convenient to use in development.
                               |""".stripMargin.sprout[Description],
              run         = IO.unit,
            )
          ),
        ),
        Topic(
          title       = "git".sprout[Title],
          description = """|Well, tracking changes on real software systems can quickly become a nightmare
                           |even when you're working alone on a piece of software. You'll learn a quick
                           |list of tips and tricks to help yourself our w/ git. Let alone learning how
                           |to collaborate.
                           |
                           |""".stripMargin.sprout[Description],
          subTopics   = List.empty,
          examples    = List(
            Example(
              description =
                """|git clone
                   |https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository
                   |
                   |run:
                   |`git clone https://github.com/busymachines/busylabs-2021.git`
                   |
                   |to get the code for this repo. And then maybe try running:
                   |`git clone https://github.com/busymachines/pure-movie-server.git`
                   |""".stripMargin.sprout[Description],
              run         = IO.unit,
            ),
            Example(
              description = """|`git log`
                               |
                               |Will display a log of recent changes.
                               |""".stripMargin.sprout[Description],
              run         = IO.unit,
            ),
          ),
        ),
      ),
      examples    = List.empty,
    )

  def ScalaBasics: Topic = Topic(
    title       = "Scala basics".sprout[Title],
    description = """|Here you have a short list of Scala features we'll be using
                     |with some examples. The first example are these data structures
                     |here!
                     |
                     |It's all Scala code!
                     |""".stripMargin.sprout[Description],
    subTopics   = List(
      Topic(
        title       = "Strings".sprout[Title],
        description = """|Well, this is a string really, when we create a string w/ 3 quotes, then
                         |we can write anything without needing to escape characters,
                         |other than that it's similar to the Java syntax
                         |
                         |""".stripMargin.sprout[Description],
        subTopics   = List.empty,
        examples    = List(
          Example(
            description = """|String interpolator
                             |We construct these string by prefixing quotes w/ s"my string here".
                             |""".stripMargin.sprout[Description],
            run         = {
              val plainString   = "plain string"
              val int           = 42
              val list          = List(int)
              val complexString =
                s"""|we constructed a complex string from:
                    |a $plainString
                    |an int $int 
                    |a list which I turn into a pretty string ${list.mkString("[", ",", "]")}
                    |
                    |Each time we insert a value in the interpolator, if its type is not
                    |String, then the .toString (equivalent to what we have from Java)
                    |method will be called on it.
                    |
                    |Therefore, it's ofttimes to use a more typesafe variant of the interpolator
                    |available in cats. See next section.
                    |""".stripMargin
              IO.println(complexString)
            },
          ),
          Example(
            description = """|Show interpolator
                             |Available from cats._
                             |N.B., in pure-movie-server via `import phms._`
                             |
                             |Its syntax is almost identical to that of s"", but semantically it
                             |does not call the .toString method on the values you insert.
                             |
                             |Rather, you have to provide so called Show typeclasses (we'll see at a later time)
                             |for each value. This is a more typesafe version, as not everything should
                             |be showable, e.g. passwords.
                             |
                             |For now we'll keep using the s"" interpolator.
                             |""".stripMargin.sprout[Description],
            run         = {
              val plainString = "plain string"
              val int         = 42
              val list        = List(int)
              val shoString   =
                show"""|we constructed a complex string from:
                       |a $plainString -- by default strings have a Show[String] instance
                       |an int $int -- by default ints have a Show[Int] instance
                       |a list which I turn into a pretty string ${list.mkString("[", ",", "]")}
                       |
                       |But if I'd try to put SettingThingsUp this would not compile.
                       |Go ahead, put a dollar sign before that, and see for yourself.
                       |""".stripMargin
              IO.println(shoString)
            },
          ),
        ),
      ),
      Topic(
        title       = "Case class".sprout[Title],
        description = """|We will be using a bunch of case classes, everywhere!
                         |The syntax is fairly intuitive:
                         |
                         |```scala
                         |final case class Topic(
                         |  title:       Title,
                         |  description: Description,
                         |  subTopics:   List[Topic],
                         |  examples:    List[Example],
                         |)
                         |```
                         |
                         |We have a class with 4 named immutable fields
                         |""".stripMargin.sprout[Description],
        subTopics   = List.empty,
        examples    = List(
          Example(
            description = """Instantiating a case class""".sprout[Description],
            run         = {
              case class Test(int: Int, string: String)
              val v1 = Test(42, "just an example") //this is how we create
              //this is how we alter the case class by making a new copy
              //and keeping the string field as is in the original
              val v2 = v1.copy(int = 55)
              for {
                _ <- IO.println(
                  "Here we showcase how to construct more complex strings using the 's' interpolator to insert values where needed"
                )
                _ <- IO.println(s"Notice how v1=$v1 is unchanged!")
                _ <- IO.println(s"This is v2=$v2")
                _ <- IO.println(s"Really, this is most you need to know about case classes for now")
              } yield ()
            },
          )
        ),
      ),
    ),
    examples    = List.empty,
  )

  def AgainstTheGrain: Topic = Topic(
    title       = "Against the grain".sprout[Title],
    description = """|Not all libraries and ecosystems are created equal.
                     |
                     |Not all Scala code is pure functional.
                     |
                     |Therefore know we won't be teaching "Scala", it's a useless
                     |moniker which can denote any number of utterly horrendous
                     |things.
                     |
                     |We will be teaching pure functional Scala, using
                     |the typelevel ecosystem.
                     |
                     |Foundational libraries:
                     |  - cats
                     |  - cats-effect
                     |  - fs2
                     |
                     |You can assume that these libraries are part of the "standard"
                     |library as far as any realistic software system is concerned.
                     |""".stripMargin.sprout[Description],
    subTopics   = List.empty,
    examples    = List.empty,
  )

}
