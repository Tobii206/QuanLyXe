# PoLoMen (2)

This is a Java Swing application that connects to a relational database (default: Microsoft SQL Server).

## Quick start

1. Configure database connection
   - Edit `src/main/resources/db.properties` or set environment variables `DB_DRIVER`, `DB_URL`, `DB_USER`, `DB_PASSWORD`.
   - Defaults target SQL Server on `localhost:1433` with database name `QLBanXe`.

2. Build
   - Install Maven (https://maven.apache.org/install.html) or generate a Maven Wrapper locally.
   - To build with Maven:

```powershell
cd 'd:\PoLoMen (2)'
mvn -DskipTests package
```

3. Run
   - From IDE: run `DA1.poLoMen.PoLoMen` main class or run the `uitl.ConnectionTest` main to test DB connection.
   - From Maven (exec plugin is configured):

```powershell
# Run the main GUI
mvn exec:java

# Run the connection test
mvn exec:java -Dexec.mainClass="uitl.ConnectionTest"
```

## If `mvn` is not installed

You can add the Maven Wrapper to the project on a machine that has Maven:

```powershell
cd 'd:\PoLoMen (2)'
# generate wrapper files
mvn -N io.takari:maven:wrapper
```

This will create the `mvnw` scripts and `.mvn/wrapper` files. Commit them to the repo so others can run `./mvnw` without installing Maven.

## Troubleshooting

- ClassNotFoundException for driver: ensure the JDBC driver dependency is in `pom.xml` and run `mvn dependency:resolve`.
- SQL Server connectivity: enable TCP/IP in SQL Server Configuration Manager, ensure firewall allows port 1433, verify username/password.
- For Windows Authentication, change JDBC URL and driver settings accordingly.

If you'd like, I can:
- Add environment-variable-only configuration (remove properties file fallback).
- Add Maven Wrapper files here (requires generating them locally and committing the binaries), or show exact commands to generate them on your machine.
- Add MySQL/Postgres dependencies and example configs.
