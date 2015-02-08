package de.frosner.gut

object GutCheckout {

  def main(args: Array[String]) {
    val repo = Repository.read
    if (args.isEmpty) {
      if (repo.commits.isEmpty) {
        println("Empty repository. Use 'gut add' and 'gut commit' to commit changes.")
      } else {
        println(s"""Head is at ${Head.read.commit.id}""")
      }
    } else {
      val commitId = args(0).toLong
      val newHead = repo.getCommit(commitId)
      if (newHead.isDefined) {
        Head(newHead.get).persist
        repo.trackedFiles.foreach(_.delete)
        repo.commits.take((newHead.get.id + 1).toInt).foreach(_.restore)
      } else {
        println( s"""No commit with id $commitId found""")
      }
    }
  }

}
