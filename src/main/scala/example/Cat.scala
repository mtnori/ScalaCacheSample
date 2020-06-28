package example

import akka.util.ByteString
import redis.ByteStringFormatter
import io.circe._, io.circe.parser._
import io.circe.syntax._
import io.circe._, io.circe.generic.semiauto._

final case class Cat(id: Int, name: String, colour: String)

object Cat {
  val duchess: Cat = Cat(1, "Duchess", "white")
  val thomas: Cat = Cat(2, "Thomas", "orange")
  val marie: Cat = Cat(3, "Marie", "white")

  // Circeで必要
  implicit val catDecoder: Decoder[Cat] = deriveDecoder[Cat]
  implicit val catEncoder: Encoder[Cat] = deriveEncoder[Cat]

  // Rediscalaで必要
  implicit val byteStringFormatter: ByteStringFormatter[Cat] =
    new ByteStringFormatter[Cat] {
      def serialize(data: Cat): ByteString = {
        ByteString(data.asJson.toString())
      }

      def deserialize(bs: ByteString): Cat = {
        val s = bs.utf8String
        decode[Cat](s) match {
          case Right(data) => data
          case _           => throw new Exception("Json deserialize is failed.")
        }
      }
    }
}
