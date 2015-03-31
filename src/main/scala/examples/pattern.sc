trait Color
case class Red() extends Color
case class Yellow() extends Color
case class Green() extends Color

trait Fruit {
  def color: Color
}

case class Apple(color: Color) extends Fruit
case class Banana(color: Color) extends Fruit
case class Peach(color: Color) extends Fruit



def patternExample(fruit: Fruit) = fruit match {
  case Apple(color: Red) => "red apple"
  case Apple(color: Green) => "green apple"
  case Banana(color) => color.toString + " banana"
  case Peach(color) =>  color.toString + " peach"
  case _ => "else"
}

patternExample(Apple(Red()))
patternExample(Apple(Yellow()))
patternExample(Banana(Green()))
