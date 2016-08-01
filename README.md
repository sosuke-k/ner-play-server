# ner-play-server
This is NER API Server with Play Framework for Stanford NLP.

![https://gyazo.com/1e67c05975d89ee14f1214fd24b42f56](https://i.gyazo.com/1e67c05975d89ee14f1214fd24b42f56.png)

## Dependencies

* [Play Framework](https://www.playframework.com/)
* [Stanford NER](http://nlp.stanford.edu/software/CRF-NER.shtml)

## Usage

1. Initialize calssifier (default: `english.all.3class.distsim.crf.ser.gz`)

  ```
  $ url http://localhost:9000/ner | jq
    % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                   Dload  Upload   Total   Spent    Left  Speed
  100    85  100    85    0     0   2459      0 --:--:-- --:--:-- --:--:--  2575
  {
    "status": "available",
    "current": "english.all.3class.distsim.crf.ser.gz",
    "message": ""
  }
  ```

2. Post a sentence

  ```
  curl --header "Content-type: application/json" --request POST --data '{"text":"I live in Japan."}' http://localhost:9000/ner | jq
    % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                   Dload  Upload   Total   Spent    Left  Speed
  100   179  100   152  100    27   4545    807 --:--:-- --:--:-- --:--:--  4606
  {
    "tokens": [
      {
        "text": "I",
        "label": "O"
      },
      {
        "text": "live",
        "label": "O"
      },
      {
        "text": "in",
        "label": "O"
      },
      {
        "text": "Japan",
        "label": "LOCATION"
      },
      {
        "text": ".",
        "label": "O"
      }
    ]
  }
  ```

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

### 3'. Run play server

```
$ ./bin/activator run
```

## Use distribution binary

Download `ner-play-server-1.0-SNAPSHOT.zip` in [this page](https://github.com/sosuke-k/ner-play-server/releases/tag/v_1.0.0) and unzip it.

```
$ wget https://github.com/sosuke-k/ner-play-server/releases/download/v_1.0.0/ner-play-server-1.0-SNAPSHOT.zip
$ unzip ner-play-server-1.0-SNAPSHOT.zip
```

Change it as execution file.

```
$ chmod +x ner-play-server-1.0-SNAPSHOT/bin/ner-play-server
```

Run it.

```
$ ner-play-server-1.0-SNAPSHOT/bin/ner-play-server
...
```
