package busylabs2021

import busylabs2021.teaching._
import cats.implicits._
import cats.effect._

import scala.concurrent.duration._

object InDepthExampleExecution {

  private def runExample(example: Example): IO[Unit] =
    for {
      _ <- IO.println("++++ example ++++")
      _ <- IO.println(s"${example.description}")
      _ <- IO.println("---- example ----")
      _ <- IO.blocking(example.run).timeoutTo(2.seconds, IO.println(s"We timed out on example: ${example.description}"))
    } yield ()

  def runTopic(topic: Topic): IO[Unit] = {
    //this is an incorrect example
    //val mapped:        List[IO[Unit]] = topic.examples.map((example: Example) => example.run)

    val currentExamples: IO[Unit] = topic.examples.traverse_(runExample)
    val fromSubTopics:   IO[Unit] = topic.subTopics.traverse_(runTopic)
    for {
      _ <- currentExamples
      _ <- fromSubTopics
    } yield ()
  }

}
