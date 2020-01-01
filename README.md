# LiCOS-JSON4Scala

[![Build Status](https://travis-ci.org/ktr-skmt/LiCOS-JSON4Scala.svg?branch=master)](https://travis-ci.org/ktr-skmt/LiCOS-JSON4Scala)
[![codecov](https://codecov.io/gh/ktr-skmt/LiCOS-JSON4Scala/branch/master/graph/badge.svg)](https://codecov.io/gh/ktr-skmt/LiCOS-JSON4Scala)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/ktr-skmt/LiCOS-JSON4Scala/blob/master/LICENSE)
[![CircleCI](https://circleci.com/gh/ktr-skmt/LiCOS-JSON4Scala.svg?style=svg)](https://circleci.com/gh/ktr-skmt/LiCOS-JSON4Scala)

## API

### The latest version

https://ktr-skmt.github.io/LiCOS-JSON4Scala/0.2.8/api/index.html

### Old versions

https://ktr-skmt.github.io/LiCOS-JSON4Scala/index.html

## Import

### SBT

```scala
resolvers += "LiCOS-JSON4Scala-snapshots-repository" at "https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots"
```

```scala
libraryDependences += "online.licos" % "licos-json4scala_2.12" % "(version)"
```

### Gradle

```javascript
repositories {
    maven {
        url "https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots"
    }
}
```

```javascript
dependencies {
    compile group: 'online.licos', name: 'licos-json4scala_2.12', version: '(version)'
}
```

### Maven

```xml
<repository>
  <id>LiCOS-JSON4Scala-snapshots-repository</id>
  <url>https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots</url>
</repository>
```

```xml
<dependency>
  <groupId>online.licos</groupId>
  <artifactId>licos-json4scala_2.12</artifactId>
  <version>(version)</version>
</dependency>
```

### Ivy

```xml
<ivysettings>
    <settings defaultResolver="chain"/>
    <resolvers>
        <chain name="chain">
            <ibiblio name="LiCOS-JSON4Scala-snapshots-repository" m2compatible="true" root="https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots"/>
        </chain>
    </resolvers>
</ivysettings>
```

```xml
<dependency org="online.licos" name="licos-json4scala_2.12" rev="(version)"/>
```
