package de.frosner.gut

import java.io._

case class Head(commit: Commit) {

  def persist = {
    val out = new ObjectOutputStream(new FileOutputStream(Head.FILE))
    out.writeObject(this)
    out.close
  }

}

object Head {

  val FILE = new File(Gut.REPO_FOLDER_NAME + "/head")

  def read = {
    val in = new ObjectInputStream(new FileInputStream(FILE))
    val stage = in.readObject().asInstanceOf[Head]
    in.close
    stage
  }

}
