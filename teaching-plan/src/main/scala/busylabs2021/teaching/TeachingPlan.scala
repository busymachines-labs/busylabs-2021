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
