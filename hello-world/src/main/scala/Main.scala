import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await, ExecutionContext}
import scala.concurrent.duration.Duration

@main def hello: Unit = {
  println("Hello world!")
  println(msg)

  Await.result(doIt(), Duration.Inf)
}

def doIt() = {
  generateMagicNumberF().flatMap({ result =>
    generateMagicNumberAndPrintF().map(_ => {
      println(s"Magic number is $result")
    })
  })
}

def generateMagicNumber(): Int = {
  Thread.sleep(3000L)
  42
}

def generateMagicNumberF()(implicit ec: ExecutionContext): Future[Int] = Future {
  generateMagicNumber()
}

def generateMagicNumberAndPrintF()(implicit ec: ExecutionContext): Future[Unit] = Future {
  val num = generateMagicNumber()
  println(s"2nd Magic number is $num")
}

def msg = "I was compiled by Scala 3. :)"