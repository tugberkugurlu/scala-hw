import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

@main def hello: Unit = {
  println("Hello world!")
  println(msg)

  doIt()

  Thread.sleep(8000L)
}

def doIt(): Future[Unit] = {
  generateMagicNumberF().map({ result =>
    for {
      result2 <- generateMagicNumberAndPrintF()
    } yield {
      println(s"Magic number is $result")
    }
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