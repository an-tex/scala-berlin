Scala Berlin Meetup - 2015-04-22

Continuous Delivery with SBT and Ansible

Andreas Teichrib

# Overview

There are two separate projects showcasing the usage of the SBT Native Packaging Plugin.

The main one is a spray based project showing different customization options, including:
- post installation commands (src/debian/DEBIAN/postinst)
- JVM Options (src/universal/conf/application.ini)
- additional commands to the startup script (src/templates/etc-default)

The other is a basic play project.

# Deployment
To create a .deb package of the application and deploy it via ansible run `sbt deploy`, or `sbt ~deploy` to have a continuous deployment running.

Don't forget to change the ansible/inventory and ansible/deploy.yml files to fit your environment (host + user) and to make sure you can login via public key.

# Ansible
I've just showed a very basic ansible playbook, for slightly deeper examples have a look at the setup.yml playbook and the two included roles `oracle-jdk8` and `system`.

# Play
Play ships with an older version of the SBT Native Packaging Plugin which has different semantics for the src/templates/etc-default file (jvm opts vs startup script additions)

# Links
- http://www.scala-sbt.org/sbt-native-packager/
- https://github.com/muuki88/sbt-native-packager-examples
- https://github.com/sbt/sbt-native-packager/
