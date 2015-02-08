package de.frosner.gut

import java.io._

@SerialVersionUID(Gut.VERSION)
case class Repository(commits: Vector[Commit]) {
  
  def trackedFiles = {
    commits.map(_.changes).reduce(_ ++ _)
  }

  def getCommit(id: Long) = {
    commits.find(_.id == id)
  }

  def add(commit: Commit) = {
    Repository(commits :+ commit)
  }

  def persist = {
    val out = new ObjectOutputStream(new FileOutputStream(Repository.FILE))
    out.writeObject(this)
    out.close
  }

}

object Repository {

  val FILE = new File(Gut.REPO_FOLDER_NAME + "/repo")

  def empty = Repository(Vector.empty)

  def read = {
    val in = new ObjectInputStream(new FileInputStream(FILE))
    val repo = in.readObject().asInstanceOf[Repository]
    in.close
    repo
  }

}
