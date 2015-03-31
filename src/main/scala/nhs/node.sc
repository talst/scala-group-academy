import nhs._

val auth = Map("a3" -> "app3Id")

val nodes = Vector(AppNode("a1", "a1", "XXX"),
  SubMenuNode("s1", "s1", Vector(UrlNode("u1", "u1", "google.com"),
    AppNode("a2", "a2", "XXX"), SubMenuNode("s3", "s3", Vector(AppNode("s5", "s5", "XXX"), UrlNode("u2", "u2", "yahoo.com"))))),
  AppNode("a3", "a3", "XXX"),
  SubMenuNode("s2", "s2", Vector(AppNode("a4", "a4", "XXX"))),
  SubMenuNode("s4", "s4", Vector(SubMenuNode("s5", "s5", Vector(SubMenuNode("s6", "s6", Vector()))))))

Utils.treeToString(nodes)

Utils.treeToString(for {
  node <- nodes
  filteredNode = node.applyAuth(auth)
  if filteredNode.nonEmpty
} yield filteredNode)
