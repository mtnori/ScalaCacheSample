package example

import scalacache._
import scalacache.caffeine.CaffeineCache

import scala.util.Try

object CaffeineCacheSample extends App {

  //キャッシュインスタンス生成: 実装はCaffeine
  implicit val cache: Cache[Cat] = CaffeineCache[Cat]

  //モードの指定(Try)
  import scalacache.modes.try_._

  //エントリを追加
  put(Cat.duchess.id)(Cat.duchess)
//  put(Cat.thomas.id)(Cat.thomas)
  //参照
  get(Cat.duchess.id) //Success(Some(Cat(1,Duchess,white)))
  //キャッシュミス
  get(Cat.thomas.id) //Success(None)

  println(get(Cat.duchess.id))

  //削除
  remove(Cat.duchess.id) //Success(())
  remove(Cat.marie.id) //Success(())

//  //キャッシュの更新処理を指定
//  cachingF(Cat.thomas.id)(ttl = None)(Try {
//    println(s"where is the cat(id=${Cat.thomas.id}) ??")
//    Cat.thomas
//  })
}
