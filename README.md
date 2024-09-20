# ai-rubric

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Tips

Make sure to export the API key
```
export QUARKUS_LANGCHAIN4J_OPENAI_JUDGE_API_KEY=sk-proj-1xMQkJtF
```

Make sure to have `ollama serve` running

Currently using:

* gpt-4o-mini at openai.com
* mistral-nemo:12b via Ollama
* llama3.1:8b via Ollama
* qwen2.5:7b via Ollama


```
java -version
openjdk version "22.0.1" 2024-04-16
OpenJDK Runtime Environment Temurin-22.0.1+8 (build 22.0.1+8)
OpenJDK 64-Bit Server VM Temurin-22.0.1+8 (build 22.0.1+8, mixed mode)
```

```
mvn -version
Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63)
Maven home: /Users/burr/.sdkman/candidates/maven/current
Java version: 22.0.1, vendor: Eclipse Adoptium, runtime: /Users/burr/.sdkman/candidates/java/22.0.1-tem
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "14.6.1", arch: "aarch64", family: "mac"
```

### Testing

Compare 4 ollama models against openai.com 

```
ollama serve
```

```
ollama pull mistral-nemo:12b
ollama pull llama3.1:8b
ollama pull qwen2.5:7b
ollama pull granite-code:8b
or
ollama pull granite-code:20b (response timeouts)
```

4 LLMs vs gpt4o-mini
```
curl localhost:8080/comparetoJudgeBurr
```

4 LLMs vs gpt4o-mini
```
curl localhost:8080/comparetoJudgeSky
```

```
curl -X POST -H 'Content-Type: application/json' -d @request.json http://localhost:8080/allcandidates
```

```
curl -X POST -H 'Content-Type: application/json' -d '{"request": "you are amazing"}' http://localhost:8080/allcandidates
```

