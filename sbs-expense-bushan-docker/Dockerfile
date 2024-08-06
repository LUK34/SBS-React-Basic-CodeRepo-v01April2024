# The below line of code will tell docker how to run the below java progamme using base image `eclipse-temurin`
FROM eclipse-temurin:8

LABEL maintainer="lukerajan.mathew@gmail.com"

# We need to setup the working directory by giving a name to base folder called `app`
WORKDIR /app

# The COPY instruction sets two parameters:
# 1. The source file: `target/SBS-Expense-Tracker-API-SecurityFilterChain-Jwt-0.0.1-SNAPSHOT.jar`
# 2. The destination path inside the container: `/app/sbs-expense-tracker.jar`
COPY target/sbs-expense-bushan-docker-0.0.1-SNAPSHOT.jar /app/sbs-expense-tracker.jar

# The ENTRYPOINT instruction defines the command that will run when the container starts
ENTRYPOINT ["java", "-jar", "sbs-expense-tracker.jar"]