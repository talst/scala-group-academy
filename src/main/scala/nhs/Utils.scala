package nhs

import scala.collection.GenSeq

object Utils {
  def treeToString(tree: GenSeq[Node]) = {
    def createIndent(numberOfTabs: Int) = {
      (for (tab <- 0 to numberOfTabs) yield "\t").mkString
    }
    def nodeToString(node: Node, indent: Int): String = node match {
      case SubMenuNode(id, name, children) =>
        "SubMenuNode(" + id + "," + name + "\n" +
          createIndent(indent) + {
          (for (child <- children)
            yield nodeToString(child, indent + 1)) mkString "\n" + createIndent(indent)
        } + "\n" + createIndent(indent - 1) + ")"

      case _ => node.toString
    }

    "\n" + ((for (node <- tree) yield nodeToString(node, 0)) mkString " \n")
  }
}
