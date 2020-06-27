package example

final case class Cat(id: Int, name: String, colour: String)

object Cat {
  val duchess: Cat = Cat(1, "Duchess", "white")
  val thomas: Cat = Cat(2, "Thomas", "orange")
  val marie: Cat = Cat(3, "Marie", "white")
}
