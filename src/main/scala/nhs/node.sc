import nhs._

val auth = Map("a3" -> "app3Id")

val nodes = Vector(AppNodeMeta("a1", "a1"),
  SubMenuNode("s1", "s1", Vector(UrlNode("u1", "u1", "google.com"),
    AppNodeMeta("a2", "a2"), SubMenuNode("s3", "s3", Vector(AppNodeMeta("s5", "s5"), UrlNode("u2", "u2", "yahoo.com"))))),
  AppNodeMeta("a3", "a3"),
  SubMenuNode("s2", "s2", Vector(AppNodeMeta("a4", "a4"))),
  SubMenuNode("s4", "s4", Vector(SubMenuNode("s5", "s5", Vector(SubMenuNode("s6", "s6", Vector()))))))

Utils treeToString nodes
Utils treeToString (for {
  node <- nodes
  if node filter auth
} yield node)
