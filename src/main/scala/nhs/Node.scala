package nhs

sealed trait Node {
  def id: String
  def name: String
  def filter(map: Map[String, String], f: (Map[String, String], Node) => Boolean = defaultFilter): Boolean = f(map, this)
  def assignment(map: Map[String, String], f: (Map[String, String], Node) => Node = defaultAssignment): Node = f(map, this)
  protected def defaultFilter(map: Map[String, String], node: Node): Boolean
  protected def defaultAssignment(map: Map[String, String], node: Node): Node
}

case class AppNodeReal(id: String, name: String, appId: String) extends Node {
  override protected def defaultFilter(map: Map[String, String], node: Node): Boolean =
    map get id match {
      case Some(newId) => true
      case None => false
    }
  override protected def defaultAssignment(map: Map[String, String], node: Node): Node = this
}

case class AppNodeMeta(id: String, name: String) extends Node {
  override protected def defaultFilter(map: Map[String, String], node: Node): Boolean =
    map get id match {
      case Some(newId) => true
      case None => false
    }
  override protected def defaultAssignment(map: Map[String, String], node: Node): Node =
    map get id match {
      case Some(newId) => AppNodeReal(id, name, newId)
      case None => EmptyNode
    }
}

case class UrlNode(override val id: String, name: String, target: String) extends Node {
  override protected def defaultFilter(map: Map[String, String], node: Node): Boolean = true
  override protected def defaultAssignment(map: Map[String, String], node: Node): Node = this
}

case class SubMenuNode(override val id: String, name: String, children: Vector[Node]) extends Node {
  override protected def defaultFilter(map: Map[String, String], node: Node): Boolean = {
    val filteredChildren = for {
      child <- children
      if child.filter(map)
    } yield child
    filteredChildren.nonEmpty
  }
  override protected def defaultAssignment(map: Map[String, String], node: Node): Node = {
    val assignedChildren = for {
      child <- children
      if child.filter(map)
    } yield child.assignment(map)
    SubMenuNode(id, name, assignedChildren)
  }
}

case object EmptyNode extends Node {
  override val id = "emptyNode"
  override val name = "empty node"
  override protected def defaultFilter(map: Map[String, String], node: Node): Boolean = false
  override protected def defaultAssignment(map: Map[String, String], node: Node): Node = this
}





