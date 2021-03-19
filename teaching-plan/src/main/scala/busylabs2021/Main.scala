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
