package de.frosner.gut

import java.io._

case class Stage(files: Set[File]) {
  
  def add(fileName: String): Stage = {
    add(new File(fileName))
  }
  
  def add(file: File): Stage = {
    Stage(files + file)
  }

  def persist = {
    val out = new ObjectOutputStream(new FileOutputStream(Stage.FILE))
    out.writeObject(this)
    out.close
  }
  
}

object Stage {

  val FILE = new File(Gut.REPO_FOLDER_NAME + "/stage")

  def empty = Stage(Set.empty)

  def read = {
    val in = new ObjectInputStream(new FileInputStream(FILE))
    val stage = in.readObject().asInstanceOf[Stage]
    in.close
    stage
  }

  def clear = empty.persist

}
