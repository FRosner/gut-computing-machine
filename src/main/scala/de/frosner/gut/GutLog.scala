package de.frosner.gut

object GutLog {

  def main(args: Array[String]) {
    val repo = Repository.read
    println(s"""Head is at ${Head.read.commit.id}""")
    println("Commits:")
    repo.commits.map(_.id).foreach(println)
  }

}
