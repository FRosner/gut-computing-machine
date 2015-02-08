package de.frosner.gut

import java.io.File

object GutAdd {

  def main(args: Array[String]) {
    if (args.isEmpty) {
      println("Staged files: ")
      Stage.read.files.foreach(println)
    } else {
      val fileToAdd = new File(args(0))
      if (fileToAdd.exists) {
        Stage.read.add(fileToAdd).persist
        println("Added " + fileToAdd)
      } else {
        println(s"""File $fileToAdd does not exist""")
      }
    }
  }

}
