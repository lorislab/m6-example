FROM lorislab/postgresql as postgresql

FROM lorislab/galleon-wildfly:16.0.0.Final as build

COPY --chown=jboss:root --from=postgresql /opt/postgresql/feature-pack /opt/jboss/.m2/repository/

ENV JBOSS_HOME /opt/jboss/wildfly

COPY src/main/docker/ /tmp/docker
RUN galleon.sh provision /tmp/docker/wildfly/provision.xml --dir=${JBOSS_HOME}

# Reconfigure the wildfly
#RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/tmp/docker/wildfly/config.cli

FROM jboss/base-jdk:11

USER root
RUN yum update -y && \
    yum clean all


USER jboss

ENV JBOSS_HOME /opt/jboss/wildfly

# Copy configuration from the build image
COPY --chown=jboss:root --from=build ${JBOSS_HOME} ${JBOSS_HOME}

RUN chown -R jboss:0 ${JBOSS_HOME} && \
    chmod -R g+rw ${JBOSS_HOME}

# Deploy the application
ADD target/*.war /opt/jboss/wildfly/standalone/deployments/

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

USER jboss

# Expose the ports we're interested in
EXPOSE 8080

# Update the database and start the server
CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -Djboss.bind.address=$(hostname -i) -Djboss.bind.address.private=$(hostname -i)
