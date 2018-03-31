Cloud foundry

On macOS X, we can type this:

$ brew tap cloudfoundry/tap
$ brew install cf-cli
=> Installing cf-cli from cloudfoundry/tap
==> Downloading
     https://cli.run.pivotal.io/stable?release=macosx64-bin...
==> Downloading from
     https://s3-us-west-1.amazonaws.com/cf-cli-release...
     ##################################################
     ####################...
==> Caveats
     Bash completion has been installed to:
     /usr/local/etc/bash_completion.d
==> Summary
     /usr/local/Cellar/cf-cli/6.32.0: 6 files, 16.7MB,
     built in 10 seco...

For Linux, we can fetch a tarball like this:

$ wget -O cf-linux.tgz "https://cli.run.pivotal.io/stable?
  release=linux64-binary&source=github"
$ tar xvfz cf-linux.tgz
$ chmod 755 ./cf



cf push spring-boot-app-eight -p build/libs/spring-boot-app-0.0.1-SNAPSHOT.jar

curl http://spring-boot-app-eight.cfapps.io?name=Greg

curl localhost:9001/actuator/health

http://localhost:9000/application/metrics/http.server.requests

--
brew install httpie

http --json -v POST localhost:9001/api/images id=10 name=image1
curl -v -H 'Content-Type:application/json' -X POST -d '{"id":10, "name": "image10"}' localhost:9001/api/images
curl -v -H 'Content-Type:application/json' -X POST -d '[{"id":11, "name": "image1"}, {"id":12, "name":"image2"}]' localhost:9001/api/images