package example

import akka.actor.ActorSystem
import redis.RedisClient

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object RedisCacheSample extends App {
  implicit val akkaSystem: ActorSystem = akka.actor.ActorSystem()

  val redis = RedisClient()

  val futurePong = redis.ping()
  println("Ping sent!")

  futurePong.map(pong => {
    println(s"Redis replied with a $pong")
  })
  Await.result(futurePong, 5 seconds)

  val futureSet = redis.set[Cat](Cat.thomas.id.toString, Cat.thomas)
  Await.result(futureSet, 5 seconds)

  val futureGet = redis.get[Cat]("1")
  futureGet.map(data => {
    println(s"${data.getOrElse(throw new Exception("no find"))}")
  })
  Await.result(futureGet, 5 seconds)

  akkaSystem.terminate()
}
