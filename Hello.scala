//> using scala "3.3.0-RC2"
//> using lib "com.github.alexarchambault::case-app-entrypoint-annotation:0.1.0"

@annotation.experimental
object Hello {
  @caseapp.entrypoint
  def hello(): Unit =
    println("Hello")
}
