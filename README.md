# LiCOS-JSON4Scala

## API

### The latest version

https://ktr-skmt.github.io/LiCOS-JSON4Scala/0.0.2/api/index.html

### Old versions

https://ktr-skmt.github.io/LiCOS-JSON4Scala/index.html

## Import

### SBT

```scala
resolvers += "LiCOS-JSON4Scala-snapshots-repository" at "https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots"
```

```scala
libraryDependences += "online.licos" % "licos-json4scala_2.12" % "0.0.2"
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
    compile group: 'online.licos', name: 'licos-json4scala_2.12', version: '0.0.2'
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
  <version>0.0.2</version>
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
<dependency org="online.licos" name="licos-json4scala_2.12" rev="0.0.2"/>
```
