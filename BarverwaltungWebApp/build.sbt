name := "Barverwaltung"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaCore,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "4.2.12.Final"
)

val appDependencies = Seq(
// Add your project dependencies here, 
javaCore,
javaJdbc,
javaJpa,
"org.hibernate" % "hibernate-entitymanager" % "4.2.1.Final"
)

play.Project.playJavaSettings

