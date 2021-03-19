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

package busylabs2021

import cats.implicits._
import cats.effect._
import cats.effect.std._

object Main extends IOApp.Simple {
  private val console = Console[IO]

  override def run: IO[Unit] = for {
    _ <- newline.replicateA(2)
    _ <- tab *> console.println("Hello BusyMachines Liga AC 2021 students!")
    _ <- newline
    _ <- tab *> console.println("If you see this message you have successfully run a Scala program")
    _ <- newline
    _ <- tab *> console.println(
      "Check out the TeachingPlan - written as a Scala program - for an outline of what we'll be learning"
    )
    _ <- newline
  } yield ()

  private val newline: IO[Unit] = console.println("")
  private val tab:     IO[Unit] = console.print("  ")

}
