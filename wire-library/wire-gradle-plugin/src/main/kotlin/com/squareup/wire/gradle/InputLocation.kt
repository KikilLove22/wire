package com.squareup.wire.gradle

import com.squareup.wire.internal.Serializable
import org.gradle.api.Project

class InputLocation private constructor(
  /** The base of this location; typically a directory or .jar file. */
  val base: String,

  /** The path to this location relative to [base]. */
  val path: String,
) : Serializable {
  companion object {
    @JvmStatic
    fun get(project: Project, path: String): InputLocation {
      // We store [path] relative to the [project] in order to not invalidate the cache when we
      // don't have to.
      return InputLocation("", project.relativePath(path))
    }

    @JvmStatic
    fun get(
      project: Project,
      base: String,
      path: String
    ): InputLocation {
      // We store [base] relative to the [project] in order to not invalidate the cache when we
      // don't have to.
      return InputLocation(project.relativePath(base).trimEnd('/'), path)
    }
  }
}
