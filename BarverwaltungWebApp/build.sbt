name := "Barverwaltung"

version := "1.0-SNAPSHOT"

resolvers += "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/"

libraryDependencies ++= Seq(
  javaJdbc,
  javaCore,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "4.2.12.Final",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

val appDependencies = Seq(
// Add your project dependencies here, 
javaCore,
javaJdbc,
javaJpa,
"org.hibernate" % "hibernate-entitymanager" % "4.2.1.Final",
"org.mindrot" % "jbcrypt" % "0.3m"
)

play.Project.playJavaSettings