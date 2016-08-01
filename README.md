# ner-play-server
This is NER API Server with Play Framework for Stanford NLP.

## Dependencies

* [Play Framework](https://www.playframework.com/)
* [Stanford NER](http://nlp.stanford.edu/software/CRF-NER.shtml)

## Build

### 1. Install `activator`

#### for OSX

```
$ brew install typesafe-activator
```

### 2. Download & Copy CRF models

```
$ wget http://nlp.stanford.edu/software/stanford-ner-2015-12-09.zip
$ unzip stanford-ner-2015-12-09.zip
$ cp -r ./stanford-ner-2015-12-09/classifiers/ ./public/classifiers/
```

### 3. Compile play distribution

Run `activator` and use `dist` command.

```
$ ./bin/activator
[info] Loading project definition from /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/project
[info] Set current project to ner-play-server (in build file:/Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/)
[ner-play-server] $ dist
[info] Packaging /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/scala-2.11/ner-play-server_2.11-1.0-SNAPSHOT-sources.jar ...
[info] Done packaging.
[info] Updating {file:/Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/}root...
[info] Wrote /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/scala-2.11/ner-play-server_2.11-1.0-SNAPSHOT.pom
[info] Resolving jline#jline;2.12.1 ...
[info] Done updating.
[info] Packaging /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/scala-2.11/ner-play-server_2.11-1.0-SNAPSHOT-javadoc.jar ...
[info] Packaging /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/scala-2.11/ner-play-server_2.11-1.0-SNAPSHOT-web-assets.jar ...
[info] Done packaging.
[info] Packaging /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/scala-2.11/ner-play-server_2.11-1.0-SNAPSHOT.jar ...
[info] Done packaging.
[info] Done packaging.
[info] Packaging /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/scala-2.11/ner-play-server_2.11-1.0-SNAPSHOT-sans-externalized.jar ...
[info] Done packaging.
[info]
[info] Your package is ready in /Users/katososuke/.ghq/github.com/sosuke-k/ner-play-server/target/universal/ner-play-server-1.0-SNAPSHOT.zip
[info]
[success] Total time: 88 s, completed 2016/08/02 5:48:00
```
