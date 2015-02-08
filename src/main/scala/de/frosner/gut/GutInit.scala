package de.frosner.gut

import java.io.File

object GutInit {

  def main(args: Array[String]) {
    new File(Gut.REPO_FOLDER_NAME).mkdir
    Repository.empty.persist
    Stage.empty.persist

    println("Initialized empty repository")
  }

}
