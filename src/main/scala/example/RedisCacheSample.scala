package example

import scalacache._
import scalacache.redis._
import scalacache.serialization.binary._

import scala.concurrent.Await
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object RedisCacheSample extends App {

  //キャッシュインスタンス生成: 実装はCaffeine
  implicit val cache: Cache[Cat] = RedisCache("localhost", 6379)

  //モードの指定(Try)
  import scalacache.modes.try_._

  //エントリを追加
  put(Cat.duchess.id)(Cat.duchess)

  //参照
  val foo = get(Cat.duchess.id) //Success(Some(Cat(1,Duchess,white)))
  //キャッシュミス
  var bar = get(Cat.thomas.id) //Success(None)

  //削除
  remove(Cat.duchess.id) //Success(())
  remove(Cat.marie.id) //Success(())

//  //キャッシュの更新処理を指定
//  cachingF(Cat.thomas.id)(ttl = None)(Try {
//    println(s"where is the cat(id=${Cat.thomas.id}) ??")
//    Cat.thomas
//  })
}
