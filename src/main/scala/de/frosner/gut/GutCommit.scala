package de.frosner.gut

import java.io.{FileOutputStream, File, FileInputStream, ObjectInputStream}

object GutCommit {

  def main(args: Array[String]) {
    val repo = Repository.read

    val newCommitId = repo.commits.length
    val commit = Commit(newCommitId, Stage.read.files)
    commit.folder.mkdir()
    commit.changes.foreach(commit.copyToRepo(_))
    println(s"""Commit $newCommitId:""")
    commit.changes.foreach(println)

    Stage.clear
    repo.add(commit).persist
    Head(commit).persist
  }

}
