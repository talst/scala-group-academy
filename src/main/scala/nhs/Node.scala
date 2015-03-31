package nhs

sealed trait Node {
  def id: String

  def name: String

  def nonEmpty: Boolean = true

  def applyAuth(auth: Map[String, String]): Node
}

case class AppNode(id: String, name: String, appId: String) extends Node {
  override def applyAuth(auth: Map[String, String]) = auth.get(id) match {
    case None => EmptyNode
    case Some(x) => AppNode(id, name, x)
  }
}

case class UrlNode(id: String, name: String, target: String) extends Node {
  override def applyAuth(auth: Map[String, String]): Node = this
}

case class SubMenuNode(id: String, name: String, children: Vector[Node]) extends Node {
  override def applyAuth(auth: Map[String, String]): Node = {
    val filteredChildren: Vector[Node] = for {
      child <- children
      filteredChild = child.applyAuth(auth)
      if filteredChild.nonEmpty
    } yield filteredChild
    SubMenuNode(id, name, filteredChildren)
  }

  override def nonEmpty: Boolean = children.nonEmpty
}

case object EmptyNode extends Node {
  override val id: String = "emptyNode"

  override val name: String = "empty node"

  override def applyAuth(auth: Map[String, String]): Node = this

  override def nonEmpty: Boolean = false
}
