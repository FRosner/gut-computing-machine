package de.frosner.gut

import java.io.{FileInputStream, FileOutputStream, File}

@SerialVersionUID(Gut.VERSION)
case class Commit(id: Long, changes: Set[File]) extends Serializable {

  lazy val folder = new File(Gut.REPO_FOLDER_NAME + "/" + id.toString)

  def restore = {
    changes.foreach(copyFromRepo(_))
  }

  def copyFromRepo(src: File) = {
    val repoSrc = new File(folder, src.getName)
    val dest = new File(src.getName)
    Commit.copyFile(repoSrc, dest)
  }

  def copyToRepo(src: File) = {
    val dest = new File(folder, src.getName)
    Commit.copyFile(src, dest)
  }

}

object Commit {

  def copyFile(src: File, dest: File) = {
    new FileOutputStream(dest).getChannel.transferFrom(
      new FileInputStream(src).getChannel, 0, Long.MaxValue)
  }

}